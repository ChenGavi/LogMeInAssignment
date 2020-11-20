package com.logmein.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AbstractTest {

    private WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        PropertyConfigurator.configure(AbstractTest.class.getClassLoader().getResourceAsStream("log4j.properties"));
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }

}
