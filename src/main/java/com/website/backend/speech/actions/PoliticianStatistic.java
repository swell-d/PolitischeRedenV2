package com.website.backend.speech.actions;

import com.website.backend.speaker.db.SpeakerRepository;
import com.website.backend.speaker.domain.Speaker;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.db.PoliticalSpeechesFilter;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;

public class PoliticianStatistic {

    private final SpeakerRepository speakerRepository;
    private final PoliticalSpeechRepository speechRepository;
    private final PoliticalSpeechesFilter filter;

    public PoliticianStatistic(SpeakerRepository speakerRepository,
                               PoliticalSpeechRepository speechRepository,
                               PoliticalSpeechesFilter filter) {
        this.speakerRepository = speakerRepository;
        this.speechRepository = speechRepository;
        this.filter = filter;
    }

    public String findPoliticianMostSpeechesIn2013() {
        return findPoliticianMostSpeechesInYear(2013);
    }

    public String findPoliticianMostSpeechesInYear(int year) {
        String result = null;
        int maxSpeechesCount = 0;
        PoliticalSpeechesFilter speechesInYearFilter = createSpeechesInYearFilter(year);
        for (Speaker speaker : speakerRepository.getAll()) {
            int speechesCount = speechesInYearFilter.getAllSpeechesBySpeaker(speaker.name).size();
            if (speechesCount > maxSpeechesCount) {
                result = speaker.name;
                maxSpeechesCount = speechesCount;
            }
        }
        return result;
    }

    public String findMostSecurityPolitician() {
        return findPoliticianWithMostTopics("Innere Sicherheit");
    }

    public String findPoliticianWithMostTopics(String topic) {
        String result = null;
        int maxSpeechesCount = 0;
        for (Speaker speaker : speakerRepository.getAll()) {
            ArrayList<PoliticalSpeech> speechesBySpeaker = filter.getAllSpeechesBySpeaker(speaker.name);
            PoliticalSpeechesFilter speechesBySpeakerFilter = filter.getInstance();
            speechesBySpeakerFilter.setSpeeches(speechesBySpeaker);
            int speechesCount = speechesBySpeakerFilter.getAllSpeechesWithTopic(topic).size();
            if (speechesCount > maxSpeechesCount) {
                result = speaker.name;
                maxSpeechesCount = speechesCount;
            }
        }
        return result;
    }

    public String findLeastWordyPolitician() {
        String result = null;
        int minWords = Integer.MAX_VALUE;
        for (Speaker speaker : speakerRepository.getAll()) {
            ArrayList<PoliticalSpeech> speechesBySpeaker = filter.getAllSpeechesBySpeaker(speaker.name);
            PoliticalSpeechesFilter speechesBySpeakerFilter = filter.getInstance();
            speechesBySpeakerFilter.setSpeeches(speechesBySpeaker);
            int totalWords = speechesBySpeakerFilter.wordsCount();
            if (totalWords < minWords) {
                result = speaker.name;
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
