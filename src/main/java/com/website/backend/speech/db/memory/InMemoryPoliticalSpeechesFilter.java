package com.website.backend.speech.db.memory;

import com.website.backend.speech.db.PoliticalSpeechesFilter;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;
import java.util.Calendar;

public class InMemoryPoliticalSpeechesFilter implements PoliticalSpeechesFilter {
    ArrayList<PoliticalSpeech> speeches;

    @Override
    public PoliticalSpeechesFilter getInstance() {
        return new InMemoryPoliticalSpeechesFilter();
    }

    @Override
    public void setSpeeches(ArrayList<PoliticalSpeech> speeches) {
        this.speeches = speeches;
    }

    @Override
    public ArrayList<PoliticalSpeech> getAllSpeechesInYear(int year) {
        ArrayList<PoliticalSpeech> speechesForOneYear = new ArrayList<>();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.date.get(Calendar.YEAR) == year) speechesForOneYear.add(speech);
        }
        return speechesForOneYear;
    }

    @Override
    public ArrayList<PoliticalSpeech> getAllSpeechesBySpeaker(String speakerName) {
        ArrayList<PoliticalSpeech> speechesFromOneSpeaker = new ArrayList<>();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.getSpeakerName().equals(speakerName)) speechesFromOneSpeaker.add(speech);
        }
        return speechesFromOneSpeaker;
    }

    @Override
    public int wordsCount() {
        int count = 0;
        for (PoliticalSpeech speech : this.speeches) count += speech.words;
        return count;
    }

    @Override
    public ArrayList<PoliticalSpeech> getAllSpeechesWithTopic(String topic) {
        ArrayList<PoliticalSpeech> speechesWithTopic = new ArrayList<>();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.topic.equals(topic)) speechesWithTopic.add(speech);
        }
        return speechesWithTopic;
    }

}
