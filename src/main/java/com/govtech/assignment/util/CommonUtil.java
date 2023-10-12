package com.govtech.assignment.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import com.govtech.assignment.constant.AppConstants;

public class CommonUtil {

	static MessageDigest md;

	public static Date getCurrentGMTDate() {
		Date date = new Date();
		TimeZone.setDefault(TimeZone.getTimeZone(AppConstants.TIMEZONE_GMT));
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		date = cal.getTime();
		return date;
	}

	public static String generateId() {
		return UUID.randomUUID().toString();
	}

	public static byte[] generateSalt() {
		byte[] salt = new byte[16];
		SecureRandom random = null;
		try {
			md = MessageDigest.getInstance(AppConstants.SHA_256_ALGO_NAME);
			random = new SecureRandom();
			random.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return salt;
	}

	public static String hash(String password, byte[] salt) {
		byte[] hashedPassword = null;
		StringBuilder passwordHash = null;
		try {
			md = MessageDigest.getInstance(AppConstants.SHA_256_ALGO_NAME);
			md.update(salt);
			hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			passwordHash = new StringBuilder();
			for (byte b : hashedPassword)
				passwordHash.append(String.format("%02x", b));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return passwordHash.toString();
	}

	public static String encodeSalt(byte[] salt) {
		return Base64.getEncoder().encodeToString(salt);
	}

	public static byte[] decodeSalt(String encodedSalt) {
		return Base64.getDecoder().decode(encodedSalt);
	}
}
