package cn.liujinnan.tools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * @author ljn
 * @version 1.0
 * @date 2024-04-28 11:27
 */
public class Main {

    public static void main(String[] args) throws Exception  {

        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setSize(500, 400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        jFrame.setContentPane(tabbedPane);

        Test t = new Test();
        BufferedImage read = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/logo.png")));
//        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/logo.png")));
        Image scaledInstance = read.getScaledInstance(10, 10, 10);
        ImageIcon imageIcon = new ImageIcon(scaledInstance);
        tabbedPane.addTab("test", imageIcon, t.getJComponent(), "提示");
    }
}
