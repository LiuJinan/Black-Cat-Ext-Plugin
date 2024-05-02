package cn.liujinnan.tools.ui;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;

/**
 * @author ljn
 * @version 1.0
 * @date 2024-04-22 10:43
 */
public class MyJPanel extends JPanel {

    private final String labelName;
    private String btnName;

    private JTextField jTextField;

    private CopyJButton btn;

    /**
     * 显示样例： labelName jTextField  btn
     *  输出： 输出框  复制按钮
     * @param labelName
     */
    public MyJPanel(String labelName) {
        this.labelName = labelName;
        init();
    }

    public MyJPanel(String labelName, String btnName) {
        this.labelName = labelName;
        this.btnName = btnName;
    }

    private void init() {
        GridLayout gridLayout = new GridLayout(1, 3);
        this.setLayout(gridLayout);
        JLabel jLabel = new JLabel(labelName);
        jLabel.setFont(new Font(null, Font.PLAIN, 25));
        jLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(jLabel);

        jTextField = new JTextField();
        jTextField.setEditable(Boolean.FALSE);
        btnName = StringUtils.isBlank(btnName) ? "复制" : btnName;
        btn = new CopyJButton(btnName, jTextField);
        this.add(jTextField);
        this.add(btn);
    }

    public void setTextFieldContent(String content) {
        jTextField.setText(content);
    }

    public void setBtnVisible(boolean visible) {
        btn.setVisible(visible);
    }
}
