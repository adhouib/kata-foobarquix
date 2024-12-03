package com.dhouib.foobarquix.controller;


import com.dhouib.foobarquix.service.NumberParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parse")
@RequiredArgsConstructor
public class NumberParserController {

    private final NumberParserService numberParserService;

    @GetMapping("/{number}")
    public String parseNumber(@PathVariable int number) {
        if (number < 0 || number > 100) {
            throw new IllegalArgumentException("Le nombre doit être compris entre 0 et 100.");
        }
        return numberParserService.parse(number);
    }
}
