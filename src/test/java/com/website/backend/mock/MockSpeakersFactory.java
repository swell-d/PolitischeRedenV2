package com.website.backend.mock;

import com.website.backend.speaker.db.SpeakerRepository;
import com.website.backend.speaker.db.memory.InMemorySpeakers;
import com.website.backend.speaker.domain.Speaker;

public class MockSpeakersFactory {

    public SpeakerRepository getSpeakers() {
        SpeakerRepository speakers = new InMemorySpeakers();
        speakers.save(new Speaker("Alexander Abel"));
        speakers.save(new Speaker("Bernhard Belling"));
        speakers.save(new Speaker("Caesare Collins"));
        return speakers;
    }
}
