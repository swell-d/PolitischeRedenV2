package com.website.backend.speech.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class PoliticalSpeechTest {

    private PoliticalSpeech testSpeach;
    private Speaker testSpeaker;

    @Before
    public void setUp() throws Exception {
        testSpeach = new PoliticalSpeech();
        testSpeaker = new Speaker("John Smith");
    }

    @Test
    public void shouldSaveValues() {
        testSpeach.speaker = testSpeaker;
        testSpeach.topic = "Test topic";
        testSpeach.date = new GregorianCalendar(2020, Calendar.JUNE, 23);
        testSpeach.words = 123;
        assertEquals("John Smith", testSpeach.getSpeakerName());
        assertEquals("Test topic", testSpeach.topic);
        assertEquals("2020-06-23", testSpeach.getDateAsText());
        assertEquals(123, testSpeach.words);
    }
}
