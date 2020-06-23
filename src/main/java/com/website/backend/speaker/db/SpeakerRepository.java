package com.website.backend.speaker.db;

import com.website.backend.speaker.domain.Speaker;

import java.util.ArrayList;

public interface SpeakerRepository {
    Speaker findByName(String name);

    void save(Speaker speaker);

    boolean checkIfAlreadyExist(Speaker speaker);

    ArrayList<Speaker> getAll();

    int size();
}
