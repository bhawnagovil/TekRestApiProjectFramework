package com.test.tests;

import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.restTests.POJOs.UserPOJO;
import com.test.helpers.ReusableMethods;
import com.test.helpers.UserServiceHelper;

import io.restassured.response.Response;

public class TekArchApiTestScriptsE2E extends UserServiceHelper {
	
	@Test(priority=1, enabled= true)
   public static void TC1ValidLoginTest(){
	  String token= getToken(); 
	  System.out.println(token);
   }
   
   @Test (priority=2, enabled= true)
   public void TC3getUserData() {
	   List<UserPOJO>ListOfUser= getUserData();
		  System.out.println("first account number ="+ ListOfUser.get(0).getAccountno());  
   }
   
   @Test(priority=3, enabled= true)
   public static  void TC3addUserDataTest() {
	 Response res= addUserData();
	 ReusableMethods.verifyStatusCode(res, 200);
	 String status= ReusableMethods.getJsonPathData(res, "status");
	 AssertJUnit.assertEquals(status, "success");
   }
   
   @Test(priority= 4, enabled= true)
   public static void updateUserDataTest() {
	  Response response = updateUserData(); 
	  ReusableMethods.verifyStatusCode(response, 200);
	  System.out.println("Data updated successfully.......................");
	  String status= ReusableMethods.getJsonPathData(response, "status");
	  AssertJUnit.assertEquals(status, "success");
   }
   @Test(priority= 5, enabled= true)
   public void deleteUserDataTest() {
	   Response response = deleteUserData(); 
	   ReusableMethods.verifyStatusCode(response, 200);
		  System.out.println("Data deleted successfully.......................");
		  String status= ReusableMethods.getJsonPathData(response, "status");
		  AssertJUnit.assertEquals(status, "success");
   }
}
