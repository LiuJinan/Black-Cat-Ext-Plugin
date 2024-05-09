package cn.liujinnan.tools.plugin.ip.listener;


import cn.liujinnan.tools.ui.MyJPanel;
import cn.liujinnan.tools.utils.IpMaskUtils;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ljn
 * @version 1.0
 * @date 2024-04-24 15:14
 */
@Builder
public class IpBtnListener implements ActionListener {

    /**
     * 提示消息展示于 msgParentComponent 中间位置， msgParentComponent=null，展示在屏幕中间
     */
    private Component msgParentComponent;

    /**
     * 输入ip短。 172.0.0.1/24
     */
    private JTextField inputText;

    /**
     * 网关地址
     */
    private MyJPanel networkAddress;

    /**
     * 广播地址
     */
    private MyJPanel broadcastAddress;

    /**
     * 第一位可用ip
     */
    private MyJPanel firstIp;

    private MyJPanel lastIp;

    private MyJPanel ipCount;

    private MyJPanel outNetIp;


    @Override
    public void actionPerformed(ActionEvent e) {
        String ipInput = inputText.getText();
        checkInput(ipInput);
        String[] split = ipInput.split("/");
        String ip = split[0];
        int mask = Integer.parseInt(split[1]);
        networkAddress.setTextFieldContent(IpMaskUtils.getNetworkAddressString(ip, mask));
        broadcastAddress.setTextFieldContent(IpMaskUtils.getBroadcastAddressString(ip, mask));
        firstIp.setTextFieldContent(IpMaskUtils.getBeginIpString(ip, mask));
        lastIp.setTextFieldContent(IpMaskUtils.getEndIpString(ip, mask));
        ipCount.setTextFieldContent(String.valueOf(IpMaskUtils.getIpCount(mask)));
        outNetIp.setTextFieldContent(IpMaskUtils.getPublicNetworkIp());
    }

    private void checkInput(String input) {
        String msg = "输入格式错误。格式:172.0.0.1/24";
        boolean contains = input.contains("/");
        if (!contains) {
            JOptionPane.showMessageDialog(msgParentComponent, msg);
            return;
        }
        if (StringUtils.countMatches(input, ".") != 3) {
            JOptionPane.showMessageDialog(msgParentComponent, msg);
            return;
        }
    }
}
