package com.selva.selenium.tests;


import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestSample {

    WebDriver driver;

    @BeforeMethod(enabled = false)
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(enabled = false)
    public void loginTest() {

        driver.get("https://www.saucedemo.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        System.out.println("Page Title: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
        // driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        // driver.findElement(By.xpath("//input[contains(@value,'Log')]")).click();
        // driver.findElement(By.xpath("//input[contains(@class,'submit-button')]")).click();
        // driver.findElement(By.xpath("//input[starts-with(@name,'login')]")).click();
        // driver.findElement(By.xpath("//input[@id='login-button' and
        // @type='submit']")).click();
        // driver.findElement(By.xpath("//input[@id='login-button' or
        // @name='login-button']")).click();
        // driver.findElement(By.id("login-button")).click();
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        // using wait
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();

        // Assert.assertTrue(driver.getPageSource().contains("Products"));
        // Assert.assertTrue(driver.findElement(By.cssSelector("span.title")).isDisplayed());
        // Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Products']")).isDisplayed());
        // Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed());
        WebElement productPageTitle = driver.findElement(By.className("title"));
        // wait.until(ExpectedConditions.visibilityOf(productPageTitle));
        // wait.until(
        // ExpectedConditions.presenceOfElementLocated(
        // By.cssSelector(".title")));

        // fluentWait.until(
        //         ExpectedConditions.visibilityOfElementLocated(
        //                 By.cssSelector(".title")));
                       
        // fluentWait.until(
        //         ExpectedConditions.visibilityOfElementLocated(
        //                 By.cssSelector(".title1")));

        Assert.assertTrue(productPageTitle.isDisplayed());
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // Wait for 5 seconds to observe the result
        driver.quit();
    }
}
