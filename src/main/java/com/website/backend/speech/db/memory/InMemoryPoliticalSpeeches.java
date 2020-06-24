package com.website.backend.speech.db.memory;

import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;
import java.util.Calendar;

public class InMemoryPoliticalSpeeches implements PoliticalSpeechRepository {
    private final ArrayList<PoliticalSpeech> speeches = new ArrayList<PoliticalSpeech>();

    @Override
    public PoliticalSpeechRepository createRepository() {
        return new InMemoryPoliticalSpeeches();
    }

    @Override
    public void save(PoliticalSpeech speech) {
        if (this.speeches.contains(speech)) throw new IllegalArgumentException("Speech is already in repository.");
        this.speeches.add(speech);
    }

    @Override
    public ArrayList<PoliticalSpeech> getAllSpeeches() {
        return this.speeches;
    }

    @Override
    public void clear() {
        this.speeches.clear();
    }

    @Override
    public int size() {
        return this.speeches.size();
    }

    @Override
    public ArrayList<PoliticalSpeech> getSpeechesInYear(int year) {
        ArrayList<PoliticalSpeech> filteredSpeeches = new ArrayList<>();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.date.get(Calendar.YEAR) == year) filteredSpeeches.add(speech);
        }
        return filteredSpeeches;
    }

    @Override
    public ArrayList<PoliticalSpeech> getSpeechesBySpeaker(String speakerName) {
        ArrayList<PoliticalSpeech> filteredSpeeches = new ArrayList<>();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.speaker.equals(speakerName)) filteredSpeeches.add(speech);
        }
        return filteredSpeeches;
    }

    @Override
    public ArrayList<PoliticalSpeech> getSpeechesWithTopic(String topic) {
        ArrayList<PoliticalSpeech> filteredSpeeches = new ArrayList<>();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.topic.equals(topic)) filteredSpeeches.add(speech);
        }
        return filteredSpeeches;
    }

    @Override
    public int wordsCount() {
        int count = 0;
        for (PoliticalSpeech speech : this.speeches) count += speech.words;
        return count;
    }
}
