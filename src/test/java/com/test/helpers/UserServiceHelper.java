package com.test.helpers;

import java.util.Arrays;
import java.util.List;

import com.restTests.POJOs.CreateUserPOJO;
import com.restTests.POJOs.LoginDataPOJO;
import com.restTests.POJOs.UserPOJO;
import com.test.constants.Endpoints;
import com.tests.utils.PropertiesUtility;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class UserServiceHelper {
	public static Response response;
	public static String token;
	
  public String getBaseUri(){
	  String baseURI= PropertiesUtility.getConfigProperties("baseUrl");
	  return baseURI;
  }
  
  public static Response LoginToApplication() {
	  String username= PropertiesUtility.getConfigProperties("username");
	  String password= PropertiesUtility.getConfigProperties("password");
	  LoginDataPOJO ob= new LoginDataPOJO(username, password );
	  response= RestAssured.given().contentType("application/json").body(ob).when().post(Endpoints.Login);
	  return response;
	  
  }
  public static String getToken() {
	  response = LoginToApplication();
	  token= response.jsonPath().get("[0].token");
	  return token;
  }
   
  public static List<UserPOJO> getUserData() {
	Header header= new Header("token",token) ;
	System.out.println("user data token====="+token);
	response= RestAssured.given().header(header).when().get(Endpoints.Get_Data);
	UserPOJO[] userArray= response.as(UserPOJO[].class);
	List<UserPOJO>list= Arrays.asList(userArray);
	response.then().statusCode(200);
	System.out.println("number of records = "+ response.jsonPath().get("$.size()"));
	return list;
	
	  
	
	  
  }
  
  public static Response addUserData() {
	  Header header= new Header("token", token);
	  CreateUserPOJO user=  ReusableMethods.getUserDataToAdd();
	  response= RestAssured.given().contentType("application/json").header(header).body(user).when()
			  .post(Endpoints.ADD_DATA);
	  return response;
  }
  
  public static Response updateUserData() {
	 UserPOJO updatedUser= new UserPOJO();
	 List<UserPOJO>listOfUsers= getUserData();
	 for(UserPOJO userToBeUpdated: listOfUsers) {
		 if(userToBeUpdated.getAccountno().equals(ReusableMethods.createUser.getAccountno())){
			 updatedUser= userToBeUpdated;
			 updatedUser.setDepartmentno("5");
			 break;
			 
		 }
	 }
	 
	 Header header= new Header("token", token);
	 response= RestAssured.given().contentType("application/json").header(header).body(updatedUser).when()
			 .put(Endpoints.UPDATE_DATA);
	 return response;
	 
  }

  public static  Response deleteUserData() {
	  String id= null;
	  String userId= null;
	  if(token==null) {
		  getToken();
	  }
	  UserPOJO delteUser= new UserPOJO();
	  List<UserPOJO>listOfUsers= getUserData();
	  for(UserPOJO userToBeDeleted: listOfUsers) {
			 if(userToBeDeleted.getAccountno().equals(ReusableMethods.createUser.getAccountno())){
				 delteUser= userToBeDeleted;
	 
  }
  
	  }
	  return response; 
  }
}
