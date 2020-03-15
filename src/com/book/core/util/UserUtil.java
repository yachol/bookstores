package com.book.core.util;

import java.util.regex.Pattern;

public class UserUtil {
	/**
	 * 1. 用户名格式校验
	 * @param username
	 * @return
	 */
	public static boolean checkUser(String username) {
		if(username == null) 
			return false;
		return Pattern.matches("^[0-9a-zA-Z\\u4e00-\\u9fa5_]{1,16}$", username);
	}
	/**
	 * 2.用户密码非空校验
	 * @param password
	 * @return
	 */
	public static boolean checkPassword(String password) {
		return password!=null && !"".equals(password);
	}
	/**
	 * 3.用户手机号格式校验
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobile(String mobile) {
		if(mobile == null) 
			return false;
		return Pattern.matches("^1[3-9]\\d{9}$", mobile);
	}
	
	/**
	 * 4.用户邮箱号格式校验
	 * @param mobile
	 * @return
	 */
	public static boolean checkEmail(String email) {
		if(email == null) 
			return false;
		return Pattern.matches("^[a-zA-Z1-9][a-zA-Z0-9_]*@[a-zA-Z0-9]+\\.[a-zA-Z]+$", email);
	}
	/**
	 * 5. 用户身份证号格式校验
	 * @param idcard
	 * @return
	 */
	public static boolean checkIdcard(String idcard) {
		if(idcard == null) 
			return false;
		return Pattern.matches("(^[1-9]\\d{14}$)|(^[1-9]\\d{16}[0-9Xx]$)", idcard);
	}
	
	public static boolean checkCompany(String company) {
		if(company == null) 
			return false;
		return company.length()<=35&&company.length()>=1;
	}
	/**
	 * 6. 用户类型校验
	 * @param utype
	 * @return
	 */
	public static boolean checkUserType(int utype) {
		return utype==0 || utype==1;
	}
}
