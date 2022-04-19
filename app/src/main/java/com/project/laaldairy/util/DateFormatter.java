package com.project.laaldairy.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {

    //1-based indexing
    private static String[] monthNameArray = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private static String[] dayNameArray = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static String getFormattedDate(String stringDate) throws ParseException {
        Date date = new Date(stringDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return dayNameArray[calendar.get(Calendar.DAY_OF_WEEK)-1]+", "+simpleDateFormat.format(date).substring(0,2)+" "+monthNameArray[calendar.get(Calendar.MONTH)];
    }

}
