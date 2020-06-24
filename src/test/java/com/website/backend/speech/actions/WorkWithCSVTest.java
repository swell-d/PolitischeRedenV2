package com.website.backend.speech.actions;

import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.db.memory.InMemoryPoliticalSpeeches;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WorkWithCSVTest {

    private WorkWithCSV workWithCSV;
    private PoliticalSpeechRepository speechRepository;

    @Before
    public void setUp() throws Exception {
        speechRepository = new InMemoryPoliticalSpeeches();
        workWithCSV = new WorkWithCSV(speechRepository);
    }

    @Test
    public void shouldDownloadCSVFile() throws MalformedURLException {
        ArrayList<URL> urls = new ArrayList<>() {{
            add(new URL("https://soldering24.ru/google.csv"));
        }};
        workWithCSV.parseSpeechesFromCSVs(urls);
        assertEquals(4, speechRepository.getAllSpeeches().size());
    }
}