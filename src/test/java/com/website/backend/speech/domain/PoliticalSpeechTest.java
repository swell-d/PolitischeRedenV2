package com.website.backend.speech.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class PoliticalSpeechTest {

    private PoliticalSpeech politicalSpeech;

    @Before
    public void setUp() throws Exception {
        politicalSpeech = new PoliticalSpeech();
    }

    @Test
    public void shouldSaveValues() {
        politicalSpeech.speaker = "John Smith";
        politicalSpeech.topic = "Test topic";
        politicalSpeech.date = new GregorianCalendar(2020, 5, 23);
        politicalSpeech.words = 123;
        assertEquals("John Smith", politicalSpeech.speaker);
        assertEquals("Test topic", politicalSpeech.topic);
        assertEquals(new GregorianCalendar(2020, 5, 23), politicalSpeech.date);
        assertEquals(123, politicalSpeech.words);
    }
}
