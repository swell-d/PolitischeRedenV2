package com.website.backend.speech.db;

import com.website.backend.speech.domain.PoliticalSpeech;

import java.net.URL;
import java.util.ArrayList;

public interface PoliticalSpeechRepository {
    PoliticalSpeechRepository createRepository();

    void save(PoliticalSpeech speech);

    ArrayList<PoliticalSpeech> getSpeeches();

    ArrayList<PoliticalSpeech> getSpeechesInYear(int year);

    ArrayList<PoliticalSpeech> getSpeechesWithTopic(String topic);

    void importCSV(URL url);

}
