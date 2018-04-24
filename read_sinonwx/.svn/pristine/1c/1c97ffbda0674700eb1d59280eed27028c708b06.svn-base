package com.sinontech.pub.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
     
	public static Timestamp getNow(){
		return new Timestamp(new Date().getTime());
	}
	
	public static String getStringLongNow(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		return sdf.format(date);
	}
	
	public static String getTime(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		return sdf.format(date);
	}
	
	public static String getStringNow(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	public static String getStringShortNow(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	public static String getStringMonth(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(date);
	}
	/**
	 * 把一个字符串转换成Timestamp
	 */
	public static Timestamp getTime(String time){
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(time);
			Timestamp timestamp =new Timestamp(date.getTime());
			return timestamp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Date convertDate(String str, String datePattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        try {
            return sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
    }
	public static boolean isOnehour(Calendar cal){
	    cal.add(Calendar.HOUR_OF_DAY, 1);
	    Timestamp target =new Timestamp(cal.getTime().getTime());
	    Timestamp now =new Timestamp(new Date().getTime());
	    if(target.after(now)){
	        return true;
	    }else{
	        return false;
	    }
	    
	}
	
	/**
	  * 得到本周周一
	  * 
	  * @return yyyy-MM-dd
	  */
	 public static String getMondayOfThisWeek() {
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar c = Calendar.getInstance();
		  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		  if (day_of_week == 0)
			  day_of_week = 7;
		  c.add(Calendar.DATE, -day_of_week + 1);
		  return sdf.format(c.getTime());
	 }

	 

	 /**
	  * 得到本周周日
	  * 
	  * @return yyyy-MM-dd
	  */
	 public static String getSundayOfThisWeek() {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Calendar c = Calendar.getInstance();
		 int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		 if (day_of_week == 0)
			 day_of_week = 7;
		 c.add(Calendar.DATE, -day_of_week + 7);
		 return sdf.format(c.getTime());
	 }
	 
	    /**
	     * 计算与当前日期之间相差的分 
	     * 
	     * @return 相差天数
	     * @throws ParseException
	     */
	    public static long mininuesBetween(String day )   {
	    	Date second = new Date();
	        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date first = null;
	        long minutes = 0;
			try {
				first = sformat.parse(day);
				first = sformat.parse(sformat.format(first));
		        second = sformat.parse(sformat.format(second));
		        Calendar calendar = Calendar.getInstance();
		        calendar.setTime(first);
		        long firstMills = calendar.getTimeInMillis();
		        calendar.setTime(second);
		        long secondMills = calendar.getTimeInMillis();
		        long rateM = 1000 * 60;
		        long mills = secondMills - firstMills;
		        minutes = mills / rateM;
		        System.out.println("时间1:" + sformat.format(first));
		        System.out.println("时间2:" + sformat.format(second));
		        System.out.println("两者相差:" + minutes + "分"  );
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        
	        return minutes;
	    }
	    
	    public static String getAfterDayWeek(String days) {
	    	int daysInt = Integer.parseInt(days);
	    	
	        Calendar canlendar = Calendar.getInstance(); // java.util包
	        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
	        Date date = canlendar.getTime();
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("E");
	        String dateStr = sdf.format(date);
	        
	        return dateStr;
	    }
	    
	    public static String getNextMonthDay(){
	    	Calendar calendar = Calendar.getInstance();
		    Date date = new Date();
		    calendar.setTime(date);
	        calendar.add(Calendar.DATE, -1);
	        calendar.add(Calendar.MONTH, 1);
	        date = calendar.getTime();
	        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
	        String dateStr = sdfd.format(date);
	        return dateStr;
	    }
	     
		
	public static void main(String[] args){
		String price = "1";
		double intprice = Double.parseDouble(price);
		double dprice = intprice/100;
		System.out.println(dprice);
		System.out.println(getMondayOfThisWeek());
		System.out.println(getSundayOfThisWeek());
//		System.out.println("privateKey="+privateKey);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String subtime = sdf.format(new Date(1382694957*1000L));
		System.out.println(subtime);
		mininuesBetween("2018-03-07 16:00:00");
		System.out.println(getNextMonthDay());
		
	}
}
