package cn.liujinnan.tools.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 子网掩码 ip 工具
 * <p>
 * 例： 10.0.0.0/24
 * 子网掩码(二进制)：1111 1111.1111 1111.1111 1111.0000 0000  前24位网络前缀为1， 剩余后8位主机号为0
 * 子网掩码(十进制)：255.255.255.0
 * <p>
 * 网关(网络)与广播地址计算：
 * 10.0.0.0二进制         --> 0000 1100.0000 0000.0000 0000.0000 0000
 * 子网掩码(二进制)         --> 1111 1111.1111 1111.1111 1111.0000 0000
 * 网关等于两二进制按位&     --> 0000 1100.0000 0000.0000 0000.0000 0000
 * 广播等于子网掩码取反后，和网络地址或运算     --> 0000 1100.0000 0000.0000 0000.1111 1111
 * 第一个可用ip， 网关地址+1
 * 最后一个可用ip：广播地址-1
 * 可用ip数： 2^(主机号数=32-掩码值) - 2    减去2(网络地址、广播地址共占用两个)
 *
 * @author ljn
 * @version 1.0
 * @date 2023-10-11 15:24
 */
public class IpMaskUtils {

    private static final String NUM_STR_ONE = "1";
    private static final String NUM_STR_ZERO = "0";

    /**
     * ip由四个数字组成，每个数字占8位
     */
    private static final Integer NUM_BIT_LENGTH = 8;

    /**
     * ip由四个数字组成
     */
    private static final Integer NUM_LENGTH = 4;

    /**
     * ip二进制位数长度
     */
    private static final Integer IP_BIT_LENGTH = 32;


    private IpMaskUtils() {
        throw new IllegalStateException();
    }

    /**
     * 获取子网掩码地址
     *
     * @param mask 掩码值(网络前置)
     * @return List<Integer> size = 4
     */
    public static List<Integer> getMaskAddress(int mask) {

        List<Integer> result = Lists.newArrayList();
        List<String> maskList = Lists.newArrayList();
        for (int i = 0; i < IP_BIT_LENGTH; i++) {
            if (i < mask) {
                maskList.add(NUM_STR_ONE);
                continue;
            }
            maskList.add(NUM_STR_ZERO);
        }
        // ip4 地址32位，按8位区分为四个值
        List<List<String>> partition = Lists.partition(maskList, NUM_BIT_LENGTH);
        if (partition.size() != NUM_LENGTH) {
            throw new IllegalStateException();
        }
        partition.forEach(list -> {
            StringBuilder stringBuffer = new StringBuilder();
            list.forEach(stringBuffer::append);
            // 二进制 -> 十进制
            int num = Integer.parseInt(stringBuffer.toString(), 2);
            result.add(num);
        });

        return result;
    }

    /**
     * 获取子网掩码地址
     *
     * @param mask 掩码值
     * @return 字符串 xxx.xxx.xxx.xxx
     */
    public static String getMaskAddressString(int mask) {
        List<Integer> maskList = getMaskAddress(mask);
        return maskList.stream().map(String::valueOf).collect(Collectors.joining("."));
    }

    /**
     * 获取网络地址
     *
     * @param ip   xxx.xxx.xxx.xxx
     * @param mask 掩码值
     * @return List<Integer> size=4
     */
    public static List<Integer> getNetworkAddress(String ip, int mask) {
        if (StringUtils.isBlank(ip)) {
            throw new IllegalStateException("ip is error");
        }
        String[] split = ip.split("\\.");
        if (split.length != NUM_LENGTH) {
            throw new IllegalStateException("ip format error");
        }
        List<Integer> ipList = Lists.newArrayList();
        Arrays.stream(split).forEach(e -> ipList.add(Integer.parseInt(e)));
        List<Integer> maskList = getMaskAddress(mask);
        List<Integer> networkList = Lists.newArrayList();
        for (int i = 0; i < NUM_LENGTH; i++) {
            Integer itemIp = ipList.get(i);
            Integer itemMask = maskList.get(i);
            networkList.add(itemIp & itemMask);
        }

        return networkList;
    }

    /**
     * 获取网络地址
     *
     * @param ip   xxx.xxx.xxx.xxx
     * @param mask 掩码值
     * @return 网络地址 xxx.xxx.xxx.xxx
     */
    public static String getNetworkAddressString(String ip, int mask) {
        List<Integer> networkAddress = getNetworkAddress(ip, mask);
        return networkAddress.stream().map(String::valueOf).collect(Collectors.joining("."));
    }

    /**
     * 获取广播地址
     *
     * @param ip   xxx.xxx.xxx.xxx
     * @param mask 掩码值
     * @return
     */
    public static List<Integer> getBroadcastAddress(String ip, int mask) {

        List<Integer> maskAddress = getMaskAddress(mask);
        List<Integer> networkAddress = getNetworkAddress(ip, mask);

        List<Integer> negateMask = Lists.newArrayList();
        // 1. 子网掩码取反
        maskAddress.forEach(e -> {
            // 取反后忽略符合位，只取后8位
            String binaryString = Integer.toBinaryString(~e);
            String right = StringUtils.right(binaryString, NUM_BIT_LENGTH);
            // 二进制转换为十进制
            int num = Integer.parseInt(right, 2);
            negateMask.add(num);
        });

        // 2. 广播地址 = 子网掩码取反 | 网络地址
        List<Integer> broadcastAddress = Lists.newArrayList();
        for (int i = 0; i < 4; i++) {
            Integer negateMaskItem = negateMask.get(i);
            Integer networkItem = networkAddress.get(i);
            // 或运算
            broadcastAddress.add(negateMaskItem | networkItem);
        }

        return broadcastAddress;
    }

    /**
     * 获取广播地址
     *
     * @param ip   xxx.xxx.xxx.xxx
     * @param mask 掩码值
     * @return
     */
    public static String getBroadcastAddressString(String ip, int mask) {
        List<Integer> broadcastAddress = getBroadcastAddress(ip, mask);
        return broadcastAddress.stream().map(String::valueOf).collect(Collectors.joining("."));
    }


    /**
     * 获取可用ip数
     *
     * @return
     */
    public static Integer getIpCount(int mask) {
        //主机位数
        int hostNum = IP_BIT_LENGTH - mask;
        // 使用左移计算 2 ^ hostNum = (2 << (hostNum - 1))
        return (2 << (hostNum - 1)) - 2;
    }

    /**
     * 获取第一位可用ip
     *
     * @param ip
     * @param mask
     * @return
     */
    public static List<Integer> getBeginIp(String ip, int mask) {

        List<Integer> networkAddress = getNetworkAddress(ip, mask);

        // 第一个可用ip = 网络地址+1
        long beginIpLong = toBinaryLong(networkAddress) + 1;
        String beginIpBinaryString = StringUtils.leftPad(Long.toBinaryString(beginIpLong), IP_BIT_LENGTH, NUM_STR_ZERO);

        return toDecimalismList(beginIpBinaryString);
    }

    /**
     * 获取第一位可用ip
     *
     * @param ip
     * @param mask
     * @return
     */
    public static String getBeginIpString(String ip, int mask) {
        List<Integer> beginIp = getBeginIp(ip, mask);
        return StringUtils.join(beginIp, ".");
    }


    /**
     * 获取最后一位可用ip
     *
     * @param ip
     * @param mask
     * @return
     */
    public static List<Integer> getEndIp(String ip, int mask) {

        List<Integer> broadcastAddress = getBroadcastAddress(ip, mask);

        // 最后一个可用ip = 广播地址-1
        long ipLong = toBinaryLong(broadcastAddress) - 1;
        String ipBinaryString = StringUtils.leftPad(Long.toBinaryString(ipLong), IP_BIT_LENGTH, NUM_STR_ZERO);

        return toDecimalismList(ipBinaryString);
    }

    /**
     * 获取最后一位可用ip
     *
     * @param ip
     * @param mask
     * @return
     */
    public static String getEndIpString(String ip, int mask) {
        List<Integer> endIp = getEndIp(ip, mask);
        return StringUtils.join(endIp, ".");
    }

    /**
     * 转换为二进制字符串
     *
     * @param ip
     * @return
     */
    private static String toBinaryString(List<Integer> ip) {
        if (ip == null || ip.size() != NUM_LENGTH) {
            throw new IllegalStateException();
        }
        StringBuffer stringBuffer = new StringBuffer();
        ip.forEach(item -> {
            // 转为二进制字符串
            String binaryString = Integer.toBinaryString(item);
            // 不够8位，左边补0
            String leftPad = StringUtils.leftPad(binaryString, NUM_BIT_LENGTH, NUM_STR_ZERO);
            stringBuffer.append(leftPad);
        });
        return stringBuffer.toString();
    }

    /**
     * 转为为二进制long
     *
     * @param ip
     * @return
     */
    private static Long toBinaryLong(List<Integer> ip) {
        String binaryString = toBinaryString(ip);
        return Long.parseLong(binaryString, 2);
    }

    /**
     * 转换为十进制数组
     *
     * @return
     */
    private static List<Integer> toDecimalismList(String ipBinaryString) {
        List<Integer> ipList = Lists.newArrayList();
        List<String> list = Lists.newArrayList();
        for (char c : ipBinaryString.toCharArray()) {
            list.add(String.valueOf(c));
        }
        // 分为四组，对应ip四位数值
        List<List<String>> partition = Lists.partition(list, NUM_BIT_LENGTH);
        for (List<String> itemList : partition) {
            StringBuilder item = new StringBuilder();
            itemList.forEach(item::append);
            int ipItem = Integer.parseInt(item.toString(), 2);
            ipList.add(ipItem);
        }
        return ipList;
    }

    /**
     * 获取公网ip
     * @return
     */
    public static String getPublicNetworkIp(){
        try {
            // 使用外部IP地址查询服务获取本机的外网IP地址. 亚马逊ip检测
            URL url = new URL("http://checkip.amazonaws.com/");
            URLConnection conn = url.openConnection();
            conn.connect();
            String ip = new java.util.Scanner(conn.getInputStream(), "UTF-8").next();
            return ip;
        } catch (Exception e) {

        }
        return "127.0.0.1";
    }

    // TODO: 2023/10/11 第一个ip，最后一个

    public static void main(String[] args) throws Exception{
        System.out.println(IpMaskUtils.getNetworkAddressString("172.17.24.18", 20));
        System.out.println(IpMaskUtils.getBroadcastAddressString("172.17.24.18", 20));

        System.out.println(getBeginIpString("172.17.24.18", 20));
        System.out.println(getEndIpString("172.17.24.18", 20));

        System.out.println(getPublicNetworkIp());
    }
}
