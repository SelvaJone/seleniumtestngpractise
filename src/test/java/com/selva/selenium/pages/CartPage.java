package com.selva.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    // This class can be expanded to include methods and locators specific to the
    // cart page of the application.
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By cartIcon = By.className("shopping_cart_link");
    By backpackItem = By.xpath("//div[@data-test='inventory-item-name' and text()='Sauce Labs Backpack']");

    // Actions
    public void clickCart() {
        driver.findElement(cartIcon).click();

    }

    public boolean isBackpackDisplayed() {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         wait.until(ExpectedConditions.visibilityOfElementLocated(backpackItem));
        return driver.findElement(backpackItem).isDisplayed();
    }

}
