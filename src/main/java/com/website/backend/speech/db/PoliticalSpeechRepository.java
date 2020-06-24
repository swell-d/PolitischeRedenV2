package com.website.backend.speech.db;

import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;

public interface PoliticalSpeechRepository {
    PoliticalSpeechRepository createRepository();

    void save(PoliticalSpeech speech);

    ArrayList<PoliticalSpeech> getAllSpeeches();

    void clear();

    int size();

    ArrayList<PoliticalSpeech> getSpeechesInYear(int year);

    ArrayList<PoliticalSpeech> getSpeechesBySpeaker(String speakerName);

    ArrayList<PoliticalSpeech> getSpeechesWithTopic(String topic);

    int wordsCount();
}
