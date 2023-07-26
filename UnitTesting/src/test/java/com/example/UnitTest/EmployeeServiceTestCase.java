package com.example.UnitTest;
public class EmployeeServiceTestCase {

@Test
public void testcom.example.UnitTest.EmployeeService() {
 // Arrange
 // Act
 EmployeeService instance = new EmployeeService();
 // Assert
}

@Test
public void testemployeeRepository() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 employee.setEmployeeRepository(null);
 // Act
 // Assert
 Assert.assertEquals(null, employee.getEmployeeRepository());
}

@Test
public void testdeleteEmployee() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 EmployeeModel param0 = null;
 // Act
 EmployeeModel result = employee.deleteEmployee(param0);
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
public void testemployeeRepository() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 void result = employee.employeeRepository();
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
