package com.dhouib.foobarquix.configuration;

import com.dhouib.foobarquix.rule.ContainRule;
import com.dhouib.foobarquix.rule.DivideRule;
import com.dhouib.foobarquix.rule.ParserRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RuleConfig {

    @Bean
    public List<ParserRule> parserRuleList() {
        return List.of(
                new DivideRule(3, "FOO"),
                new DivideRule(5, "BAR"),
                new ContainRule('3', "FOO"),
                new ContainRule('5', "BAR"),
                new ContainRule('7', "QUIX")
        );
    }
}
