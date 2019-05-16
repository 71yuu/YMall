package com.yuu.ymall.web.admin.commons.utils;

import com.yuu.ymall.commons.utils.HttpUtil;
import com.yuu.ymall.commons.utils.MapperUtil;
import com.yuu.ymall.web.admin.commons.dto.IpWeatherResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP 信息工具类
 *
 * @Classname IPInfoUtil
 * @Date 2019/5/13 14:09
 * @Created by Yuu
 */
public class IPInfoUtil {

    private static final Logger log = LoggerFactory.getLogger(IPInfoUtil.class);

    /**
     * Mob全国天气预报接口
     */
    private final static String GET_WEATHER="http://apicloud.mob.com/v1/weather/ip?key=2b1e8ccdabd32&ip=";

    /**
     * 获取客户端 IP 地址
     *
     * @param request 请求
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
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况下，第一个 IP 为客户端真实 IP ，多个 IP 按照‘，’分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip="127.0.0.1";
        }
        return ip;
    }

    /**
     * 获取 IP 返回天气
     * @param ip ip 地址
     * @return
     */
    public static String getIpInfo(String ip) {
        if (null != ip) {
            String url = GET_WEATHER + ip;
            String result = HttpUtil.doGet(url);
            return result;
        }
        return null;
    }

    /**
     * 获取 IP 返回地理信息
     *
     * @param ip ip 地址
     * @return
     */
    public static String getIpCity(String ip) {
        if (ip != null) {
            String url = GET_WEATHER + ip;
            String json = HttpUtil.doGet(url);
            IpWeatherResult weather = null;
            String result = "未知";
            try {
                weather = MapperUtil.json2pojo(json, IpWeatherResult.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            result = weather.getResult().get(0).getCity() + " " + weather.getResult().get(0).getDistrct();
            return result;
        }
        return null;
    }

    public static void main(String[] args) {
        log.info(getIpInfo("192.168.23.1"));
    }
}
