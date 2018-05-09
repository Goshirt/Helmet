package com.helmet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.helmet.util.StringUtil;

public class DateUtil {
	
	/**
	 * 把日期类转为字符串格式
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formateDateToString(Date date,String format) {
		String result="";
		SimpleDateFormat sFormat=new SimpleDateFormat(format);
		if (date!=null) {
			result=sFormat.format(date);
		}
		return result;
	}
	
	/**
	 * 把字符串转为日期格式
	 * @param sourceStr
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date formatStringToDate(String sourceStr,String format) throws ParseException {
		if (StringUtil.isEmpty(sourceStr)) {
			return null;
		}
		SimpleDateFormat sFormat=new SimpleDateFormat(format);
		return sFormat.parse(sourceStr);
	}
	
	/**
	 * 获得当前时间的字符串表示
	 * @param format
	 * @return
	 */
	public static String getNowTimeString(String format) {
		SimpleDateFormat sFormat=new SimpleDateFormat(format);
		return sFormat.format(new Date());
	}
}
