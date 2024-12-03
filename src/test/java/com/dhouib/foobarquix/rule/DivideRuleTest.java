package com.dhouib.foobarquix.rule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivideRuleTest {
    @Test
    void testDivisibleBy3() {
        DivideRule rule = new DivideRule(3, "FOO");
        assertTrue(rule.matches(9));
    }
}
