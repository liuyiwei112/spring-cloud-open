package com.finance.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String format_ymd = "yyyy-MM-dd";
    public static String format_ymd_hms = "yyyy-MM-dd HH:mm:ss";

    /**
     * N天前的具体时间
     *
     * @param n    0 表示今天，-1代表昨天
     * @param type yyyyMMddHHmmss、yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getSomeDaysAgoString(int n, String type) {
        Date today = new Date();
        SimpleDateFormat sp = new SimpleDateFormat();
        sp.applyPattern(type);
        Calendar ca = Calendar.getInstance();
        ca.setTime(today);
        ca.add(Calendar.DAY_OF_YEAR, n);
        return sp.format(ca.getTime());
    }

    /**
     * 获取当前时间前后的时间
     *
     * @param n     0 表示今天，其它整数，则根据field的类型来加减n来获取相应的时间
     * @param field Calendar.DAY_OF_YEAR,Calendar.HOUR_OF_DAY等
     * @return
     * @Demo getSomeDate(-1, Calendar.HOUR_OF_DAY) //获取当前时间的前一小时的时间
     */
    public static Date getSomeDate(int n, int field) {
        return getSomeDate(n, field, new Date());
    }

    /**
     * 获取给定时间的前几天时间
     *
     * @param setData
     * @param days
     * @return
     */
    public static Date getBeforeData(Date setData, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(setData);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        Date startData = calendar.getTime();
        return startData;
    }

    /**
     * 根据指定时间进行时间加减操作
     *
     * @param n
     * @param field
     * @param date
     * @return
     * @user WeiZheng
     * @date 2014年12月18日 上午11:20:42
     */
    public static Date getSomeDate(int n, int field, Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        switch (field) {
            case Calendar.YEAR:
                ca.add(Calendar.YEAR, n);
                break;
            case Calendar.MONTH:
                ca.add(Calendar.MONTH, n);
                break;
            case Calendar.DAY_OF_YEAR:
                ca.add(Calendar.DAY_OF_YEAR, n);
                break;
            case Calendar.HOUR_OF_DAY:
                ca.add(Calendar.HOUR_OF_DAY, n);
                break;
            case Calendar.MINUTE:
                ca.add(Calendar.MINUTE, n);
                break;
            case Calendar.SECOND:
                ca.add(Calendar.MINUTE, n);
                break;
            default:
                break;
        }

        return ca.getTime();
    }

    /**
     * 将指定时间格式化指定字符串
     *
     * @param date java.util.Date时间类型
     * @param type yyyyMMddHHmmss、yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatSomeDate(Date date, String type) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sp = new SimpleDateFormat();
        sp.applyPattern(type);
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return sp.format(ca.getTime());
    }

    /**
     * 获取指定时间的小时
     *
     * @param date
     * @return
     * @user WeiZheng
     * @date 2015年1月15日 下午4:09:57
     */
    public static int getHour(Date date) {
        try {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);

            return ca.get(Calendar.HOUR_OF_DAY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取指定时间的日期
     *
     * @param date
     * @return
     * @user WeiZheng
     * @date 2015年1月15日 下午4:13:17
     */
    public static int getDay(Date date) {
        try {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);

            return ca.get(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取指定时间的分钟
     *
     * @param date
     * @return
     * @user WeiZheng
     * @date 2015年1月15日 下午4:35:03
     */
    public static int getMinute(Date date) {
        try {
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);

            return ca.get(Calendar.MINUTE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据日期得到当月天数
     *
     * @param date
     * @return
     */
    public static int getDaysByDate(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONDAY) + 1;
        int day = 0;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            if (month == 2) {
                day = 29;
            }
        } else {
            if (month == 2) {
                day = 28;
            }
        }

        if (month == 4 || month == 6 || month == 9 || month == 11) {
            day = 30;
        }

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            day = 31;
        }
        return day;
    }

    /**
     * 格式化某一时间字符串
     *
     * @param date
     * @param format
     * @return Date
     */
    public static Date getDate(String date, String format) {
        SimpleDateFormat sp = new SimpleDateFormat(format);
        try {
            if (date != null) {
                return sp.parse(date);
            }
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getCurrentDate() {
        try {
            SimpleDateFormat sp = new SimpleDateFormat(format_ymd_hms);
            return sp.parse(sp.format(new Date(System.currentTimeMillis())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化某一时间
     *
     * @param date
     * @param format
     * @return string
     */
    public static String getDateStr(Date date, String format) {
        SimpleDateFormat sp = new SimpleDateFormat(format);
        try {
            if (date != null) {
                return sp.format(date);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取两个日期间相差的小时数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Long getHourTwoDate(Date date1, Date date2) {
        long hour = (date2.getTime() - date1.getTime()) / (1000 * 60 * 60);
        return hour;
    }

    /**
     * 根据给定日期,返回是星期几(1-7)
     *
     * @param date
     * @return
     */
    public static int getDateWeek(Date date) {
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 根据给定日期,返回是星期几(1-7)
     *
     * @param date
     * @return
     */
    public static int getDateWeek(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date dt = sdf.parse(date);
            int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;
            return weekDays[w];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Boolean isValidDate(String dateStr, String pattern) {
        boolean flag = true;
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.setLenient(false);
            format.parse(dateStr);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 时间比较
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return startTime大于endTime 返回-1 ,小于返回1，相等返回0
     */
    public static int compareDate(String startTime, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(startTime);
            Date dt2 = df.parse(endTime);
            return dt2.compareTo(dt1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        /*//System.out.println(DateUtils.formatSomeDate(DateUtils.getSomeDate(-1, Calendar.HOUR_OF_DAY), "yyyy-MM-dd HH:mm:ss"));
        Date t = DateUtils.getSomeDate(-1, Calendar.HOUR_OF_DAY);
    	Date now = new Date();
    	if (now.after(t)) {
    		System.out.println("--------------");
    	}*/
//        System.out.println(getDateWeek(new Date()));
//        System.out.println(getDateWeek("2017-06-25","yyyy-MM-dd"));
        System.out.println();
        System.out.println(getDate("13:50", "hh:mm"));
        System.out.println(getDateStr(getDate("13:50", "hh:mm"), "yyyy-MM-dd hh:mm"));
    }

}
