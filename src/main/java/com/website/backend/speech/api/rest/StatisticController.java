package com.website.backend.speech.api.rest;

import com.website.backend.speech.actions.PoliticianStatistic;
import com.website.backend.speech.actions.StatisticResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatisticController {

    private final PoliticianStatistic politicianStatistic;

    public StatisticController(PoliticianStatistic politicianStatistic) {
        this.politicianStatistic = politicianStatistic;
    }

    @GetMapping(path = "/evaluation", produces = "application/json")
    public StatisticResponse statisticResponse(@RequestParam Map<String, String> allParams) {
        return politicianStatistic.getStatistic(allParams);
    }

}
