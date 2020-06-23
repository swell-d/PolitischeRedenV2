package com.website.backend.speech.db.memory;

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

    public ArrayList<PoliticalSpeech> getAllSpeechesFromSpeaker(String speakerName) {
        ArrayList<PoliticalSpeech> speechesFromOneSpeaker = new ArrayList<>();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.getSpeakerName().equals(speakerName)) speechesFromOneSpeaker.add(speech);
        }
        return speechesFromOneSpeaker;
    }

    public int wordsCount() {
        int count = 0;
        for (PoliticalSpeech speech : this.speeches) count += speech.words;
        return count;
    }

}
