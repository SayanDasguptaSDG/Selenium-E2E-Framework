package com.selenium.practice.tests.test;

import com.selenium.practice.pageobjects.*;
import com.selenium.practice.tests.testComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PracticeTest extends BaseTest {
    String productName = "ZARA COAT 3";
    String email = "abcxyz@dummy.com";
    String password = "Abc@1234";
    @Test
    public void submitOrder() {
        ProductCatalog productCatalog = landingPage.loginApplication(email, password);

        List<WebElement> products = productCatalog.getProductList();
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
}
