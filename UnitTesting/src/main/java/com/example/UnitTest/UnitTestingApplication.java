package com.example.UnitTest;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnitTestingApplication {
   
    public static void main(String[] args) {
        TestGeneration generation = new TestGeneration();
       generation.generateTestCases("com.example.UnitTest");

    }

}

