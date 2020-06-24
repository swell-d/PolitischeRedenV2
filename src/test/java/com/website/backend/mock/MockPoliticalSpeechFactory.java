package com.website.backend.mock;

import com.website.backend.speech.domain.PoliticalSpeech;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MockPoliticalSpeechFactory {

    public PoliticalSpeech getSpeech1() {
        return new PoliticalSpeech(
                "John Smith",
                "Test topic",
                new GregorianCalendar(2020, Calendar.JUNE, 23),
                123
        );
    }

    public PoliticalSpeech getSpeech2() {
        return new PoliticalSpeech(
                "Neo",
                "Matrix",
                new GregorianCalendar(2120, Calendar.JUNE, 23),
                234
        );
    }

}
