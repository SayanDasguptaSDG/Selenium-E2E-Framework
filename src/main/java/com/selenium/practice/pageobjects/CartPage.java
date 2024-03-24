package com.selenium.practice.pageobjects;

import com.selenium.practice.reusableComponents.ReusableComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends ReusableComponents {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".totalRow button")
    private WebElement checkoutElement;

    @FindBy(css=".cartSection h3")
    private List<WebElement> productTitles;

    public Boolean verifyProductDisplay(String productName) {
        return productTitles.stream().anyMatch(product->
                product.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout() {
        checkoutElement.click();
        return new CheckoutPage(driver);
    }
}
