package com.website.backend.speaker.db.memory;

import com.website.backend.speaker.db.SpeakerRepository;
import com.website.backend.speaker.domain.Speaker;

import java.util.ArrayList;

public class InMemorySpeakers implements SpeakerRepository {
    private final ArrayList<Speaker> speakers = new ArrayList<Speaker>();

    @Override
    public Speaker findByName(String name) {
        for (Speaker speaker : this.speakers) {
            if (name.equals(speaker.name)) return speaker;
        }
        return null;
    }

    @Override
    public void save(Speaker speaker) {
        if (checkIfAlreadyExist(speaker))
            throw new IllegalArgumentException("Speaker is already in repository.");
        this.speakers.add(speaker);
    }

    @Override
    public boolean checkIfAlreadyExist(Speaker speaker) {
        return findByName(speaker.name) != null;
    }

    @Override
    public ArrayList<Speaker> getAll() {
        return speakers;
    }

    @Override
    public int size() {
        return speakers.size();
    }
}
