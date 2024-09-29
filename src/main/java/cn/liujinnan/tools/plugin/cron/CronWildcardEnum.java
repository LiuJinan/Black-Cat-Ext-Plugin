package cn.liujinnan.tools.plugin.cron;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author ljn
 * @version 1.0
 * @date 2024-09-29 16:14
 */
@RequiredArgsConstructor
@Getter
public enum CronWildcardEnum {

    /**
     * 星号， *
     */
    ASTERISK("*"),

    /**
     * 逗号
     */
    COMMA(","),


    Hyphen("-"),

    /**
     * 问号
     */
    QUESTION_MARK("?"),

    /**
     * 用于确定每个月第几个星期几，只能出现在Day of Week域。例如在4#2，表示某月的第二个星期三。
     */
    Hash ("#"),

    /**
     * 表示起始时间开始触发，然后每隔固定时间触发一次。例如在Minutes域使用5/20,则意味着5分钟触发一次，而25，45等分别触发一次.
     */
    Slash ("/"),

    /**
     * 表示最后，只能出现在Day of Week和Day of Month域。如果在Day of Week域使用5L,意味着在最后的一个星期四触发。
     */
    L("L"),

    /**
     * 表示有效工作日(周一到周五),只能出现在Day of Month域，
     * 系统将在离指定日期的最近的有效工作日触发事件。
     * 例如：在 Day of Month使用5W，如果5号是星期六，
     * 则将在最近的工作日：星期五，即4号触发。
     * 如果5号是星期天，则在6号(周一)触发；
     * 如果5号在星期一到星期五中的一天，则就在5号触发。
     * 另外一点，W的最近寻找不会跨过月份 。
     */
    W("W"),

    /**
     * 只能出现在Day of Month域
     * 这两个字符可以连用，表示在某个月最后一个工作日，即最后一个星期五。
     */
    LW("LW"),

    ;

    private final String wildcard;

}
