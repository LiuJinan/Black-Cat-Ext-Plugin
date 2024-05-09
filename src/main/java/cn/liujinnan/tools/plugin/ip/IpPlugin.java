package cn.liujinnan.tools.plugin.ip;

import cn.liujinnan.tools.ext.plugin.Plugin;
import cn.liujinnan.tools.ext.plugin.annotation.PluginComponent;
import cn.liujinnan.tools.plugin.ip.listener.IpBtnListener;
import cn.liujinnan.tools.ui.MsgJButton;
import cn.liujinnan.tools.ui.MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @description: ip工具
 * @author: ljn
 * @create: 2024-05-02 12:32
 **/
@PluginComponent(name = "IP", icon = "logo.png")
public class IpPlugin implements Plugin {

    @Override
    public JComponent getJComponent() {
        JPanel panel = new JPanel(new GridLayout(13, 1));
        JLabel title = new JLabel("IP工具");
        title.setFont(new Font(null, Font.PLAIN, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title);
        panel.add(new JLabel());

        // 输入
        JPanel input = new JPanel(new GridLayout(1, 2));
        panel.add(input);
        JLabel codeLabel = new JLabel("IP：");
        codeLabel.setFont(new Font(null, Font.PLAIN, 25));
        codeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(codeLabel);

        JTextField inputText = new JTextField();
        inputText.setHorizontalAlignment(SwingConstants.CENTER);
        input.add(inputText);

        input.add(new MsgJButton("格式:172.0.0.1/24", panel));

        // 网关地址
        MyJPanel networkAddress = new MyJPanel("网关地址:");
        panel.add(networkAddress);

        // 广播地址
        MyJPanel broadcastAddress = new MyJPanel("广播地址:");
        panel.add(broadcastAddress);

        // 第一位可用ip
        MyJPanel firstIp = new MyJPanel("首位IP:");
        panel.add(firstIp);

        // 最后一位可用ip
        MyJPanel lastIp = new MyJPanel("末位IP:");
        panel.add(lastIp);

        // ip数量
        MyJPanel ipCount = new MyJPanel("可用IP数量:");
        ipCount.setBtnVisible(Boolean.FALSE);
        panel.add(ipCount);

        // 外网ip
        MyJPanel outNetIp = new MyJPanel("外网IP:");
        panel.add(outNetIp);

        panel.add(new JPanel());
        JPanel btnPanel = new JPanel(new GridLayout(1, 2));
        JButton btn = new JButton("执行");
        btnPanel.add(new JLabel());
        btnPanel.add(btn);
        btnPanel.add(new JLabel());
        panel.add(btnPanel);

        IpBtnListener ipBtnListener = IpBtnListener.builder()
                .inputText(inputText)
                .networkAddress(networkAddress)
                .broadcastAddress(broadcastAddress)
                .firstIp(firstIp)
                .lastIp(lastIp)
                .ipCount(ipCount)
                .outNetIp(outNetIp)
                .build();
        btn.addActionListener(ipBtnListener);

        return panel;
    }
}
