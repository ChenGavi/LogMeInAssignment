package com.logmein.pages;


import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Log4j
public class BasePage {

    private static final long TIMEOUT = 60;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void waitForWebElements(List<WebElement> mandatoryElements){
        log.info("Looking for mandatory elements on the page");
        //Wait for dom to go to ready state
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Wait fo mandatory elements to be clickable
        for (WebElement elm : mandatoryElements) {
            try {
                wait.ignoring(StaleElementReferenceException.class)
                        .withTimeout(Duration.ofSeconds(60))
                        .pollingEvery(Duration.ofSeconds(1))
                        .until(visibilityOf(elm));
            } catch (TimeoutException e) {
                log.info("Could not verify visibility of the element: " + elm);
                throw new RuntimeException(e);
            }
        }
    }

    public void waitForElementToBeDisplayed(@NonNull By by, int timeout, int pollInterval) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollInterval))
                .ignoring(NoSuchElementException.class)
                .until(driver -> driver.findElement(by).isDisplayed());
    }

    public void presenceOfElementLocated(@NonNull By by, int timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.presenceOfElementLocated(by));
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }
}
