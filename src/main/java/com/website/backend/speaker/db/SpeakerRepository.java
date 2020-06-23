package com.website.backend.speaker.db;

import com.website.backend.speaker.domain.Speaker;

public interface SpeakerRepository {
    Speaker findByName(String name) throws Exception;

    void save(Speaker speaker) throws Exception;

    boolean checkIfAlreadyExist(Speaker speaker);

    int size();
}
