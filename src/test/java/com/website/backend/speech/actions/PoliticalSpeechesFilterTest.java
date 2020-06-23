package com.website.backend.speech.actions;

import com.website.backend.mock.MockPoliticalSpeechesFactory;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PoliticalSpeechesFilterTest {

    PoliticalSpeechRepository speechRepository;
    PoliticalSpeechesFilter filter;

    @Before
    public void setUp() throws Exception {
        speechRepository = new MockPoliticalSpeechesFactory().getRepository();
        filter = new PoliticalSpeechesFilter(speechRepository.getAll());
    }

    @Test
    public void shouldReturnSpeechesForOneYear() {
        assertEquals(4, filter.getAllSpeechesInYear(2012).size());
        assertEquals(0, filter.getAllSpeechesInYear(2013).size());
    }

    @Test
    public void shouldReturnSpeechesFromOneSpeaker() {
        assertEquals(2, filter.getAllSpeechesFromSpeaker("Alexander Abel").size());
        assertEquals(1, filter.getAllSpeechesFromSpeaker("Bernhard Belling").size());
    }
}