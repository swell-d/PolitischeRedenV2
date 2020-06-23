package com.website.backend.speaker.db;

import com.website.backend.speaker.domain.Speaker;

public interface SpeakerRepository {
    Speaker findByName(String name);

    void save(Speaker speaker);

    boolean checkIfAlreadyExist(Speaker speaker);

    int size();
}
