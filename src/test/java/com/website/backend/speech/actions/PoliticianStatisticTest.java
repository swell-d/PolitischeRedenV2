package com.website.backend.speech.actions;

import com.website.backend.mock.MockPoliticalSpeechesFactory;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PoliticianStatisticTest {

    private PoliticianStatistic politicianStatistic;

    @Before
    public void setUp() throws Exception {
        PoliticalSpeechRepository speechRepository = new MockPoliticalSpeechesFactory().getRepository();
        politicianStatistic = new PoliticianStatistic(null) {
            @Override
            protected PoliticalSpeechRepository createNewRepository() {
                return speechRepository;
            }
        };
    }

    @Test
    public void shouldReturnStatisticResponse() {
        StatisticResponse expected = new StatisticResponse(
                null,
                "Alexander Abel",
                "Caesare Collins"
        );
        assertEquals(expected, politicianStatistic.getStatistic(new ArrayList<>()));
    }
}