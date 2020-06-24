package com.website.backend.speech.domain;

import com.website.backend.mock.MockPoliticalSpeechFactory;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class PoliticalSpeechTest {

    @Test
    public void shouldCreateObject() {
        PoliticalSpeech speech = new MockPoliticalSpeechFactory().getSpeech();
        assertEquals("John Smith", speech.speaker);
        assertEquals("Test topic", speech.topic);
        assertEquals(new GregorianCalendar(2020, Calendar.JUNE, 23), speech.date);
        assertEquals(123, speech.words);
    }

    @Test
    public void shouldBeEquals() {
        PoliticalSpeech speech1 = new MockPoliticalSpeechFactory().getSpeech();
        PoliticalSpeech speech2 = new MockPoliticalSpeechFactory().getSpeech();
        assertEquals(speech1, speech2);
        assertEquals(speech1.hashCode(), speech2.hashCode());
    }
}
