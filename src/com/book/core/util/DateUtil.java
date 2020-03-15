package com.book.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String dateMy(Date date) {
		return new SimpleDateFormat("yyyy年MM月").format(date);
	}
}
