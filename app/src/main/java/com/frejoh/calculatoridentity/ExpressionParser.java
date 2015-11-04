package com.frejoh.calculatoridentity;


import java.util.HashMap;

/**
 * File: ExpressionParser.java
 * Project: Calculator Identity
 * Author: Fredrik Johansson
 * Date: 2015.10.31
 */

public class ExpressionParser {

    private HashMap<String, String> symbols = new HashMap<>();

    public ExpressionParser(HashMap<String, String> symbols) {
        this.symbols = symbols;
    }

    public String convert(String symbol) {
        return symbols.get(symbol);
    }

    public String deleteLastSymbol(String expression) {
        for (String symbol : symbols.values()) {
            if (expression.endsWith(symbol)) {
                return expression.substring(0, expression.length()-symbol.length());
            }
        }
        // else remove only 1 character
        return expression.substring(0, expression.length()-1);
    }

    public String parse(String expression) {

        expression = expression.replaceAll(" mod ", "%");
        expression = expression.replaceAll("√", "sqrt");
        expression = expression.replaceAll("π", "PI");
        return expression;
    }
}
