package com.AARestAssuredAPI.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.AARestAssuredAPI.base.BaseDriver;
import com.AARestAssuredAPI.utilities.RestAPI_Utilities;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post_NewRecord_intoDatabase extends BaseDriver {

	RequestSpecification httpRequest;
	Response response;
	String empName = RestAPI_Utilities.empName();
	String empSalary = RestAPI_Utilities.empSal();
	String empAge = RestAPI_Utilities.empAge();

	@BeforeClass
	public void createEmployee() throws InterruptedException {
		logger.info("**** TC#3- Started Test Case Post New Record into the Database ******");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.request(Method.POST, "/create");
		Thread.sleep(5000);
	}

	@Test
	public void checkResponseBody() {
		logger.info("*******This Method is Check the Request Response Body****");
		String responseBody = response.getBody().asString();
		logger.info("Response Body = " + responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
	}

	@Test
	public void checkStatusCode() {
		logger.info("*******This Method is Check the Request Status Code****");
		int statuscode = response.getStatusCode();
		logger.info("Status Code = " + statuscode);
		Assert.assertEquals(statuscode, 200);
	}

	@Test
	public void checkResponseTime() {
		logger.info("This Method is to check the Response Time of the Request");
		long responseTime = response.getTime();
		logger.info("Response Time is ==" + responseTime);
		if (responseTime > 3000) {
			logger.warn("Response Time is morethan 3000");
			Assert.assertTrue(responseTime < 3000);
		}
	}

	@Test
	public void checkstatusLine() {
		logger.info("This Method is to check the Response's Status Line");
		String statusline = response.getStatusLine();
		logger.info("Response's Status Line =" + statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}

	@Test
	public void checkContentType() {
		logger.info("This Method is to check the Status line of the Request");
		// String contentType = response.header("Content-Type");
		String contentType = response.header("Content-TYpe");
		logger.info("Content-Type is == " + contentType);
		Assert.assertEquals(contentType, "text/html;chartset=UTF-8");

	}

	@Test

	public void checkserverType() {
		logger.info("This Method is to check the Server Type of the Request");
		String servertype = response.header("Server");
		logger.info("Server Type == " + servertype);
		Assert.assertEquals(servertype, "nginx/1.21.6");
	}

	@Test
	public void checkcontentLength() {
		logger.info("This Method is to check the Content Lenght of the Request");
		String contentlength = response.header("Content-Length");
		logger.info("Content-Length is == " + contentlength);
		Assert.assertTrue(Integer.parseInt(contentlength) < 1500);

	}

	@Test
	public void checkcontentEncoding() {
		logger.info("This Method is to check the Content Encoding of the Request ");
		String contentencoding = response.header("Content-Encoding");
		logger.info("Content-Encoding==" + contentencoding);
		Assert.assertEquals(contentencoding, "gzip");
	}

	@AfterClass
	public void tearDown() {
		logger.info("*******TC#3 - Finished TestCase Post_NewRecord_intoDatabase*******");
	}

}
