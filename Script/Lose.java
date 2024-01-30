package Script;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lose {
    public static void lose(JPanel panel) {
        //清屏
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        JFrame  frame = new JFrame();
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);

        //粘贴GameOver图片，退出程序
        ImageIcon gameOverImage = new ImageIcon("src/img/others/GameOver.png");
        JLabel gameOverLabel = new JLabel(gameOverImage);
        gameOverLabel.setBounds(83, 231, gameOverImage.getIconWidth(), gameOverImage.getIconHeight());
        frame.add(gameOverLabel);

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // 5秒后退出程序
            }
        });
        timer.setRepeats(false); // 仅执行一次
        timer.start();
    }
}