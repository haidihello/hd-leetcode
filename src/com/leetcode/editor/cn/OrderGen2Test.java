package com.leetcode.editor.cn;

import org.apache.commons.lang3.RandomStringUtils;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrderGen2Test {
    /**
     * 订单号生成
     **/
    private static ZoneId ZONE_ID = ZoneId.of("GMT");
    private static final AtomicInteger SEQ = new AtomicInteger(100000);
    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");

    private static final int sequenceMask = 9;

    private static int sequence = 0;
    private static long lastTimestamp = -1L;

    private static SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static String generateOrderNo() {
        String id = nextId();
        LocalDateTime dataTime = LocalDateTime.now(ZONE_ID);
//        return dataTime.format(DF_FMT_PREFIX) + getLocalIpSuffix() + SEQ.getAndIncrement();
        return id + getLocalIpSuffix();

    }

    private volatile static String IP_SUFFIX = null;

    private static String getLocalIpSuffix() {
        if (null != IP_SUFFIX) {
            return IP_SUFFIX;
        }
        try {
            synchronized (OrderGen2Test.class) {
                if (null != IP_SUFFIX) {
                    return IP_SUFFIX;
                }
                InetAddress addr = InetAddress.getLocalHost();
                //192.168.126.13,
                String hostAddress = addr.getHostAddress();
                if (null != hostAddress && hostAddress.length() > 4) {
                    String ipSuffix = hostAddress.trim().split("\\.")[3];
                    if (ipSuffix.length() == 2) {
                        IP_SUFFIX = ipSuffix;
                        return IP_SUFFIX;
                    }
                    ipSuffix = "0" + ipSuffix;
                    IP_SUFFIX = ipSuffix.substring(ipSuffix.length() - 2);
                    return IP_SUFFIX;
                }
                IP_SUFFIX = RandomStringUtils.random(2, false, true);

                return IP_SUFFIX;
            }
        } catch (Exception e) {
            System.out.println("获取IP失败:" + e.getMessage());
            IP_SUFFIX = RandomStringUtils.random(2, false, true);
            return IP_SUFFIX;
        }
    }

    /**
     * 毫秒并发处理
     * @return
     */
    public static synchronized String nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            if (sequence == sequenceMask) {
                sequence = 0;
                timestamp = tilNextMillis(lastTimestamp);
            } else {
                sequence = sequence + 1;
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp);
        Date date1 = c.getTime();
        return sd.format(date1) + String.format("%01d", sequence);
    }

    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        List orderNos = Collections.synchronizedList(new ArrayList());
        IntStream.range(0, 100000).parallel().forEach(i -> {
            orderNos.add(generateOrderNo());
            System.out.println(generateOrderNo());
        });
        List filterOrderNos = (List) orderNos.stream().distinct().collect(Collectors.toList());
        System.out.println("订单样例：" + orderNos.get(22));
        System.out.println("生成订单数：" + orderNos.size());
        System.out.println("过滤重复后订单数：" + filterOrderNos.size());
        System.out.println("重复订单数：" + (orderNos.size() - filterOrderNos.size()));
        System.out.println("总耗时" + String.valueOf(System.currentTimeMillis() - start));
    }
}/**
 * 订单样例：20082115575546011022  生成订单数：8000  过滤重复后订单数：8000  重复订单数：0
 **/