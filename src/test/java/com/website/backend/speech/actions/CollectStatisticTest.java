package com.website.backend.speech.actions;

import com.website.backend.speech.domain.PoliticalSpeech;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class CollectStatisticTest {

    private CollectStatistic statistic;

    @Before
    public void setUp() throws ParseException {
        statistic = new CollectStatistic();
        statistic.addToStatistic(new PoliticalSpeech("Alexander Abel", "Bildungspolitik", "2012-10-30", "5310"));
        statistic.addToStatistic(new PoliticalSpeech("Bernhard Belling", "Kohlesubventionen", "2012-11-05", "1210"));
        statistic.addToStatistic(new PoliticalSpeech("Caesare Collins", "Kohlesubventionen", "2012-11-06", "1119"));
        statistic.addToStatistic(new PoliticalSpeech("Alexander Abel", "Innere Sicherheit", "2012-12-11", "911"));
    }

    @Test
    public void shouldCalculateStatistic() {
        StatisticResponse expected = new StatisticResponse(
                null,
                "Alexander Abel",
                "Caesare Collins"
        );
        assertEquals(expected, statistic.calculateResult());
    }
}