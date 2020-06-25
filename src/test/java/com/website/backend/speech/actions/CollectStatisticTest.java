package com.website.backend.speech.actions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectStatisticTest {

    private CollectStatistic statistic;

    @Before
    public void setUp() throws Exception {
        statistic = new CollectStatistic();
        statistic.addToStatistic(
                "Alexander Abel",
                "Bildungspolitik",
                DateConverter.convertStringToCalendarFormat("2012-10-30"),
                5310
        );
        statistic.addToStatistic(
                "Bernhard Belling",
                "Kohlesubventionen",
                DateConverter.convertStringToCalendarFormat("2012-11-05"),
                1210
        );
        statistic.addToStatistic(
                "Caesare Collins",
                "Kohlesubventionen",
                DateConverter.convertStringToCalendarFormat("2012-11-06"),
                1119
        );
        statistic.addToStatistic(
                "Alexander Abel",
                "Innere Sicherheit",
                DateConverter.convertStringToCalendarFormat("2012-12-11"),
                911
        );
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