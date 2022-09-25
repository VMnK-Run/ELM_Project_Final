package com.tju.elmboot.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class CommonUtil {

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

    public static Integer getRandomNumber(int t) {
        Random random = new Random();
        int base = 0;
        if(5 <= t && t <= 10) {
            base = 1;
        } else {
            base = 2;
        }
        int res = base + random.nextInt(1);
        return res;
    }
}
