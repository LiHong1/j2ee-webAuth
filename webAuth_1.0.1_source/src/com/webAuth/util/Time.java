package com.webAuth.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    public static long dateStringToLong(String value){
        Date date = null;   //定义时间类型       
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd hh:ss"); 
        try { 
        date = inputFormat.parse(value); //将字符型转换成日期型
        } catch (Exception e) { 
        e.printStackTrace(); 
        } 
        return date.getTime();   //返回毫秒数
    }
    
    public static String dateToString(Date date){
        SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-mm-dd hh:ss"); 
        return inputFormat.format(date);
    }
    
    
}
