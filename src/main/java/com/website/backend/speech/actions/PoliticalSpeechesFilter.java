package com.website.backend.speech.actions;

import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;
import java.util.Calendar;

public class PoliticalSpeechesFilter {
    PoliticalSpeechRepository speeches;

    public PoliticalSpeechesFilter(PoliticalSpeechRepository speeches) {
        this.speeches = speeches;
    }

    public ArrayList<PoliticalSpeech> getAllSpeechesInYear(int year) {
        ArrayList<PoliticalSpeech> speechesForOneYear = new ArrayList<>();

        for (PoliticalSpeech speech : this.speeches.getAll()) {
            if (speech.date.get(Calendar.YEAR) == year) {
                speechesForOneYear.add(speech);
            }
        }

        return speechesForOneYear;
    }
}
