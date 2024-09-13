package com.ktpl.mitadt.comon;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {

	/**/

	public static enum MoldListCatagoryStatus {

		LIFE(1), MAINTANCE(2), LOCATION(3);

		private final int value;

		MoldListCatagoryStatus(final int newValue) {
			value = newValue;
		}

		public int getValue() {
			return value;
		}

	}

	/** Static Logger object */

	public static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

	public static Date convertLongToDate(Long longDate) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(longDate);
		return calendar.getTime();
	}

//		public static Date getUtcDate() {
//			Date utcDate = new Date();
//			try {
	//
//				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//
//				simpleDateFormat.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
	//
//				utcDate = simpleDateFormat
//						.parse(simpleDateFormat.format(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime()));
	//
//				return utcDate;
	//
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			return utcDate;
//		}

	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}

	/*
	 * public static Integer getUserRole(char type) { Integer userRoleId = null;
	 * switch(type) { case 'A' : userRoleId = AppConstant.ORG_ADMIN_USER; break;
	 * case 'U' : userRoleId =AppConstant.ORG_USER; break; } return userRoleId; }
	 */

	/**
	 * generatePassword():method to auto-generate password
	 * 
	 * @return String
	 */
	public static String generatePassword() {
		final String ALLOWED_PASSWORD_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!$%*";
		SecureRandom randomNo = new SecureRandom();
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < 8; i++) {
			result.append(ALLOWED_PASSWORD_CHARS.charAt(randomNo.nextInt(ALLOWED_PASSWORD_CHARS.length())));
		}

		generateHash(result.toString());

		return result.toString();
	}

	public static Date getDateFromDateUIPickerStringDate(String date) {

		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date1;
	}

	public static Date getFormattedDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

}