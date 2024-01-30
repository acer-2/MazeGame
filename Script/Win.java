package Script;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Win {
    public static void win(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        JFrame  frame = new JFrame();
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);

        ImageIcon youWinIcon = new ImageIcon("src/img/others/YouWin.png");
        JLabel youWinLabel = new JLabel(youWinIcon);
        youWinLabel.setBounds(83, 230, youWinIcon.getIconWidth(), youWinIcon.getIconHeight());
        frame.add(youWinLabel);

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
