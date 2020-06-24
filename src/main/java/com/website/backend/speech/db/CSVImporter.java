package com.website.backend.speech.db;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CSVImporter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ArrayList<String[]> getRows(URL url) {
        ArrayList<String[]> rows = new ArrayList<>();
        try (BufferedInputStream stream = new BufferedInputStream(url.openStream())) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            String[] row;
            while ((row = csvReader.readNext()) != null) rows.add(row);
        } catch (Exception e) {
            logger.error("Error with file: " + url);
        }
        return rows;
    }

}
