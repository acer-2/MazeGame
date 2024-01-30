package Script;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeginPage {
    JFrame window;
    JButton startButton;



    public BeginPage() {
        // 创建窗口
        window = new JFrame("MazeGame");
        window.setSize(486, 864);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setBackground(Color.BLACK);

        // 加载背景图片
        ImageIcon background = new ImageIcon("src/img/bg/background1.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, 486, 864);
        window.add(backgroundLabel);

        //加载标题图片title.png
        ImageIcon title = new ImageIcon("src/img/others/title1.png");
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBounds(121, 270, 243, 54);
        backgroundLabel.add(titleLabel);

        //加载播放音乐
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.playMusic("src/audio/BeginPage.wav");

        // 添加开始游戏按钮
        startButton = new JButton("开始游戏");
        startButton.setBounds(121, 529, 243, 121); // 设置按钮位置和大小
        window.add(startButton);

        // 在BeginPage类中为开始游戏按钮添加事件监听器
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里编写触发开始游戏事件的代码
                // 例如，可以打开新的游戏界面或加载游戏场景
                System.out.println("开始游戏按钮被点击了");
                new ChooseRole(window);

                //关闭音乐播放
                musicPlayer.stopMusic();

            }
        });




        // 显示窗口
        window.setLayout(null); // 关闭布局管理器
        window.setVisible(true);
    }
}