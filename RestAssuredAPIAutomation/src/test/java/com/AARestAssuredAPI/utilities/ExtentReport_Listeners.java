package com.AARestAssuredAPI.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport_Listeners extends TestListenerAdapter {
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext)
	{
		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"//src//test//resources//reports//APIReport.html");
		htmlReporter.config().setDocumentTitle("RestAssured API Test Automation Report");
		htmlReporter.config().setReportName("API Test Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name","Associate Architect - API Test Framework");
		extent.setSystemInfo("Host Name","Localhost");
		extent.setSystemInfo("Enviornment","QA Instance");
		extent.setSystemInfo("User","Vinay");
		
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.log(Status.PASS,"Test Case PASSED IS " + result.getName());
	}
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL,"Test Case Failed IS" + result.getName());
		test.log(Status.FAIL,"Test Case Failed IS" +result.getThrowable());
	}
	public void onTestSkip(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.log(Status.SKIP,"Test Case SKIPED IS " + result.getName());
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
