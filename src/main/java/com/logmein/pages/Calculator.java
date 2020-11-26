package com.logmein.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;


@Log4j
public class Calculator extends BasePage{

    JavascriptExecutor driver_js= ((JavascriptExecutor) driver);
    String expression, output;

    @FindBy(className = "numeric")
    public List<WebElement> numeric;

    @FindBy(className = "operation")
    public List<WebElement> operator;

    @FindBy(id = "expression")
    public WebElement input_expression;

    @FindBy(xpath = "//*[@id=\"output\"]")
    public WebElement result;

//    @FindBy(xpath = "/html/body/div/div[3]/button[1]")
//    private WebElement buttonOne;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[2]")
//    private WebElement buttonTwo;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[3]")
//    private WebElement buttonThree;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[4]")
//    private WebElement buttonFour;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[5]")
//    private WebElement buttonFive;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[6]")
//    private WebElement buttonSix;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[7]")
//    private WebElement buttonSeven;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[8]")
//    private WebElement buttonEight;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[9]")
//    private WebElement buttonNine;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[10]")
//    private WebElement buttonZero;

    @FindBy(xpath = "/html/body/div/div[3]/button[11]")
    public WebElement buttonDelete;

//    @FindBy(xpath = "/html/body/div/div[3]/button[12]")
//    private WebElement operatorEqual;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[13]")
//    private WebElement operatorPlus;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[14]")
//    private WebElement operatorMinus;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[15]")
//    private WebElement operatorMultiply;
//
//    @FindBy(xpath = "/html/body/div/div[3]/button[16]")
//    private WebElement operatorDivide;

    public Calculator(WebDriver driver) {
        super(driver);
    }

    public Calculator gotoCalculatorPage(String url){

        String server = String.format("%s/hudape/1", url);
        log.info("Navigate to - " + server);
        driver.navigate().to(server);

        List<WebElement> mandary = new ArrayList<>();
        mandary.add(buttonDelete);
        mandary.add(input_expression);
        mandary.add(result);
        mandary.addAll(operator);
        mandary.addAll(numeric);
        waitForWebElements(mandary);

        return this;
    }

    public void validResultExpression(){
        // Calculate expression 16+27×38-49/50
        driver.navigate().refresh();

        numeric.get(0).click();
        numeric.get(5).click();
        operator.get(1).click();
        numeric.get(1).click();
        numeric.get(6).click();
        operator.get(3).click();
        numeric.get(2).click();
        numeric.get(7).click();
        operator.get(2).click();
        numeric.get(3).click();
        numeric.get(8).click();
        operator.get(4).click();
        numeric.get(4).click();
        numeric.get(9).click();

        operator.get(0).click();
        output = "1041.02";
        expression = "16+27×38-49/50";

        log.info("Start to calculate the expression " + expression);
        String input_expression = getExpressionByJS();
        assertEquals(input_expression, expression);
        log.info("Input Expression is the same as actual Expression");

        assertEquals(result.getText(), output);
        log.info("the result is our expect");

    }

//
//    public void infinityResultExpression(){
//        // Calculate expression 6+7×8-9/0
//        buttonSix.click();
//        operatorPlus.click();
//        buttonSeven.click();
//        operatorMultiply.click();
//        buttonEight.click();
//        operatorMinus.click();
//        buttonNine.click();
//        buttonDelete.click();
//        buttonNine.click();
//        operatorDivide.click();
//        buttonZero.click();
//        operatorEqual.click();
//        output = "-Infinity";
//        expression = "6+7×8-9/0";
//
//        log.info("Start to calculate the expression " + expression);
//
//        String input_expression = getExpressionByJS();
//        assertEquals(input_expression, expression);
//        log.info("Input Expression is the same as actual Expression");
//
//        assertEquals(result.getText(), output);
//        log.info("the result is our expect");
//    }
//
//    public void errorExpression(){
//
//        // Calculate expression 6+7×8-/9
//
//        buttonSix.click();
//        operatorPlus.click();
//        buttonSeven.click();
//        operatorMultiply.click();
//        buttonEight.click();
//        operatorMinus.click();
//        operatorDivide.click();
//        buttonNine.click();
//        operatorEqual.click();
//        output = "ERR";
//        expression = "6+7×8-/9";
//
//        log.info("Start to calculate the expression " + expression);
//
//        String input_expression = getExpressionByJS();
//        assertEquals(input_expression, expression);
//        log.info("Input Expression is the same as actual Expression");
//
//        assertEquals(result.getText(), output);
//        log.info("the result is our expect");
//    }

    public String getExpressionByJS(){
        String execute_js = "var input = document.getElementById(\"expression\").value; return input";
        return (String) driver_js.executeScript(execute_js);
    }


//    public void getElement(){
//
//        List<WebElement> numeric = driver.findElements(By.className("numeric"));
//        System.out.println("@@@@@@@@@@@@@@@");
////        numeric.addAll(driver.findElements(By.xpath("/html/body/div/div[3]")));
//
//        for( WebElement element : numeric){
//            System.out.println(element.getText());
//        }
//
//        System.out.println("///0" + numeric.size());
//
//        List<WebElement> operator = driver.findElements((By.className("operation")));
//
//
//        for( WebElement element : operator){
//            System.out.println("///" + element.getText());
//        }
//
////        numeric.get(0).click();
////        operator.get(1).click();
////        numeric.get(1).click();
////        operator.get(0).click();
////
////        String input_expression = getExpressionByJS();
////        System.out.println(input_expression);
////
////        output = "3";
////        expression = "1+2*3-4/5";
////
////        assertEquals(input_expression, expression);
////        assertEquals(result.getText(), output);
//
//        numeric.get(0).click();
//        operator.get(1).click();
//        numeric.get(1).click();
//        operator.get(3).click();
//        numeric.get(2).click();
//        operator.get(2).click();
//        numeric.get(3).click();
//        operator.get(4).click();
//        numeric.get(4).click();
//
//        operator.get(0).click();
//        output = "6.2";
//        expression = "1+2×3-4/5";
//
//        log.info("Start to calculate the expression " + expression);
//        String input_expression = getExpressionByJS();
//        System.out.println(input_expression);
//
//        assertEquals(input_expression, expression);
//        log.info("Input Expression is the same as actual Expression");
//
//        assertEquals(result.getText(), output);
//        log.info("the result is our expect");
//
//
//
//
//
//
////        WebElement select = driver.findElement(By.xpath("/html/body/div/div[3]"));
////        Select dropDown = new Select(select);
////        List <WebElement> Options = dropDown.getOptions();
////        for(WebElement option : Options){
////            if(option.getText().equals("1")){
////                option.click();
////            }
////        }
//
//    }

}
