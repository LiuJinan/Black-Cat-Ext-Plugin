/*
 * copyright(c) 2024 by liujinnan.cn All rights Reserved.
 */

package cn.liujinnan.tools.plugin.id;

import cn.liujinnan.tools.ext.plugin.Plugin;
import cn.liujinnan.tools.ext.plugin.annotation.PluginComponent;
import cn.liujinnan.tools.ui.MyJPanel;
import cn.liujinnan.tools.utils.SnowflakeIdWorker;
import com.google.common.collect.Lists;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

/**
 * @description:
 * @author: liujinnan
 * @create: 2024-05-10 01:01
 **/
@PluginComponent(name = "ID")
public class IdPlugin implements Plugin {
    @Override
    public JComponent getJComponent() {
        JPanel panel = new JPanel(new GridLayout(12, 1));
        JLabel title = new JLabel("随机ID");
        title.setFont(new Font(null, Font.PLAIN, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title);
        panel.add(new JLabel());

        List<MyJPanel> numList = Lists.newArrayList();
        for (int i = 1; i <= 3; i++) {
            MyJPanel myJPanel = new MyJPanel("数值ID-" + i + ":");
            numList.add(myJPanel);
            panel.add(myJPanel);
        }
        panel.add(new JLabel());

        List<MyJPanel> strList = Lists.newArrayList();
        for (int i = 1; i <= 3; i++) {
            MyJPanel myJPanel = new MyJPanel("UUID-" + i + ":");
            strList.add(myJPanel);
            panel.add(myJPanel);
        }

        panel.add(new JPanel());
        JPanel btnPanel = new JPanel(new GridLayout(1, 2));
        JButton btn = new JButton("执行");
        btnPanel.add(new JLabel());
        btnPanel.add(btn);
        btnPanel.add(new JLabel());
        panel.add(btnPanel);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                for (MyJPanel myJPanel : numList) {
                    myJPanel.setTextFieldContent(idWorker.nextId() + "");
                }
                for (MyJPanel myJPanel : strList) {
                    myJPanel.setTextFieldContent(UUID.randomUUID().toString().replace("-", ""));
                }
            }
        });

        return panel;
    }
}
