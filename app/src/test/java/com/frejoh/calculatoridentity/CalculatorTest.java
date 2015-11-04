package com.frejoh.calculatoridentity;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * File: CalculatorTest.java
 * Project: Calculator Identity
 * Author: Fredrik Johansson
 * Date: 2015.10.312
 */

public class CalculatorTest {

    @Test
    public void shouldHandelAdditionExpressions() {
        Calculator calc = new Calculator(new ExpressionParser(new HashMap<String, String>()));
        System.out.println(calc.compute("2+3"));
        assertEquals(calc.compute("2+3").getBigDecimal(), new BigDecimal(5));
    }

}