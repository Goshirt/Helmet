package com.helmet.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 实体转为json格式
 * @author Administrator
 *
 */
public class ObjectJsonValueProcessor implements JsonValueProcessor{

	/*
	 * 实体中需要在json中显示的属性
	 */
	private String[] properties;  
	
	/*
	 * 实体类，反射
	 */
	private Class<?> clazz;  
	
	
	public ObjectJsonValueProcessor(String[] properties,Class<?> clazz){  
        this.properties = properties;  
        this.clazz =clazz;  
    }  
	
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		PropertyDescriptor pd = null;  
        Method method = null;  
        StringBuffer json = new StringBuffer("{");  
        try{  
            for(int i=0;i<properties.length;i++){  
                pd = new PropertyDescriptor(properties[i], clazz);  
                method = pd.getReadMethod();  
                String v = String.valueOf(method.invoke(value));  
                json.append("'"+properties[i]+"':'"+v+"'");  
                json.append(i != properties.length-1?",":"");  
            }  
            json.append("}");  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
        return JSONObject.fromObject(json.toString());  
	}

}
