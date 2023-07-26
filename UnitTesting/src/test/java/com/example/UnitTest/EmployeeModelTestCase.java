package com.example.UnitTest;
public class EmployeeModelTestCase {

@Test
public void testcom.example.UnitTest.EmployeeModel() {
 // Arrange
 // Act
 EmployeeModel instance = new EmployeeModel();
 // Assert
}

@Test
public void testcom.example.UnitTest.EmployeeModel() {
 // Arrange
 Long param0 = 0L;
 int param1 = 0;
 String param2 = null;
 String param3 = null;
 String param4 = null;
 String param5 = null;
 // Act
 EmployeeModel instance = new EmployeeModel(param0, param1, param2, param3, param4, param5);
 // Assert
}

@Test
public void testEmp_id() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 employee.setEmp_id(1234L);
 // Act
 // Assert
 Assert.assertEquals(1234L, employee.getEmp_id());
}

@Test
public void testAge() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 employee.setAge(30);
 // Act
 // Assert
 Assert.assertEquals(30, employee.getAge());
}

@Test
public void testEmployeeFirstName() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 employee.setEmployeeFirstName("John");
 // Act
 // Assert
 Assert.assertEquals("John", employee.getEmployeeFirstName());
}

@Test
public void testEmployeeLastName() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 employee.setEmployeeLastName("John");
 // Act
 // Assert
 Assert.assertEquals("John", employee.getEmployeeLastName());
}

@Test
public void testphoneNumber() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 employee.setPhoneNumber("John");
 // Act
 // Assert
 Assert.assertEquals("John", employee.getPhoneNumber());
}

@Test
public void testEmailID() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 employee.setEmailID("John");
 // Act
 // Assert
 Assert.assertEquals("John", employee.getEmailID());
}

@Test
public void testgetStatusCode() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 Object result = employee.getStatusCode();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testsetPhoneNumber() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 String param0 = null;
 // Act
 void result = employee.setPhoneNumber(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testgetEmailID() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 String result = employee.getEmailID();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testsetEmailID() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 String param0 = null;
 // Act
 void result = employee.setEmailID(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testgetPhoneNumber() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 String result = employee.getPhoneNumber();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testsetAge() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 int param0 = 0;
 // Act
 void result = employee.setAge(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testgetEmp_Id() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 Long result = employee.getEmp_Id();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(0L);
 softAssertions.assertAll();
}

@Test
public void testsetEmp_Id() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 Long param0 = 0L;
 // Act
 void result = employee.setEmp_Id(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testgetAge() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 int result = employee.getAge();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(0);
 softAssertions.assertAll();
}

@Test
public void testgetEmployeeFirstName() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 String result = employee.getEmployeeFirstName();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testsetEmployeeFirstName() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 String param0 = null;
 // Act
 void result = employee.setEmployeeFirstName(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testgetEmployeeLastName() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 // Act
 String result = employee.getEmployeeLastName();
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testsetEmployeeLastName() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 String param0 = null;
 // Act
 void result = employee.setEmployeeLastName(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

}
