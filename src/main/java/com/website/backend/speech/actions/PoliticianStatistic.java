package com.website.backend.speech.actions;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PoliticianStatistic {

    private CollectStatistic statistic;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public StatisticResponse getStatistic(ArrayList<URL> urls) {
        statistic = new CollectStatistic();
        for (URL url : urls) parseFile(url);
        return statistic.calculateResult();
    }

    private void parseFile(URL url) {
        try (BufferedInputStream stream = getStream(url)) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            String[] row = csvReader.readNext();
            if (row == null) throw new IllegalArgumentException();
            if (!titleRowIsCorrect(row)) return;
            while ((row = csvReader.readNext()) != null) parseRow(row);
        } catch (Exception e) {
            logger.error("Error with file: " + url);
        }
    }

    protected BufferedInputStream getStream(URL url) throws IOException {
        return new BufferedInputStream(url.openStream());
    }

    private boolean titleRowIsCorrect(String[] row) {
        try {
            String speaker = row[0].trim();
            String topic = row[1].trim();
            String date = row[2].trim();
            String words = row[3].trim();
            if (row.length == 4 & speaker.equals("Redner") & topic.equals("Thema") & date.equals("Datum") & words.equals("Wörter"))
                return true;
            throw new IllegalArgumentException();
        } catch (Exception e) {
            logger.error("Wrong title row. File skipped");
        }
        return false;
    }

    private void parseRow(String[] row) {
        try {
            if (row.length != 4) throw new IllegalArgumentException();
            extractDataFromRowAndCollectStatistic(row);
        } catch (Exception e) {
            logger.error("Wrong data in row");
        }
    }

    private void extractDataFromRowAndCollectStatistic(String[] row) {
        String speaker = row[0].trim();
        String topic = row[1].trim();
        String date = row[2].trim();
        String words = row[3].trim();
        statistic.addToStatistic(speaker, topic, date, words);
    }

}
