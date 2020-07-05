package com.website.backend.speech.actions;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;

public class CSVParser {

    private final Statistics statistics;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CSVParser(Statistics statistics) {
        this.statistics = statistics;
    }

    public StatisticResponse getStatistic(ArrayList<URL> urls) {
        for (URL url : urls) parseFile(url);
        return statistics.calculateResult();
    }

    private void parseFile(URL url) {
        try (BufferedInputStream stream = getStream(url)) {
            parseStream(stream);
        } catch (Exception e) {
            logger.error("Error with file: " + url + ". File skipped", e);
        }
    }

    private void parseStream(BufferedInputStream stream) throws IOException, CsvValidationException, ParseException {
        CSVReader csvReader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        checkFirstRow(csvReader.readNext());
        parseRows(csvReader);
    }

    private void parseRows(CSVReader csvReader) throws IOException, CsvValidationException, ParseException {
        String[] row;
        while ((row = csvReader.readNext()) != null) {
            if (row.length == 4) statistics.add(row);
        }
    }

    protected BufferedInputStream getStream(URL url) throws IOException {
        return new BufferedInputStream(url.openStream());
    }

    private void checkFirstRow(String[] row) {
        if (row == null || !titleRowIsCorrect(row)) throw new IllegalArgumentException();
    }

    private boolean titleRowIsCorrect(String[] row) {
        return row.length == 4 & row[0].trim().equals("Redner") & row[1].trim().equals("Thema") &
                row[2].trim().equals("Datum") & row[3].trim().equals("Wörter");
    }

}
