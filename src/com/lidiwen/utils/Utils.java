package com.lidiwen.utils;

import com.bnuz.bbkt.commons.result.SystemConstant;
import com.bnuz.bbkt.service.config.RedisService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Utils {

    public static final String DATE_FORMAT_SIMPLE = "yyyyMMddHHmmss";

    public static final Long ONE_DAY_MILLISECOND = 1000L * 60 * 60 * 24;

    public static final Long ONE_MINUTE_MILLISECOND = 1000L * 60;

    public static final Long ONE_MINUTE_SECOND = 1L * 60;

    @Autowired
    RedisService redisService;

    public static String getUUID32() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }

    /**
     * 获取n位随机整数
     *
     * @param n
     * @return
     */
    public static String getRandomNumber(int n) {
        return String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, n - 1)));
    }

    /**
     * 获取当前毫秒数
     *
     * @return 当前毫秒数
     */
    public static Long getNowSecondTime() {
        return new Date().getTime();
    }


    /**
     * 获取当前格式化时间
     *
     * @return 当前格式化时间
     */
    public static String getNowTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    public static Long changeSecondTimeToHour(Long milliSeconds) {
        Long second = milliSeconds / 1000;
        Long hour = second / 3600;
        return hour;
    }

    /**
     * 获得客户端真实IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 一定不会有前导0的情况
     */
    public static String getSixRandomCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

    private static final double EARTH_RADIUS = 6378.137;

    /**
     * 转化为弧度(rad)
     *
     * @param d
     * @return
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两个坐标的距离
     *
     * @param long1
     * @param lat1
     * @param long2
     * @param lat2
     * @return
     */
    public static double getDistanceByLongitudeAndlatitude(double long1, double lat1, double long2, double lat2) {
        double a, b, d, sa2, sb2;
        lat1 = rad(lat1);
        lat2 = rad(lat2);
        a = lat1 - lat2;
        b = rad(long1 - long2);

        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * EARTH_RADIUS * 100
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

    public static void printByJSON(HttpServletResponse response, Object message) {
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            response.setHeader("Access-Control-Allow-Origin", "*");
            PrintWriter writer = response.getWriter();
            JSONObject array = JSONObject.fromObject(message);
            writer.print(array);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTempToken(String uuid) {
        //基于uuid创建token
        String token = JwtUtils.createJWT("wechat", uuid, SystemConstant.JWT_TTL_LONG);
        return token;
    }

    public static String getClassKey(int n) {
        String str = "";
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            char num = ch[random.nextInt(ch.length)];
            str += num;
        }
        return str;
    }
}