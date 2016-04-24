import java.awt.*;
import java.awt.event.*;
public class Dialogs extends Dialog{
    LineNPC board;
    LineReal rboard;
    Button a=new Button("One Player");
    Button b=new Button("Two Players");
    Button c=new Button("Choose One Kind!!");
    Frame f;
    public Dialogs(Frame parent){
        super(parent,"WRONG",true);
        f=parent;
        this.setSize(100,100);
        this.addWindowListener(new Listeners(){
            public void windowClosing(WindowEvent e){
                Dialogs.this.dispose();
            }
        });
        c.addActionListener(new Listeners(){
            public void actionPerformed(ActionEvent e){
                Dialogs.this.dispose();
            }
        });
        this.setLayout(new GridLayout(3,1));
        this.add(new Label(""));
        this.add(c);
        Dimension dSize=this.getSize();
        Dimension sSize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(sSize.width/2-dSize.width/2,sSize.height/2-dSize.height/2);
        this.setVisible(true);
    }
    public Dialogs(Frame parent,String title){
        super(parent,title,true);
        f=parent;
        this.setSize(200,200);
        this.addWindowListener(new Listeners(){
            public void windowClosing(WindowEvent e){
                new Dialogs(f);
            }
        });
        this.setLayout(new GridLayout(2,1));
        a.addActionListener(new Listeners(){
            public void actionPerformed(ActionEvent e){
                board=new LineNPC(f);
                Dialogs.this.dispose();
                f.setTitle("五子棋----你是黑色");
                f.add(board);
            }
        });
        b.addActionListener(new Listeners(){
            public void actionPerformed(ActionEvent e){
                rboard=new LineReal(f);
                Dialogs.this.dispose();
                f.setTitle("五子棋----黑色先下");
                f.add(rboard);
            }
        });
        this.add(a);
        this.add(b);
        Dimension dSize=this.getSize();
        Dimension sSize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(sSize.width/2-dSize.width/2,sSize.height/2-dSize.height/2);
        this.setVisible(true);
    }
    public Dialogs(Frame parent,String title,String sens){
        super(parent,title,true);
        this.setSize(200,200);
        this.addWindowListener(new Listeners(){
            public void windowClosing(WindowEvent e){
                Dialogs.this.dispose();
            }
        });
        Panel p=new Panel();
        p.setLayout(new GridLayout(1,2));
        Button a=new Button("繼續"),b=new Button("結束");
        a.addActionListener(new Listeners(){
            public void actionPerformed(ActionEvent e){
                Dialogs.this.dispose();
            }
        });
        b.addActionListener(new Listeners(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        p.add(a);
        p.add(b);
        this.setBackground(Color.white);
        if(title.equals("關於本遊戲")){
            this.setLayout(new GridLayout(5,1));
            this.add(new Label(sens,Label.CENTER));
            this.add(new Label("F2    開新局",Label.CENTER));
            this.add(new Label("Ctrl+Z  悔棋",Label.CENTER));
            this.add(new Label("Alt+F4  結束",Label.CENTER));
        }
        else{
            this.setLayout(new GridLayout(4,1));
            this.add(new Label("恭喜",Label.CENTER));
            this.add(new Label("贏家是",Label.CENTER));
            this.add(new Label(sens,Label.CENTER));
        }
        this.add(p);
        Dimension dSize=this.getSize();
        Dimension sSize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(sSize.width/2-dSize.width/2,sSize.height/2-dSize.height/2);
        this.setVisible(true);
    }
}