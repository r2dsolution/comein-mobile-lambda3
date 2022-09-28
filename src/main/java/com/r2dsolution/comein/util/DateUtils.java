package com.r2dsolution.comein.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static java.sql.Date initSQLDate(String dateStr) {
		Date date = initDate(dateStr,"yyyy-MM-dd");
		return new  java.sql.Date(date.getTime());
	}
	public static Date initDate(String dateStr,String format) {
		
		try {
			Calendar cal = Calendar.getInstance(Locale.ENGLISH);
			DateFormat dFormat = new SimpleDateFormat(format,Locale.ENGLISH);
			return dFormat.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static Timestamp initTimestamp(String dateStr) {
		Date date = initDate(dateStr,"yyyy-MM-dd HH:mm:ss");
		return new  java.sql.Timestamp(date.getTime());
	}
}
