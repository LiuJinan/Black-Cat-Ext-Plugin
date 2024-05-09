package cn.liujinnan.tools;

import cn.liujinnan.tools.ext.plugin.test.TestPlugin;
import cn.liujinnan.tools.plugin.id.IdPlugin;
import cn.liujinnan.tools.plugin.ip.IpPlugin;

/**
 * @author ljn
 * @version 1.0
 * @date 2024-05-06 14:42
 */
public class Test {

    public static void main(String[] args) {
        TestPlugin.runTest(new IdPlugin());
    }
}
