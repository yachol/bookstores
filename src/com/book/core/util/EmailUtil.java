package com.book.core.util;

import java.util.Random;

import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
	//邮箱验证码
		public static boolean sendEmail(String emailaddress,String code){
			try {
				HtmlEmail email = new HtmlEmail();//不用更改
				email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
				email.setCharset("UTF-8");
				email.addTo(emailaddress);// 收件地址
	 
				email.setFrom("1350295310@qq.com", "yy");//此处填邮箱地址和用户名,用户名可以任意填写
	 
				email.setAuthentication("1350295310@qq.com", "tkfoptrankmphhbj");//此处填写邮箱地址和客户端授权码
	 
				email.setSubject("蜗牛书店");//此处填写邮件名，邮件名可任意填写
				email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + code);//此处填写邮件内容
	 
				email.send();
				return true;
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		
		/**
		 * 产生4位随机字符串 
		 */
		public static String getCheckCode() {
			String base = "0123456789ABCDEFGabcdefg";
			int size = base.length();
			Random r = new Random();
			StringBuffer sb = new StringBuffer();
			for(int i=1;i<=4;i++){
				//产生0到size-1的随机值
				int index = r.nextInt(size);
				//在base字符串中获取下标为index的字符
				char c = base.charAt(index);
				//将c放入到StringBuffer中去
				sb.append(c);
			}
			return sb.toString();
		}
}

