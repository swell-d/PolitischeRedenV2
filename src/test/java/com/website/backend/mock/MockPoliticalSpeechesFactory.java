package com.website.backend.mock;

import com.website.backend.speech.db.CSVImporter;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.db.memory.InMemoryPoliticalSpeeches;
import com.website.backend.speech.domain.DateConverter;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

public class MockPoliticalSpeechesFactory {

    public PoliticalSpeechRepository getRepository() throws ParseException {
        CSVImporter csvImporter = new CSVImporter() {
            @Override
            protected BufferedInputStream getStream(URL url) throws IOException {
                return new BufferedInputStream(new FileInputStream(new File("csv.csv")));
            }
        };
        PoliticalSpeechRepository speeches = new InMemoryPoliticalSpeeches() {
            @Override
            protected ArrayList<String[]> getRowsFromWeb(URL url) {
                return csvImporter.getRows(null);
            }
        };
        speeches.save(new PoliticalSpeech(
                "Alexander Abel",
                "Bildungspolitik",
                DateConverter.convertStringToCalendarFormat("2012-10-30"),
                5310
        ));
        speeches.save(new PoliticalSpeech(
                "Bernhard Belling",
                "Kohlesubventionen",
                DateConverter.convertStringToCalendarFormat("2012-11-05"),
                1210
        ));
        speeches.save(new PoliticalSpeech(
                "Caesare Collins",
                "Kohlesubventionen",
                DateConverter.convertStringToCalendarFormat("2012-11-06"),
                1119
        ));
        speeches.save(new PoliticalSpeech(
                "Alexander Abel",
                "Innere Sicherheit",
                DateConverter.convertStringToCalendarFormat("2012-12-11"),
                911
        ));
        return speeches;
    }

}
