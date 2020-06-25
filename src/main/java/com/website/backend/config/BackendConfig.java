package com.website.backend.config;

import com.website.backend.speech.actions.CollectStatistic;
import com.website.backend.speech.actions.PoliticianStatistic;
import com.website.backend.speech.api.rest.StatisticController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackendConfig {

    @Bean
    public CollectStatistic collectStatistic() {
        return new CollectStatistic();
    }

    @Bean
    public PoliticianStatistic politicianStatistic(CollectStatistic collectStatistic) {
        return new PoliticianStatistic(collectStatistic);
    }

    @Bean
    public StatisticController statisticController(PoliticianStatistic politicianStatistic) {
        return new StatisticController(politicianStatistic);
    }
}
