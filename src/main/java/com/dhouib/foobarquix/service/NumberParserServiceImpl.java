package com.dhouib.foobarquix.service;

import com.dhouib.foobarquix.rule.ParserRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NumberParserServiceImpl implements NumberParserService {

    private final List<ParserRule> rules;

    @Override
    public String parse(int number) {
        StringBuilder result = new StringBuilder();

        for (ParserRule rule : rules) {
            result.append(rule.apply(number));
        }

        return result.isEmpty() ? String.valueOf(number) : result.toString();
    }
}
