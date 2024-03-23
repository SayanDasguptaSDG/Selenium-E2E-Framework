package com.selenium.practice.tests.test;

import com.selenium.practice.pageobjects.*;
import com.selenium.practice.tests.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PracticeTest extends BaseTest {
    String productName = "ZARA COAT 3";
    String email = "abcxyz@dummy.com";
    String password = "Abc@1234";
    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(String email, String password, String productName) {
        ProductCatalog productCatalog = landingPage.loginApplication(email, password);

        productCatalog.addToCart(productName);

        CartPage cartPage = productCatalog.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        ConfirmationPage confirmationMessage = checkoutPage.submitOrder();
        String confirmMessage = confirmationMessage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(
                "THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = { "submitOrder" })
    public void orderHistory() {

        ProductCatalog productCatalog = landingPage.loginApplication(email, password);

        OrdersPage ordersPage = productCatalog.goToOrdersPage();
        Boolean match = ordersPage.verifyOrderDisplay(productName);
        Assert.assertTrue(match);
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][] {{"abcxyz@dummy.com", "Abc@1234", "ZARA COAT 3"}, {"abcdxyz@dummy.com", "Abc@1234", "ADIDAS ORIGINAL"}};
    }
}
