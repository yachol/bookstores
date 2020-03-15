package com.book.core.util;

import javax.servlet.http.Cookie;

public class CookieUtil {
	/**
	 *   从 cookie数组中找出指定名称的cookie
	 * @param name
	 * @param cookies
	 * @return
	 */
	public static Cookie getCookie(String name,Cookie[] cookies) {
		if(name==null || cookies==null || "".equals(name)) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if(name.equals(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}
}
