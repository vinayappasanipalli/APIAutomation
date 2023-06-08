package com.AARestAssuredAPI.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestAPI_Utilities {
	
	public static String empName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("infy"+generatedString);
	}
	public static String empSal() {
		String generatedString = RandomStringUtils.randomNumeric(5);
		return("sal"+generatedString);
	}
		
		public static String empAge() {
			String generatedString = RandomStringUtils.randomNumeric(2);
			return("age"+generatedString);
		}
}
