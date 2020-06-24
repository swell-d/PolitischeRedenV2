package com.website.backend.speech.db.memory;

import com.website.backend.mock.MockPoliticalSpeechFactory;
import com.website.backend.mock.MockPoliticalSpeechesFactory;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.domain.PoliticalSpeech;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InMemoryPoliticalSpeechesTest {

    private PoliticalSpeechRepository speeches;
    private final PoliticalSpeech politicalSpeech = new MockPoliticalSpeechFactory().getSpeech();

    @Before
    public void setUp() throws Exception {
        speeches = new MockPoliticalSpeechesFactory().getRepository();
    }

    @Test
    public void shouldSaveSpeeches() {
        assertEquals(4, speeches.size());
        speeches.save(politicalSpeech);
        assertEquals(5, speeches.size());
    }

    @Test
    public void shouldReturnSavedSpeeches() {
        speeches.save(politicalSpeech);
        assertTrue(speeches.getAllSpeeches().contains(politicalSpeech));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorIfSpeechIsAlreadyInRepository() {
        speeches.save(politicalSpeech);
        speeches.save(politicalSpeech);
    }

    @Test
    public void shouldReturnSpeakersList() throws ParseException {
        assertEquals(3, speeches.getAllSpeakers().size());
    }

    @Test
    public void shouldClearRepository() {
        speeches.clear();
        assertEquals(0, speeches.size());
    }

    @Test
    public void shouldReturnSpeechesForOneYear() throws ParseException {
        assertEquals(4, speeches.getSpeechesInYear(2012).size());
        assertEquals(0, speeches.getSpeechesInYear(2020).size());
    }

    @Test
    public void shouldReturnSpeechesFromOneSpeaker() throws ParseException {
        assertEquals(2, speeches.getSpeechesBySpeaker("Alexander Abel").size());
        assertEquals(1, speeches.getSpeechesBySpeaker("Bernhard Belling").size());
        assertEquals(0, speeches.getSpeechesBySpeaker("John Smith").size());
    }

    @Test
    public void shouldReturnWordsCount() throws ParseException {
        assertEquals(8550, speeches.wordsCount());
    }

    @Test
    public void shouldReturnWordsCountForOneSpeakerInOneYear() throws ParseException {
        assertEquals(6221,
                speeches.getSpeechesInYear(2012).getSpeechesBySpeaker("Alexander Abel").wordsCount());
    }

    @Test
    public void shouldReturnTopicsCountForCertainTopic() throws ParseException {
        assertEquals(1, speeches.getSpeechesWithTopic("Innere Sicherheit").size());
        assertEquals(0, speeches.getSpeechesWithTopic("Test topic").size());
    }
}