package com.vikash.kafkaconsumer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class DateUtil {

    private DateUtil(){

    }

    public static String formatDateToString(Date date,String format,String timeZone){
        SimpleDateFormat simpleDateFormat = null;
        if(date == null) return null;
        if(format.length() != 0 || !format.isBlank()){
            simpleDateFormat  = new SimpleDateFormat(format);
        }
        if(timeZone == null || "".equalsIgnoreCase(timeZone.trim())){
            timeZone = Calendar.getInstance().getTimeZone().getID();
        }
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return simpleDateFormat.format(date);
    }
}
