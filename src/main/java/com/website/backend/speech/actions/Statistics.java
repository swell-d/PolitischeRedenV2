package com.website.backend.speech.actions;

import com.website.backend.speech.domain.PoliticalSpeech;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Statistics {

    private final Map<String, Integer> mostSpeeches = new HashMap<>();
    private final Map<String, Integer> mostSecurity = new HashMap<>();
    private final Map<String, Integer> leastWordy = new HashMap<>();

    public void add(String... row) throws ParseException {
        PoliticalSpeech speech = new PoliticalSpeech(row);
        if (speech.date.get(Calendar.YEAR) == 2013) increaseValue(mostSpeeches, speech.speaker, 1);
        if (speech.topic.equals("Innere Sicherheit")) increaseValue(mostSecurity, speech.speaker, 1);
        increaseValue(leastWordy, speech.speaker, speech.words);
    }

    private void increaseValue(Map<String, Integer> map, String key, int value) {
        Integer exist = map.get(key);
        if (exist == null) exist = 0;
        map.put(key, exist + value);
    }

    public StatisticResponse calculateResult() {
        return new StatisticResponse(
                findMaxValue(mostSpeeches),
                findMaxValue(mostSecurity),
                findMinValue(leastWordy)
        );
    }

    private String findMaxValue(Map<String, Integer> map) {
        if (map.values().size() == 0) return null;
        int maxValueInMap = Collections.max(map.values());
        return findUniqueValue(map, maxValueInMap);
    }

    private String findMinValue(Map<String, Integer> map) {
        if (map.values().size() == 0) return null;
        int minValueInMap = Collections.min(map.values());
        return findUniqueValue(map, minValueInMap);
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
