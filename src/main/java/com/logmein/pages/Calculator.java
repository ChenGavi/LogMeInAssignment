package com.logmein.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;


@Log4j
public class Calculator extends BasePage{

    JavascriptExecutor driver_js= ((JavascriptExecutor) driver);
    String expression, output;
    Map<String, WebElement> number;
    Map<String, WebElement> operator;

    @FindBy(className = "numeric")
    public List<WebElement> numeric;

    @FindBy(className = "operation")
    public List<WebElement> operation;

    @FindBy(id = "expression")
    public WebElement input_expression;

    @FindBy(xpath = "//*[@id=\"output\"]")
    public WebElement result;

    @FindBy(xpath = "/html/body/div/div[3]/button[11]")
    public WebElement buttonDelete;


    public Calculator(WebDriver driver) {
        super(driver);
    }

    public void setNumber(List<WebElement> number_button){
        this.number = convertListToMap(number_button, "");
//        Map<String, WebElement> operator1 = operation.stream().collect(Collectors.toMap(WebElement::getText, WebElement -> WebElement));
    }

    public void serOperator(List<WebElement> operation_button){
        this.operator = convertListToMap(operation_button, "value");
    }

    public void gotoCalculatorPage(String url){

        String server = String.format("%s/hudape/1", url);
        log.info("Navigate to - " + server);
        driver.navigate().to(server);

        List<WebElement> mandatory = new ArrayList<>();
        mandatory.add(buttonDelete);
        mandatory.add(input_expression);
        mandatory.add(result);
        mandatory.addAll(operation);
        mandatory.addAll(numeric);
        waitForWebElements(mandatory);

        setNumber(numeric);
        serOperator(operation);

    }

    public void validResultExpression(){
        // Calculate expression 16+27×38-49/50

        number.get("1").click();
        number.get("6").click();
        operator.get("+").click();
        number.get("2").click();
        number.get("7").click();
        operator.get("×").click();
        number.get("3").click();
        number.get("8").click();
        operator.get("-").click();
        number.get("4").click();
        number.get("9").click();
        operator.get("/").click();
        number.get("5").click();
        number.get("0").click();
        operator.get("=").click();

        output = "1041.02";
        expression = "16+27×38-49/50";

        log.info("Start to calculate the expression " + expression);
        String input_expression = getExpressionByJS();
        assertEquals(input_expression, expression);
        log.info("Input Expression is the same as actual Expression");

        assertEquals(result.getText(), output);
        log.info("the result is our expect");

    }


    public void infinityResultExpression(){
        // Calculate expression 6+7×8-9/0
        number.get("6").click();
        operator.get("+").click();
        number.get("7").click();
        operator.get("×").click();
        number.get("8").click();
        operator.get("-").click();
        number.get("9").click();
        buttonDelete.click();
        number.get("9").click();
        operator.get("/").click();
        number.get("0").click();
        operator.get("=").click();
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

        number.get("6").click();
        operator.get("+").click();
        number.get("7").click();
        operator.get("×").click();
        number.get("8").click();
        operator.get("-").click();
        operator.get("/").click();
        number.get("9").click();
        operator.get("=").click();
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
        return (String) driver_js.executeScript("var input = document.getElementById(\"expression\").value; return input");
    }

    public static Map<String, WebElement> convertListToMap(List<WebElement> mandatory, String value){
        Map<String, WebElement> webElementMap = new HashMap<>();
        for(WebElement webElement : mandatory){
            if (value.equals("")){
                webElementMap.put(webElement.getText(), webElement);
            }else{
                webElementMap.put(webElement.getAttribute(value), webElement);
            }
        }
        return webElementMap;
    }

}
