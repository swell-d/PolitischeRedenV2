package com.website.backend.config;

import com.website.backend.speech.actions.CSVParser;
import com.website.backend.speech.actions.Statistics;
import com.website.backend.speech.api.rest.StatisticController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackendConfig {

    @Bean
    public Statistics statistics() {
        return new Statistics();
    }

    @Bean
    public CSVParser politicianStatistic(Statistics statistics) {
        return new CSVParser(statistics);
    }

    @Bean
    public StatisticController statisticController(CSVParser CSVParser) {
        return new StatisticController(CSVParser);
    }
}
