package com.restTests.simpleTests;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class TekArchLoginAPITest {
	String extractedToken= null;
	@BeforeClass
  public void init() {
	  RestAssured.baseURI= "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
  }
	@Test
	public  void  loginToApi() {
		
	
		Response res = RestAssured.given().contentType(ContentType.JSON)
				.body("{\"username\":\"bhawna.govil@gmail.com\",\"password\":\"Tekarch@123\"}").when()
				.post("login");

		res.then().statusCode(201).contentType(ContentType.JSON).time(Matchers.lessThan(5000L));

		String extractedToken = res.body().jsonPath().getString("[0].token");
		System.out.println("token=" + extractedToken);
		
	}

	@Test(dependsOnMethods = "loginToApi")
	public void getUsers() {
		Header ob = new Header("token", extractedToken);
		Response res = RestAssured.given().header(ob).when()
				.get("getdata");

		Assert.assertEquals(res.getStatusCode(), 200) ;
		 Assert.assertEquals(res.getContentType(), "application/json");

		 //Assert.asserthat(time(Matchers.lessThan(10000L)));

		res.prettyPrint();
		int totalrecord = res.body().jsonPath().get("size()");
		System.out.println("total number of the records " + totalrecord);
		int totalRecords = res.body().jsonPath().get("size()");
		Assert.assertTrue(totalRecords < 10000, "Number of records is not less than 5000");
		String extractedMinSalary = res.body().jsonPath().getString("[0].salary.min()");
		String extractedMaxSalary = res.body().jsonPath().getString("[0].salary.max()");
		// String responseBody = res.getBody().asString();
		// List<String>accountNumbers= res.body().jsonPath().getList("findAll { account
		// -> account.userid == 'lzQHg4ywe0MI87vM7fpF' }.accountno");
		List<String> accountNumbersForUser  = new ArrayList<>();
		List<Object> accounts = res.body().jsonPath().getList("[*]");
		  // Find the total number of records matching the specified user ID
		for (Object account : accounts) {
			String userId = res.body().jsonPath().getString("userid");
			if ("lzQHg4ywe0MI87vM7fpF".equals(userId)) {
				String accountNumber = res.body().jsonPath().getString("accountno");
				int numberOfAccountwithuserid= res.body().jsonPath().get("size()");
				accountNumbersForUser .add(accountNumber);
			}

			// Print the account numbers
			int totalRecordsForUser = accountNumbersForUser.size();
			for (String accountNumber : accountNumbersForUser) {
				System.out.println(accountNumber);
			}
			  // Find the total number of records with department number 5
			 String deptNoToFind = "5";
		        int totalRecordsWithDeptNo = 0;
		        for (Object accountFordept : accounts) {
		            String deptNo = res.body().jsonPath().getString("[*].departmentno");
		            if (deptNoToFind.equals(deptNo)) {
		                totalRecordsWithDeptNo++;
		            }
		        }
		        System.out.println("Total Records with Department Number " + deptNoToFind + ": " + totalRecordsWithDeptNo);
		        // Find the total salary of all records for the specified user ID
		        int totalSalaryForUser = 0;
		        String userIdToFind= "lzQHg4ywe0MI87vM7fpF";
		        for (Object accountForSalary : accounts) {
		            String alluserId = res.body().jsonPath().getString("[*].userid");
		            if (alluserId.equals(userIdToFind)) {
		                String salaryString =res.body().jsonPath().getString("[*].salary");
		                int salary = Integer.parseInt(salaryString);
		                totalSalaryForUser += salary;
		            }
		            System.out.println("Total Salary for User " + userIdToFind + ": " + totalSalaryForUser);
		            
			
		}
		
		

	}

}
	
}
