package com.website.backend.config;

import com.website.backend.speech.actions.PoliticianStatistic;
import com.website.backend.speech.api.rest.StatisticController;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.db.memory.InMemoryPoliticalSpeeches;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;

@Configuration
public class BackendConfig {

    @Bean
    public PoliticalSpeechRepository politicalSpeechRepository() throws ParseException {
        return new InMemoryPoliticalSpeeches();
    }

    @Bean
    public PoliticianStatistic politicianStatistic(
            PoliticalSpeechRepository politicalSpeechRepository
    ) {
        return new PoliticianStatistic(politicalSpeechRepository);
    }

    @Bean
    public StatisticController statisticController(PoliticianStatistic politicianStatistic) {
        return new StatisticController(politicianStatistic);
    }
}
