package com.dhouib.foobarquix.rule;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DivideRule implements ParserRule {

    private final int divisor;
    private final String output;


    @Override
    public String apply(int number) {
        return matches(number) ? output : "";
    }

    @Override
    public boolean matches(int number) {
        return number % divisor == 0;
    }
}
