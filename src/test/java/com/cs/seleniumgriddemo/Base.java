package com.cs.seleniumgriddemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Base {

    public WebDriver driver;
    public MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }


        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.get("https://www.jetbrains.com/");

            mainPage = new MainPage(driver);
        }
@AfterMethod
        public void tearDown(){driver.quit();}
    }

