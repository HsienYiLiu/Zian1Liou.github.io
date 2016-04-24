import java.awt.*;
import java.awt.event.*;
import java.net.URL;
public class LineReal extends Component{
    URL address=Five.class.getResource("Board.jpg");
    int[] seeds=new int[400];
    int black=2;
    int white=1;
    int playing=black;
    int[][] bingo=new int[1152][5];
    Frame parent;
    int[] times=new int[400];
    int count=0;
    int limit;
    int x;
    int y;
    public LineReal(Frame f){
        parent=f;
        for(int i=0;i<20;i++)//parallel(320)
            for(int j=0;j<16;j++)
                for(int k=0;k<5;k++)
                    bingo[i*16+j][k]=j+k+i*20;
        for(int i=0;i<20;i++)//vertical(320)
            for(int j=0;j<16;j++)
                for(int k=0;k<5;k++)
                    bingo[i*16+j+320][k]=(j+k)*20+i;
        for(int i=0;i<16;i++)//cross(16+16=32)
            for(int k=0;k<5;k++){
                bingo[i+640][k]=(i+k)*21;
                bingo[i+656][k]=(i+k+1)*19;
            }
        for(int i=0;i<15;i++)//slash(240+240=480)
            for(int j=0;j<15-i;j++)
                for(int k=0;k<5;k++){
                    bingo[(31-i)*i/2+j+672][k]=(i+1)*20+(j+k)*21;//left-negative
                    bingo[(31-i)*i/2+j+792][k]=(i+1)+(j+k)*21;//right-negative
                    bingo[(31-i)*i/2+j+912][k]=18-i+(j+k)*19;//left-positive
                    bingo[(31-i)*i/2+j+1032][k]=(i+2)*20-1+(j+k)*19;//right-positive
                }
        this.addMouseListener(new Listeners(){
            public void mousePressed(MouseEvent e){
                x=(e.getX()-(parent.getSize().width/2-limit*100/2))/(limit*5);
                y=e.getY()/(limit*5);
                if(x>=20||y>=20||x<0||y<0)
                    return;
                if(seeds[y*20+x]==0){
                    seeds[y*20+x]=playing;
                    times[count++]=y*20+x;
                    repaint();
                    for(int i=0;i<bingo.length;i++){
                        int j=0;
                        for(j=0;j<5&&seeds[bingo[i][j]]==playing;j++);
                        if(j==5){
                            if(playing==black){
                                new Dialogs(parent,"誰贏了?","黑色玩家!!!");
                            }else if(playing==white){
                                new Dialogs(parent,"誰贏了?","白色玩家!!!");
                            }
                            newGame();
                            return;
                        }
                    }
                    if(playing==black){
                        playing=white;
                        parent.setTitle("五子棋----現在換白棋下");
                    }
                    else{
                        playing=black;
                        parent.setTitle("五子棋----現在換黑棋下");
                    }
                }
            }
        });
        f.addComponentListener(new Listeners(){
            public void componentResized(ComponentEvent e){
                int wid=(parent.getSize().width-10)/100;
                int hei=(parent.getSize().height-50)/100;
                limit=wid<hei?wid:hei;
                if(limit<=3)
                    limit=3;
            }
        });
        f.addKeyListener(new Listeners(){
            public void keyPressed(KeyEvent e){
                if(e.isControlDown()==true){
                    if(e.getKeyCode()==90&&count>0){
                        seeds[times[--count]]=0;
                        if(playing==black){
                            playing=white;
                            parent.setTitle("五子棋----現在換白棋下");
                        }
                        else{
                            playing=black;
                            parent.setTitle("五子棋----現在換黑棋下");
                        }
                        repaint();
                        if(count==0){
                            parent.setTitle("五子棋----黑棋先下");
                            return;
                        }
                    }
                }
                if(e.getKeyCode()==113){
                    playing=black;
                    parent.setTitle("五子棋----黑棋先下");
                    newGame();
                }
            }
        });
    }
    public void paint(Graphics g){
        Toolkit tool=getToolkit();
        Image boardjpg=tool.getImage(address);
        g.drawImage(boardjpg,parent.getSize().width/2-limit*100/2,0,limit*100,limit*100,this);
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(seeds[i*20+j]==1){
                    g.setColor(Color.white);
                    g.fillOval((parent.getSize().width/2-limit*100/2)+limit*5*j,limit*5*i,limit*5,limit*5);
                    g.setColor(Color.black);
                    g.drawOval((parent.getSize().width/2-limit*100/2)+limit*5*j,limit*5*i,limit*5,limit*5);
                }
                else if(seeds[i*20+j]==2){
                    g.setColor(Color.black);
                    g.fillOval((parent.getSize().width/2-limit*100/2)+limit*5*j,limit*5*i,limit*5,limit*5);
                }
            }
        }
        if(count>0){
            if(seeds[times[count-1]]==1){
                g.setColor(Color.black);
                g.fillRect((parent.getSize().width/2-limit*100/2)+limit*5*(times[count-1]%20)+limit*5/2-limit/2,limit*5*(times[count-1]/20)+limit*5/2-limit/2,limit,limit);
            }
            else if(seeds[times[count-1]]==2){
                g.setColor(Color.white);
                g.fillRect((parent.getSize().width/2-limit*100/2)+limit*5*(times[count-1]%20)+limit*5/2-limit/2,limit*5*(times[count-1]/20)+limit*5/2-limit/2,limit,limit);
            }
        }
    }
    public void newGame(){
        for(int i=0;i<400;i++)
            seeds[i]=0;
        parent.setTitle("五子棋----黑棋先下");
        playing=black;
        count=0;
        repaint();
    }
}