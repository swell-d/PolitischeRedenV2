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
        filter.setSpeeches(speechRepository.getAll());
        politicianStatistic = new PoliticianStatistic(speakerRepository, speechRepository, filter);
    }

    @Test
    public void shouldReturnPoliticianWithTheGreatestNumberOfSpeechesIn2013() {
        assertEquals("Alexander Abel", politicianStatistic.findPoliticianMostSpeechesInYear(2012));
        assertNull(politicianStatistic.findPoliticianMostSpeechesIn2013());
    }

    @Test
    public void shouldReturnMostSecurityPolitician() {
        assertEquals("Alexander Abel", politicianStatistic.findMostSecurityPolitician());
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