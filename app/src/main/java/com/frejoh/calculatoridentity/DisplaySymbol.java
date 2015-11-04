package com.frejoh.calculatoridentity;

/**
 * File: DisplaySymbol.java
 * Project: Calculator Identity
 * Author: Fredrik Johansson
 * Date: 2015.11.01
 */

public enum DisplaySymbol {

    // for numpad
    plus   ("+"),
    minus  ("-"),
    times  ("*"),
    divide ("/"),
    equals ("="),
    dot    ("."),
    zero   ("0"),
    one    ("1"),
    two    ("2"),
    three  ("3"),
    four   ("4"),
    five   ("5"),
    six    ("6"),
    seven  ("7"),
    eight  ("8"),
    nine   ("9"),

    // for extended pad
    pi              ("π"),
    e               ("e"),
    percent         ("%"),
    faculty         ("!"),
    sin             ("sin("),
    cos             ("cos("),
    tan             ("tan("),
    log             ("log("),
    ln              ("ln("),
    mod             (" mod "),
    abs             ("abs("),
    exponent        ("^"),
    sqrt            ("√("),
    starParentheses ("("),
    endParentheses  (")"),
    x               ("x");

    private String displayValue;

    private DisplaySymbol(String s) {
        displayValue = s;
    }

    public String getDisplayValue() {
        return this.displayValue;
    }

    // valueOf() will return enum
}
