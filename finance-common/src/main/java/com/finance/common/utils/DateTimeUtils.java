package com.finance.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间工具类
 * @author zhoujie
 *
 */
public class DateTimeUtils {
	private final static Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);
	
	public final static String DEFAULT_DATE_FORMAT_PATTERN_SHORT = "yyyy-MM-dd";
	
	public final static String DEFAULT_DATE_NOT_SECOND_FORMAT_PATTERN_FULL = "yyyy-MM-dd HH:mm";
	
	public final static String DEFAULT_TIME_FORMAT_PATTERN_SHORT = "HH:mm:ss";
	
	public final static String DEFAULT_DATE_FORMAT_PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";
	
	public final static String DEFAULT_MONTH_DAY_FORMAT_PATTERN_SHORT = "MM-dd";
	
	public final static String DEFAULT_TIME_NOT_SECOND_FORMAT_PATTERN_SHORT = "HH:mm";
	
	
	public final static String DEFAULT_DATE_TIME = "23:59:59";
	
	private  static Map<String, FastDateFormat> conMap = new ConcurrentHashMap<String, FastDateFormat>();
	
	static {
		conMap.put(DEFAULT_DATE_FORMAT_PATTERN_SHORT, FastDateFormat.getInstance(DEFAULT_DATE_FORMAT_PATTERN_SHORT));
		conMap.put(DEFAULT_TIME_FORMAT_PATTERN_SHORT, FastDateFormat.getInstance(DEFAULT_DATE_FORMAT_PATTERN_SHORT));
		conMap.put(DEFAULT_DATE_FORMAT_PATTERN_FULL, FastDateFormat.getInstance(DEFAULT_DATE_FORMAT_PATTERN_FULL));
	}
	/**
	 * 获得当前时间的字符串
	 * @param pattern 时间格式
	 * @return 时间字符串
	 */
	public static String getCurrentDateString(String pattern){
		FastDateFormat sdf = getDateFormat(pattern);
		return sdf.format(new Date());
	}
	/**
	 * 获得时间格式对象
	 * @param pattern
	 * @return
	 */
	private static FastDateFormat getDateFormat(String pattern){
		if(StringUtils.isEmpty(pattern)){
			return null;
		}
		FastDateFormat sdf = null;
		if(conMap.containsKey(pattern)){
			sdf = conMap.get(pattern);
		}else{
			//处理没有找到的格式
			try {
				sdf = FastDateFormat.getInstance(pattern);
				conMap.put(pattern, sdf);
			} catch (Exception e) {
				sdf = FastDateFormat.getInstance(DEFAULT_DATE_FORMAT_PATTERN_FULL);
			}
		}
		return sdf;
	}
	/**
	 * 获得时间的字符串
	 * @param date 待转换的日期
	 * @param pattern 时间格式
	 * @return
	 */
	public static String getDateString(Date date, String pattern){
		FastDateFormat sdf = getDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static Date getDateByString(String dateStr, String pattern){
		if(StringUtils.isEmpty(dateStr) ){
			return null;
		}
		FastDateFormat sdf = getDateFormat(pattern);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.error("getDateByString e={}",e);
			return null;
		}
	}
	
	/**
	 * 根据时期和时间创建时间
	 * @param date
	 * @param time
	 * @return
	 */
	public static Date creatDate(String date, String time){
		if(StringUtils.isEmpty(date) || StringUtils.isEmpty(time)){
			return null;
		}
		StringBuffer dateSb = new StringBuffer(date);
		return getDateByString(dateSb.append(" ").append(time).toString(), DEFAULT_DATE_FORMAT_PATTERN_FULL);
	}
	
	/**
	 * @Description: 从当前时间开始计算，获取指定偏移时间
	 * @param offset(-60:向前偏移60天;45向后偏移45天)
	 * @return 
	 */
	    
	public static String getDateByOffsetDay(int offset){
		return getDateByOffsetDay(offset,DEFAULT_DATE_FORMAT_PATTERN_SHORT);  
	}
	
	/**
	 * @Description: 从当前时间开始计算，获取指定偏移时间
	 * @param offset(-60:向前偏移60天;45向后偏移45天)
	 * @param dateFmt
	 * @return 
	 */
	    
	public static String getDateByOffsetDay(int offset, String dateFmt){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, offset);
		FastDateFormat sdf = getDateFormat(dateFmt);
		return sdf.format(cal.getTime());  
	}

	/**
	 * @Description: 当前时间按HOUR偏移
	 * @param offset
	 * @return 
	 */
	    
	public static Date getDateByOffsetHours(int offset){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, offset);
		return cal.getTime();  
	}
	

    /**
     * @Description: 当前时间按Minutes偏移
     * @param offset
     * @return 
     */
        
    public static Date getDateByOffsetMinutes(int offset){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, offset);
        return cal.getTime();  
    }
    

    /**
     * @Description: 当前时间按Minutes偏移
     * @param offset
     * @return 
     */
        
    public static Time getTimeByOffsetMinutes(int offset)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, offset);
        Date date=cal.getTime();
        String time = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        return Time.valueOf(time);
    }

	public static Date getDate(String date , String dateFmt) throws ParseException {
		 DateFormat df = new SimpleDateFormat(dateFmt);
		 return df.parse(date);
	}

	/**
	 * 校验格式为yyyy-mm-dd 如2017-02-01
	 * @param dateString
	 * @return
	 */
	public static boolean isValidYyyyMm(String dateString){
		//使用正则表达式 测试 字符 符合 dddd-dd-dd 的格式(d表示数字)
		Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");
		Matcher m = p.matcher(dateString);
		if(!m.matches()){	return false;}

		//得到年月日
		String[] array = dateString.split("-");
		int year = Integer.valueOf(array[0]);
		int month = Integer.valueOf(array[1]);
		int day = Integer.valueOf(array[2]);

		if(month<1 || month>12){	return false;}
		int[] monthLengths = new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if(isLeapYear(year)){
			monthLengths[2] = 29;
		}else{
			monthLengths[2] = 28;
		}
		int monthLength = monthLengths[month];
		if(day!=1){
			return false;
		}
		/*if(day<1 || day>monthLength){
			return false;
		}*/
		return true;
	}
	/**
	 * 校验格式为yyyy-mm-dd 如2017-02-01
	 * @param dateString
	 * @return
	 */
	public static boolean isValidYyyy(String dateString){
		//使用正则表达式 测试 字符 符合 dddd-dd-dd 的格式(d表示数字)
		Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");
		Matcher m = p.matcher(dateString);
		if(!m.matches()){	return false;}

		//得到年月日
		String[] array = dateString.split("-");
		int month = Integer.valueOf(array[1]);

		if(month<1 || month>12){	return false;}
		return true;
	}
	/**
	 * 获取两个时间差多少天
	 * @param str1 开始时间 格式如2017-01-12 12:12:12
	 * @param str2 结束时间 格式如2017-01-15 12:12:12
	 * @return
	 * @throws Exception
	 */
	public static long getDistanceDays(String str1, String str2){
		DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT_PATTERN_FULL);
		Date one;
		Date two;
		long days=0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff ;
			if(time1<time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}
	/**
	 * 获取两个时间差多少天
	 * @param date1 开始日期
	 * @param date2 结束日期
	 * @return date1 和 date2 相差天数
	 * @throws Exception
	 */
	public static long getDistanceDays(Date date1, Date date2){
		long days=0;
		try {
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			long diff ;
			if(time1<time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}
	/** 是否是闰年 */
	private static boolean isLeapYear(int year){
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ;
	}
}
