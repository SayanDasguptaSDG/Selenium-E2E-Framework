package com.selenium.practice.pageobjects;

import com.selenium.practice.reusableComponents.ReusableComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends ReusableComponents {
    WebDriver driver;

    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".mb-3")
    private List<WebElement> products;

    @FindBy(css=".ng-animating")
    private WebElement spinner;

    private final By productsBy = By.cssSelector(".mb-3");
    private final By addToCart = By.cssSelector(".card-body button:last-of-type");
    private final By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductList().stream().filter(product->
                product.findElement(By.cssSelector("b")).getText()
                        .equals(productName)).findFirst().orElse(null);
    }

    public void addToCart(String productName) {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }
}
