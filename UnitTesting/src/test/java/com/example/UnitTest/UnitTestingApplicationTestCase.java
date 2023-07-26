package com.example.UnitTest;
public class UnitTestingApplicationTestCase {

@Test
public void testcom.example.UnitTest.UnitTestingApplication() {
 // Arrange
 // Act
 UnitTestingApplication instance = new UnitTestingApplication();
 // Assert
}

@Test
public void testmain() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 String[] param0 = null;
 // Act
 void result = employee.main(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

}
