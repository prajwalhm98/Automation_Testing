package com.example.UnitTest;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class TestGeneration {
	static String INPUT_FILE = "C:\\Users\\241359\\Documents\\workspace-spring-tool-suite-4-4.17.1.RELEASE\\UnitTesting.txt";
	static String OUTPUT_DIRECTORY = "C:\\Users\\241359\\Documents\\workspace-spring-tool-suite-4-4.17.1.RELEASE\\UnitTesting\\src\\test\\java\\com\\example\\UnitTest";

	public void generateTestCases(String basePackage) {
		String reflectionsOutputFile = INPUT_FILE;
		scanAndSaveReflectionsData(basePackage, reflectionsOutputFile);
		generateTestCasesFromReflectionsData(reflectionsOutputFile);
	}

	private void scanAndSaveReflectionsData(String basePackage, String outputFile) {
		try {
			Reflections reflections = new Reflections(basePackage, new SubTypesScanner(false));
			Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
			FileOutputStream fos = new FileOutputStream(outputFile);
			PrintStream ps = new PrintStream(fos);

			System.setOut(ps);
			for (Class<?> targetClass : classes) {
				generateReflectionsData(targetClass);
			}

			ps.close();
			fos.close();

			System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateReflectionsData(Class<?> targetClass) {

		System.out.println("Class: " + targetClass.getName());

		if (targetClass.isArray()) {
			Class<?> componentType = targetClass.getComponentType();
			System.out.println("Array Component Type: " + componentType.getName());
		}

		Constructor<?>[] constructors = targetClass.getDeclaredConstructors();
		System.out.println("Constructors:");
		for (Constructor<?> constructor : constructors) {
			System.out.println(constructor);
		}

		Field[] fields = targetClass.getDeclaredFields();
		System.out.println("Fields:");
		for (Field field : fields) {
			System.out.println(field);
		}

		Method[] methods = targetClass.getDeclaredMethods();
		System.out.println("Methods:");
		for (Method method : methods) {
			System.out.println(method);
		}
		System.out.println("_______________________");
	}

	private void generateTestCasesFromReflectionsData(String reflectionsOutputFile) {
		try {
			File file = new File(reflectionsOutputFile);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.startsWith("Class: ")) {
					String className = line.substring(7);
					Class<?> targetClass = Class.forName(className);
					generateTestCases(targetClass);
				}
			}
			scanner.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void generateTestCases(Class<?> targetClass) {
		StringBuilder testCaseBuilder = new StringBuilder();
		testCaseBuilder.append("package com.example.UnitTest;\n");
		//testCaseBuilder.append("import org.junit.Test;\n");
		//testCaseBuilder.append("import static org.assertj.core.api.Assertions.*;\n\n");
	//	 testCaseBuilder.append("import ").append(targetClass.getName()).append(";\n\n");
		testCaseBuilder.append("public class ").append(targetClass.getSimpleName()).append("TestCase {\n\n");

		Constructor<?>[] constructors = targetClass.getDeclaredConstructors();
		Field[] fields = targetClass.getDeclaredFields();
		Method[] methods = targetClass.getDeclaredMethods();

		for (Constructor<?> constructor : constructors) {
			String constructorName = constructor.getName();
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			testCaseBuilder.append("@Test\n");
			testCaseBuilder.append("public void test").append(constructorName).append("() {\n");
			testCaseBuilder.append(" // Arrange\n");

			for (int i = 0; i < parameterTypes.length; i++) {
				Class<?> parameterType = parameterTypes[i];
				String defaultValue = getDefaultValue(parameterType);
				testCaseBuilder.append(" ").append(parameterType.getSimpleName()).append(" param").append(i)
						.append(" = ").append(defaultValue).append(";\n");
			}
			testCaseBuilder.append(" // Act\n");
			testCaseBuilder.append(" ").append(targetClass.getSimpleName()).append(" instance = new ")
					.append(targetClass.getSimpleName()).append("(");
			for (int i = 0; i < parameterTypes.length; i++) {
				testCaseBuilder.append("param").append(i);
				if (i < parameterTypes.length - 1) {
					testCaseBuilder.append(", ");
				}
			}
			testCaseBuilder.append(");\n");
			testCaseBuilder.append(" // Assert\n");

			testCaseBuilder.append("}\n\n");
		}

		generateFieldAssertions(targetClass, fields, testCaseBuilder);

		generateMethodAssertions(targetClass, methods, testCaseBuilder);
		testCaseBuilder.append("}\n");

		String outputFilePath = OUTPUT_DIRECTORY + File.separator + targetClass.getSimpleName() + "TestCase.java";
		try (PrintWriter writer = new PrintWriter(outputFilePath)) {
			writer.write(testCaseBuilder.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void generateFieldAssertions(Class<?> targetClass, Field[] fields, StringBuilder testCaseBuilder) {
		for (Field field : fields) {
			String fieldName = field.getName();
			Class<?> fieldType = field.getType();
			testCaseBuilder.append("@Test\n");
			testCaseBuilder.append("public void test").append(fieldName).append("() {\n");
			testCaseBuilder.append(" // Arrange\n");
			testCaseBuilder.append(" EmployeeModel employee = new EmployeeModel();\n");

			String fieldValue;
			if (fieldType.equals(String.class)) {
				fieldValue = "\"John\"";
			} else if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
				fieldValue = "30";
			} else if (fieldType.equals(Long.class)) {
				fieldValue = "1234L";
			} else {

				fieldValue = "null";
			}
			testCaseBuilder.append(" employee.set").append(capitalize(fieldName)).append("(").append(fieldValue)
					.append(");\n");
			testCaseBuilder.append(" // Act\n");

			testCaseBuilder.append(" // Assert\n");
			testCaseBuilder.append(" Assert.assertEquals(").append(fieldValue).append(", employee.get")
					.append(capitalize(fieldName)).append("());\n");
			testCaseBuilder.append("}\n\n");
		}
	}


	private void generateMethodAssertions(Class<?> targetClass, Method[] methods, StringBuilder testCaseBuilder) {
		for (Method method : methods) {
			String methodName = method.getName();
			Class<?>[] parameterTypes = method.getParameterTypes();
			Class<?> returnType = method.getReturnType();
			testCaseBuilder.append("@Test\n");
			testCaseBuilder.append("public void test").append(methodName).append("() {\n");
			testCaseBuilder.append(" // Arrange\n");
			testCaseBuilder.append(" EmployeeModel employee = new EmployeeModel();\n");

			StringBuilder parametersBuilder = new StringBuilder();
			for (int i = 0; i < parameterTypes.length; i++) {
				Class<?> parameterType = parameterTypes[i];
				String defaultValue = getDefaultValue(parameterType);
				String parameterName = "param" + i;
				testCaseBuilder.append(" ").append(parameterType.getSimpleName()).append(" ").append(parameterName)
						.append(" = ").append(defaultValue).append(";\n");
				parametersBuilder.append(parameterName);
				if (i < parameterTypes.length - 1) {
					parametersBuilder.append(", ");
				}
			}
			testCaseBuilder.append(" // Act\n");
			testCaseBuilder.append(" ").append(returnType.getSimpleName()).append(" result = employee.")
					.append(methodName).append("(").append(parametersBuilder.toString()).append(");\n");
			testCaseBuilder.append(" // Assert\n");
			testCaseBuilder.append(" SoftAssertions softAssertions = new SoftAssertions();\n");
			if (!returnType.equals(void.class)) {
				String returnValue = getDefaultValue(returnType);
				testCaseBuilder.append(" softAssertions.assertThat(result).isEqualTo(").append(returnValue)
						.append(");\n");
			}

			testCaseBuilder.append(" softAssertions.assertAll();\n");
			testCaseBuilder.append("}\n\n");
		}
	}

// private void generateAssertions(Class<?> targetClass, Field field, StringBuilder testCaseBuilder) {
// String fieldName = field.getName();
// Object value = null;
// try {
// field.setAccessible(true);
// value = field.get(targetClass.newInstance());
// } catch (IllegalAccessException | InstantiationException e) {
// e.printStackTrace();
// }
//
// String assertion = "Assert.assertEquals(" + value + ", emp.get" + capitalize(fieldName) + "());";
// if (field.getType() == String.class) {
// assertion = "Assert.assertEquals(\"" + value + "\", emp.get" + capitalize(fieldName) + "());";
// }
//
// testCaseBuilder.append(" // Additional assertion for field\n");
// testCaseBuilder.append(" ").append(assertion).append("\n");
// }
//
// private void generateAssertions(Class<?> targetClass, Method method, StringBuilder testCaseBuilder) {

// String methodName = method.getName();
//

// Class<?> returnType = method.getReturnType();
//
// // Get the parameter types
// Class<?>[] parameterTypes = method.getParameterTypes();
//
// testCaseBuilder.append("@Test\n");
// testCaseBuilder.append("public void test").append(methodName).append("() {\n");
// testCaseBuilder.append(" // Arrange\n");
// testCaseBuilder.append(" ").append(targetClass.getSimpleName()).append(" instance = new ")
// .append(targetClass.getSimpleName()).append("();\n");
//

// for (int i = 0; i < parameterTypes.length; i++) {
// Class<?> parameterType = parameterTypes[i];
// String defaultValue = getDefaultValue(parameterType);
// testCaseBuilder.append(" ").append(parameterType.getSimpleName()).append(" param").append(i)
// .append(" = ").append(defaultValue).append(";\n");
// }
//
// testCaseBuilder.append(" // Act\n");
//

// StringBuilder methodInvocation = new StringBuilder();
// methodInvocation.append(" ").append(returnType.getSimpleName()).append(" result = instance.")
// .append(methodName).append("(");
// for (int i = 0; i < parameterTypes.length; i++) {
// methodInvocation.append("param").append(i);
// if (i < parameterTypes.length - 1) {
// methodInvocation.append(", ");
// }
// }
// methodInvocation.append(");\n");
// testCaseBuilder.append(methodInvocation.toString());
//
// testCaseBuilder.append(" // Assert\n");
//

// if (!returnType.equals(void.class)) {
// String returnValue = getDefaultValue(returnType);
// testCaseBuilder.append(" ").append("Assert.assertEquals(").append(returnValue)
// .append(", result);\n");
// }
//
// testCaseBuilder.append("}\n\n");
// }
	String getDefaultValue(Class<?> type) {
		if (type == boolean.class || type == Boolean.class) {
			return "false";
		} else if (type == int.class || type == Integer.class) {
			return "0";
		} else if (type == long.class || type == Long.class) {
			return "0L";
		} else if (type == double.class || type == Double.class) {
			return "0.0";
		} else if (type == float.class || type == Float.class) {
			return "0.0f";
		} else if (type == char.class || type == Character.class) {
			return "'\\u0000'";
		} else if (type == byte.class || type == Byte.class) {
			return "(byte) 0";
		} else if (type == short.class || type == Short.class) {
			return "(short) 0";
		} else {
			return "null";
		}
	}

	String capitalize(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}
}
//import java.io.File;
//import java.io.FileDescriptor;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.io.PrintWriter;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.Scanner;
//import java.util.Set;
//
//import org.reflections.Reflections;
//import org.reflections.scanners.SubTypesScanner;
//
//
//public class TestGeneration {
//
// static String INPUT_FILE = "C:\\Users\\241359\\Documents\\workspace-spring-tool-suite-4-4.17.1.RELEASE\\UnitTesting.txt";
// static String OUTPUT_DIRECTORY = "C:\\Users\\241359\\Documents\\workspace-spring-tool-suite-4-4.17.1.RELEASE\\UnitTesting\\src\\test\\java\\com\\example\\UnitTest";
//
// public void generateTestCases(String basePackage) {
// String reflectionsOutputFile = INPUT_FILE;
// scanAndSaveReflectionsData(basePackage, reflectionsOutputFile);
// generateTestCasesFromReflectionsData(reflectionsOutputFile);
// }
//
// private void scanAndSaveReflectionsData(String basePackage, String outputFile) {
// try {
// Reflections reflections = new Reflections(basePackage, new SubTypesScanner(false));
// Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
//
// FileOutputStream fos = new FileOutputStream(outputFile);
// PrintStream ps = new PrintStream(fos);
//

// System.setOut(ps);
//
// for (Class<?> targetClass : classes) {
// generateReflectionsData(targetClass);
// }
//

// ps.close();
// fos.close();
//
// // Reset the output stream to console
// System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
//
// private void generateReflectionsData(Class<?> targetClass) {

// System.out.println("Class: " + targetClass.getName());
//

// if (targetClass.isArray()) {
// Class<?> componentType = targetClass.getComponentType();
// System.out.println("Array Component Type: " + componentType.getName());
// }
//

// Constructor<?>[] constructors = targetClass.getDeclaredConstructors();
// System.out.println("Constructors:");
// for (Constructor<?> constructor : constructors) {
// System.out.println(constructor);
// }
//

// Field[] fields = targetClass.getDeclaredFields();
// System.out.println("Fields:");
// for (Field field : fields) {
// System.out.println(field);
// }
//

// Method[] methods = targetClass.getDeclaredMethods();
// System.out.println("Methods:");
// for (Method method : methods) {
// System.out.println(method);
// }
//
// System.out.println("_______________________");
// }
//
// private void generateTestCasesFromReflectionsData(String reflectionsOutputFile) {
// try {
// File file = new File(reflectionsOutputFile);
// Scanner scanner = new Scanner(file);
//
// while (scanner.hasNextLine()) {
// String line = scanner.nextLine();
// if (line.startsWith("Class: ")) {
// String className = line.substring(7);
// Class<?> targetClass = Class.forName(className);
// generateTestCases(targetClass);
// }
// }
//
// scanner.close();
// } catch (IOException | ClassNotFoundException e) {
// e.printStackTrace();
// }
// }
//
// private void generateTestCases(Class<?> targetClass) {
// StringBuilder testCaseBuilder = new StringBuilder();
// testCaseBuilder.append("import org.junit.Test;\n");
// testCaseBuilder.append("import org.junit.Assert;\n\n");
// testCaseBuilder.append("public class ").append(targetClass.getSimpleName()).append("TestCase {\n\n");
//

// Constructor<?>[] constructors = targetClass.getDeclaredConstructors();
// Field[] fields = targetClass.getDeclaredFields();
// Method[] methods = targetClass.getDeclaredMethods();
//

// for (Constructor<?> constructor : constructors) {
// String constructorName = constructor.getName();
// Class<?>[] parameterTypes = constructor.getParameterTypes();
//
// testCaseBuilder.append("@Test\n");
// testCaseBuilder.append("public void test").append(constructorName).append("() {\n");
// testCaseBuilder.append(" // Arrange\n");

// for (int i = 0; i < parameterTypes.length; i++) {
// Class<?> parameterType = parameterTypes[i];
// String defaultValue = getDefaultValue(parameterType);
// testCaseBuilder.append(" ").append(parameterType.getSimpleName()).append(" param").append(i).append(" = ").append(defaultValue).append(";\n");
// }
//
// testCaseBuilder.append(" // Act\n");
// testCaseBuilder.append(" ").append(targetClass.getSimpleName()).append(" instance = new ").append(targetClass.getSimpleName()).append("(");
// for (int i = 0; i < parameterTypes.length; i++) {
// testCaseBuilder.append("param").append(i);
// if (i < parameterTypes.length - 1) {
// testCaseBuilder.append(", ");
// }
// }
// testCaseBuilder.append(");\n");
//
// testCaseBuilder.append(" // Assert\n");

// testCaseBuilder.append(" Assert.assertNotNull(instance);\n");
//
// testCaseBuilder.append("}\n\n");
// }
//
// // Generate test case for each field
// for (Field field : fields) {
// String fieldName = field.getName();
// Class<?> fieldType = field.getType();
//
// testCaseBuilder.append("@Test\n");
// testCaseBuilder.append("public void test").append(fieldName).append("() {\n");
// testCaseBuilder.append(" // Arrange\n");

// testCaseBuilder.append(" ").append(targetClass.getSimpleName()).append(" instance = new ").append(targetClass.getSimpleName()).append("();\n");
// testCaseBuilder.append(" ").append(fieldType.getSimpleName()).append(" fieldValue = ").append(getDefaultValue(fieldType)).append(";\n");
// testCaseBuilder.append(" instance.").append(fieldName).append(" = fieldValue;\n");
//
// testCaseBuilder.append(" // Act\n");

//
// testCaseBuilder.append(" // Assert\n");

// testCaseBuilder.append(" Assert.assertEquals(fieldValue, instance.").append(fieldName).append(");\n");

//
// testCaseBuilder.append("}\n\n");
// }
//

// for (Method method : methods) {
// String methodName = method.getName();
// Class<?>[] parameterTypes = method.getParameterTypes();
// Class<?> returnType = method.getReturnType();
//
// testCaseBuilder.append("@Test\n");
// testCaseBuilder.append("public void test").append(methodName).append("() {\n");
// testCaseBuilder.append(" // Arrange\n");

// testCaseBuilder.append(" ").append(targetClass.getSimpleName()).append(" instance = new ").append(targetClass.getSimpleName()).append("();\n");
// for (int i = 0; i < parameterTypes.length; i++) {
// Class<?> parameterType = parameterTypes[i];
// String defaultValue = getDefaultValue(parameterType);
// testCaseBuilder.append(" ").append(parameterType.getSimpleName()).append(" param").append(i).append(" = ").append(defaultValue).append(";\n");
// }
//
// testCaseBuilder.append(" // Act\n");

// if (returnType != void.class) {
// testCaseBuilder.append(" ").append(returnType.getSimpleName()).append(" result = instance.").append(methodName).append("(");
// for (int i = 0; i < parameterTypes.length; i++) {
// testCaseBuilder.append("param").append(i);
// if (i < parameterTypes.length - 1) {
// testCaseBuilder.append(", ");
// }
// }
// testCaseBuilder.append(");\n");
// } else {
// testCaseBuilder.append(" instance.").append(methodName).append("(");
// for (int i = 0; i < parameterTypes.length; i++) {
// testCaseBuilder.append("param").append(i);
// if (i < parameterTypes.length - 1) {
// testCaseBuilder.append(" ");
// }
// }
// testCaseBuilder.append(");\n");
// }
//
// testCaseBuilder.append(" // Assert\n");

// if (returnType != void.class) {
// testCaseBuilder.append(" ");
// }
//
// testCaseBuilder.append("}\n\n");
// }
//
// testCaseBuilder.append("}\n");
//

// String outputFilePath = OUTPUT_DIRECTORY + File.separator + targetClass.getSimpleName() + "TestCase.java";
// try (PrintWriter writer = new PrintWriter(outputFilePath)) {
// writer.write(testCaseBuilder.toString());
// } catch (FileNotFoundException e) {
// e.printStackTrace();
// }
// }
//
// String getDefaultValue(Class<?> type) {
// if (type == byte.class || type == short.class || type == int.class || type == long.class || type == float.class || type == double.class) {
// return "0";
// } else if (type == char.class) {
// return "'\u0000'";
// } else if (type == boolean.class) {
// return "false";
// } else {
// return "null";
// }
// }
//}


//---------------------------------------
//package com.example.UnitTest;
//
//import java.io.File;
//import java.io.FileDescriptor;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.io.PrintWriter;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
//import java.util.Scanner;
//import java.util.Set;
//
//import org.reflections.Reflections;
//import org.reflections.scanners.SubTypesScanner;
//
//public class TestGeneration {
//    private static final String INPUT_FILE = "C:\\Users\\241359\\Documents\\workspace-spring-tool-suite-4-4.17.1.RELEASE\\UnitTesting.txt";
//    private static final String OUTPUT_FOLDER = "C:\\Users\\241359\\Documents\\workspace-spring-tool-suite-4-4.17.1.RELEASE\\UnitTesting\\src\\test\\java\\com\\example\\UnitTest";
//
//    public void generateTestCases(String basePackage) {
//        String reflectionsOutputFile = INPUT_FILE;
//        scanAndSaveReflectionsData(basePackage, INPUT_FILE);
//        generateTestCasesFromReflectionsData(INPUT_FILE);
//    }
//
//    private void scanAndSaveReflectionsData(String basePackage, String OUTPUT_FOLDER) {
//        try {
//            Reflections reflections = new Reflections(basePackage, new SubTypesScanner(false));
//            Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
//
//            FileOutputStream fos = new FileOutputStream(OUTPUT_FOLDER);
//            PrintStream ps = new PrintStream(fos);
//
//            // Redirect the output to the file
//            System.setOut(ps);
//
//            for (Class<?> targetClass : classes) {
//                generateReflectionsData(targetClass);
//            }
//
//            // Close the streams
//            ps.close();
//            fos.close();
//
//            // Reset the output stream to console
//            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void generateReflectionsData(Class<?> targetClass) {
//        // Print class name
//        System.out.println("Class: " + targetClass.getName());
//
//        // Print array information
//        if (targetClass.isArray()) {
//            Class<?> componentType = targetClass.getComponentType();
//            System.out.println("Array Component Type: " +componentType.getName());
//        }
//
//        // Print constructors
//        Constructor<?>[] constructors = targetClass.getDeclaredConstructors();
//        System.out.println("Constructors:");
//        for (Constructor<?> constructor : constructors) {
//            System.out.println(constructor);
//        }
//
//        // Print fields
//        Field[] fields = targetClass.getDeclaredFields();
//        System.out.println("Fields:");
//        for (Field field : fields) {
//            System.out.println(field);
//        }
//
//        // Print methods
//        Method[] methods = targetClass.getDeclaredMethods();
//        System.out.println("Methods:");
//        for (Method method : methods) {
//            System.out.println(method);
//        }
//
//        System.out.println("_______________________");
//    }
//
//    private void generateTestCasesFromReflectionsData(String INPUT_FILE) {
//        try {
//            File file = new File(INPUT_FILE);
//            Scanner scanner = new Scanner(file);
//
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                if (line.startsWith("Class: ")) {
//                    String className = line.substring(7);
//                    Class<?> targetClass = Class.forName(className);
//                    generateTestCases(targetClass);
//                }
//            }
//
//            scanner.close();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void generateTestCases(Class<?> targetClass) {
//        StringBuilder testCaseBuilder = new StringBuilder();
//        testCaseBuilder.append("import org.junit.Test;\n");
//        testCaseBuilder.append("import org.junit.Assert;\n\n");
//        testCaseBuilder.append("public class ").append(targetClass.getSimpleName()).append("TestCase {\n\n");
//
//       
////        Constructor<?>[] constructors = targetClass.getDeclaredConstructors();
//     Field[] fields = targetClass.getDeclaredFields();
//       Method[] methods = targetClass.getDeclaredMethods();
////
////        // Generate test case for each constructor
////        for (Constructor<?> constructor : constructors) {
////            testCaseBuilder.append("@Test\n");
////            testCaseBuilder.append("public void test").append(constructor.getName()).append("() {\n");
////            testCaseBuilder.append("    // Arrange\n");
////            // Add necessary code to set up constructor parameters if applicable
////
////            testCaseBuilder.append("    // Act\n");
////            testCaseBuilder.append("    ").append(targetClass.getSimpleName()).append(" instance = new ").append(targetClass.getSimpleName()).append("(/* constructor parameters */);\n");
////
////            testCaseBuilder.append("    // Assert\n");
////            testCaseBuilder.append("    Assert.assertNotNull(instance);\n");
////            testCaseBuilder.append("}\n\n");
////        }
//     testCaseBuilder.append("    // Arrange\n");
//
//     Constructor<?>[] constructors = targetClass.getDeclaredConstructors();
//     for (Constructor<?> constructor : constructors) {
//         String constructorName = constructor.getName();
//         Parameter[] parameters = constructor.getParameters();
//
//        
//         testCaseBuilder.append("    public void test").append(constructorName).append("() {\n");
//
//        
//         for (Parameter parameter : parameters) {
//             String parameterType = parameter.getType().getSimpleName();
//             String parameterName = parameter.getName();
//             testCaseBuilder.append("        ").append(parameterType).append(" ").append(parameterName).append(" = createTest").append(parameterType).append("();\n");
//         }
//
//         
//         testCaseBuilder.append("\n        // Act\n");
//         testCaseBuilder.append("        ").append(constructorName).append("(");
//         for (int i = 0; i < parameters.length; i++) {
//             String parameterName = parameters[i].getName();
//             testCaseBuilder.append(parameterName);
//             if (i < parameters.length - 1) {
//                 testCaseBuilder.append(", ");
//             }
//         }
//         testCaseBuilder.append(");\n");
//
//         
//         testCaseBuilder.append("\n        // Assert\n");
//        
//         for (Parameter parameter : parameters) {
//             String parameterName = parameter.getName();
//             String assertion = String.format("        assertEquals(%s, %s.get%s());", parameterName, parameterName, capitalizeFirstLetter(parameterName));
//             testCaseBuilder.append(assertion).append("\n");
//         }
//        
//         testCaseBuilder.append("    }\n\n");
//     }
//
//    
//     System.out.println(testCaseBuilder.toString());
//       
//     for (Field field : fields) {
//    	    testCaseBuilder.append("@Test\n");
//    	    testCaseBuilder.append("public void test").append(field.getName()).append("() {\n");
//    	    testCaseBuilder.append("    // Arrange\n");
//    	   
//    	    testCaseBuilder.append("    EmployeeModel employeeModel = new EmployeeModel();\n");
//    	    
//    	    
//    	    testCaseBuilder.append("    // Act\n");
//    	    
//    	    String fieldName = field.getName();
//    	    testCaseBuilder.append("    ").append(field.getType().getSimpleName()).append(" fieldValue = employeeModel.get").append(capitalizeFirstLetter(fieldName)).append("();\n");
//    	    
//    	    testCaseBuilder.append("    // Assert\n");
//    	   
//    	    testCaseBuilder.append("    assertNotNull(fieldValue);\n");
//    	   
//    	    testCaseBuilder.append("    assertEquals(fieldValue);\n");
//    	    testCaseBuilder.append("}\n\n");
//    	}
//
//
//        
//     for (Method method : methods) {
//    	    testCaseBuilder.append("@Test\n");
//    	    testCaseBuilder.append("public void test").append(method.getName()).append("() {\n");
//    	    testCaseBuilder.append("    // Arrange\n");
//    	   
//    	    testCaseBuilder.append("    EmployeeModel employeeModel = new EmployeeModel();\n");
//    	    
//    	    Object employeeModel;
//    	    
//    	    testCaseBuilder.append("    EmployeeService employeeService = new EmployeeService();\n");
//    	    Object employeeRepository;
//			
//    	    EmployeeService.employeeRepository();
//    	    testCaseBuilder.append("    EmployeeController employeeController = new EmployeeController();\n");
//    	    Object employeeService = null;
//			EmployeeController.setEmployeeService(employeeService);
//    	    
//    	    testCaseBuilder.append("    // Act\n");
//    	  
//    	    String methodName = method.getName();
//    	    String returnType = method.getReturnType().getSimpleName();
//    	    if (!returnType.equals("void")) {
//    	        testCaseBuilder.append("    ").append(returnType).append(" result = employeeController.").append(methodName).append("();\n");
//    	    } else {
//    	        testCaseBuilder.append("    employeeController.").append(methodName).append("();\n");
//    	    }
//    	    
//    	    testCaseBuilder.append("    // Assert\n");
//    	    
//    	    if (returnType.equals("int")) {
//    	        testCaseBuilder.append("    assertEquals(result);\n");
//    	    }
//    	    
//    	  //  testCaseBuilder.append("    // Assert EmployeeController behavior\n");
//    	    
//    	    testCaseBuilder.append("    ResponseEntity<?> response = employeeController.").append(methodName).append("();\n");
//    	    testCaseBuilder.append("    assertNotNull(response);\n");
//    	    testCaseBuilder.append("    assertEquals(HttpStatus.OK, response.getStatusCode());\n");
//    	    
//    	   
//    	 //   testCaseBuilder.append("    // Assert EmployeeService behavior\n");
//    	   
//    	    
//    	    testCaseBuilder.append("}\n\n");
//    	}
//
//    	testCaseBuilder.append("}\n");
//
//
//    
//
//
//      
//        String outputFilePath = OUTPUT_FOLDER + File.separator + targetClass.getSimpleName() + "TestCase.java";
//        try (PrintWriter writer = new PrintWriter(outputFilePath)) {
//            writer.write(testCaseBuilder.toString());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    private String capitalizeFirstLetter(String word) {
//	    if (word != null && word.length() > 0) {
//	        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
//	    }
//	    return word;
//	}
//}
