package com.website.backend.speech.api.rest;

import com.website.backend.speech.actions.CSVParser;
import com.website.backend.speech.actions.StatisticResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

@RestController
public class StatisticController {

    private final CSVParser CSVParser;

    public StatisticController(CSVParser CSVParser) {
        this.CSVParser = CSVParser;
    }

    @GetMapping(path = "/evaluation", produces = "application/json")
    public StatisticResponse statisticResponse(@RequestParam Map<String, String> allParams) {
        ArrayList<URL> urls = new WorkWithURL().parseParameters(allParams);
        return CSVParser.getStatistic(urls);
    }

}
