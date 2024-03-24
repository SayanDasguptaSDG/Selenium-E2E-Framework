package com.selenium.practice.reusableComponents;

import com.selenium.practice.pageobjects.CartPage;
import com.selenium.practice.pageobjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReusableComponents {
    WebDriver driver;
    WebDriverWait wait;
    public ReusableComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void waitForElementToAppear(By findBy) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToDisappear(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void waitForWebElementToAppear(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @FindBy(css="[routerlink*='cart']")
    private WebElement cartHeader;

    @FindBy(css="[routerlink*='myorders']")
    private WebElement ordersHeader;

    public CartPage goToCartPage() {
        cartHeader.click();
        return new CartPage(driver);
    }

    public OrdersPage goToOrdersPage() {
        ordersHeader.click();
        return new OrdersPage(driver);
    }
}
