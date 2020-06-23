package com.website.backend.speech.db;

import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.ArrayList;

public interface PoliticalSpeechesFilter {

    PoliticalSpeechesFilter getInstance();

    void setSpeeches(ArrayList<PoliticalSpeech> speeches);

    ArrayList<PoliticalSpeech> getAllSpeechesInYear(int year);

    ArrayList<PoliticalSpeech> getAllSpeechesBySpeaker(String speakerName);

    int wordsCount();

    ArrayList<PoliticalSpeech> getAllSpeechesWithTopic(String topic);
}
