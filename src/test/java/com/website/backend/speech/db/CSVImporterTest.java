package com.website.backend.speech.db;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CSVImporterTest {

    private final CSVImporter csvImporter = new CSVImporter();

    @Test
    public void shouldCreateRepositoryFromExternalFile() throws MalformedURLException {
        ArrayList<String[]> rows = csvImporter.getRows(new URL("https://soldering24.ru/google.csv"));
        assertEquals(5, rows.size());
    }
}