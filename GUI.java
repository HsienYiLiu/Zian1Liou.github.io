import javax.swing.*;
import java.awt.*;
public class GUI extends JFrame{
    public GUI(){
        JFrame f = new JFrame();
        f.setSize(500,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(Color.BLUE);
        Button button1 = new Button("THIS IS A BUTTON");
        JCheckBox checkbox = new JCheckBox("JCheckBox");
        JRadioButton radiobutton = new JRadioButton("JRadiobutton");
        JLabel label = new JLabel("JLabel");
        JTextArea textarea = new JTextArea("I    LOVE   YOU");
        f.getContentPane().add(BorderLayout.EAST, checkbox);
        f.getContentPane().add(BorderLayout.WEST, radiobutton);
        f.getContentPane().add(BorderLayout.SOUTH, button1);
        f.getContentPane().add(BorderLayout.NORTH , label);
        f.getContentPane().add(BorderLayout.CENTER, textarea);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] argv){
        new GUI();
    }
}
