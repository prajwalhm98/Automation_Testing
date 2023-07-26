package com.example.UnitTest;



import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name="employee")
public class EmployeeModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 Long Emp_id;
	
	int Age;

	 String EmployeeFirstName;

	 String EmployeeLastName;
	
	 String phoneNumber;
	
	 String EmailID;

	public Long getEmp_Id() {
		return Emp_id;
	}

	public void setEmp_Id(Long Emp_id) {
		this.Emp_id = Emp_id;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		this.Age = age;
	}

	public String getEmployeeFirstName() {
		return EmployeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.EmployeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return EmployeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.EmployeeLastName = employeeLastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailID() {
		return EmailID;
	}

	public void setEmailID(String emailID) {
		this.EmailID = emailID;
	}

	public EmployeeModel(Long Emp_id, int age, String employeeFirstName, String employeeLastName, String phoneNumber,
			String emailID) {
		super();
		this.Emp_id = Emp_id;
		this.Age = age;
		this.EmployeeFirstName = employeeFirstName;
		this.EmployeeLastName = employeeLastName;
		this.phoneNumber = phoneNumber;
		this.EmailID = emailID;
	}

	public EmployeeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Object getStatusCode() {
		// TODO Auto-generated method stub
		return null;
	}


	
}

