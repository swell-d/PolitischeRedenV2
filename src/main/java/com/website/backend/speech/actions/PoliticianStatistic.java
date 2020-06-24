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
        PoliticalSpeechRepository speechRepository = createNewRepository();
        for (URL url : urls) speechRepository.importCSV(url);
        return calculateResult(speechRepository);
    }

    protected PoliticalSpeechRepository createNewRepository() {
        return this.speechRepository.createRepository();
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
