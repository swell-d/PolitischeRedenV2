package com.website.backend.speech.actions;

import com.website.backend.speech.db.PoliticalSpeechRepository;

import java.util.Map;

public class PoliticianStatistic {

    private final PoliticalSpeechRepository speechRepository;

    public PoliticianStatistic(PoliticalSpeechRepository speechRepository) {
        this.speechRepository = speechRepository;
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
        PoliticalSpeechRepository speechesInYear = speechRepository.getSpeechesInYear(year);
        for (String speaker : speechRepository.getAllSpeakers()) {
            int speechesCount = speechesInYear.getSpeechesBySpeaker(speaker).size();
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
            int speechesCount = speechRepository
                    .getSpeechesBySpeaker(speaker)
                    .getSpeechesWithTopic(topic)
                    .size();
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
            int totalWords = speechRepository.getSpeechesBySpeaker(speaker).wordsCount();
            if (totalWords < minWords) {
                result = speaker;
                minWords = totalWords;
            }
        }
        return result;
    }

}
