package com.lenovo.rms.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 简述
 * <p>
 * 详细说明第一行<br>
 * 详细说明第二行
 * 
 * @date 2015年4月4日 下午9:03:01
 * @author zhanglei
 * @version V1.0
 */
public class DateUtils {
    /**
     * 
     * 根据日期取得星期几
     * 
     * @date 2015年4月4日 下午9:11:57
     * @author zhanglei
     * @param date
     * @return String
     */
    public static String getWeek(Date date) {
        String[] weeks = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0)
            week_index = 0;
        return weeks[week_index];
    }

    private static int getDayDiff(Date date) {
        // String[] weeks = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0)
            week_index = 0;
        int[] diff = { 6, 0, 1, 2, 3, 4, 5 };
        return diff[week_index];
    }

    /**
     * 根据日期取得星期几
     * 
     * @date 2015年4月4日 下午9:18:25
     * @author zhanglei
     * @param date
     * @return String
     */
    public static String getSysWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        return week;
    }

    /**
     * 
     * 取得日期是某年的第几周
     * 
     * @date 2015年4月4日 下午9:13:47
     * @author zhanglei
     * @param date
     * @return int
     */
    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 
     * 取得日期是某月的第几周
     * 
     * @date 2015年4月4日 下午9:21:47
     * @author zhanglei
     * @param date
     * @return int
     */
    public static int getWeekOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 
     * 取得某个月有多少天
     * 
     * @date 2015年4月4日 下午9:15:20
     * @author zhanglei
     * @param year
     * @param month
     * @return int
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int days_of_month = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days_of_month;
    }

    /**
     * 
     * 取得两个日期之间的相差多少天
     * 
     * @date 2015年4月4日 下午9:16:28
     * @author zhanglei
     * @param date0
     * @param date1
     * @return long
     */
    public static long getDaysBetween(Date date0, Date date1) {
        long daysBetween = (date1.getTime() - date0.getTime() + 1000000) / 86400000;
        return daysBetween;
    }

    public static boolean isReachDeadLine(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) >= 11;
    }

    public static Date[] firstAndLastDate(Date date) {
        // 为了保证效率，返回Date数组，依次存放上个月第一天;上个月第一个周一;最后一天;最后一个周日
        Date[] dates = new Date[4];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        dates[0] = calendar.getTime();

        int diff = getDayDiff(dates[0]);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - diff);
        dates[1] = calendar.getTime();

        calendar.setTime(date);
        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
        calendar.set(Calendar.DAY_OF_MONTH,
                getDaysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1));
        dates[2] = calendar.getTime();

        diff = 6 - getDayDiff(dates[2]);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + diff);
        dates[3] = calendar.getTime();

        return dates;
    }

    public static Date prevMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date prevMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
        calendar.set(Calendar.DAY_OF_MONTH,
                getDaysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1));
        return calendar.getTime();
    }

    public static Date prevMonthFirstMon(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date prevMonthFirstDay = calendar.getTime();
        int diff = getDayDiff(prevMonthFirstDay);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - diff);
        return calendar.getTime();
    }

    public static Date prevMonthLastSun(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
        calendar.set(Calendar.DAY_OF_MONTH,
                getDaysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1));
        Date prevMonthFirstDay = calendar.getTime();
        int diff = 6 - getDayDiff(prevMonthFirstDay);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + diff);
        return calendar.getTime();
    }

    public static Date getFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date[] getPrevMonthLastWeek(Date date) {
        Date[] dates = new Date[2];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
        calendar.set(Calendar.DAY_OF_MONTH,
                getDaysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1));
        dates[1] = calendar.getTime();

        int diff = getDayDiff(dates[1]);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - diff);
        dates[0] = calendar.getTime();

        return dates;
    }
    
    public static Date parseString(String dateString){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date=null;
    	try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    	return date;
    }
    
    public static String formatDate(Date date){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String dateString=null;
		dateString = sdf.format(date);
    	return dateString;
    }

    public static void main(String[] args) {
        Date date = new Date();
        Date start = DateUtils.prevMonthFirstMon(date);
        Date end = DateUtils.prevMonthLastSun(date);
        System.out.println(start);
        System.out.println(end);
        System.out.println((DateUtils.getDaysBetween(start, end) + 1) / 7);
    }
}
