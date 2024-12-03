package com.dhouib.foobarquix.rule;

public interface ParserRule {
    /**
     * Apply the rule
     * @param number int
     * @return String
     */
    String apply(int number);

    /**
     * Check if the rule can be applied on the given number
     * @param number
     * @return
     */
    boolean matches(int number);
}
