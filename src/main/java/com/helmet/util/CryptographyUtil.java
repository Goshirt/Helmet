package com.helmet.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具
 * 
 * @author Helmet
 * 2018年5月2日
 */
public class CryptographyUtil {

	
	/**
	 * Md5加密
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	public static void main(String[] args) {
		String password="123456";
		
		System.out.println("Md5加密"+CryptographyUtil.md5(password, "helmet"));
	}
}
