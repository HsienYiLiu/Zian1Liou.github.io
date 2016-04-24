import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUIDemo extends JFrame implements ActionListener{
    //object varible
    JLabel label;
    int count = 0;
    //overwrite actionlistener's method
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if(s.equals("+1")){
            count++;
        }

        else if(s.equals("-1")){
            count--;
        }
        else if(s.equals("end")){
            this.dispose();
        }
        else if(s.equals("about")){
            JDialog d = new JDialog(this,"你來亂的");
            d.setSize(100,100);
            JLabel label = new JLabel("hahahaha",JLabel.CENTER);
            d.add(label,BorderLayout.CENTER);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        }
        else{
            System.out.println(s);
        }
        label.setText(Integer.toString(count));
        System.out.println(count);
    }
    public GUIDemo(){
        //this mean "this object
        //set size
        this.setSize(500,500);
        //new a new label
        this.setTitle("103213038 劉憲一");
        this.setForeground(Color.magenta);
        label = new JLabel("0", JLabel.CENTER);
        label.setFont(new Font(Font.MONOSPACED,Font.BOLD,100));
        this.add(label,BorderLayout.CENTER);
        label.setOpaque(true);
        label.setForeground(Color.magenta);
        JButton plus = new JButton("+1");
        plus.addActionListener(this);
        plus.setForeground(Color.YELLOW);
        plus.setBackground(Color.BLUE);
        this.add(plus,BorderLayout.WEST);
        JButton minus = new JButton("-1");
        minus.addActionListener(this);
        minus.setForeground(Color.GREEN);
        minus.setBackground(Color.BLACK);
        this.add(minus,BorderLayout.EAST);
        JMenuBar mb = new JMenuBar();
        this.setJMenuBar(mb);
        JMenu file = new JMenu("file");
        mb.add(file);
        JMenuItem about = new JMenuItem("about");

        about.addActionListener(this);
        JMenuItem exit = new JMenuItem("end");
        exit.addActionListener(this);
        file.add(exit);
        JTextField textField = new JTextField(count);
        textField.addActionListener(this);
        this.add(textField);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] argv){
        new GUIDemo();
    }
}
