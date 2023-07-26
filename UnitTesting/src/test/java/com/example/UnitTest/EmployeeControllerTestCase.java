package com.example.UnitTest;

import java.util.List;

import org.assertj.core.api.Assert;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class EmployeeControllerTestCase {

@Test
public void testEmployeeController() {
 // Arrange
 // Act
 EmployeeController instance = new EmployeeController();
 // Assert
}

@Test
public void testemployeeService() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 employee.setEmployeeService(null);
 // Act
 // Assert
 Assert.assertEquals(null, employee.getEmployeeService());
}

@Test
public void testdeleteEmployee() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 Long param0 = 0L;
 EmployeeModel param1 = null;
 // Act
 ResponseEntity result = employee.deleteEmployee(param0, param1);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testupdateEmployee() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 Long param0 = 0L;
 EmployeeModel param1 = null;
 // Act
 EmployeeModel result = employee.updateEmployee(param0, param1);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testsetPhoneNumber() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 ResponseEntity result = employee.setPhoneNumber();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testsetEmailID() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 ResponseEntity result = employee.setEmailID();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testsetAge() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 ResponseEntity result = employee.setAge();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testsetEmp_Id() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 ResponseEntity result = employee.setEmp_Id();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testsetEmployeeFirstName() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 ResponseEntity result = employee.setEmployeeFirstName();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testsetEmployeeService() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 Object param0 = null;
 // Act
 void result = employee.setEmployeeService(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testgetAllEmployee() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 List result = employee.getAllEmployee();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testgetEmployeeById() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 Long param0 = 0L;
 // Act
 EmployeeModel result = employee.getEmployeeById(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testcreateEmployee() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 EmployeeModel param0 = null;
 // Act
 EmployeeModel result = employee.createEmployee(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

}
