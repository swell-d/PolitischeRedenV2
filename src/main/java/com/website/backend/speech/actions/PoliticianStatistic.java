package com.website.backend.speech.actions;

import com.website.backend.speech.db.PoliticalSpeechRepository;

import java.net.URL;
import java.util.ArrayList;

public class PoliticianStatistic {

    private final PoliticalSpeechRepository speechRepository;

    public PoliticianStatistic(PoliticalSpeechRepository speechRepository) {
        this.speechRepository = speechRepository;
    }

    public StatisticResponse getStatistic(ArrayList<URL> urls) {
        parseSpeechesFromCSVs(urls);
        StatisticResponse result = calculateResult();
        this.speechRepository.clear();
        return result;
    }

    private void parseSpeechesFromCSVs(ArrayList<URL> urls) {
        new WorkWithCSV(this.speechRepository).parseSpeechesFromCSVs(urls);
    }

    private StatisticResponse calculateResult() {
        CollectStatistic collectStatistic = new CollectStatistic(this.speechRepository);
        return new StatisticResponse(
                collectStatistic.findPoliticianMostSpeechesInYear(2013),
                collectStatistic.findPoliticianWithMostTopics("Innere Sicherheit"),
                collectStatistic.findLeastWordyPolitician()
        );
    }

}
