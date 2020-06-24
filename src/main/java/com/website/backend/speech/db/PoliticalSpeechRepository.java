package com.website.backend.speech.db;

import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;

public interface PoliticalSpeechRepository {
    void save(PoliticalSpeech speech);

    ArrayList<PoliticalSpeech> getAllSpeeches();

    ArrayList<String> getAllSpeakers();

    void clear();

    int size();

    PoliticalSpeechRepository getSpeechesInYear(int year);

    PoliticalSpeechRepository getSpeechesBySpeaker(String speakerName);

    PoliticalSpeechRepository getSpeechesWithTopic(String topic);

    int wordsCount();
}
