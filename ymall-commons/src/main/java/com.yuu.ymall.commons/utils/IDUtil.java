package com.yuu.ymall.commons.utils;

import java.util.Random;

/**
 * @Classname IDUtil
 * @Date 2019/6/5 23:46
 * @Created by Yuu
 */
public class IDUtil {

    /**
     * 随机 id 生成
     *
     * @return
     */
    public static Long getRandomId() {
        Long millis = System.currentTimeMillis();
        // 加上两位随机数
        Random random = new Random();
        int end = random.nextInt(99);
        // 如果不足两位前面补 0
        String str = millis + String.format("%02d", end);
        Long id = new Long(str);
        return id;
    }
}
