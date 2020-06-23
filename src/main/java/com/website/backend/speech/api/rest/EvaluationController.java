package com.website.backend.speech.api.rest;

import com.website.backend.speech.actions.PoliticianStatistic;
import com.website.backend.speech.actions.StatisticResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluationController {

    private final PoliticianStatistic politicianStatistic;

    public EvaluationController(PoliticianStatistic politicianStatistic) {
        this.politicianStatistic = politicianStatistic;
    }

    @GetMapping(path = "/evaluation", produces = "application/json")
    public StatisticResponse statisticResponse() {
        return politicianStatistic.getStatistic();
    }

}
