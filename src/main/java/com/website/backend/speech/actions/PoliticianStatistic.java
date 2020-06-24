package com.website.backend.speech.actions;

import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.db.PoliticalSpeechesFilter;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;
import java.util.Map;

public class PoliticianStatistic {

    private final PoliticalSpeechRepository speechRepository;
    private final PoliticalSpeechesFilter filter;

    public PoliticianStatistic(PoliticalSpeechRepository speechRepository,
                               PoliticalSpeechesFilter filter) {
        this.speechRepository = speechRepository;
        this.filter = filter;
        filter.setSpeeches(speechRepository.getAllSpeeches());
    }

    public StatisticResponse getStatistic(Map<String, String> allParams) {
        // Todo download csvs and parse them

        return new StatisticResponse(
                findPoliticianMostSpeechesInYear(2013),
                findPoliticianWithMostTopics("Innere Sicherheit"),
                findLeastWordyPolitician()
        );
    }

    public String findPoliticianMostSpeechesInYear(int year) {
        String result = null;
        int maxSpeechesCount = 0;
        PoliticalSpeechesFilter speechesInYearFilter = createSpeechesInYearFilter(year);
        for (String speaker : speechRepository.getAllSpeakers()) {
            int speechesCount = speechesInYearFilter.getAllSpeechesBySpeaker(speaker).size();
            if (speechesCount > maxSpeechesCount) {
                result = speaker;
                maxSpeechesCount = speechesCount;
            }
        }
        return result;
    }

    public String findPoliticianWithMostTopics(String topic) {
        String result = null;
        int maxSpeechesCount = 0;
        for (String speaker : speechRepository.getAllSpeakers()) {
            ArrayList<PoliticalSpeech> speechesBySpeaker = filter.getAllSpeechesBySpeaker(speaker);
            PoliticalSpeechesFilter speechesBySpeakerFilter = filter.getInstance();
            speechesBySpeakerFilter.setSpeeches(speechesBySpeaker);
            int speechesCount = speechesBySpeakerFilter.getAllSpeechesWithTopic(topic).size();
            if (speechesCount > maxSpeechesCount) {
                result = speaker;
                maxSpeechesCount = speechesCount;
            }
        }
        return result;
    }

    public String findLeastWordyPolitician() {
        String result = null;
        int minWords = Integer.MAX_VALUE;
        for (String speaker : speechRepository.getAllSpeakers()) {
            ArrayList<PoliticalSpeech> speechesBySpeaker = filter.getAllSpeechesBySpeaker(speaker);
            PoliticalSpeechesFilter speechesBySpeakerFilter = filter.getInstance();
            speechesBySpeakerFilter.setSpeeches(speechesBySpeaker);
            int totalWords = speechesBySpeakerFilter.wordsCount();
            if (totalWords < minWords) {
                result = speaker;
                minWords = totalWords;
            }
        }
        return result;
    }

    private PoliticalSpeechesFilter createSpeechesInYearFilter(int year) {
        PoliticalSpeechesFilter speechesInYearFilter = filter.getInstance();
        speechesInYearFilter.setSpeeches(filter.getAllSpeechesInYear(year));
        return speechesInYearFilter;
    }

}
