/*
 * copyright(c) 2024 by liujinnan.cn All rights Reserved.
 */

package cn.liujinnan.tools;

import cn.liujinnan.tools.ext.plugin.Plugin;
import cn.liujinnan.tools.ext.plugin.annotation.PluginComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @description:
 * @author: liujinnan
 * @create: 2024-04-27 14:43
 **/
@PluginComponent(name = "test")
public class Test implements Plugin {
    @Override
    public JComponent getJComponent() {

        JPanel jPanel = new JPanel();
        jPanel.add(new Button("按钮1"));

        return jPanel;
    }
}
