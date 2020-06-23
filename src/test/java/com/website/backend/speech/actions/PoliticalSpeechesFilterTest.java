package com.website.backend.speech.actions;

import com.website.backend.mock.MockPoliticalSpeechesFactory;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PoliticalSpeechesFilterTest {

    PoliticalSpeechRepository speeches;
    PoliticalSpeechesFilter filter;

    @Before
    public void setUp() throws Exception {
        speeches = new MockPoliticalSpeechesFactory().getSpeeches();
        filter = new PoliticalSpeechesFilter(speeches);
    }

    @Test
    public void shoudReturnSpeechesForOneYaer() {
        assertEquals(4, filter.getAllSpeechesInYear(2012).size());
        assertEquals(0, filter.getAllSpeechesInYear(2013).size());
    }
}