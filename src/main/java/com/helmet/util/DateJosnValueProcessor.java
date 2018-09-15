package com.helmet.util;

import java.text.SimpleDateFormat;


import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 把Date 类型转换为Json接收的字符串格式
 * 
 * @author Helmet
 * 2018年5月10日
 */
public class DateJosnValueProcessor implements JsonValueProcessor{
	
	//转换成的字符串显示格式
	private String pattern;
	public DateJosnValueProcessor(String pattern) {
		this.pattern=pattern;
	}
	
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return null;
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		if (value==null) {
			return "";
		}
			
		if (value instanceof java.sql.Timestamp) {
			String string=new SimpleDateFormat(pattern).format((java.sql.Timestamp)value);
			return string;
		}
		if (value instanceof java.util.Date) {
			String string=new SimpleDateFormat(pattern).format((java.util.Date)value);
			return string;
		}
		return value.toString();
	}
	
}
