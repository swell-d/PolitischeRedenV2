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
        PoliticalSpeechRepository speeches = createNewRepository();
        parseSpeechesFromCSVs(urls, speeches);
        return calculateResult(speeches);
    }

    protected PoliticalSpeechRepository createNewRepository() {
        return this.speechRepository.createRepository();
    }

    private void parseSpeechesFromCSVs(ArrayList<URL> urls, PoliticalSpeechRepository speeches) {
        new WorkWithCSV(speeches).parseSpeechesFromCSVs(urls);
    }

    private StatisticResponse calculateResult(PoliticalSpeechRepository speeches) {
        CollectStatistic collectStatistic = new CollectStatistic(speeches);
        return new StatisticResponse(
                collectStatistic.findPoliticianMostSpeechesInYear(2013),
                collectStatistic.findPoliticianWithMostTopics("Innere Sicherheit"),
                collectStatistic.findLeastWordyPolitician()
        );
    }

}
