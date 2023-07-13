package com.restTests.POJOs;

import java.util.HashMap;
import java.util.Map;

public class UserPOJO {

	private String accountno;
    private String departmentno;
    private String salary;
    private String pincode;
    private String userid;
    private String id;
    private Map<String, Object >additionalProperties= new HashMap<String, Object>();
    
    public String getAccountno() {
        return accountno;
    }
    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }
    public String getDepartmentno() {
        return departmentno;
    }
    public void setDepartmentno(String departmentno) {
        this.departmentno = departmentno;
    }
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    public void setUserid(String userid) {
    	this.userid= userid;
    }
    public String getUserid() {
    	return userid;
    }
   
}

