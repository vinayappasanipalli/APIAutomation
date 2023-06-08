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

public class Put_Update_EmployeeDetails extends BaseDriver {
	
	RequestSpecification httpRequest;
	Response response;
	String empName = RestAPI_Utilities.empName();
	String empSalary = RestAPI_Utilities.empSal();
	String empAge = RestAPI_Utilities.empAge();
	
	@BeforeClass
	 void updateEmployeeDetails() throws InterruptedException {
		logger.info("**** TC#4- Started Test Case Update Employee Details  into the Database ******");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.request(Method.PUT, "/update/"+empID);
		Thread.sleep(8000);
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
		logger.info("********This Method is to check the status COde of the Response");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==" + statusCode);
		Assert.assertEquals(statusCode,200);
	}
	@AfterClass
	public void tearDown() {
		logger.info("*******TC#4 is Finished******");
	}

}
