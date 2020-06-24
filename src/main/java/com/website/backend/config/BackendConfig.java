package com.website.backend.config;

import com.website.backend.speech.actions.PoliticianStatistic;
import com.website.backend.speech.api.rest.StatisticController;
import com.website.backend.speech.db.memory.InMemoryPoliticalSpeeches;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackendConfig {

    @Bean
    public PoliticianStatistic politicianStatistic() {
        return new PoliticianStatistic(new InMemoryPoliticalSpeeches());
    }

    @Bean
    public StatisticController statisticController(PoliticianStatistic politicianStatistic) {
        return new StatisticController(politicianStatistic);
    }
}
