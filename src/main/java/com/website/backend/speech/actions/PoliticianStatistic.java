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
        for (Speaker speaker : speakerRepository.getAll()) {
            int speechesCount = getSpeechesInYearFilter(year).getAllSpeechesFromSpeaker(speaker.name).size();
            if (speechesCount > maxSpeechesCount) {
                result = speaker.name;
                maxSpeechesCount = speechesCount;
            }
        }
        return result;
    }

    private PoliticalSpeechesFilter getSpeechesInYearFilter(int year) {
        ArrayList<PoliticalSpeech> speechesInYear = filter.getAllSpeechesInYear(year);
        PoliticalSpeechesFilter newFilter = filter.getInstance();
        newFilter.setSpeeches(speechesInYear);
        return newFilter;
    }

}
