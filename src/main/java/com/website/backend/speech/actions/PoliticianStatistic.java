package com.website.backend.speech.actions;

import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.Collections;
import java.util.HashMap;
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
        Map<String, Integer> countOfSpeeches = new HashMap<>();
        for (PoliticalSpeech speech : speechRepository.getSpeechesInYear(year).getAllSpeeches()) {
            Integer exist = countOfSpeeches.get(speech.speaker);
            if (exist == null) exist = 0;
            countOfSpeeches.put(speech.speaker, exist + 1);
        }
        return findMostSpeeches(countOfSpeeches);
    }

    private String findMostSpeeches(Map<String, Integer> countOfSpeeches) {
        String result = null;
        if (countOfSpeeches.values().size() == 0) return null;
        int maxValueInMap = Collections.max(countOfSpeeches.values());
        for (Map.Entry<String, Integer> entry : countOfSpeeches.entrySet()) {
            if (result != null & entry.getValue() == maxValueInMap) return null;
            if (result == null & entry.getValue() == maxValueInMap) result = entry.getKey();
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
