public class Bingo{
    public int[][] bingos(){
        int[][] bingo=new int[1152][5];
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
            }
        for(int i=0;i<15;i++)//slash(240+240=480)
            for(int j=0;j<15-i;j++)
                for(int k=0;k<5;k++){
                    bingo[(31-i)*i/2+j+656][k]=(i+1)*20+(j+k)*21;//left-negative
                    bingo[(31-i)*i/2+j+776][k]=(i+1)+(j+k)*21;//right-negative
                }
        for(int i=0;i<16;i++)//cross(16+16=32)
            for(int k=0;k<5;k++){
                bingo[i+896][k]=(i+k+1)*19;
            }
        for(int i=0;i<15;i++)//slash(240+240=480)
            for(int j=0;j<15-i;j++)
                for(int k=0;k<5;k++){
                    bingo[(31-i)*i/2+j+912][k]=18-i+(j+k)*19;//left-positive
                    bingo[(31-i)*i/2+j+1032][k]=(i+2)*20-1+(j+k)*19;//right-positive
                }
        return bingo;
    }
    public int[][] fours(){
        int[][] four=new int[1258][4];
        for(int i=0;i<20;i++)
            for(int j=0;j<17;j++)
                for(int k=0;k<4;k++)
                    four[i*17+j][k]=j+k+i*20;
        for(int i=0;i<20;i++)
            for(int j=0;j<17;j++)
                for(int k=0;k<4;k++)
                    four[i*17+j+340][k]=(j+k)*20+i;
        for(int i=0;i<17;i++)
            for(int k=0;k<4;k++){
                four[i+680][k]=(i+k)*21;
            }
        for(int i=0;i<16;i++)
            for(int j=0;j<16-i;j++)
                for(int k=0;k<4;k++){
                    four[(33-i)*i/2+j+697][k]=(i+1)*20+(j+k)*21;
                    four[(33-i)*i/2+j+833][k]=(i+1)+(j+k)*21;
                }
        for(int i=0;i<17;i++)
            for(int k=0;k<4;k++){
                four[i+969][k]=(i+k+1)*19;
            }
        for(int i=0;i<16;i++)
            for(int j=0;j<16-i;j++)
                for(int k=0;k<4;k++){
                    four[(33-i)*i/2+j+986][k]=18-i+(j+k)*19;
                    four[(33-i)*i/2+j+1122][k]=(i+2)*20-1+(j+k)*19;
                }
        return four;
    }
    public int[][] threes(){
        int[][] three=new int[1368][3];
        for(int i=0;i<20;i++)
            for(int j=0;j<18;j++)
                for(int k=0;k<3;k++)
                    three[i*18+j][k]=j+k+i*20;
        for(int i=0;i<20;i++)
            for(int j=0;j<18;j++)
                for(int k=0;k<3;k++)
                    three[i*18+j+360][k]=(j+k)*20+i;
        for(int i=0;i<18;i++)
            for(int k=0;k<3;k++){
                three[i+720][k]=(i+k)*21;
            }
        for(int i=0;i<17;i++)
            for(int j=0;j<17-i;j++)
                for(int k=0;k<3;k++){
                    three[(35-i)*i/2+j+738][k]=(i+1)*20+(j+k)*21;
                    three[(35-i)*i/2+j+891][k]=(i+1)+(j+k)*21;
                }
        for(int i=0;i<18;i++)
            for(int k=0;k<3;k++){
                three[i+1044][k]=(i+k+1)*19;
            }
        for(int i=0;i<17;i++)
            for(int j=0;j<17-i;j++)
                for(int k=0;k<3;k++){
                    three[(35-i)*i/2+j+1062][k]=18-i+(j+k)*19;
                    three[(35-i)*i/2+j+1215][k]=(i+2)*20-1+(j+k)*19;
                }
        return three;
    }
    public int[][] twos(){
        int[][] two=new int[1482][2];
        for(int i=0;i<20;i++)
            for(int j=0;j<19;j++)
                for(int k=0;k<2;k++)
                    two[i*19+j][k]=j+k+i*20;
        for(int i=0;i<20;i++)
            for(int j=0;j<19;j++)
                for(int k=0;k<2;k++)
                    two[i*19+j+380][k]=(j+k)*20+i;
        for(int i=0;i<19;i++)
            for(int k=0;k<2;k++){
                two[i+760][k]=(i+k)*21;
            }
        for(int i=0;i<18;i++)
            for(int j=0;j<18-i;j++)
                for(int k=0;k<2;k++){
                    two[(37-i)*i/2+j+779][k]=(i+1)*20+(j+k)*21;
                    two[(37-i)*i/2+j+950][k]=(i+1)+(j+k)*21;
                }
        for(int i=0;i<19;i++)
            for(int k=0;k<2;k++){
                two[i+1121][k]=(i+k+1)*19;
            }
        for(int i=0;i<18;i++)
            for(int j=0;j<18-i;j++)
                for(int k=0;k<2;k++){
                    two[(37-i)*i/2+j+1140][k]=18-i+(j+k)*19;
                    two[(37-i)*i/2+j+1311][k]=(i+2)*20-1+(j+k)*19;
                }
        return two;
    }
    public int[][] ones(){
        int[][] one=new int[400][1];
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++)
                one[i*20+j][0]=i*20+j;
        return one;
    }
}