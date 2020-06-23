package com.website.backend.speech.actions;

import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;
import java.util.Calendar;

public class PoliticalSpeechesFilter {
    ArrayList<PoliticalSpeech> speeches;

    public PoliticalSpeechesFilter(ArrayList<PoliticalSpeech> speeches) {
        this.speeches = speeches;
    }

    public ArrayList<PoliticalSpeech> getAllSpeechesInYear(int year) {
        ArrayList<PoliticalSpeech> speechesForOneYear = new ArrayList<>();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.date.get(Calendar.YEAR) == year) speechesForOneYear.add(speech);
        }
        return speechesForOneYear;
    }


}
