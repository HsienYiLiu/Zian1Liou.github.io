import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Guess extends JFrame implements ActionListener{
    int answer=(int)(Math.random()*100+1);
    int upper=100;
    int lower=1;
    int count=0;
    JTextField textField;
    public void actionPerformed(ActionEvent e){
        try{
            count++;
            int tmp=Integer.parseInt(textField.getText());
            if(tmp==answer){
                String message="You Win! You guessed "+count+" times.";
                message+="\nAnswer is "+answer;
                message+="\nGame will be reset!";
                JOptionPane.showMessageDialog(this,message);
                answer=(int)(Math.random()*100);
                upper=100;
                lower=0;
                count=0;
            }
            else{
                if(tmp>answer&&tmp<upper)
                    upper=tmp;
                if(tmp<answer&&tmp>lower)
                    lower=tmp;
                String message="The answer is between "+lower+" and "+upper;
                JOptionPane.showMessageDialog(this,message);
            }
        }
        catch(Exception err){
            String message="Must Be A Integer!";
            JOptionPane.showMessageDialog(this,message);
        }
        finally{
            textField.setText("");
        }
    }
    public Guess(){
        this.setTitle("Guess Guess");
        this.setSize(400,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        textField=new JTextField();
        textField.addActionListener(this);
        textField.setFont(new Font(Font.MONOSPACED,Font.BOLD,40));
        this.add(textField,BorderLayout.CENTER);
        this.setVisible(true);
    }
    public static void main(String[] argv){
        new Guess();
    }
}
