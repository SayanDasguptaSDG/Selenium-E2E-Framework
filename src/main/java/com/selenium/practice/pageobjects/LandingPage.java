package com.selenium.practice.pageobjects;

import com.selenium.practice.reusableComponents.ReusableComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends ReusableComponents {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="userEmail")
    private WebElement userEmail;

    @FindBy(id="userPassword")
    private WebElement userPassword;

    @FindBy(id="login")
    private WebElement loginButton;

    @FindBy(css="[class*='flyInOut']")
    private WebElement errorMessage;

    public ProductCatalog loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
        return new ProductCatalog(driver);
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }

}
