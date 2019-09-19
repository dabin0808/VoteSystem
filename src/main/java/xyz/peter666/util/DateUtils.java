package xyz.peter666.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String parse(Date date){
        String time = DateUtils.format.format(date);
        return time;
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        String time = parse(date);
        System.out.println(time);

    }
}
