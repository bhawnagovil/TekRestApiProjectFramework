package com.test.helpers;

import java.util.concurrent.TimeUnit;

import com.restTests.POJOs.CreateUserPOJO;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class ReusableMethods {
    public static CreateUserPOJO createUser;
	
	
	public int getStatusCode(Response response) {
		return response.getStatusCode();
	}
	
	public static void verifyStatusCode(Response response, int statuscode) {
		response.then().statusCode(statuscode);
	}
	
	public String getContentType(Response response) {
		return response.getContentType();
		
	}
	
	
	public long getResponseTimeIn(Response response, TimeUnit unit) {
		return response.getTimeIn(unit);
	}
	
	public static String getJsonPathData(Response response, String path) {
	return response.jsonPath().get(path);	
	}
	
	public static CreateUserPOJO getUserDataToAdd() {
		createUser= new CreateUserPOJO();
		createUser.setAccountno("TB-YYYUUU");
		createUser.setDepartmentno("1");
		createUser.setPincode("111111");
		createUser.setSalary("1234");
		return createUser;
		
	}
	
	
}
