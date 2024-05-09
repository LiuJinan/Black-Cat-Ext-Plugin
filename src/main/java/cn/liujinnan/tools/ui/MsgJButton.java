package cn.liujinnan.tools.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @description:
 * @author: ljn
 * @create: 2024-05-02 13:27
 **/
public class MsgJButton extends JButton {

    public MsgJButton(String msg, Component parentComponent) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/info.png")));
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);

        this.setIcon(new ImageIcon(newImg));
        this.setSize(30, 20);
        this.addActionListener(new MsgListener(msg, parentComponent));
    }


    static class MsgListener implements ActionListener {

        private final String msg;

        private final Component parentComponent;

        public MsgListener(String msg, Component parentComponent) {
            this.msg = msg;
            this.parentComponent = parentComponent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(parentComponent, msg);
        }

    }
}
