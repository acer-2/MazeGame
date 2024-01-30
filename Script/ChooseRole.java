package Script;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class ChooseRole {
    JButton confirmButton;
    JButton LeftButton;
    JButton RightButton;
    ImageIcon background;
    ImageIcon shadow;

    static String playerName;
    static String playerName2;
    static String playerName3;

    String putUseName;

    Subgraph player = new Subgraph();
    BufferedImage [][]  playerImg = new BufferedImage[4][3];

    //BufferedImage [][]  playerImg2 = new BufferedImage[4][4];

    BufferedImage [][] playerImg3 = new BufferedImage[4][4];

    public ChooseRole(JFrame window) {
        // 清除窗口中的所有元件
        window.getContentPane().removeAll();
        window.getContentPane().revalidate();
        window.getContentPane().repaint();

        // 加载背景图片
        background = new ImageIcon("src/img/bg/background2.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, 486, 864);
        window.add(backgroundLabel);

        //加载阴影
        shadow = new ImageIcon("src/img/others/Shadow.png");
        JLabel shadowLabel = new JLabel(shadow);
        shadowLabel.setBounds(202,394,82,38);
        backgroundLabel.add(shadowLabel);

        //显示第一个角色
        Role role = new Role();
        ArrayList<String> rolePath = role.getRoleImages();
        ImageIcon roleIcon = new ImageIcon(rolePath.get(0));
        JLabel roleLabel = new JLabel(roleIcon);
        roleLabel.setBounds(211, 349, 64, 64);
        backgroundLabel.add(roleLabel);

        //添加左按钮
        LeftButton = new JButton("左");
        LeftButton.setBounds(5,197,40,40);
        backgroundLabel.add(LeftButton);
        LeftButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // 切换到上一个角色
                roleLabel.setIcon(new ImageIcon(role.LastRolePath()));

            }
        });

        //加载音乐
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.playMusic("src/audio/ChoosePage.wav");

        //添加右按钮
        RightButton = new JButton("右");
        RightButton.setBounds(431,197,40,40);
        backgroundLabel.add(RightButton);
        RightButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // 切换到下一个角色
                roleLabel.setIcon(new ImageIcon(role.NextRolePath()));

            }
        });

        // 添加确定按钮
        confirmButton = new JButton("确定");
        confirmButton.setBounds(121, 529, 243, 121); // 设置按钮位置和大小
        backgroundLabel.add(confirmButton);
        // 添加事件监听器，触发确定按钮事件
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("确认选中角色");

                //保存选中图片的文件名
                playerName = Role.getFileNameFromPath(role.getCurrentImagePath());
                playerName2 = Role.getFileNameFromPath(role.getCurrentImagePath());
                playerName3 = Role.getFileNameFromPath(role.getCurrentImagePath());

                //4*3 => 4*4
//                String extension = ".png";
//                putUseName =  playerName.replace(extension, "");

                playerName = "src/img/4_4/" + playerName;
                playerName2 = "src/img/PauseFrame/" + playerName2;
                playerName3 = "src/img/RoleDisplay/" + playerName3;

//                try{
//                    playerImg3 = Subgraph.splitImage(playerName3, 4, 3);
//                }catch (Exception e1){
//                    throw new RuntimeException(e1);
//                }



                try {
                    //分割动画帧
                    playerImg = player.completeAnimation(player.splitImage(playerName3, 4, 3));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

//                try{
//                    playerImg2 =player.getSameColumnImages(player.splitImage(playerName, 4, 3));
//                }catch (IOException ex){
//                    throw new RuntimeException(ex);
//                }

                System.out.println("playerName: " + playerName);


                //new Subgraph().putTogether(playerImg2, putUseName);

                //关闭音乐
                musicPlayer.stopMusic();

                //进入游戏页面
                window.dispose();

                LoadingPage gamePage = new LoadingPage();
                gamePage.LoadingScene(playerImg);

                //开始游戏


                //分割动画帧
                //new Subgraph().displayImages(playerImg);

            }
        });

        // 显示窗口
        window.setLayout(null); // 关闭布局管理器
        window.setVisible(true);
    }

    public static String getPlayerName() {
        return playerName;
    }

    public static String getPlayerName2() {
        return playerName2;
    }
}