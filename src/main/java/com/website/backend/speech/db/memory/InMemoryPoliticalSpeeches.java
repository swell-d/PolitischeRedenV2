package com.website.backend.speech.db.memory;

import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;
import java.util.Calendar;

public class InMemoryPoliticalSpeeches implements PoliticalSpeechRepository {
    private final ArrayList<PoliticalSpeech> speeches = new ArrayList<PoliticalSpeech>();

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
    public ArrayList<String> getAllSpeakers() {
        ArrayList<String> speakers = new ArrayList<>();
        for (PoliticalSpeech speech : this.speeches) {
            if (!speakers.contains(speech.speaker)) speakers.add(speech.speaker);
        }
        return speakers;
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
    public PoliticalSpeechRepository getSpeechesInYear(int year) {
        InMemoryPoliticalSpeeches filteredSpeeches = new InMemoryPoliticalSpeeches();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.date.get(Calendar.YEAR) == year) filteredSpeeches.save(speech);
        }
        return filteredSpeeches;
    }

    @Override
    public PoliticalSpeechRepository getSpeechesBySpeaker(String speakerName) {
        InMemoryPoliticalSpeeches filteredSpeeches = new InMemoryPoliticalSpeeches();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.speaker.equals(speakerName)) filteredSpeeches.save(speech);
        }
        return filteredSpeeches;
    }

    @Override
    public PoliticalSpeechRepository getSpeechesWithTopic(String topic) {
        InMemoryPoliticalSpeeches filteredSpeeches = new InMemoryPoliticalSpeeches();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.topic.equals(topic)) filteredSpeeches.save(speech);
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
