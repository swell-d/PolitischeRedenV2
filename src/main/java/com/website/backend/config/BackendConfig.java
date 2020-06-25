package com.website.backend.config;

import com.website.backend.speech.actions.PoliticianStatistic;
import com.website.backend.speech.api.rest.StatisticController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackendConfig {

    @Bean
    public PoliticianStatistic politicianStatistic() {
        return new PoliticianStatistic();
    }

    @Bean
    public StatisticController statisticController(PoliticianStatistic politicianStatistic) {
        return new StatisticController(politicianStatistic);
    }
}
