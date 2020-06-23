package com.website.backend.speech.actions;

import com.website.backend.mock.MockPoliticalSpeechesFactory;
import com.website.backend.mock.MockSpeakersFactory;
import com.website.backend.speaker.db.SpeakerRepository;
import com.website.backend.speaker.db.memory.InMemorySpeakers;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.db.memory.InMemoryPoliticalSpeeches;
import com.website.backend.speech.db.memory.InMemoryPoliticalSpeechesFilter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PoliticianStatisticTest {
    SpeakerRepository speakerRepository;
    PoliticalSpeechRepository speechRepository;
    InMemoryPoliticalSpeechesFilter filter;
    PoliticianStatistic politicianStatistic;

    @Before
    public void setUp() throws Exception {
        speakerRepository = new MockSpeakersFactory().getSpeakerRepository();
        speechRepository = new MockPoliticalSpeechesFactory().getRepository();
        filter = new InMemoryPoliticalSpeechesFilter();
        politicianStatistic = new PoliticianStatistic(speakerRepository, speechRepository, filter);
    }

    @Test
    public void shouldReturnStatisticResponse() {
        StatisticResponse expected = new StatisticResponse(
                null,
                "Alexander Abel",
                "Caesare Collins"
        );
        assertEquals(expected, politicianStatistic.getStatistic());
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

        PoliticianStatistic emptyDB = new PoliticianStatistic(
                new InMemorySpeakers(),
                new InMemoryPoliticalSpeeches(),
                new InMemoryPoliticalSpeechesFilter()
        );
        assertNull(emptyDB.findLeastWordyPolitician());
    }
}