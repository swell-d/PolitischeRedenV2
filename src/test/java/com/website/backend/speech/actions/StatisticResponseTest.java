package com.website.backend.speech.actions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatisticResponseTest {

    @Test
    public void shouldCreateNewObject() {
        StatisticResponse statisticResponse = new StatisticResponse("Test1", "Test2", "Test3");
        assertEquals("Test1", statisticResponse.mostSpeeches);
        assertEquals("Test2", statisticResponse.mostSecurity);
        assertEquals("Test3", statisticResponse.leastWordy);
    }

    @Test
    public void shouldBeEquals() {
        StatisticResponse statisticResponse1 = new StatisticResponse("Test1", "Test2", "Test3");
        StatisticResponse statisticResponse2 = new StatisticResponse("Test1", "Test2", "Test3");
        assertEquals(statisticResponse1, statisticResponse2);
        assertEquals(statisticResponse1.hashCode(), statisticResponse2.hashCode());
    }
}