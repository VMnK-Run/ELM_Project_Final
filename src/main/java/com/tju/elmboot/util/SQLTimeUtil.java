package com.tju.elmboot.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SQLTimeUtil {
    public static Date getNowDate() {
        long d = System.currentTimeMillis();
        return new Date(d);
    }

    public static Date addDays(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        java.util.Date utilDate = (java.util.Date)calendar.getTime();
        utilDate = (java.util.Date) calendar.getTime();
        return new Date(utilDate.getTime());
    }
}
