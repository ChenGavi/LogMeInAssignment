package com.logmein.tests;

import com.logmein.pages.Calculator;
import org.testng.annotations.Test;

public class CalculatorTest extends AbstractTest {

    private static final String url = "https://www.jsbin.com";

    @Test
    public void validResultTest() {
        Calculator calculator = new Calculator(getDriver());

        calculator.gotoCalculatorPage(url);

        // Calculate expression 16+27*38-49/50
        calculator.validResultExpression();

    }

    @Test
    public void invalidResultTest() {
        Calculator calculator = new Calculator(getDriver());

        calculator.gotoCalculatorPage(url);

        // Calculate expression 6+7×8-/9
        calculator.errorExpression();

    }

    @Test
    public void infinityResultTest() {
        Calculator calculator = new Calculator(getDriver());

        calculator.gotoCalculatorPage(url);

        // Calculate expression 6+7×8-9/0
        calculator.infinityResultExpression();

    }

}
