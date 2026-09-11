package com.selva.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    // This class can be expanded to include methods and locators specific to the
    // product page of the application.
    WebDriver driver;
    By productTitle = By.className("title");
    By backpackProduct = By.xpath(
            "//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']");

    By addToCartButton = By.cssSelector("button");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductTitleDisplayed() {
        return driver.findElement(productTitle).isDisplayed();
    }

    public void addBackPackToCart() {
        WebElement backpack = driver.findElement(backpackProduct);
         // Find the "Add to Cart" button within the backpack product element
        WebElement addToCart = backpack.findElement(addToCartButton);

        addToCart.click();
    }

}
