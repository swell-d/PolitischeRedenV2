package com.website.backend.speech.actions;

import com.website.backend.mock.MockPoliticalSpeechesFactory;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.db.memory.InMemoryPoliticalSpeeches;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CollectStatisticTest {
    private CollectStatistic collectStatistic;

    @Before
    public void setUp() throws Exception {
        PoliticalSpeechRepository speechRepository = new MockPoliticalSpeechesFactory().getRepository();
        collectStatistic = new CollectStatistic(speechRepository);
    }

    @Test
    public void shouldReturnPoliticianWithTheGreatestNumberOfSpeechesIn2013() {
        assertEquals("Alexander Abel", collectStatistic.findPoliticianMostSpeechesInYear(2012));
        assertNull(collectStatistic.findPoliticianMostSpeechesInYear(2013));
    }

    @Test
    public void shouldReturnMostSecurityPolitician() {
        assertEquals("Alexander Abel", collectStatistic.findPoliticianWithMostTopics("Innere Sicherheit"));
        assertNull(collectStatistic.findPoliticianWithMostTopics("Test topic"));
    }

    @Test
    public void shouldReturnLeastWordyPolitician() {
        assertEquals("Caesare Collins", collectStatistic.findLeastWordyPolitician());
        CollectStatistic emptyDB = new CollectStatistic(new InMemoryPoliticalSpeeches());
        assertNull(emptyDB.findLeastWordyPolitician());
    }
}