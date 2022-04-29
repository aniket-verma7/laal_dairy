package com.project.laaldairy.util;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {

    //1-based indexing
    private static final String[] monthNameArray = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private static final String[] dayNameArray = {"","Mon","Tue","Wed","Thu","Fri","Sat","Sun"};

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getFormattedDate(String stringDate) throws ParseException {
        int day = Integer.parseInt(stringDate.substring(0,2));
        int month = Integer.parseInt(stringDate.substring(3,5));
        int year = Integer.parseInt(stringDate.substring(6,10));

        LocalDate localDate = LocalDate.of(year,month,day);
        return dayNameArray[localDate.getDayOfWeek().getValue()]+", "+ day+" "+ monthNameArray[month-1]+" "+year;
    }

    public static String[] getDays()
    {
        return dayNameArray;
    }

}
