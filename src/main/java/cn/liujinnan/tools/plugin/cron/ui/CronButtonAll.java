package cn.liujinnan.tools.plugin.cron.ui;

import cn.liujinnan.tools.plugin.cron.CronWildcardEnum;

import javax.swing.*;
import java.util.List;

/**
 * 允许的通配符。 任意匹配
 *
 * @author ljn
 * @version 1.0
 * @date 2024-09-29 16:12
 */
public class CronButtonAll extends JButton {

    /**
     * 支持的通配符
     */
    private List<CronWildcardEnum> wildcardEnumList;

    /**
     * 名称
     */
    private String name;


    public CronButtonAll(String name, List<CronWildcardEnum> wildcardEnumList) {

    }

}
