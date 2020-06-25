package com.website.backend.speech.actions;

import com.opencsv.CSVReader;
import com.website.backend.speech.domain.PoliticalSpeech;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PoliticianStatistic {

    private final CollectStatistic statistic;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public PoliticianStatistic(CollectStatistic statistic) {
        this.statistic = statistic;
    }

    public StatisticResponse getStatistic(ArrayList<URL> urls) {
        for (URL url : urls) parseFile(url);
        return statistic.calculateResult();
    }

    private void parseFile(URL url) {
        try (BufferedInputStream stream = getStream(url)) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            String[] row = csvReader.readNext();
            checkFirstRow(row);
            while ((row = csvReader.readNext()) != null) {
                if (row.length == 4) statistic.addToStatistic(new PoliticalSpeech(row));
            }
        } catch (Exception e) {
            logger.error("Error with file: " + url + ". File skipped");
        }
    }

    protected BufferedInputStream getStream(URL url) throws IOException {
        return new BufferedInputStream(url.openStream());
    }

    private void checkFirstRow(String[] row) {
        if (row == null) throw new IllegalArgumentException();
        if (!titleRowIsCorrect(row)) throw new IllegalArgumentException();
    }

    private boolean titleRowIsCorrect(String[] row) {
        return row.length == 4 & row[0].trim().equals("Redner") & row[1].trim().equals("Thema") &
                row[2].trim().equals("Datum") & row[3].trim().equals("Wörter");
    }

}
