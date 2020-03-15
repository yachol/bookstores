package com.book.core.util;

import java.net.URL;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		Date date = new Date();
		Class<? extends Date> clazz = date.getClass();
		URL resource = clazz.getResource("/");//gen
		System.out.println(resource);
		URL resource2 = Test.class.getResource("");//bao
		System.out.println(resource2);
		URL resource3 = Test.class.getResource("/");//gen
		System.out.println(resource3);
		
		 ClassLoader classLoader = Test.class.getClassLoader();
		 System.out.println(classLoader);
		 URL resource4 = classLoader.getResource("");
		 String path = resource4.getPath();
		 System.out.println(path);
		 URL resource5 = classLoader.getResource("/");
		 String path2 = resource5.getPath();
		 System.out.println(path2);
		 
		/*
		 * file:/D:/javacode/eclipse-webwork/Mvc/build/classes/
		 * file:/D:/javacode/eclipse-webwork/Mvc/build/classes/com/woniu/core/util/
		 * file:/D:/javacode/eclipse-webwork/Mvc/build/classes/
		 * sun.misc.Launcher$AppClassLoader@73d16e93
		 * /D:/javacode/eclipse-webwork/Mvc/build/classes/ Exception in thread "main"
		 * java.lang.NullPointerException at com.woniu.core.util.Test.main(Test.java:23)
		 */
	}
}
