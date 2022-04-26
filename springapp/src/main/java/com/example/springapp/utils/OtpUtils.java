package com.example.springapp.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class OtpUtils {
	
	public String generateOtp() {
		int length = 10;
	    boolean useLetters = true;
	    boolean useNumbers = true;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	    return generatedString;
	}
}

