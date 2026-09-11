package com.selva.selenium.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selva.selenium.pages.CartPage;
import com.selva.selenium.pages.LoginPage;
import com.selva.selenium.pages.ProductPage;

import org.testng.Assert;

public class LoginTest {
    // WebDriver driver;
    ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    void setup() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        // driver = new ChromeDriver();
        driver.set(new ChromeDriver(options));
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get().manage().window().maximize();
        System.out.println(
                "Browser created | Thread ID: " + Thread.currentThread().getId());
    }

    @DataProvider(name = "loginData", parallel = true)
    public Object[][] loginData() {
        return new Object[][] {
                { "standard_user", "secret_sauce", "successful login" },
                // {"locked_out_user", "secret_sauce"},
                { "problem_user", "secret_sauce", "successful login" },
                { "performance_glitch_user", "secret_sauce", "successful login" },
                { "locked_out_user", "secret_sauce", "login failure" }
        };
    }

    @Test(dataProvider = "loginData", enabled = true)
    public void loginTest(String username, String password, String expectedResult) {
        System.out.println(
                "Running test: " + username +
                        " | Thread ID: " + Thread.currentThread().getId());
        driver.get().get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver.get());
        ProductPage productPage = new ProductPage(driver.get());
        CartPage cartPage = new CartPage(driver.get());
        loginPage.login(username, password);
        // Assert.assertEquals(expectedResult, "successful login","Login failed for
        // user: ");
        // Assert.assertTrue(driver.get().findElement(By.className("title")).isDisplayed(),
        // "Page title is not displayed after login");
        if (expectedResult.equals("successful login")) {
            Assert.assertTrue(productPage.isProductTitleDisplayed(), "Page title is not displayed after login");
            productPage.addBackPackToCart();
            cartPage.clickCart();
            Assert.assertTrue(cartPage.isBackpackDisplayed(), "Sauce Labs Backpack was not added to the cart");

        } else if (expectedResult.equals("login failure")) {

            Assert.assertEquals(
                    loginPage.getErrorMessage(),
                    "Epic sadface: Sorry, this user has been locked out.",
                    "Expected login failure message was not displayed");
        }
    }

    @Test(enabled = false)
    public void lockedOutUserTest() {
        driver.get().get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver.get());
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Expected locked-out error message was not displayed");

    }

    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // Wait for 5 seconds to observe the result
          // driver.quit();
        driver.get().quit();
        driver.remove();
        System.out.println(
                "Browser closed | Thread ID: " + Thread.currentThread().getId());
    }
}
