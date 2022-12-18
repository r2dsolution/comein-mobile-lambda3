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
		if (date!=null) {
			return new  java.sql.Date(date.getTime());
		}else {
			return null;
		}
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
	public static java.sql.Date nowSQLDate() {
		Date date = nowDate();
		return new  java.sql.Date(date.getTime());
	}
	public static Date nowDate() {
		Calendar cal = Calendar.getInstance(Locale.ENGLISH);
		return cal.getTime();
	}
	public static Timestamp nowTimestamp() {
		Date d = nowDate();
		return new  java.sql.Timestamp(d.getTime());
	}
	public static Timestamp initTimestamp(String dateStr) {
		Date date = initDate(dateStr,"yyyy-MM-dd HH:mm:ss");
		if (date!=null) {
			return new  java.sql.Timestamp(date.getTime());
		}else {
			return null;
		}
	}
	public static String format(Date d, String format) {
		DateFormat dFormat = new SimpleDateFormat(format,Locale.ENGLISH);
		return dFormat.format(d);
		
	}
}
