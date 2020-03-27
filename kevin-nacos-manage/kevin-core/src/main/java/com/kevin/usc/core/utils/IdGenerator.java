package com.kevin.usc.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
	private static AtomicInteger ID = new AtomicInteger(0);

	/**
	 * 流水号.
	 *
	 * @return String
	 */
	public synchronized static String createOrderId(String prefix) {
		if (ID.intValue() > 9999) {
			ID.set(0);
		}
		String str = getYMD() + String.format("%04d", ID.getAndIncrement());
		if (prefix != null) {
			return prefix + str;
		} else {
			return str;
		}
	}

	/**
	 * Method getYMD.
	 *
	 * @return String
	 */
	private static String getYMD() {
		SimpleDateFormat fymd = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		return fymd.format(date);
	}
}
