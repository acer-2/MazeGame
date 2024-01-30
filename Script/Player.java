package Script;

import javax.swing.*;
import java.awt.*;

public class Player extends Thread implements gameConfig{

    //角色中点相对游戏面板的位置(在游戏中是不变的)
    static int px = panelX/2;
    static int py = panelY/2;
    //角色中点在整张地图中的位置(设置人最开始中点的位置一定要是一个元素中心的位置)
    static int x = 75;
    static int y = 75;
    //角色的偏移量
    static int mx = 50;
    static int my = 50;
    //角色的步长
    static int step = 5;
    //角色是否移动
    static boolean up = false;
    static boolean down = false;
    static boolean left = false;
    static boolean right = false;
    //角色的朝向    1,2,3,4分别代表上下左右(用来处理角色不移动时的朝向问题)
    static int towards = 1;
    //角色的移动累积量（用来控制循环的变化4张角色图片来达成动态移动的）
    static int up1 = 0;
    static int down1 = 0;
    static int left1 = 0;
    static int right1 = 0;

    @Override
    public void run() {
        while(true){
            moveUD();
            moveLR();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //角色移动方法
    //1、角色上下移动
    public void moveUD(){
        if(up){
            //当按住上键时，给up1加1，当up1大于20时候又置为0，达成循环
            up1++;
            if(up1>=20){
                up1=0;
            }
            //如果角色当前位置上方的数组值不为0（角色上方有物体挡着），不能移动到上面一格
            if(ReadMapFile.map2[y/eleSize-1][x/eleSize]!=0){
                int y1 = (y/eleSize-1)*eleSize+eleSize/2;
                int x1 = (x/eleSize)*eleSize+eleSize/2;
                if((y-y1)*(y-y1)>=eleSize*eleSize){
                    y=y-step;
                    my=my-step;
                }
            }else if(ReadMapFile.map2[y/eleSize-1][x/eleSize]==0){//上方没物体，可以继续向上移动
                y=y-step;
                my=my-step;

                if(ReadMapFile.map3[y/eleSize-1][x/eleSize]!=0){
                    mainFrame frame = new mainFrame();
                    JPanel panel = frame.getPanel();
                    Lose.lose(panel);
                }else if(ReadMapFile.map4[y/eleSize-1][x/eleSize]!=0){
                    mainFrame frame = new mainFrame();
                    JPanel panel = frame.getPanel();
                    Win.win(panel);
                }
            }
        }else if(down){
            down1++;
            if(down1>=20){
                down1=0;
            }
            if(ReadMapFile.map2[y/eleSize+1][x/eleSize]!=0){
                int y1 = (y/eleSize+1)*eleSize+eleSize/2;
                int x1 = (x/eleSize)*eleSize+eleSize/2;
                if((y-y1)*(y-y1)>=eleSize*eleSize){
                    y=y+step;
                    my=my+step;
                }
            }else if(ReadMapFile.map2[y/eleSize+1][x/eleSize]==0){
                y=y+step;
                my=my+step;

                if(ReadMapFile.map3[y/eleSize+1][x/eleSize]!=0){
                    mainFrame frame = new mainFrame();
                    JPanel panel = frame.getPanel();
                    Lose.lose(panel);
                }else if(ReadMapFile.map4[y/eleSize+1][x/eleSize]!=0){
                    mainFrame frame = new mainFrame();
                    JPanel panel = frame.getPanel();
                    Win.win(panel);
                }
            }
        }
    }

    //2、角色左右移动
    public void moveLR(){
        if(left){
            left1++;
            if(left1>=20){
                left1=0;
            }
            if(ReadMapFile.map2[y/eleSize][x/eleSize-1]!=0){
                int y1 = (y/eleSize)*eleSize+eleSize/2;
                int x1 = (x/eleSize-1)*eleSize+eleSize/2;
                if((x-x1)*(x-x1)>=eleSize*eleSize){
                    x=x-step;
                    mx=mx-step;
                }
            }else if(ReadMapFile.map2[y/eleSize][x/eleSize-1]==0){
                x=x-step;
                mx=mx-step;

                if(ReadMapFile.map3[y/eleSize][x/eleSize-1]!=0){
                    mainFrame frame = new mainFrame();
                    JPanel panel = frame.getPanel();
                    Lose.lose(panel);
                }else if(ReadMapFile.map4[y/eleSize][x/eleSize-1]!=0){
                    mainFrame frame = new mainFrame();
                    JPanel panel = frame.getPanel();
                    Win.win(panel);
                }
            }
        }else if(right){
            right1++;
            if(right1>=20){
                right1=0;
            }
            if(ReadMapFile.map2[y/eleSize][x/eleSize+1]!=0){
                int y1 = (y/eleSize)*eleSize+eleSize/2;
                int x1 = (x/eleSize+1)*eleSize+eleSize/2;
                if((x-x1)*(x-x1)>=eleSize*eleSize){
                    x=x+step;
                    mx=mx+step;
                }
            }else if(ReadMapFile.map2[y/eleSize][x/eleSize+1]==0){
                x=x+step;
                mx=mx+step;

                if(ReadMapFile.map3[y/eleSize][x/eleSize+1]!=0){
                    mainFrame frame = new mainFrame();
                    JPanel panel = frame.getPanel();
                    Lose.lose(panel);
                }else if(ReadMapFile.map4[y/eleSize][x/eleSize+1]!=0){
                    mainFrame frame = new mainFrame();
                    JPanel panel = frame.getPanel();
                    Win.win(panel);
                }
            }
        }
    }

    //动画生成
    public static void draw(Graphics g){
        //如果角色不在移动中
        if(!up&&!down&&!left&&!right){
            if(towards==1){//如果角色移动的最后朝向为上
                g.drawImage(walk2.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 0, 64*3, 64, 64*4, null);
            }else if(towards==2){//最后移动朝向下
                g.drawImage(walk2.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 0, 0, 64, 64, null);
            }else if(towards==3){//最后移动朝向左
                g.drawImage(walk2.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 0, 64, 64, 64*2, null);
            }else if(towards==4){//最后移动朝向右
                g.drawImage(walk2.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 0, 64*2, 64, 64*3, null);
            }
        }else{//如果角色在移动中
            if(up){
                //通过up1的值，来决定画哪一张图片
                if(up1<5){
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 0, 64*3, 64, 64*4, null);
                }else if(up1<10){
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 64, 64*3, 64*2, 64*4, null);
                }else if(up1<15){
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 64*2, 64*3, 64*3, 64*4, null);
                }else{
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 64*3, 64*3, 64*4, 64*4, null);
                }
            }else if(down){
                if(down1<5){
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 0, 0, 64, 64, null);
                }else if(down1<10){
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 64, 0, 64*2, 64, null);
                }else if(down1<15){
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 64*2, 0, 64*3, 64, null);
                }else{
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 64*3, 0, 64*4, 64, null);
                }
            }else if(left){
                if(left1<5){
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 0, 64, 64, 64*2, null);
                }else if(left1<10){
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 64, 64, 64*2, 64*2, null);
                }else if(left1<15){
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 64*2, 64, 64*3, 64*2, null);
                }else{
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 64*3, 64, 64*4, 64*2, null);
                }

            }else if(right){
                if(right1<5){
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 0, 64*2, 64, 64*3, null);
                }else if(right1<10){
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 64, 64*2, 64*2, 64*3, null);
                }else if(right1<15){
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 64*2, 64*2, 64*3, 64*3, null);
                }else{
                    g.drawImage(walk1.getImage(), Player.px-eleSize/2-15, Player.py-eleSize/2-25, Player.px-eleSize/2+65, Player.py-eleSize/2+55, 64*3, 64*2, 64*4, 64*3, null);
                }
            }
        }
    }

    //得到角色在数组中的位置I
    public static int getI(){
        return (y-(roleSize/2))/50;
    }
    //得到角色在数组中的位置J
    public static int getJ(){
        return (x-(roleSize/2))/50;
    }
}
