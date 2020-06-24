package com.website.backend.speech.db;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CSVImporter {

    private final PoliticalSpeechRepository speechRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CSVImporter(PoliticalSpeechRepository speechRepository) {
        this.speechRepository = speechRepository;
    }

    public void addSpeechesToRepository(URL url) {
        try (BufferedInputStream stream = new BufferedInputStream(url.openStream())) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            String[] row;
            if (!titleRowIsCorrect(csvReader.readNext())) return;
            while ((row = csvReader.readNext()) != null) parseRow(row);
        } catch (Exception e) {
            logger.error("Error with file: " + url);
        }
    }

    private boolean titleRowIsCorrect(String[] row) {
        try {
            String speaker = row[0].trim();
            String topic = row[1].trim();
            String date = row[2].trim();
            String words = row[3].trim();
            if (row.length == 4 & speaker.equals("Redner") & topic.equals("Thema") & date.equals("Datum") & words.equals("Wörter"))
                return true;
            throw new IllegalArgumentException("Wrong title row");
        } catch (Exception e) {
            logger.error("Wrong title row");
        }
        return false;
    }

    private void parseRow(String[] row) {
        try {
            if (row.length != 4) throw new IllegalArgumentException("Wrong data in row");
            speechRepository.saveDataRowToRepository(row);
        } catch (Exception e) {
            logger.error("Wrong data in row");
        }
    }
}
