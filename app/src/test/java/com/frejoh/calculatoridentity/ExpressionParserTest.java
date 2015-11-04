package com.frejoh.calculatoridentity;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * File: ExpressionParserTest.java
 * Project: Calculator Identity
 * Author: Fredrik Johansson
 * Date: 2015.10.31
 */

public class ExpressionParserTest {

    private HashMap<String, String> symbols = new HashMap<>();
    private ExpressionParser parser;

    @Before
    public void setUp() {
        symbols.put("|x|", "abs(");
        symbols.put("n!", "!");
        symbols.put("mod", " mod ");
        parser = new ExpressionParser(symbols);
    }

    @Test
    public void shouldConvertValues() {
        assertEquals("abs(", parser.convert("|x|"));
    }

    @Test
    public void shouldRemoveLastSymbol() {
        assertEquals("!", parser.deleteLastSymbol("! mod "));
    }
}