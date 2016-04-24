import java.awt.*;
import java.awt.event.*;
import java.net.URL;
public class LineNPC extends Component{
    URL address=Five.class.getResource("Board.jpg");
    int[] seeds=new int[400];
    int black=2;
    int white=1;
    int playing=black;
    Bingo bin=new Bingo();
    int[][] bingo=bin.bingos();
    int[][] four=bin.fours();
    int[][] three=bin.threes();
    int[][] two=bin.twos();
    int[][] one=bin.ones();
    Frame parent;
    int[] times=new int[400];
    int count=0;
    int limit;
    int nowBlack;
    int nowWhite;
    boolean changes;
    public LineNPC(Frame f){
        parent=f;
        this.addMouseListener(new Listeners(){
            public void mousePressed(MouseEvent e){
                int x=(e.getX()-(parent.getSize().width/2-limit*100/2))/(limit*5);
                int y=e.getY()/(limit*5);
                if(x>=20||y>=20||x<0||y<0)
                    return;
                if(seeds[y*20+x]==0){
                    seeds[y*20+x]=playing;
                    times[count++]=y*20+x;
                    repaint();
                    //black win
                    for(int i=0;i<bingo.length;i++){
                        int j=0;
                        for(j=0;j<5&&seeds[bingo[i][j]]==black;j++);
                        if(j==5){
                            new Dialogs(parent,"誰贏了?","黑色玩家!!!");
                            newGame();
                            return;
                        }
                    }
                    NPC:{
                        changes=false;
                        holeFours(white);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        fours(white);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        holeFours(black);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        fours(black);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        liveThrees(white);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        liveHoleThrees(white);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        liveThrees(black);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        liveHoleThrees(black);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        deadThrees(white);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        deadHoleThrees(white);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        liveHoleTwos(white);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        liveTwos(white);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        liveHoleTwos(black);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        liveTwos(black);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        deadThrees(black);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        deadHoleThrees(black);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        deadTwos(white);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        deadTwos(black);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        ones(white);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                        ones(black);
                        if(changes){
                            repaint();
                            break NPC;
                        }
                    }
                    //white win
                    for(int i=0;i<bingo.length;i++){
                        int j=0;
                        for(j=0;j<5&&seeds[bingo[i][j]]==white;j++);
                        if(j==5){
                            new Dialogs(parent,"誰贏了?","白色玩家!!!");
                            newGame();
                            return;
                        }
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
                        seeds[times[--count]]=0;
                        repaint();
                        if(count==0)
                            return;
                    }
                }
                if(e.getKeyCode()==113){
                    playing=black;
                    parent.setTitle("五子棋----你是黑色");
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
            g.setColor(Color.black);
            g.fillRect((parent.getSize().width/2-limit*100/2)+limit*5*(times[count-1]%20)+limit*5/2-limit/2,limit*5*(times[count-1]/20)+limit*5/2-limit/2,limit,limit);
            g.setColor(Color.white);
            g.fillRect((parent.getSize().width/2-limit*100/2)+limit*5*(times[count-2]%20)+limit*5/2-limit/2,limit*5*(times[count-2]/20)+limit*5/2-limit/2,limit,limit);
        }
    }
    public void newGame(){
        for(int i=0;i<400;i++)
            seeds[i]=0;
        playing=black;
        count=0;
        repaint();
    }
    public void holeFours(int whos){
        for(int i=0;i<bingo.length;i++){
            int j=0;
            for(j=0;j<1&&seeds[bingo[i][j]]==whos;j++);
            if(j==1)
                if(seeds[bingo[i][1]]==0&&seeds[bingo[i][2]]==whos&&seeds[bingo[i][3]]==whos&&seeds[bingo[i][4]]==whos){
                    seeds[bingo[i][1]]=white;
                    times[count++]=bingo[i][1];
                    changes=true;
                    return;
                }
            for(j=0;j<2&&seeds[bingo[i][j]]==whos;j++);
            if(j==2)
                if(seeds[bingo[i][2]]==0&&seeds[bingo[i][3]]==whos&&seeds[bingo[i][4]]==whos){
                    seeds[bingo[i][2]]=white;
                    times[count++]=bingo[i][2];
                    changes=true;
                    return;
                }
            for(j=0;j<3&&seeds[bingo[i][j]]==whos;j++);
            if(j==3)
                if(seeds[bingo[i][3]]==0&&seeds[bingo[i][4]]==whos){
                    seeds[bingo[i][3]]=white;
                    times[count++]=bingo[i][3];
                    changes=true;
                    return;
                }
        }
    }
    public void fours(int whos){
        for(int i=0;i<four.length;i++){//活棋
            int j=0;
            for(j=0;j<4&&seeds[four[i][j]]==whos;j++);
            if(j==4){
                if(i>968){
                    if(four[i][0]>19&&four[i][0]%20!=19&&four[i][3]<380&&four[i][3]%20!=0&&seeds[four[i][0]-19]==0&&seeds[four[i][3]+19]==0){
                        seeds[four[i][0]-19]=white;
                        times[count++]=four[i][0]-19;
                        changes=true;
                        return;
                    }
                }
                else if(i>679){
                    if(four[i][0]>19&&four[i][3]%20!=19&&four[i][3]<380&&four[i][0]%20!=0&&seeds[four[i][0]-21]==0&&seeds[four[i][3]+21]==0){
                        seeds[four[i][0]-21]=white;
                        times[count++]=four[i][0]-21;
                        changes=true;
                        return;
                    }
                }
                else if(i>339){
                    if(four[i][0]>19&&four[i][3]<380&&seeds[four[i][0]-20]==0&&seeds[four[i][3]+20]==0){
                        seeds[four[i][0]-20]=white;
                        times[count++]=four[i][0]-20;
                        changes=true;
                        return;
                    }
                }
                else{
                    if(four[i][0]%20!=0&&four[i][3]%20!=19&&seeds[four[i][0]-1]==0&&seeds[four[i][3]+1]==0){
                        seeds[four[i][0]-1]=white;
                        times[count++]=four[i][0]-1;
                        changes=true;
                        return;
                    }
                }
            }
        }
        for(int i=0;i<four.length;i++){//死棋
            int j=0;
            for(j=0;j<4&&seeds[four[i][j]]==whos;j++);
            if(j==4){
                if(i>968){
                    if(four[i][0]>19&&four[i][0]%20!=19&&seeds[four[i][0]-19]==0){
                        seeds[four[i][0]-19]=white;
                        times[count++]=four[i][0]-19;
                        changes=true;
                        return;
                    }
                    else if(four[i][3]<380&&four[i][3]%20!=0&&seeds[four[i][3]+19]==0){
                        seeds[four[i][3]+19]=white;
                        times[count++]=four[i][3]+19;
                        changes=true;
                        return;
                    }
                }
                else if(i>679){
                    if(four[i][0]>19&&four[i][0]%20!=0&&seeds[four[i][0]-21]==0){
                        seeds[four[i][0]-21]=white;
                        times[count++]=four[i][0]-21;
                        changes=true;
                        return;
                    }
                    else if(four[i][3]%20!=19&&four[i][3]<380&&seeds[four[i][3]+21]==0){
                        seeds[four[i][3]+21]=white;
                        times[count++]=four[i][3]+21;
                        changes=true;
                        return;
                    }
                }
                else if(i>339){
                    if(four[i][0]>19&&seeds[four[i][0]-20]==0){
                        seeds[four[i][0]-20]=white;
                        times[count++]=four[i][0]-20;
                        changes=true;
                        return;
                    }
                    else if(four[i][3]<380&&seeds[four[i][3]+20]==0){
                        seeds[four[i][3]+20]=white;
                        times[count++]=four[i][3]+20;
                        changes=true;
                        return;
                    }
                }
                else{
                    if(four[i][0]%20!=0&&seeds[four[i][0]-1]==0){
                        seeds[four[i][0]-1]=white;
                        times[count++]=four[i][0]-1;
                        changes=true;
                        return;
                    }
                    else if(four[i][3]%20!=19&&seeds[four[i][3]+1]==0){
                        seeds[four[i][3]+1]=white;
                        times[count++]=four[i][3]+1;
                        changes=true;
                        return;
                    }
                }
            }
        }
    }
    public void liveHoleThrees(int whos){
        for(int i=0;i<four.length;i++){//活棋
            int j=0;
            for(j=0;j<1&&seeds[four[i][j]]==whos;j++);
            if(j==1){
                if(i>968){
                    if(four[i][0]>19&&four[i][0]%20!=19&&four[i][3]<380&&four[i][3]%20!=0&&seeds[four[i][0]-19]==0&&seeds[four[i][3]+19]==0){
                        if(seeds[four[i][1]]==0&&seeds[four[i][2]]==whos&&seeds[four[i][3]]==whos){
                            seeds[four[i][1]]=white;
                            times[count++]=four[i][1];
                            changes=true;
                            return;
                        }
                    }
                }
                else if(i>679){
                    if(four[i][0]>19&&four[i][3]%20!=19&&four[i][3]<380&&four[i][0]%20!=0&&seeds[four[i][0]-21]==0&&seeds[four[i][3]+21]==0){
                        if(seeds[four[i][1]]==0&&seeds[four[i][2]]==whos&&seeds[four[i][3]]==whos){
                            seeds[four[i][1]]=white;
                            times[count++]=four[i][1];
                            changes=true;
                            return;
                        }
                    }
                }
                else if(i>339){
                    if(four[i][0]>19&&four[i][3]<380&&seeds[four[i][0]-20]==0&&seeds[four[i][3]+20]==0){
                        if(seeds[four[i][1]]==0&&seeds[four[i][2]]==whos&&seeds[four[i][3]]==whos){
                            seeds[four[i][1]]=white;
                            times[count++]=four[i][1];
                            changes=true;
                            return;
                        }
                    }
                }
                else{
                    if(four[i][0]%20!=0&&four[i][3]%20!=19&&seeds[four[i][0]-1]==0&&seeds[four[i][3]+1]==0){
                        if(seeds[four[i][1]]==0&&seeds[four[i][2]]==whos&&seeds[four[i][3]]==whos){
                            seeds[four[i][1]]=white;
                            times[count++]=four[i][1];
                            changes=true;
                            return;
                        }
                    }
                }
            }
            for(j=0;j<2&&seeds[four[i][j]]==whos;j++);
            if(j==2){
                if(i>968){
                    if(four[i][0]>19&&four[i][0]%20!=19&&four[i][3]<380&&four[i][3]%20!=0&&seeds[four[i][0]-19]==0&&seeds[four[i][3]+19]==0){
                        if(seeds[four[i][2]]==0&&seeds[four[i][3]]==whos){
                            seeds[four[i][2]]=white;
                            times[count++]=four[i][2];
                            changes=true;
                            return;
                        }
                    }
                }
                else if(i>679){
                    if(four[i][0]>19&&four[i][3]%20!=19&&four[i][3]<380&&four[i][0]%20!=0&&seeds[four[i][0]-21]==0&&seeds[four[i][3]+21]==0){
                        if(seeds[four[i][2]]==0&&seeds[four[i][3]]==whos){
                            seeds[four[i][2]]=white;
                            times[count++]=four[i][2];
                            changes=true;
                            return;
                        }
                    }
                }
                else if(i>339){
                    if(four[i][0]>19&&four[i][3]<380&&seeds[four[i][0]-20]==0&&seeds[four[i][3]+20]==0){
                        if(seeds[four[i][2]]==0&&seeds[four[i][3]]==whos){
                            seeds[four[i][2]]=white;
                            times[count++]=four[i][2];
                            changes=true;
                            return;
                        }
                    }
                }
                else{
                    if(four[i][0]%20!=0&&four[i][3]%20!=19&&seeds[four[i][0]-1]==0&&seeds[four[i][3]+1]==0){
                        if(seeds[four[i][2]]==0&&seeds[four[i][3]]==whos){
                            seeds[four[i][2]]=white;
                            times[count++]=four[i][2];
                            changes=true;
                            return;
                        }
                    }
                }
            }
        }
    }
    public void deadHoleThrees(int whos){
        for(int i=0;i<four.length;i++){//死棋
            int j=0;
            for(j=0;j<1&&seeds[four[i][j]]==whos;j++);
            if(j==1){
                if(i>968){
                    if(four[i][0]>19&&four[i][0]%20!=19&&four[i][3]<380&&four[i][3]%20!=0&&seeds[four[i][0]-19]!=0&&seeds[four[i][3]+19]!=0)
                        return;
                    if(four[i][0]>19&&four[i][0]%20!=19&&seeds[four[i][0]-19]==0){
                        if(seeds[four[i][1]]==0&&seeds[four[i][2]]==whos&&seeds[four[i][3]]==whos){
                            seeds[four[i][1]]=white;
                            times[count++]=four[i][1];
                            changes=true;
                            return;
                        }
                    }
                    else if(four[i][3]<380&&four[i][3]%20!=0&&seeds[four[i][3]+19]==0){
                        if(seeds[four[i][1]]==0&&seeds[four[i][2]]==whos&&seeds[four[i][3]]==whos){
                            seeds[four[i][1]]=white;
                            times[count++]=four[i][1];
                            changes=true;
                            return;
                        }
                    }
                }
                else if(i>679){
                    if(four[i][0]>19&&four[i][3]%20!=19&&four[i][3]<380&&four[i][0]%20!=0&&seeds[four[i][0]-21]!=0&&seeds[four[i][3]+21]!=0)
                        return;
                    if(four[i][0]>19&&four[i][0]%20!=0&&seeds[four[i][0]-21]==0){
                        if(seeds[four[i][1]]==0&&seeds[four[i][2]]==whos&&seeds[four[i][3]]==whos){
                            seeds[four[i][1]]=white;
                            times[count++]=four[i][1];
                            changes=true;
                            return;
                        }
                    }
                    else if(four[i][3]%20!=19&&four[i][3]<380&&seeds[four[i][3]+21]==0){
                        if(seeds[four[i][1]]==0&&seeds[four[i][2]]==whos&&seeds[four[i][3]]==whos){
                            seeds[four[i][1]]=white;
                            times[count++]=four[i][1];
                            changes=true;
                            return;
                        }
                    }
                }
                else if(i>339){
                    if(four[i][0]>19&&four[i][3]<380&&seeds[four[i][0]-20]!=0&&seeds[four[i][3]+20]!=0)
                        return;
                    if(four[i][0]>19&&seeds[four[i][0]-20]==0){
                        if(seeds[four[i][1]]==0&&seeds[four[i][2]]==whos&&seeds[four[i][3]]==whos){
                            seeds[four[i][1]]=white;
                            times[count++]=four[i][1];
                            changes=true;
                            return;
                        }
                    }
                    else if(four[i][3]<380&&seeds[four[i][3]+20]==0){
                        if(seeds[four[i][1]]==0&&seeds[four[i][2]]==whos&&seeds[four[i][3]]==whos){
                            seeds[four[i][1]]=white;
                            times[count++]=four[i][1];
                            changes=true;
                            return;
                        }
                    }
                }
                else{
                    if(four[i][0]%20!=0&&four[i][3]%20!=19&&seeds[four[i][0]-1]!=0&&seeds[four[i][3]+1]!=0)
                        return;
                    if(four[i][0]%20!=0&&seeds[four[i][0]-1]==0){
                        if(seeds[four[i][1]]==0&&seeds[four[i][2]]==whos&&seeds[four[i][3]]==whos){
                            seeds[four[i][1]]=white;
                            times[count++]=four[i][1];
                            changes=true;
                            return;
                        }
                    }
                    else if(four[i][3]%20!=19&&seeds[four[i][3]+1]==0){
                        if(seeds[four[i][1]]==0&&seeds[four[i][2]]==whos&&seeds[four[i][3]]==whos){
                            seeds[four[i][1]]=white;
                            times[count++]=four[i][1];
                            changes=true;
                            return;
                        }
                    }
                }
            }
            for(j=0;j<2&&seeds[four[i][j]]==whos;j++);
            if(j==2){
                if(i>968){
                    if(four[i][0]>19&&four[i][0]%20!=19&&four[i][3]<380&&four[i][3]%20!=0&&seeds[four[i][0]-19]!=0&&seeds[four[i][3]+19]!=0)
                        return;
                    if(four[i][0]>19&&four[i][0]%20!=19&&seeds[four[i][0]-19]==0){
                        if(seeds[four[i][2]]==0&&seeds[four[i][3]]==whos){
                            seeds[four[i][2]]=white;
                            times[count++]=four[i][2];
                            changes=true;
                            return;
                        }
                    }
                    else if(four[i][3]<380&&four[i][3]%20!=0&&seeds[four[i][3]+19]==0){
                        if(seeds[four[i][2]]==0&&seeds[four[i][3]]==whos){
                            seeds[four[i][2]]=white;
                            times[count++]=four[i][2];
                            changes=true;
                            return;
                        }
                    }
                }
                else if(i>679){
                    if(four[i][0]>19&&four[i][3]%20!=19&&four[i][3]<380&&four[i][0]%20!=0&&seeds[four[i][0]-21]!=0&&seeds[four[i][3]+21]!=0)
                        return;
                    if(four[i][0]>19&&four[i][0]%20!=0&&seeds[four[i][0]-21]==0){
                        if(seeds[four[i][2]]==0&&seeds[four[i][3]]==whos){
                            seeds[four[i][2]]=white;
                            times[count++]=four[i][2];
                            changes=true;
                            return;
                        }
                    }
                    else if(four[i][3]%20!=19&&four[i][3]<380&&seeds[four[i][3]+21]==0){
                        if(seeds[four[i][2]]==0&&seeds[four[i][3]]==whos){
                            seeds[four[i][2]]=white;
                            times[count++]=four[i][2];
                            changes=true;
                            return;
                        }
                    }
                }
                else if(i>339){
                    if(four[i][0]>19&&four[i][3]<380&&seeds[four[i][0]-20]!=0&&seeds[four[i][3]+20]!=0)
                        return;
                    if(four[i][0]>19&&seeds[four[i][0]-20]==0){
                        if(seeds[four[i][2]]==0&&seeds[four[i][3]]==whos){
                            seeds[four[i][2]]=white;
                            times[count++]=four[i][2];
                            changes=true;
                            return;
                        }
                    }
                    else if(four[i][3]<380&&seeds[four[i][3]+20]==0){
                        if(seeds[four[i][2]]==0&&seeds[four[i][3]]==whos){
                            seeds[four[i][2]]=white;
                            times[count++]=four[i][2];
                            changes=true;
                            return;
                        }
                    }
                }
                else{
                    if(four[i][0]%20!=0&&four[i][3]%20!=19&&seeds[four[i][0]-1]!=0&&seeds[four[i][3]+1]!=0)
                        return;
                    if(four[i][0]%20!=0&&seeds[four[i][0]-1]==0){
                        if(seeds[four[i][2]]==0&&seeds[four[i][3]]==whos){
                            seeds[four[i][2]]=white;
                            times[count++]=four[i][2];
                            changes=true;
                            return;
                        }
                    }
                    else if(four[i][3]%20!=19&&seeds[four[i][3]+1]==0){
                        if(seeds[four[i][2]]==0&&seeds[four[i][3]]==whos){
                            seeds[four[i][2]]=white;
                            times[count++]=four[i][2];
                            changes=true;
                            return;
                        }
                    }
                }
            }
        }
    }
    public void liveThrees(int whos){
        for(int i=0;i<three.length;i++){//活棋
            int j=0;
            for(j=0;j<3&&seeds[three[i][j]]==whos;j++);
            if(j==3){
                if(i>1043){
                    if(three[i][0]>19&&three[i][0]%20!=19&&three[i][2]<380&&three[i][2]%20!=0&&seeds[three[i][0]-19]==0&&seeds[three[i][2]+19]==0){
                        seeds[three[i][0]-19]=white;
                        times[count++]=three[i][0]-19;
                        changes=true;
                        return;
                    }
                }
                else if(i>719){
                    if(three[i][0]>19&&three[i][2]%20!=19&&three[i][2]<380&&three[i][0]%20!=0&&seeds[three[i][0]-21]==0&&seeds[three[i][2]+21]==0){
                        seeds[three[i][0]-21]=white;
                        times[count++]=three[i][0]-21;
                        changes=true;
                        return;
                    }
                }
                else if(i>359){
                    if(three[i][0]>19&&three[i][2]<380&&seeds[three[i][0]-20]==0&&seeds[three[i][2]+20]==0){
                        seeds[three[i][0]-20]=white;
                        times[count++]=three[i][0]-20;
                        changes=true;
                        return;
                    }
                }
                else{
                    if(three[i][0]%20!=0&&three[i][2]%20!=19&&seeds[three[i][0]-1]==0&&seeds[three[i][2]+1]==0){
                        seeds[three[i][0]-1]=white;
                        times[count++]=three[i][0]-1;
                        changes=true;
                        return;
                    }
                }
            }
        }
    }
    public void deadThrees(int whos){
        for(int i=0;i<three.length;i++){//死棋
            int j=0;
            for(j=0;j<3&&seeds[three[i][j]]==whos;j++);
            if(j==3){
                if(i>1043){
                    if(three[i][0]>19&&three[i][0]%20!=19&&seeds[three[i][0]-19]==0){
                        seeds[three[i][0]-19]=white;
                        times[count++]=three[i][0]-19;
                        changes=true;
                        return;
                    }
                    else if(three[i][2]<380&&three[i][2]%20!=0&&seeds[three[i][2]+19]==0){
                        seeds[three[i][2]+19]=white;
                        times[count++]=three[i][2]+19;
                        changes=true;
                        return;
                    }
                }
                else if(i>719){
                    if(three[i][0]>19&&three[i][0]%20!=0&&seeds[three[i][0]-21]==0){
                        seeds[three[i][0]-21]=white;
                        times[count++]=three[i][0]-21;
                        changes=true;
                        return;
                    }
                    else if(three[i][2]%20!=19&&three[i][2]<380&&seeds[three[i][2]+21]==0){
                        seeds[three[i][2]+21]=white;
                        times[count++]=three[i][2]+21;
                        changes=true;
                        return;
                    }
                }
                else if(i>359){
                    if(three[i][0]>19&&seeds[three[i][0]-20]==0){
                        seeds[three[i][0]-20]=white;
                        times[count++]=three[i][0]-20;
                        changes=true;
                        return;
                    }
                    else if(three[i][2]<380&&seeds[three[i][2]+20]==0){
                        seeds[three[i][2]+20]=white;
                        times[count++]=three[i][2]+20;
                        changes=true;
                        return;
                    }
                }
                else{
                    if(three[i][0]%20!=0&&seeds[three[i][0]-1]==0){
                        seeds[three[i][0]-1]=white;
                        times[count++]=three[i][0]-1;
                        changes=true;
                        return;
                    }
                    else if(three[i][2]%20!=19&&seeds[three[i][2]+1]==0){
                        seeds[three[i][2]+1]=white;
                        times[count++]=three[i][2]+1;
                        changes=true;
                        return;
                    }
                }
            }
        }         
    }
    public void liveHoleTwos(int whos){
        for(int i=0;i<three.length;i++){//活棋
            int j=0;
            for(j=0;j<1&&seeds[three[i][j]]==whos;j++);
            if(j==1){
                if(i>1043){
                    if(three[i][0]>19&&three[i][0]%20!=19&&three[i][2]<380&&three[i][2]%20!=0&&seeds[three[i][0]-19]==0&&seeds[three[i][2]+19]==0){
                        if(seeds[three[i][1]]==0&&seeds[three[i][2]]==whos){
                            seeds[three[i][1]]=white;
                            times[count++]=three[i][1];
                            changes=true;
                            return;
                        }
                    }
                }
                else if(i>719){
                    if(three[i][0]>19&&three[i][2]%20!=19&&three[i][2]<380&&three[i][0]%20!=0&&seeds[three[i][0]-21]==0&&seeds[three[i][2]+21]==0){
                        if(seeds[three[i][1]]==0&&seeds[three[i][2]]==whos){
                            seeds[three[i][1]]=white;
                            times[count++]=three[i][1];
                            changes=true;
                            return;
                        }
                    }
                }
                else if(i>359){
                    if(three[i][0]>19&&three[i][2]<380&&seeds[three[i][0]-20]==0&&seeds[three[i][2]+20]==0){
                        if(seeds[three[i][1]]==0&&seeds[three[i][2]]==whos){
                            seeds[three[i][1]]=white;
                            times[count++]=three[i][1];
                            changes=true;
                            return;
                        }
                    }
                }
                else{
                    if(three[i][0]%20!=0&&three[i][2]%20!=19&&seeds[three[i][0]-1]==0&&seeds[three[i][2]+1]==0){
                        if(seeds[three[i][1]]==0&&seeds[three[i][2]]==whos){
                            seeds[three[i][1]]=white;
                            times[count++]=three[i][1];
                            changes=true;
                            return;
                        }
                    }
                }
            }
        }
    }
    public void liveTwos(int whos){
        for(int i=0;i<two.length;i++){//活棋
            int j=0;
            for(j=0;j<2&&seeds[two[i][j]]==whos;j++);
            if(j==2){
                if(i>1120){
                    if(two[i][0]>19&&two[i][0]%20!=19&&two[i][1]<380&&two[i][1]%20!=0&&seeds[two[i][0]-19]==0&&seeds[two[i][1]+19]==0){
                        seeds[two[i][0]-19]=white;
                        times[count++]=two[i][0]-19;
                        changes=true;
                        return;
                    }
                }
                else if(i>759){
                    if(two[i][0]>19&&two[i][1]%20!=19&&two[i][1]<380&&two[i][0]%20!=0&&seeds[two[i][0]-21]==0&&seeds[two[i][1]+21]==0){
                        seeds[two[i][0]-21]=white;
                        times[count++]=two[i][0]-21;
                        changes=true;
                        return;
                    }
                }
                else if(i>379){
                    if(two[i][0]>19&&two[i][1]<380&&seeds[two[i][0]-20]==0&&seeds[two[i][1]+20]==0){
                        seeds[two[i][0]-20]=white;
                        times[count++]=two[i][0]-20;
                        changes=true;
                        return;
                    }
                }
                else{
                    if(two[i][0]%20!=0&&two[i][1]%20!=19&&seeds[two[i][0]-1]==0&&seeds[two[i][1]+1]==0){
                        seeds[two[i][0]-1]=white;
                        times[count++]=two[i][0]-1;
                        changes=true;
                        return;
                    }
                }
            }
        }
    }
    public void deadTwos(int whos){
        for(int i=0;i<two.length;i++){//死棋
            int j=0;
            for(j=0;j<2&&seeds[two[i][j]]==whos;j++);
            if(j==2){
                if(i>1120){
                    if(two[i][0]>19&&two[i][0]%20!=19&&seeds[two[i][0]-19]==0){
                        seeds[two[i][0]-19]=white;
                        times[count++]=two[i][0]-19;
                        changes=true;
                        return;
                    }
                    else if(two[i][1]<380&&two[i][1]%20!=0&&seeds[two[i][1]+19]==0){
                        seeds[two[i][1]+19]=white;
                        times[count++]=two[i][1]+19;
                        changes=true;
                        return;
                    }
                }
                else if(i>759){
                    if(two[i][0]>19&&two[i][0]%20!=0&&seeds[two[i][0]-21]==0){
                        seeds[two[i][0]-21]=white;
                        times[count++]=two[i][0]-21;
                        changes=true;
                        return;
                    }
                    else if(two[i][1]%20!=19&&two[i][1]<380&&seeds[two[i][1]+21]==0){
                        seeds[two[i][1]+21]=white;
                        times[count++]=two[i][1]+21;
                        changes=true;
                        return;
                    }
                }
                else if(i>379){
                    if(two[i][0]>19&&seeds[two[i][0]-20]==0){
                        seeds[two[i][0]-20]=white;
                        times[count++]=two[i][0]-20;
                        changes=true;
                        return;
                    }
                    else if(two[i][1]<380&&seeds[two[i][1]+20]==0){
                        seeds[two[i][1]+20]=white;
                        times[count++]=two[i][1]+20;
                        changes=true;
                        return;
                    }
                }
                else{
                    if(two[i][0]%20!=0&&seeds[two[i][0]-1]==0){
                        seeds[two[i][0]-1]=white;
                        times[count++]=two[i][0]-1;
                        changes=true;
                        return;
                    }
                    else if(two[i][1]%20!=19&&seeds[two[i][1]+1]==0){
                        seeds[two[i][1]+1]=white;
                        times[count++]=two[i][1]+1;
                        changes=true;
                        return;
                    }
                }
            }
        }
    }
    public void ones(int whos){
        for(int i=0;i<seeds.length;i++){//死棋
            int j=0;
            for(j=0;j<1&&seeds[i]==whos;j++);
            if(j==1){
                if(i%20!=19&&seeds[i+1]==0){
                    seeds[i+1]=white;
                    times[count++]=i+1;
                    changes=true;
                    return;
                }
                else if(i%20!=0&&seeds[i-1]==0){
                    seeds[i-1]=white;
                    times[count++]=i-1;
                    changes=true;
                    return;
                }
                else if(i>19&&seeds[i-20]==0){
                    seeds[i-20]=white;
                    times[count++]=i-20;
                    changes=true;
                    return;
                }
                else if(i<380&&seeds[i+20]==0){
                    seeds[i+20]=white;
                    times[count++]=i+20;
                    changes=true;
                    return;
                }
                else if(i%20!=19&&i>19&&seeds[i-19]==0){
                    seeds[i-19]=white;
                    times[count++]=i-19;
                    changes=true;
                    return;
                }
                else if(i%20!=0&&i<380&&seeds[i+19]==0){
                    seeds[i+19]=white;
                    times[count++]=i+19;
                    changes=true;
                    return;
                }
                else if(i%20!=0&&i>20&&seeds[i-21]==0){
                    seeds[i-21]=white;
                    times[count++]=i-21;
                    changes=true;
                    return;
                }
                else if(i%20!=19&&i<380&&seeds[i+21]==0){
                    seeds[i+21]=white;
                    times[count++]=i+21;
                    changes=true;
                    return;
                }
            }
        }
    }
}