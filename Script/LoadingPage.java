package Script;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class LoadingPage {

    JFrame loadingWindow;
//    ImageIcon mapImg = new ImageIcon("src/img/Map/Map1.png");
//    JLabel mapLabel = new JLabel(mapImg);

    public void LoadingScene(BufferedImage [][] playerImg) {
        // 创建全局窗口GameWindow
        loadingWindow = new JFrame();
        loadingWindow.setSize(768, 600);
        loadingWindow.setTitle("GameScene");
        loadingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadingWindow.setLocationRelativeTo(null);
        loadingWindow.getContentPane().setBackground(Color.BLACK);

        // 读取Loading.png图片
        ImageIcon loadingImg = new ImageIcon("src/img/others/Loading.png");
        JLabel loadingLabel = new JLabel(loadingImg);
        loadingLabel.setBounds(184, 250, 400, 100);
        loadingWindow.add(loadingLabel);
        Subgraph.displayImages(playerImg, loadingWindow);

        //加载音乐
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.playMusic("src/audio/LoadingPage.wav");

        // 五秒后关闭窗口
        Timer timer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //关闭音乐
                musicPlayer.stopMusic();

                loadingWindow.dispose();



                ReadMapFile.readfile("src/img/Map/Map1.map");
                mainFrame mf = new mainFrame();
//                gameWindow.getContentPane().removeAll();
//                gameWindow.repaint();
//                gameWindow.getContentPane().validate();

//                mapLabel.setBounds(0, 0, 768, 600);
//                gameWindow.add(mapLabel);
            }
        });
        timer.setRepeats(false);
        timer.start();


        //new Player().WalkAnimation(playerImg, mapLabel);

        loadingWindow.setVisible(true);
    }
}
