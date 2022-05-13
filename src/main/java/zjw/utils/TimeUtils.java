package zjw.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static String getcurrentTime(){
        String format = simpleDateFormat.format(new Date());
        return format;
    }

    public static void main(String[] args) {
        String s = getcurrentTime();
        System.out.println(s);
    }

}
