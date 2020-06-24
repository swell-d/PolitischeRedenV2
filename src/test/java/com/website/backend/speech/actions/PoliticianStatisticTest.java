package com.website.backend.speech.actions;

import com.website.backend.mock.MockPoliticalSpeechesFactory;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.db.memory.InMemoryPoliticalSpeeches;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PoliticianStatisticTest {
    PoliticalSpeechRepository speechRepository;
    PoliticianStatistic politicianStatistic;

    @Before
    public void setUp() throws Exception {
        speechRepository = new MockPoliticalSpeechesFactory().getRepository();
        politicianStatistic = new PoliticianStatistic(speechRepository);
    }

    @Test
    public void shouldReturnStatisticResponse() {
        StatisticResponse expected = new StatisticResponse(
                null,
                "Alexander Abel",
                "Caesare Collins"
        );
        assertEquals(expected, politicianStatistic.getStatistic(null));
    }

    @Test
    public void shouldReturnPoliticianWithTheGreatestNumberOfSpeechesIn2013() {
        assertEquals("Alexander Abel", politicianStatistic.findPoliticianMostSpeechesInYear(2012));
        assertNull(politicianStatistic.findPoliticianMostSpeechesInYear(2013));
    }

    @Test
    public void shouldReturnMostSecurityPolitician() {
        assertEquals("Alexander Abel", politicianStatistic.findPoliticianWithMostTopics("Innere Sicherheit"));
        assertNull(politicianStatistic.findPoliticianWithMostTopics("Test topic"));
    }

    @Test
    public void shouldReturnLeastWordyPolitician() {
        assertEquals("Caesare Collins", politicianStatistic.findLeastWordyPolitician());
        PoliticianStatistic emptyDB = new PoliticianStatistic(new InMemoryPoliticalSpeeches());
        assertNull(emptyDB.findLeastWordyPolitician());
    }
}