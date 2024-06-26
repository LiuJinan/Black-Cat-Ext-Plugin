/*
 * copyright(c) 2024 by liujinnan.cn All rights Reserved.
 */

package cn.liujinnan.tools.plugin;

import cn.liujinnan.tools.ext.plugin.Plugin;
import cn.liujinnan.tools.ext.plugin.annotation.PluginComponent;

import javax.swing.*;

/**
 * @description:
 * @author: liujinnan
 * @create: 2024-04-27 14:43
 **/
@PluginComponent(name = "test4", icon = "logo.png")
public class Test4 implements Plugin {
    @Override
    public JComponent getJComponent() {

        JPanel jPanel = new JPanel();
        jPanel.add(new JButton("按钮1"));

        return jPanel;
    }
}
