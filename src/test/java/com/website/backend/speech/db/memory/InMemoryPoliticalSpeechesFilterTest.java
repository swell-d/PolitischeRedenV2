package com.website.backend.speech.db.memory;

import com.website.backend.mock.MockPoliticalSpeechesFactory;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InMemoryPoliticalSpeechesFilterTest {

    PoliticalSpeechRepository speechRepository;
    InMemoryPoliticalSpeechesFilter filter;

    @Before
    public void setUp() throws Exception {
        speechRepository = new MockPoliticalSpeechesFactory().getRepository();
        filter = new InMemoryPoliticalSpeechesFilter();
        filter.setSpeeches(speechRepository.getAll());
    }

    @Test
    public void shouldReturnSpeechesForOneYear() {
        assertEquals(4, filter.getAllSpeechesInYear(2012).size());
        assertEquals(0, filter.getAllSpeechesInYear(2020).size());
    }

    @Test
    public void shouldReturnSpeechesFromOneSpeaker() {
        assertEquals(2, filter.getAllSpeechesFromSpeaker("Alexander Abel").size());
        assertEquals(1, filter.getAllSpeechesFromSpeaker("Bernhard Belling").size());
        assertEquals(0, filter.getAllSpeechesFromSpeaker("John Smith").size());
    }

    @Test
    public void shouldReturnWordsCount() {
        assertEquals(8550, filter.wordsCount());
    }

    @Test
    public void shouldReturnWordsCountForOneSpeakerInOneYear() {
        filter.setSpeeches(filter.getAllSpeechesInYear(2012));
        filter.setSpeeches(filter.getAllSpeechesFromSpeaker("Alexander Abel"));
        assertEquals(6221, filter.wordsCount());
    }

    @Test
    public void shouldReturnTopicsCountForCertainTopic() {
        assertEquals(1, filter.getAllSpeechesWithTopic("Innere Sicherheit").size());
        assertEquals(0, filter.getAllSpeechesWithTopic("Test topic").size());
    }
}