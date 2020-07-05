package com.website.backend.speech.actions;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    private Statistics statistics;

    @Before
    public void setUp() throws ParseException {
        statistics = new Statistics();
        statistics.add("Alexander Abel", "Bildungspolitik", "2012-10-30", "5310");
        statistics.add("Bernhard Belling", "Kohlesubventionen", "2012-11-05", "1210");
        statistics.add("Caesare Collins", "Kohlesubventionen", "2012-11-06", "1119");
        statistics.add("Alexander Abel", "Innere Sicherheit", "2012-12-11", "911");
    }

    @Test
    public void shouldCalculateStatistic() {
        StatisticResponse expected = new StatisticResponse(
                null,
                "Alexander Abel",
                "Caesare Collins"
        );
        assertEquals(expected, statistics.calculateResult());
    }
}