package com.selenium.practice.tests.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int count = 0;

    @Override
    public boolean retry(ITestResult result) {
        int maxTry = 1;
        if(count < maxTry) {
            count++;
            return true;
        }
        return false;
    }
}
