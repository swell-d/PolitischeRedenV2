package com.website.backend.speech.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpeakerTest {

    private Speaker speaker;

    @Before
    public void setUp() throws Exception {
        speaker = new Speaker("John Smith");
    }

    @Test
    public void shouldReturnName() {
        assertEquals("John Smith", speaker.name);
    }
}