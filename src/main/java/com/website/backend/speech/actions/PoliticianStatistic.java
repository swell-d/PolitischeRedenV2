package com.website.backend.speech.actions;

import com.website.backend.speaker.db.SpeakerRepository;
import com.website.backend.speaker.domain.Speaker;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.db.memory.PoliticalSpeechesFilter;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;

public class PoliticianStatistic {

    private final SpeakerRepository speakerRepository;
    private final PoliticalSpeechRepository speechRepository;

    public PoliticianStatistic(SpeakerRepository speakerRepository, PoliticalSpeechRepository speechRepository) {
        this.speakerRepository = speakerRepository;
        this.speechRepository = speechRepository;
    }

    public String findPoliticianMostSpeechesIn2013() {
        return findPoliticianMostSpeechesInYear(2013);
    }

    public String findPoliticianMostSpeechesInYear(int year) {
        ArrayList<PoliticalSpeech> speechesInYear =
                new PoliticalSpeechesFilter(speechRepository.getAll()).getAllSpeechesInYear(year);
        PoliticalSpeechesFilter speechesInYearFilter = new PoliticalSpeechesFilter(speechesInYear);

        String result = null;
        int maxSpeechesCount = 0;
        for (Speaker speaker : speakerRepository.getAll()) {
            int speechesCount = speechesInYearFilter.getAllSpeechesFromSpeaker(speaker.name).size();
            if (speechesCount > maxSpeechesCount) {
                result = speaker.name;
                maxSpeechesCount = speechesCount;
            }
        }
        return result;
    }
}
