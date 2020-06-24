package com.website.backend.speech.db;

import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;

public interface PoliticalSpeechRepository {
    void save(PoliticalSpeech speech);

    ArrayList<PoliticalSpeech> getAllSpeeches();

    ArrayList<String> getAllSpeakers();

    int size();
}
