package com.restTests.POJOs;

public class LoginDataPOJO {
	private String username;
	private String password;
	
	public LoginDataPOJO() {
		System.out.println("Inside the LoginDtaPojo Constructor");
	}
	public LoginDataPOJO (String username, String password){
		this.username= username;
		this.password= password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
