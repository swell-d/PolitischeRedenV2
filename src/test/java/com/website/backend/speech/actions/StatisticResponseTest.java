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
}