package cn.liujinnan.tools.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 复制按钮，创建是需要文本框参数
 *
 * @author ljn
 * @version 1.0
 * @date 2024-03-09 10:55
 */
public class CopyJButton extends JButton {

    /**
     * @param text       按钮标题
     * @param jTextField 文本框，需要复制内容的文本框
     */
    public CopyJButton(String text, JTextField jTextField) {
        this.setText(text);
        this.addActionListener(new BtnCopyTextListener(jTextField));
    }

    static class BtnCopyTextListener implements ActionListener {

        private final JTextField jTextField;

        public BtnCopyTextListener(JTextField jTextField) {
            this.jTextField = jTextField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection = new StringSelection(jTextField.getText());
            clipboard.setContents(stringSelection, null);
        }
    }
}
