
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    private Frame mainFrame;
    private Panel area_of_Button;
    private TextField area_of_Outcome;
    private Button
            b00=new Button("7"),
            b01=new Button("8"),
            b02=new Button("9"),
            b03=new Button("+"),
            b10=new Button("4"),
            b11=new Button("5"),
            b12=new Button("6"),
            b13=new Button("-"),
            b20=new Button("1"),
            b21=new Button("2"),
            b22=new Button("3"),
            b23=new Button("*"),
            b30=new Button("0"),
            b31=new Button("."),
            b32=new Button("="),
            b33=new Button("/");
    public static void main(String args[]) {
        Calculator calculator = new Calculator();
        calculator.go();
    }
    public void go(){
        mainFrame = new Frame("Calculator");
        area_of_Button = new Panel();
        area_of_Outcome = new TextField("0");
        mainFrame.add(area_of_Outcome,BorderLayout.NORTH);
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
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.addWindowListener(new WinListener());
    }

    class NumButton implements ActionListener{
        public void actionPerformed(ActionEvent press){
            area_of_Outcome.setText(press.getActionCommand());
        }
    }

    class WinListener extends WindowAdapter{
        public void windowClosing(WindowEvent close){
            System.exit(0);
        }
    }
}
