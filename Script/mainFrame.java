package Script;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class mainFrame extends JFrame implements gameConfig{
    //游戏面板
    JPanel panel;

    public mainFrame() {
        init();

    }


    //设置窗体
    public void init(){

        this.setTitle(title);
        this.setSize(frameX, frameY);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);


        //创建游戏面板
        panel = setpanel();


        this.add(panel);
        //使frame可视
        this.setVisible(true);

        //安装键盘监听器
        PanelListenner plis = new PanelListenner();
        this.addKeyListener(plis);

        //启动人物移动线程
        Player player = new Player();
        player.start();

        //启动刷新面板线程
        UpdateThread ut = new UpdateThread(panel);
        ut.start();
    }


    //设置游戏面板
    public JPanel setpanel(){
        JPanel panel = new MyPanel();
        panel.setPreferredSize(new Dimension(panelX, panelY));
        panel.setLayout(null);
        panel.setBackground(Color.black);

        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.playMusic("src/audio/GamePage.wav");

        return panel;
    }

    //播放音乐



    //内部游戏按键监听类(镜头移动)
    static class PanelListenner extends KeyAdapter {
        //当按键按下
        public void keyPressed(KeyEvent e){
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_UP -> {
                    Player.up = true;
                    Player.towards = 1;
                }
                case KeyEvent.VK_DOWN -> {
                    Player.down = true;
                    Player.towards = 2;
                }
                case KeyEvent.VK_LEFT -> {
                    Player.left = true;
                    Player.towards = 3;
                }
                case KeyEvent.VK_RIGHT -> {
                    Player.right = true;
                    Player.towards = 4;
                }
                default -> {
                }
            }
        }
        //当按键释放
        public void keyReleased(KeyEvent e){
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_UP -> {
                    Player.up = false;
                    Player.up1 = 0;
                }
                case KeyEvent.VK_DOWN -> {
                    Player.down = false;
                    Player.down1 = 0;
                }
                case KeyEvent.VK_LEFT -> {
                    Player.left = false;
                    Player.left1 = 0;
                }
                case KeyEvent.VK_RIGHT -> {
                    Player.right = false;
                    Player.right1 = 0;
                }
                default -> {
                }
            }
        }
    }


    //自定义内部游戏面板，地图素材加载
    static class MyPanel extends JPanel{
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //找到角色旁边的素材，上下左右各5格
            for(int i=Player.getI()-6; i<=Player.getI()+6; i++){
                for(int j=Player.getJ()-6; j<=Player.getJ()+6; j++){
                    //如果这一格没有超界
                    if(i>=0 && j>=0 && i<ReadMapFile.map1.length && j<ReadMapFile.map1[0].length){
                        //画第一层元素
                        ImageIcon icon = GetMap.intToicon(ReadMapFile.map1[i][j]);
                        if (icon != null) {
                            g.drawImage(icon.getImage(), (Player.px-eleSize/2)+((j-Player.getJ())*eleSize)-(Player.mx%eleSize), (Player.py-eleSize/2)+((i-Player.getI())*eleSize)-(Player.my%eleSize), eleSize, eleSize, null);
                        }
                        //第二层
                        ImageIcon icon2 = GetMap.intToicon(ReadMapFile.map2[i][j]);
                        if (icon2 != null) {
                            g.drawImage(icon2.getImage(), (Player.px-eleSize/2)+((j-Player.getJ())*eleSize)-(Player.mx%eleSize), (Player.py-eleSize/2)+((i-Player.getI())*eleSize)-(Player.my%eleSize), eleSize, eleSize, null);
                        }
                        //第三层
						ImageIcon icon3 = GetMap.intToicon(ReadMapFile.map3[i][j]);
                        if (icon3 != null) {
                            g.drawImage(icon3.getImage(), (Player.px-eleSize/2)+((j-Player.getJ())*eleSize)-(Player.mx%eleSize), (Player.py-eleSize/2)+((i-Player.getI())*eleSize)-(Player.my%eleSize), eleSize, eleSize, null);
                        }
                        //第四层
                        ImageIcon icon4 = GetMap.intToicon(ReadMapFile.map4[i][j]);
                        if(icon4 != null){
                            g.drawImage(icon4.getImage(), (Player.px-eleSize/2)+((j-Player.getJ())*eleSize)-(Player.mx%eleSize), (Player.py-eleSize/2)+((i-Player.getI())*eleSize)-(Player.my%eleSize), eleSize, eleSize, null);
                        }
                    }
                }
            }


            //角色行走动画
            Player.draw(g);

            //一个黑色的图片，然后中间挖空一个圆，加上模糊效果，来模拟人的视野
            g.drawImage(shadow2.getImage(), 0, 0, 650, 650, null);
        }
    }

    //返回panel
    public JPanel getPanel() {
        return panel;
    }
}
