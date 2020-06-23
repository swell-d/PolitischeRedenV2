package com.website.backend.speech.db.memory;

import com.website.backend.mock.MockPoliticalSpeechFactory;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.domain.PoliticalSpeech;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InMemoryPoliticalSpeechesTest {

    private PoliticalSpeechRepository speeches;
    private PoliticalSpeech politicalSpeech1;
    private PoliticalSpeech politicalSpeech2;

    @Before
    public void setUp() throws Exception {
        speeches = new InMemoryPoliticalSpeeches();
        politicalSpeech1 = new MockPoliticalSpeechFactory().getSpeech();
        speeches.save(politicalSpeech1);
        politicalSpeech2 = new MockPoliticalSpeechFactory().getSpeech();
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
        assertTrue(speeches.getAll().contains(politicalSpeech1));
        assertTrue(speeches.getAll().contains(politicalSpeech2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorIfSpeechIsAlreadyInRepository() {
        speeches.save(politicalSpeech1);
    }
}