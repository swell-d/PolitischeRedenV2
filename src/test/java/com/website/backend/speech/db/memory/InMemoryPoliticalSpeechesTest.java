package com.website.backend.speech.db.memory;

import com.website.backend.mock.MockPoliticalSpeechFactory;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.domain.PoliticalSpeech;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InMemoryPoliticalSpeechesTest {

    private PoliticalSpeechRepository speeches;
    private final PoliticalSpeech politicalSpeech1 = new MockPoliticalSpeechFactory().getSpeech1();
    private final PoliticalSpeech politicalSpeech2 = new MockPoliticalSpeechFactory().getSpeech2();

    @Before
    public void setUp() throws Exception {
        speeches = new InMemoryPoliticalSpeeches();
        speeches.save(politicalSpeech1);
    }

    @Test
    public void shouldSaveSpeeches() {
        assertEquals(1, speeches.size());
        speeches.save(politicalSpeech2);
        assertEquals(2, speeches.size());
    }

    @Test
    public void shouldReturnSavedSpeeches() {
        speeches.save(politicalSpeech2);
        assertTrue(speeches.getAllSpeeches().contains(politicalSpeech1));
        assertTrue(speeches.getAllSpeeches().contains(politicalSpeech2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorIfSpeechIsAlreadyInRepository() {
        speeches.save(politicalSpeech1);
    }

    @Test
    public void shouldReturnSpeakersList() throws ParseException {
        speeches.save(politicalSpeech2);
        assertEquals(2, speeches.getAllSpeakers().size());
    }
}