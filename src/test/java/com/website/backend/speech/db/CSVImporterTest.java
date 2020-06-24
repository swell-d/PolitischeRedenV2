package com.website.backend.speech.db;

import com.website.backend.speech.db.memory.InMemoryPoliticalSpeeches;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class CSVImporterTest {

    private com.website.backend.speech.db.CSVImporter CSVImporter;
    private PoliticalSpeechRepository speechRepository;

    @Before
    public void setUp() {
        speechRepository = new InMemoryPoliticalSpeeches();
        CSVImporter = new CSVImporter(speechRepository);
    }

    @Test
    public void shouldCreateRepositoryFromExternalFile() throws MalformedURLException {
        CSVImporter.addSpeechesToRepository(new URL("https://soldering24.ru/google.csv"));
        assertEquals(4, speechRepository.size());
    }
}