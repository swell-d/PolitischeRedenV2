package com.website.backend.speech.actions;

import com.opencsv.CSVReader;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.domain.DateConverter;
import com.website.backend.speech.domain.PoliticalSpeech;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class WorkWithCSV {

    private final PoliticalSpeechRepository speechRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public WorkWithCSV(PoliticalSpeechRepository speechRepository) {
        this.speechRepository = speechRepository;
    }

    public void parseSpeechesFromCSVs(ArrayList<URL> urls) {
        for (URL url : urls) {
            addSpeechesToRepository(url);
        }
    }

    private void addSpeechesToRepository(URL url) {
        try (BufferedInputStream stream = new BufferedInputStream(url.openStream())) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            String[] row;
            while ((row = csvReader.readNext()) != null) parseRow(row);
        } catch (Exception e) {
            logger.error("Error with file: " + url);
        }
    }

    private void parseRow(String[] row) {
        try {
            if (rowIsTitle(row)) return;
            if (row.length != 4) throw new IllegalArgumentException("Wrong data in row");
            saveDataToRepository(row);
        } catch (Exception e) {
            logger.error("Wrong data in row: " + row[0] + "," + row[1] + "," + row[2] + "," + row[3]);
        }
    }

    private boolean rowIsTitle(String[] row) {
        return row[0].equals("Redner");
    }

    private void saveDataToRepository(String[] row) throws ParseException {
        String speaker = row[0].trim();
        String topic = row[1].trim();
        Calendar date = DateConverter.convertStringToCalendarFormat(row[2].trim());
        int words = Integer.parseInt(row[3].trim());
        speechRepository.save(new PoliticalSpeech(speaker, topic, date, words));
    }
}
