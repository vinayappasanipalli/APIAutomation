package com.AARestAssuredAPI.utilities;

import java.util.Properties;
import java.io.FileReader;

public class ReadpropertyFile {
	public static void main() throws Throwable {
		FileReader fr = new FileReader(
				"D:\\Vinay\\Associate_Architect\\AutomationFramework\\AA_Framework\\RestAssuredAPIAutomation\\src\\test\\java\\com\\AARestAssuredAPI\\utilities\\ReadpropertyFile.java");
		Properties p = new Properties();
		p.load(fr);
	}

}
