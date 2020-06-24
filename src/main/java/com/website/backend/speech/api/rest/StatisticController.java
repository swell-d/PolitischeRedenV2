package com.website.backend.speech.api.rest;

import com.website.backend.speech.actions.PoliticianStatistic;
import com.website.backend.speech.actions.StatisticResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

@RestController
public class StatisticController {

    private final PoliticianStatistic politicianStatistic;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public StatisticController(PoliticianStatistic politicianStatistic) {
        this.politicianStatistic = politicianStatistic;
    }

    @GetMapping(path = "/evaluation", produces = "application/json")
    public StatisticResponse statisticResponse(@RequestParam Map<String, String> allParams) {
        return politicianStatistic.getStatistic(parseParameters(allParams));
    }

    public ArrayList<URL> parseParameters(Map<String, String> parameters) {
        ArrayList<URL> urls = new ArrayList<>();
        if (parameters == null) return urls;
        for (String url : parameters.values()) {
            try {
                String urlAsText = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8);
                urls.add(new URL(urlAsText));
            } catch (Exception e) {
                logger.error("Can't decode link: " + url);
            }
        }
        return urls;
    }
}
