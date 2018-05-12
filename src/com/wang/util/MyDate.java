package com.wang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * <p>
 * 单例模式
 *
 * @auther ten
 */
public class MyDate {

    private static final MyDate INSTANCE = new MyDate();

    private MyDate() {
        System.out.println("MyDate加载:" + this);
    }

    public static MyDate getInstance() {
        return INSTANCE;
    }

    //获取当前日期时间
    public String getCurrentTime() {
        String temp_str = "";
        Date dt = new Date();
        //aa表示“上午”或“下午” HH表示24小时制 hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");
        temp_str = sdf.format(dt);
        return temp_str;
    }

    //返回当前时间
    @Override
    public String toString() {
        return this.getCurrentTime();
    }
}
