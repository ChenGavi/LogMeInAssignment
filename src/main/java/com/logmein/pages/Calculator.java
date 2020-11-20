package com.logmein.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;


@Log4j
public class Calculator extends BasePage{

    JavascriptExecutor driver_js= ((JavascriptExecutor) driver);
    String expression, output;

    @FindBy(id = "expression")
    private WebElement input_expression;

    @FindBy(xpath = "//*[@id=\"output\"]")
    private WebElement result;

    @FindBy(xpath = "/html/body/div/div[3]/button[1]")
    private WebElement buttonOne;

    @FindBy(xpath = "/html/body/div/div[3]/button[2]")
    private WebElement buttonTwo;

    @FindBy(xpath = "/html/body/div/div[3]/button[3]")
    private WebElement buttonThree;

    @FindBy(xpath = "/html/body/div/div[3]/button[4]")
    private WebElement buttonFour;

    @FindBy(xpath = "/html/body/div/div[3]/button[5]")
    private WebElement buttonFive;

    @FindBy(xpath = "/html/body/div/div[3]/button[6]")
    private WebElement buttonSix;

    @FindBy(xpath = "/html/body/div/div[3]/button[7]")
    private WebElement buttonSeven;

    @FindBy(xpath = "/html/body/div/div[3]/button[8]")
    private WebElement buttonEight;

    @FindBy(xpath = "/html/body/div/div[3]/button[9]")
    private WebElement buttonNine;

    @FindBy(xpath = "/html/body/div/div[3]/button[10]")
    private WebElement buttonZero;

    @FindBy(xpath = "/html/body/div/div[3]/button[11]")
    private WebElement buttonDelete;

    @FindBy(xpath = "/html/body/div/div[3]/button[12]")
    private WebElement operatorEqual;

    @FindBy(xpath = "/html/body/div/div[3]/button[13]")
    private WebElement operatorPlus;

    @FindBy(xpath = "/html/body/div/div[3]/button[14]")
    private WebElement operatorMinus;

    @FindBy(xpath = "/html/body/div/div[3]/button[15]")
    private WebElement operatorMultiply;

    @FindBy(xpath = "/html/body/div/div[3]/button[16]")
    private WebElement operatorDivide;


    public Calculator(WebDriver driver) {
        super(driver);
    }

    public Calculator gotoCalculatorPage(String url){

        String server = String.format("%s/hudape/1", url);
        log.info("Navigate to - " + server);
        driver.navigate().to(server);
        waitForWebElements(Arrays.asList(input_expression, buttonDelete, result, buttonOne, buttonTwo, buttonThree, buttonFour,
                buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine, buttonZero, operatorDivide, operatorEqual,
                operatorMinus, operatorMultiply, operatorPlus));

        return this;
    }

    public void validResultExpression1(){
        // Calculate expression 1+2×3-4/5
        driver.navigate().refresh();
        buttonOne.click();
        operatorPlus.click();
        buttonTwo.click();
        operatorMultiply.click();
        buttonThree.click();
        operatorMinus.click();
        buttonFour.click();
        operatorDivide.click();
        buttonFive.click();
        operatorEqual.click();
        output = "6.2";
        expression = "1+2×3-4/5";

        log.info("Start to calculate the expression " + expression);
        String input_expression = getExpressionByJS();
        assertEquals(input_expression, expression);
        log.info("Input Expression is the same as actual Expression");

        assertEquals(result.getText(), output);
        log.info("the result is our expect");

    }

    public void validResultExpression2(){

        // Calculate expression 6+7×8-0/9
        driver.navigate().refresh();
        buttonSix.click();
        operatorPlus.click();
        buttonSeven.click();
        operatorMultiply.click();
        buttonEight.click();
        operatorMinus.click();
        buttonZero.click();
        operatorDivide.click();
        buttonNine.click();
        operatorEqual.click();
        output = "62";
        expression = "6+7×8-0/9";

        log.info("Start to calculate the expression " + expression);

        String input_expression = getExpressionByJS();
        assertEquals(input_expression, expression);
        log.info("Input Expression is the same as actual Expression");

        assertEquals(result.getText(), output);
        log.info("the result is our expect");

    }

    public void infinityResultExpression(){
        // Calculate expression 6+7×8-9/0
        buttonSix.click();
        operatorPlus.click();
        buttonSeven.click();
        operatorMultiply.click();
        buttonEight.click();
        operatorMinus.click();
        buttonNine.click();
        buttonDelete.click();
        buttonNine.click();
        operatorDivide.click();
        buttonZero.click();
        operatorEqual.click();
        output = "-Infinity";
        expression = "6+7×8-9/0";

        log.info("Start to calculate the expression " + expression);

        String input_expression = getExpressionByJS();
        assertEquals(input_expression, expression);
        log.info("Input Expression is the same as actual Expression");

        assertEquals(result.getText(), output);
        log.info("the result is our expect");
    }

    public void errorExpression(){

        // Calculate expression 6+7×8-/9

        buttonSix.click();
        operatorPlus.click();
        buttonSeven.click();
        operatorMultiply.click();
        buttonEight.click();
        operatorMinus.click();
        operatorDivide.click();
        buttonNine.click();
        operatorEqual.click();
        output = "ERR";
        expression = "6+7×8-/9";

        log.info("Start to calculate the expression " + expression);

        String input_expression = getExpressionByJS();
        assertEquals(input_expression, expression);
        log.info("Input Expression is the same as actual Expression");

        assertEquals(result.getText(), output);
        log.info("the result is our expect");
    }

    public String getExpressionByJS(){
        String execute_js = "var input = document.getElementById(\"expression\").value; return input";
        return (String) driver_js.executeScript(execute_js);
    }


}
