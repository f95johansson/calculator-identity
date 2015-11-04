package com.frejoh.calculatoridentity;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;

/**
 * File: Calculator.java
 * Project: Calculator Identity
 * Author: Fredrik Johansson
 * Date: 2015.10.31
 */


public class Calculator {

    private ExpressionParser parser;

    public Calculator(ExpressionParser parser) {
        this.parser = parser;
    }

    // Set variables etc

    public Answer compute(String expression) {
        String parsedExpression = parser.parse(expression);
        Expression exp = new Expression(parsedExpression);

        return new Answer(exp.eval());
    }

    public class Answer {

        private BigDecimal ans;

        public Answer(BigDecimal ans) {
            this.ans = ans;
        }

        public double getDecimal() {
            return ans.doubleValue();
        }

        public String getFractional() {
            return ans.toString();
        }

        @Override
        public String toString() {
            return String.format("{%s, %f}", ans.toString(), ans.doubleValue());
        }

        public BigDecimal getBigDecimal() {
            return ans;
        }
    }


}
