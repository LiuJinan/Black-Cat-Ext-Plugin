package cn.liujinnan.tools.plugin.cron;

import cn.liujinnan.tools.ext.plugin.Plugin;
import cn.liujinnan.tools.ext.plugin.annotation.PluginComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @author ljn
 * @version 1.0
 * @date 2024-09-25 14:20
 *
 */
@PluginComponent(name = "Cron", icon = "cron.png")
public class CronPlugin implements Plugin {


    @Override
    public JComponent getJComponent() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        // cron选项框
        JTabbedPane optionPane = new JTabbedPane();
        panel.add(optionPane);
        // 表达式
        JPanel expression = new JPanel();
        panel.add(expression);

        // 秒、分、时
        JPanel second = new JPanel();

        ButtonGroup buttonGroup = new ButtonGroup();

        JRadioButton allow = new JRadioButton("每秒", true);
        allow.setFont(new Font(null, Font.BOLD, allow.getFont().getSize()));

        second.add(allow);
        second.add(new JLabel("允许的通配符[, - * /]"));
        buttonGroup.add(allow);

        optionPane.add("秒", second);
        optionPane.add("分", new JPanel());
        optionPane.add("时", new JPanel());
        optionPane.add("日", new JPanel());
        optionPane.add("月", new JPanel());
        optionPane.add("年", new JPanel());



//        expression.add()

        return panel;
    }

}
