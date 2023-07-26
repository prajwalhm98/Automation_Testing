package com.example.UnitTest;
public class TestGenerationTestCase {

@Test
public void testcom.example.UnitTest.TestGeneration() {
 // Arrange
 // Act
 TestGeneration instance = new TestGeneration();
 // Assert
}

@Test
public void testINPUT_FILE() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 employee.setINPUT_FILE("John");
 // Act
 // Assert
 Assert.assertEquals("John", employee.getINPUT_FILE());
}

@Test
public void testOUTPUT_DIRECTORY() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 employee.setOUTPUT_DIRECTORY("John");
 // Act
 // Assert
 Assert.assertEquals("John", employee.getOUTPUT_DIRECTORY());
}

@Test
public void testgenerateTestCasesFromReflectionsData() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 String param0 = null;
 // Act
 void result = employee.generateTestCasesFromReflectionsData(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testgenerateFieldAssertions() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 Class param0 = null;
 Field[] param1 = null;
 StringBuilder param2 = null;
 // Act
 void result = employee.generateFieldAssertions(param0, param1, param2);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testscanAndSaveReflectionsData() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 String param0 = null;
 String param1 = null;
 // Act
 void result = employee.scanAndSaveReflectionsData(param0, param1);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testgenerateMethodAssertions() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 Class param0 = null;
 Method[] param1 = null;
 StringBuilder param2 = null;
 // Act
 void result = employee.generateMethodAssertions(param0, param1, param2);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testgenerateReflectionsData() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 Class param0 = null;
 // Act
 void result = employee.generateReflectionsData(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testgenerateTestCases() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 Class param0 = null;
 // Act
 void result = employee.generateTestCases(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testgenerateTestCases() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 String param0 = null;
 // Act
 void result = employee.generateTestCases(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertAll();
}

@Test
public void testcapitalize() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 String param0 = null;
 // Act
 String result = employee.capitalize(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

@Test
public void testgetDefaultValue() {
 // Arrange
 EmployeeModel employee = new EmployeeModel();
 Class param0 = null;
 // Act
 String result = employee.getDefaultValue(param0);
 // Assert
 SoftAssertions softAssertions = new SoftAssertions();
 softAssertions.assertThat(result).isEqualTo(null);
 softAssertions.assertAll();
}

}
