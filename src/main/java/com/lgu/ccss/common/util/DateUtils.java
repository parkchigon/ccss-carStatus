package com.lgu.ccss.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	public final static String DATE_FORMAT_YMDHMS = "yyyyMMddHHmmss";
	
	/**
	 * 현재 시간을 특정 Date Pattern 으로 가져온다.
	 * 
	 * @param datePattern
	 *            yyyyMMdd...
	 * @return
	 */
	public static String getCurrentTime(String datePattern) {
		/*
		long sysTime = System.currentTimeMillis();

		Date date = new Date(sysTime);
		return getFormattedTime(date, datePattern);
		*/

		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String today = (new SimpleDateFormat(datePattern).format(date));

        return today;
	}
	
	/**
	 * 오늘 날짜에서 요청한 날만큼 차이가 나는 날짜 구하기 ex) getCurrentItme( Calendar.MONTH, -1,
	 * 'yyyyMMdd');
	 * 
	 * @param field  ex) Calendar.MONTH, Calendar.DATE
	 * @param interval
	 * @param format ex) 'yyyyMMdd', 'yyyyMMddHHmmss'
	 * @return
	 */
	public static String getCurrentDate(int field, int interval, String format) {

		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
		cal.add(field, interval);
		java.util.Date ago = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());

		return formatter.format(ago);
	}
	public static Date getCurrentDate(int field, int interval) {

		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
		cal.add(field, interval);
		java.util.Date ago = cal.getTime();

		return ago;
	}
	
	public static String getAddDate(String oldDateStr, int field, int interval, String format) throws Exception{
		DateFormat formatter = new SimpleDateFormat(format);
		Date oldDate = formatter.parse(oldDateStr);
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
		cal.setTime(oldDate);
		cal.add(field, interval);
		
		Date newDate = cal.getTime();
		
		return formatter.format(newDate);
	}
	public static Date getAddDate(Date oldDate, int field, int interval) throws Exception{
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0x1ee6280, "KST"));
		cal.setTime(oldDate);
		cal.add(field, interval);

		return cal.getTime();
	}
	/**
	 * Date 값을 주어진 Date Pattern 으로 변환하여 반환한다.
	 * 
	 * @param date 변환하고자 하는 날짜의 Date 값
	 * @param datePattern 날짜 포맷
	 * @return
	 */
	public static String getFormattedTime(Date date, String datePattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
		return formatter.format(date);
	}
	
	/**
	 * 현재 월의 마지막 날짜를 구한다.
	 */
	public static String getLastDate(){
		Calendar calendar = Calendar.getInstance();
		String lastDate = getCurrentTime("YYYYMM") + calendar.getActualMaximum(Calendar.DATE);
		

		return lastDate;		
		
	}

	public static int getMonthMaxDay(String dateStr){
		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6));
		int date = Integer.parseInt(dateStr.substring(6, 8));
        
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, date);
     
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static String getNextYearMonthLastDay(String dateStr){
		String nextYearMonthLastDay = "";
		
		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6));
		int date = Integer.parseInt(dateStr.substring(6, 8));
        
		Calendar calendar = Calendar.getInstance();
		calendar.set(year+1, month-1, date);
		
		nextYearMonthLastDay = String.valueOf(year+1) + getDayToString(month) + getDayToString(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return nextYearMonthLastDay;
	}	
	
	public static String getDayToString(int day){
		String dayStr = "";
		
		if(day < 10){
			dayStr = "0" + day;
		}else{
			dayStr = String.valueOf(day);
		}
		return dayStr;
	}
	
	public static boolean checkDateFormat(String dateFormat, String dateString){
		boolean result = false;
		
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		
		try {
			Date date = format.parse(dateString);
			
			if(date != null){
				result = true;
			}
		} catch (ParseException e) {
			result = false;
			logger.debug("Not Matched Date Format!!! " + dateFormat + ":" + dateString);
			logger.error("Exception", e);
		}
		
		return result;
	}
	
	public static String changeDateFormat(String oldFormat, String newFormat, String dateString){
		String newDateString = "";
		
		SimpleDateFormat oldDateFormat = new SimpleDateFormat(oldFormat);
		SimpleDateFormat newDateFormat = new SimpleDateFormat(newFormat);
		
		try {
			Date oldDate = oldDateFormat.parse(dateString);
			
			newDateString = newDateFormat.format(oldDate);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
		}
		
		return newDateString;
	}
	
	public static String toGmtString(Date date){
	    SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
	    sd.setTimeZone(TimeZone.getTimeZone("GMT"));
	    return sd.format(date);
	}
	
	public static String getDate(String dateFormat) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, java.util.Locale.KOREAN);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		return sdf.format(c.getTime());
	}

	public static String getMillisecondsTime() {
		return getDate("yyyyMMddHHmmssSSS");
	}
	
	public static String getBasicCurrentTime() {
		return getDate("yyyyMMddHHmmss");
	}
	
	@SuppressWarnings("deprecation")
	public static boolean comparedDate(Date nowDt, Date regDt) {
		if(nowDt.getDate() == regDt.getDate()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static int comparedExpDate(Date nowDt, Date expDt) {
		
		return expDt.compareTo(nowDt);
	}
	
	public static Date stringToDate(String dateStr) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date toDate = null;
		try {
			toDate = transFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return toDate;
	}
	
	public static Date stringToDate(String dateStr, String format) {
		SimpleDateFormat transFormat = new SimpleDateFormat(format);
		Date toDate = null;
		try {
			toDate = transFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return toDate;
	}
	
	public static String getString(Date date, String format) {
		SimpleDateFormat transFormat = new SimpleDateFormat(format);
		return transFormat.format(date);
	}
	
	public static Date getDate(String date, String format) {
		SimpleDateFormat transFormat = new SimpleDateFormat(format);
		Date toDate = null;
		try {
			toDate = transFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return toDate;
	}
	
	public static long getDiffDay(String beginDateStr, String endDateStr) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = format.parse(beginDateStr);
		Date endDate = format.parse(endDateStr);
		
		return getDiffDay(beginDate, endDate);
	}
	
	public static long getDiffDay(Date beginDate, Date endDate) {		 
	    long diff = endDate.getTime() - beginDate.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	    
	    return diffDays;
	}
	
	public static boolean isExpireTime(Date joinDate, String period) throws ParseException {
		// get now date
		Date now = new Date();

		// get expiration date
		Calendar cal = Calendar.getInstance(Locale.KOREA);
		cal.setTime(joinDate);
		
		cal.add(Calendar.DATE, Integer.parseInt(period));
		Date targetDate = cal.getTime();

		// diff date
		long diffDays = DateUtils.getDiffDay(now, targetDate);
		if (diffDays <= 0) {
			return true;
		}

		return false;
	}
}
