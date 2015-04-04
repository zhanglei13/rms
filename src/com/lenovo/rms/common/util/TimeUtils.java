package com.lenovo.rms.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	// 取得当前时间 格式yyyy-MM-dd hh:mm:ss
	public static String nowTime() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(new Date().getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(c.getTime());
	}
	
	public static Timestamp nowTimestamp(){
	    Timestamp ts = new Timestamp(System.currentTimeMillis());  
	    return ts;
	}

	// 取得当前日期
	public static String nowdate() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(new Date().getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(c.getTime());
	}

	// 取得N个月以后的日期
	public static String N_MonthDate(int n) {
		Calendar c = Calendar.getInstance();
		c.add(c.MONTH, n);
		return "" + c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-"
				+ c.get(c.DATE);
	}

	// 取得标准时间 2014-05-23从格式yyyy-MM-dd hh:mm:ss
	public static String getHalfDate(String timeStr) {
		String t = timeStr;
		if (timeStr.contains("-")) {
			t = timeStr.substring(0, 10);
		}
		return t;
	}

	// 取得标准时间 2014从格式yyyy-MM-dd hh:mm:ss
	public static String getYear(String timeStr) {
		String t = timeStr;
		if (timeStr.contains("-")) {
			t = timeStr.substring(0, 4);
		}
		return t;
	}

	// 取得标准时间 2014从格式yyyy-MM-dd hh:mm:ss
	public static String getDay(String timeStr) {
		String t = timeStr;
		if (timeStr.contains("-")) {
			String[] str = timeStr.split("-");
			if (str.length > 2) {
				t = str[2];
			}
		}
		return t;
	}

	// 计算两个时间的差：耗时多少
	public static String getPastTime(String nowtime, String oldtime)
			throws ParseException {//
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now = df.parse(nowtime);
		java.util.Date date = df.parse(oldtime);
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String pastTime = "" + day + "天" + hour + "小时" + min + "分" + s + "秒";

		return String.valueOf(l);
	}

	// 计算是否过期,true未过期；false过期
	public static boolean isInvalid(String nowtime, String overtime)
			throws ParseException {//
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now = df.parse(nowtime);
		java.util.Date over = df.parse(overtime);
		long l = now.getTime() - over.getTime();
		boolean flag = true;
		if (l > 0) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	// 取得前一天的时间
	public static String getDayBefore(String specifiedDay) {
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c
				.getTime());
		return dayBefore;
	}

	// 取得后一天的时间
	public static String getDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c
				.getTime());
		return dayAfter;
	}

	// 取得前后N天的时间,N=正负数
	public static String getDayAfterOrBeforeN(String specifiedDay, int N) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + N);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c
				.getTime());
		return dayAfter;
	}

	// 取得前后N分钟后的时间,N=正负数
	public static String getMinuteAfterOrBeforeN(String specifiedDay, int N) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int minute = c.get(Calendar.MINUTE);
		c.set(Calendar.MINUTE, minute + N);

		String minuteAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(c.getTime());
		return minuteAfter;
	}

	// 取得当前的年月比如： 1407
	public static String getYearMonth() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(new Date().getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
		return dateFormat.format(c.getTime());
	}

}
