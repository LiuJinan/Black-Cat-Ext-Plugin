package cn.liujinnan.tools.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @description:
 * @author: ljn
 * @create: 2024-05-02 13:27
 **/
public class MsgJButton extends JButton {

    public MsgJButton(String msg) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/info.png")));
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);

        this.setIcon(new ImageIcon(newImg));
        this.setSize(30, 20);
        this.addActionListener(new MsgListener(msg));
    }


    static class MsgListener implements ActionListener {

        private String msg;

        public MsgListener(String msg) {
            this.msg = msg;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, msg);
        }

    }
}
