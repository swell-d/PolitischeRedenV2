package com.website.backend.speech.actions;

import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CollectStatistic {

    private final PoliticalSpeechRepository speechRepository;

    public CollectStatistic(PoliticalSpeechRepository speechRepository) {
        this.speechRepository = speechRepository;
    }

    public String findPoliticianMostSpeechesInYear(int year) {
        Map<String, Integer> countOfSpeeches = new HashMap<>();
        for (PoliticalSpeech speech : speechRepository.getSpeechesInYear(year)) {
            increaseValue(countOfSpeeches, speech.speaker, 1);
        }
        return findMostSpeechesInMap(countOfSpeeches);
    }

    private void increaseValue(Map<String, Integer> map, String key, int value) {
        Integer exist = map.get(key);
        if (exist == null) exist = 0;
        map.put(key, exist + value);
    }

    private String findMostSpeechesInMap(Map<String, Integer> countOfSpeeches) {
        if (countOfSpeeches.values().size() == 0) return null;
        int maxValueInMap = Collections.max(countOfSpeeches.values());
        return findUniqueValue(countOfSpeeches, maxValueInMap);
    }

    public String findPoliticianWithMostTopics(String topic) {
        Map<String, Integer> countOfSpeeches = new HashMap<>();
        for (PoliticalSpeech speech : this.speechRepository.getSpeechesWithTopic(topic)) {
            increaseValue(countOfSpeeches, speech.speaker, 1);
        }
        return findMostSpeechesInMap(countOfSpeeches);
    }

    public String findLeastWordyPolitician() {
        Map<String, Integer> countOfWords = new HashMap<>();
        for (PoliticalSpeech speech : this.speechRepository.getSpeeches()) {
            increaseValue(countOfWords, speech.speaker, speech.words);
        }
        return findLeastWordyInMap(countOfWords);
    }

    private String findLeastWordyInMap(Map<String, Integer> countOfWords) {
        if (countOfWords.values().size() == 0) return null;
        int minValueInMap = Collections.min(countOfWords.values());
        return findUniqueValue(countOfWords, minValueInMap);
    }

    private String findUniqueValue(Map<String, Integer> map, int value) {
        String result = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (result != null & entry.getValue() == value) return null;
            if (result == null & entry.getValue() == value) result = entry.getKey();
        }
        return result;
    }
}
