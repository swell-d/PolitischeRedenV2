package com.website.backend.speech.domain;

import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class PoliticalSpeechTest {

    @Test
    public void shouldCreateObjectFromStrings() throws ParseException {
        PoliticalSpeech speech = getTestSpeech();
        assertEquals("John Smith", speech.speaker);
        assertEquals("Test topic", speech.topic);
        assertEquals(new GregorianCalendar(2020, Calendar.JUNE, 23), speech.date);
        assertEquals(123, speech.words);
    }

    @Test
    public void shouldBeEquals() throws ParseException {
        PoliticalSpeech speech1 = getTestSpeech();
        PoliticalSpeech speech2 = getTestSpeech();
        assertEquals(speech1, speech2);
        assertEquals(speech1.hashCode(), speech2.hashCode());
    }

    private PoliticalSpeech getTestSpeech() throws ParseException {
        return new PoliticalSpeech(
                "John Smith",
                "Test topic",
                "2020-06-23",
                "123"
        );
    }
}