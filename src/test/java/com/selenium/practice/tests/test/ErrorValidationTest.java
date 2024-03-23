package com.selenium.practice.tests.test;

import com.selenium.practice.pageobjects.CartPage;
import com.selenium.practice.pageobjects.ProductCatalog;
import com.selenium.practice.tests.testComponents.BaseTest;
import com.selenium.practice.tests.testComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTest extends BaseTest {
    @Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() {
        String email = "abcdxyz@dummy.com";
        String password = "Abc@1234";

        landingPage.loginApplication(email, password);
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email and password.");
    }

    @Test
    public void productErrorValidation() {
        String productName = "ZARA COAT 3";
        String expectedProductName = "ZARA COAT 33";
        String email = "abcxyz@dummy.com";
        String password = "Abc@1234";

        ProductCatalog productCatalog = landingPage.loginApplication(email, password);
        productCatalog.addToCart(productName);

        CartPage cartPage = productCatalog.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(expectedProductName);
        Assert.assertFalse(match);
    }
}
