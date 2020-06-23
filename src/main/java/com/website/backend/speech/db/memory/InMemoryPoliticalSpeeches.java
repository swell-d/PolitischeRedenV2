package com.website.backend.speech.db.memory;

import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;

public class InMemoryPoliticalSpeeches implements PoliticalSpeechRepository {
    private final ArrayList<PoliticalSpeech> speeches = new ArrayList<PoliticalSpeech>();

    @Override
    public void save(PoliticalSpeech speech) {
        if (speeches.contains(speech))
            throw new IllegalArgumentException("Speech is already in repository.");
        this.speeches.add(speech);
    }

    @Override
    public ArrayList<PoliticalSpeech> getAll() {
        return speeches;
    }

    @Override
    public int size() {
        return speeches.size();
    }
}