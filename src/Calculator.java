import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.text.DecimalFormat;

public class Calculator {
    private Calculate calculate = new Calculate();
    private JFrame mainFrame;
    private JPanel area_of_Button;
    private JTextField area_of_Outcome;
    private JButton
            b00=new JButton("7"),
            b01=new JButton("8"),
            b02=new JButton("9"),
            b03=new JButton("+"),
            b10=new JButton("4"),
            b11=new JButton("5"),
            b12=new JButton("6"),
            b13=new JButton("-"),
            b20=new JButton("1"),
            b21=new JButton("2"),
            b22=new JButton("3"),
            b23=new JButton("*"),
            b30=new JButton("0"),
            b31=new JButton("."),
            b32=new JButton("="),
            b33=new JButton("/");
    private boolean flag = true;

    public static void main(String args[]) {
        Calculator calculator = new Calculator();
        calculator.go();
    }
    public void go(){
        mainFrame = new JFrame("Calculator");
        mainFrame.setPreferredSize(new Dimension(480,360));
        Font btnFont = new Font("",Font.PLAIN,36);
        area_of_Button = new JPanel();
        area_of_Outcome = new JTextField(String.valueOf(calculate.result));
        area_of_Outcome.setHorizontalAlignment(JTextField.RIGHT);
        mainFrame.add(area_of_Outcome,BorderLayout.NORTH);
        area_of_Outcome.setFont(new Font("",Font.PLAIN,48));
        mainFrame.add(area_of_Button,BorderLayout.CENTER);
        area_of_Button.setLayout(new GridLayout(4,4));
        area_of_Button.add(b00);
        area_of_Button.add(b01);
        area_of_Button.add(b02);
        area_of_Button.add(b03);
        area_of_Button.add(b10);
        area_of_Button.add(b11);
        area_of_Button.add(b12);
        area_of_Button.add(b13);
        area_of_Button.add(b20);
        area_of_Button.add(b21);
        area_of_Button.add(b22);
        area_of_Button.add(b23);
        area_of_Button.add(b30);
        area_of_Button.add(b31);
        area_of_Button.add(b32);
        area_of_Button.add(b33);
        b00.setFont(btnFont);
        b01.setFont(btnFont);
        b02.setFont(btnFont);
        b03.setFont(btnFont);
        b10.setFont(btnFont);
        b11.setFont(btnFont);
        b12.setFont(btnFont);
        b13.setFont(btnFont);
        b20.setFont(btnFont);
        b21.setFont(btnFont);
        b22.setFont(btnFont);
        b23.setFont(btnFont);
        b30.setFont(btnFont);
        b31.setFont(btnFont);
        b32.setFont(btnFont);
        b33.setFont(btnFont);
        b00.addActionListener(new NumButton());
        b01.addActionListener(new NumButton());
        b02.addActionListener(new NumButton());
        b10.addActionListener(new NumButton());
        b11.addActionListener(new NumButton());
        b12.addActionListener(new NumButton());
        b20.addActionListener(new NumButton());
        b21.addActionListener(new NumButton());
        b22.addActionListener(new NumButton());
        b30.addActionListener(new NumButton());
        b31.addActionListener(new NumButton());
        b03.addActionListener(new Plus());
        b13.addActionListener(new Minus());
        b23.addActionListener(new Multiply());
        b33.addActionListener(new Divide());
        b32.addActionListener(new Equal());
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.addWindowListener(new WinListener());

    }

    class Calculate{
        public Calculate(){
            result = 0;
            temp = 0;
            operator1 = null;
            operator2 = null;
        }
        private double result, temp;
        Operator operator1, operator2;
        public double getResult(){
            return result;
        }
        public void Cale(double inNum, Operator operator){
            boolean op = operator == Operator.MULTIPLY || operator == Operator.DIVIDE;
            boolean op1 = operator1 == Operator.MULTIPLY || operator1 == Operator.DIVIDE;
            boolean op2 = operator2 == Operator.MULTIPLY || operator2 == Operator.DIVIDE;
            if(operator1 == null && operator2 == null){
                operator1 = operator;
                result = inNum;
                return;
            }

            if(op1){
                switch (operator1){
                    case MULTIPLY: result *= inNum;break;
                    case DIVIDE: result /= inNum;break;
                }
                operator1 = operator;
                return;
            }

            if(operator2 == null){
                if(op) {
                    operator2 = operator;
                    temp = result;
                    result = inNum;
                    return;
                }else{
                    switch (operator1){
                        case PLUS: result += inNum;break;
                        case MINUS: result -= inNum;break;
                    }
                    operator1 = operator;
                    return;
                }
            }

            if(op2){
                switch (operator2){
                    case MULTIPLY: result *= inNum;break;
                    case DIVIDE: result /= inNum;break;
                }
                if(op) {
                    operator2 = operator;
                }else {
                    switch (operator1){
                        case PLUS: result += temp;break;
                        case MINUS: result = temp - result;break;
                    }
                    operator1 = operator;
                    return;
                }
            }
        }

        private void Plus(double inNum){
            Operator operator = Operator.PLUS;
            Cale(inNum,operator);
        }
        private void Minus(double inNum){
            Operator operator = Operator.MINUS;
            Cale(inNum,operator);
        }
        private void Multiply(double inNum){
            Operator operator = Operator.MULTIPLY;
            Cale(inNum,operator);
        }
        private void Divide(double inNum){
            Operator operator = Operator.DIVIDE;
            Cale(inNum,operator);
        }
        private void Equal(double inNum){
            Operator operator = Operator.EQUAL;
            Cale(inNum,operator);
            operator1 = null;
            operator2 = null;
        }
    }

    enum Operator{
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        EQUAL
    }

    class NumButton implements ActionListener{
        public void actionPerformed(ActionEvent press){
            if(flag){
                String init = press.getActionCommand();
                area_of_Outcome.setText(init);
                flag = false;
            }else{
                String init = area_of_Outcome.getText();
                init += press.getActionCommand();
                area_of_Outcome.setText(init);
            }
        }
    }

    class Plus implements ActionListener{
        public void actionPerformed(ActionEvent press){
            double inNum = Double.valueOf(area_of_Outcome.getText());
            calculate.Plus(inNum);
            area_of_Outcome.setText(String.valueOf(new DecimalFormat("#.##########").format(calculate.getResult())));
            flag = true;
        }
    }

    class Minus implements ActionListener{
        public void actionPerformed(ActionEvent press){
            double inNum = Double.valueOf(area_of_Outcome.getText());
            calculate.Minus(inNum);
            area_of_Outcome.setText(String.valueOf(new DecimalFormat("#.##########").format(calculate.getResult())));
            flag = true;
        }
    }
    class Multiply implements ActionListener{
        public void actionPerformed(ActionEvent press){
            double inNum = Double.valueOf(area_of_Outcome.getText());
            calculate.Multiply(inNum);
            area_of_Outcome.setText(String.valueOf(new DecimalFormat("#.##########").format(calculate.getResult())));
            flag = true;
        }
    }

    class Divide implements ActionListener{
        public void actionPerformed(ActionEvent press){
            double inNum = Double.valueOf(area_of_Outcome.getText());
            calculate.Divide(inNum);
            area_of_Outcome.setText(String.valueOf(new DecimalFormat("#.##########").format(calculate.getResult())));
            flag = true;
        }
    }

    class Equal implements ActionListener{
        public void actionPerformed(ActionEvent press){
            double inNum = Double.valueOf(area_of_Outcome.getText());
            calculate.Equal(inNum);
            area_of_Outcome.setText(String.valueOf(new DecimalFormat("#.##########").format(calculate.getResult())));
            flag = true;
        }
    }

    class WinListener extends WindowAdapter{
        public void windowClosing(WindowEvent close){
            System.exit(0);
        }
    }
}
