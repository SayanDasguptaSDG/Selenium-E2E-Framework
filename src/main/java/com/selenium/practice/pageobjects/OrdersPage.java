package com.selenium.practice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class OrdersPage {
    WebDriver driver;
    public OrdersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//tbody/tr/td[2]")
    List<WebElement> orderTitles;

    public Boolean verifyOrderDisplay(String productName) {
        return orderTitles.stream().anyMatch(product->
                product.getText().equalsIgnoreCase(productName));
    }
}