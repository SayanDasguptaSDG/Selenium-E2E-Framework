package com.selenium.practice.tests.test;

import com.selenium.practice.pageobjects.*;
import com.selenium.practice.tests.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PracticeTest extends BaseTest {

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) {
        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

        productCatalog.addToCart(input.get("product"));

        CartPage cartPage = productCatalog.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        ConfirmationPage confirmationMessage = checkoutPage.submitOrder();
        String confirmMessage = confirmationMessage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(
                "THANKYOU FOR THE ORDER."));
    }

    @Test(dataProvider = "getData", groups = {"Purchase"},
            dependsOnMethods = { "submitOrder" })
    public void orderHistory(HashMap<String,String> input) {

        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

        OrdersPage ordersPage = productCatalog.goToOrdersPage();
        Boolean match = ordersPage.verifyOrderDisplay(input.get("product"));
        Assert.assertTrue(match);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJSONDataToMap("//src/test/java/com/selenium/practice/tests/data/PurchaseOrder.json");

        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
}
