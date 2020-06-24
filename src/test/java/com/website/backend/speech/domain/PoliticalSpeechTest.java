package com.website.backend.speech.domain;

import com.website.backend.mock.MockPoliticalSpeechFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PoliticalSpeechTest {

    @Test
    public void shouldCreateObject() {
        PoliticalSpeech speech = new MockPoliticalSpeechFactory().getSpeech();
        assertEquals("John Smith", speech.speaker);
        assertEquals("Test topic", speech.topic);
        assertEquals("2020-06-23", speech.getDateAsText());
        assertEquals(123, speech.words);
    }

    @Test
    public void shouldBeEquals() {
        PoliticalSpeech speech1 = new MockPoliticalSpeechFactory().getSpeech();
        PoliticalSpeech speech2 = new MockPoliticalSpeechFactory().getSpeech();
        assertEquals(speech1, speech2);
    }
}
