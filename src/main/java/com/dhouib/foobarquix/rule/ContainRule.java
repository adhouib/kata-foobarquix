package com.dhouib.foobarquix.rule;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContainRule implements ParserRule {

    private final int numberIn;
    private final String output;


    @Override
    public String apply(int number) {
        return matches(number) ? output : "";
    }

    @Override
    public boolean matches(int number) {
        return String.valueOf(number).contains(String.valueOf(numberIn));
    }
}
