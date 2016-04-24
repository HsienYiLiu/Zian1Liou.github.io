/*
* File Name: Five.java
* ID: 100213014
* Author:龍志雄
* Since 2012/06/13
* Toolkit: UltraEdit
*/
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
public class Five{
    Frame f;
    public Five(){
        f=new Frame();
        Dimension sSize=Toolkit.getDefaultToolkit().getScreenSize();
        f.setSize((sSize.height/100-1)*100+10,(sSize.height/100-1)*100+50);
        Dimension fSize=f.getSize();
        f.setLocation(sSize.width/2-fSize.width/2,0);
        f.addWindowListener(new Listeners(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        Menu m;
        MenuBar mb;
        f.setMenuBar(mb=new MenuBar());
        mb.add(m=new Menu("遊戲"));
        m.add(new MenuItem("新遊戲(1P/2P)")).addActionListener(new Listeners(){
            public void actionPerformed(ActionEvent e){
                f.setVisible(false);
                new Five();
            }
        });
        m.add(new MenuItem("結束  ALT+F4")).addActionListener(new Listeners(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        mb.add(m=new Menu("說明"));
        m.add(new MenuItem("關於")).addActionListener(new Listeners(){
            public void actionPerformed(ActionEvent e){
                new Dialogs(f,"關於本遊戲","龍哥花兩天寫出來的");
            }
        });
        new Dialogs(f,"Choose");
        f.setVisible(true);
    }
    public static void main(String[] argv){
        Five f=new Five();
    }
}