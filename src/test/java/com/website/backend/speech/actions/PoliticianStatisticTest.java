package com.website.backend.speech.actions;

import com.website.backend.mock.MockPoliticalSpeechesFactory;
import com.website.backend.mock.MockSpeakersFactory;
import com.website.backend.speaker.db.SpeakerRepository;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PoliticianStatisticTest {
    SpeakerRepository speakerRepository;
    PoliticalSpeechRepository speechRepository;
    PoliticianStatistic politicianStatistic;

    @Before
    public void setUp() throws Exception {
        speakerRepository = new MockSpeakersFactory().getSpeakerRepository();
        speechRepository = new MockPoliticalSpeechesFactory().getRepository();
        politicianStatistic = new PoliticianStatistic(speakerRepository, speechRepository);
    }

    @Test
    public void shouldReturnPoliticianWithTheGreatestNumberOfSpeechesIn2013() {
        assertEquals("Alexander Abel", politicianStatistic.findPoliticianMostSpeechesInYear(2012));
        assertNull(politicianStatistic.findPoliticianMostSpeechesIn2013());
    }
}