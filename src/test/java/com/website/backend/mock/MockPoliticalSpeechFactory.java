package com.website.backend.mock;

import com.website.backend.speaker.domain.Speaker;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MockPoliticalSpeechFactory {

    public PoliticalSpeech getSpeech() {
        return new PoliticalSpeech(
                new Speaker("John Smith"),
                "Test topic",
                new GregorianCalendar(2020, Calendar.JUNE, 23),
                123
        );
    }

}
