package com.website.backend.speech.actions;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PoliticianStatisticTest {

    private final PoliticianStatistic politicianStatistic = new PoliticianStatistic();

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