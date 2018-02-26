package com.test.app.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    
	public static Date convertStringToDate(String dateString) {
        String pattern = "MM/dd/yyyy";
        Date fmtDate = new Date();
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            fmtDate = df.parse(dateString);            
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return fmtDate;
    }
}