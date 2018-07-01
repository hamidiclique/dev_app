package com.test.app.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

	public static String getCurrentTimestampToString() {
		String pattern = "yyyyMMddHHmmss";
		Date fmtDate = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			TimeZone zone = TimeZone.getTimeZone("GMT+6");
			sdf.setTimeZone(zone);
			return sdf.format(fmtDate);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}