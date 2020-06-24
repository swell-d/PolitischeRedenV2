package com.website.backend.speech.db;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CSVImporterTest {

    private final CSVImporter csvImporter = new CSVImporter() {
        @Override
        protected BufferedInputStream getStream(URL url) throws IOException {
            return new BufferedInputStream(new FileInputStream(new File("csv.csv")));
        }
    };

    @Test
    public void shouldCreateRepositoryFromExternalFile() throws MalformedURLException {
        ArrayList<String[]> rows = csvImporter.getRows(null);
        assertEquals(5, rows.size());
    }
}