package Script;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Subgraph {

    JLabel walkLabel;

    //分割行走动画帧
    public static BufferedImage[][] splitImage(String imagePath, int rows, int cols) throws IOException {
        BufferedImage[][] images = new BufferedImage[rows][cols + 1];
        BufferedImage image = ImageIO.read(new File(imagePath));
        int width = image.getWidth() / cols;
        int height = image.getHeight() / rows;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                images[i][j] = image.getSubimage(j * width, i * height, width, height);
            }
        }
        return images;
    }

    //补充动画
    public static BufferedImage[][] completeAnimation(BufferedImage[][] images) {
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                if (j == 3) {
                    images[i][j] = images[i][1]; // 将第二列复制给第四列
                }
            }
        }
        return images;
    }
    //private static int index = 0;

    //制作停止帧
    public BufferedImage[][] getSameColumnImages(BufferedImage[][] images) {
        int column = 1;
        BufferedImage[][] newImages = new BufferedImage[images.length][images[0].length];
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                newImages[i][j] = images[i][column];
            }
        }
        return newImages;
    }

    //重新拼接：4*3 => 4*4
    public static void putTogether(BufferedImage[][] images, String outputFileName) {

        int width = images[0][0].getWidth() * images[0].length;
        int height = images[0][0].getHeight() * images.length;
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                BufferedImage image = images[i][j];
                result.createGraphics().drawImage(image, image.getWidth() * j, image.getHeight() * i, null);
            }
        }
        try {
            File output = new File("src/img/PauseFrame/" + outputFileName + ".png");
            ImageIO.write(result, "png", output);
        } catch (IOException e) {
            System.out.println("Error during image saving: " + e.getMessage());
        }
    }

    //测试图片是否分割正确
    public static void displayImages(BufferedImage[][] images, JFrame window) {

        JLabel label = new JLabel();
        window.add(label);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Runnable task = () -> {
            for (BufferedImage[] imageArray : images) {
                for (BufferedImage image : imageArray) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    label.setIcon(new ImageIcon(image));
                    label.setBounds(0, 500, 64, 64);
                    label.repaint();
                }
            }
        };

        executor.execute(task);
    }
}