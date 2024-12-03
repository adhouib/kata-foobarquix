package com.dhouib.foobarquix.batch;


import com.dhouib.foobarquix.service.NumberParserService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NumberProcessor implements ItemProcessor<Integer, String> {

    @Autowired
    private NumberParserService numberParserService;

    @Override
    public String process(Integer item) {
        return numberParserService.parse(item);
    }
}
