package com.website.backend.speaker.db.memory;

import com.website.backend.speaker.db.SpeakerRepository;
import com.website.backend.speaker.domain.Speaker;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InMemorySpeakersTest {

    private SpeakerRepository speakers;
    private Speaker testSpeaker;

    @Before
    public void setUp() {
        speakers = new InMemorySpeakers();
        testSpeaker = new Speaker("John Smith");
        speakers.save(testSpeaker);
    }

    @Test
    public void shouldReturnSpeakerIfSpeakerIsInRepository() {
        assertEquals(testSpeaker, speakers.findByName(testSpeaker.name));
    }

    @Test
    public void shouldReturnNullIfSpeakerIsNotInRepository() {
        assertNull(speakers.findByName("Neo"));
    }

    @Test
    public void shouldSaveSpeakersToRepository() {
        assertEquals(1, speakers.size());
        speakers.save(new Speaker("Neo"));
        assertEquals(2, speakers.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorIfSpeakerIsAlreadyInRepository() {
        speakers.save(new Speaker(testSpeaker.name));
    }
}