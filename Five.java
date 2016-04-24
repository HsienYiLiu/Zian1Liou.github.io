import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Five extends JFrame{
    JLabel label;
    public Five(){
        this.setSize(1000,1000);
        this.setVisible(true);
        this.setTitle("123");
        //this.pack();
    }
    /*overwrite*/
    public void paint(Graphics g) {
        int startX = 50;
        int startY = 50;
        for(int i = 1 ; i < 20 ; i++){
            g.drawLine(startX,startY*i+50,startX+1000,startY*i+50);
            g.drawLine(startX*i+50,startY,startX*i+50,startY+1000);
        }
    }
    public static void main(String[] argv){
        new Five();
    }
}
