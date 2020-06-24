package com.website.backend.speech.db.memory;

import com.website.backend.speech.db.CSVImporter;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.domain.DateConverter;
import com.website.backend.speech.domain.PoliticalSpeech;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class InMemoryPoliticalSpeeches implements PoliticalSpeechRepository {

    private final ArrayList<PoliticalSpeech> speeches = new ArrayList<PoliticalSpeech>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public PoliticalSpeechRepository createRepository() {
        return new InMemoryPoliticalSpeeches();
    }

    @Override
    public void save(PoliticalSpeech speech) {
        if (this.speeches.contains(speech)) throw new IllegalArgumentException("Speech is already in repository.");
        this.speeches.add(speech);
    }

    @Override
    public ArrayList<PoliticalSpeech> getAllSpeeches() {
        return this.speeches;
    }

    @Override
    public void clear() {
        this.speeches.clear();
    }

    @Override
    public int size() {
        return this.speeches.size();
    }

    @Override
    public ArrayList<PoliticalSpeech> getSpeechesInYear(int year) {
        ArrayList<PoliticalSpeech> filteredSpeeches = new ArrayList<>();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.date.get(Calendar.YEAR) == year) filteredSpeeches.add(speech);
        }
        return filteredSpeeches;
    }

    @Override
    public ArrayList<PoliticalSpeech> getSpeechesBySpeaker(String speakerName) {
        ArrayList<PoliticalSpeech> filteredSpeeches = new ArrayList<>();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.speaker.equals(speakerName)) filteredSpeeches.add(speech);
        }
        return filteredSpeeches;
    }

    @Override
    public ArrayList<PoliticalSpeech> getSpeechesWithTopic(String topic) {
        ArrayList<PoliticalSpeech> filteredSpeeches = new ArrayList<>();
        for (PoliticalSpeech speech : this.speeches) {
            if (speech.topic.equals(topic)) filteredSpeeches.add(speech);
        }
        return filteredSpeeches;
    }

    @Override
    public int wordsCount() {
        int count = 0;
        for (PoliticalSpeech speech : this.speeches) count += speech.words;
        return count;
    }

    @Override
    public void importCSV(URL url) {
        ArrayList<String[]> rows = new CSVImporter().getRows(url);
        if (rows.size() == 0 | !titleRowIsCorrect(rows.remove(0))) return;
        addRowsToRepository(rows);
    }

    private void addRowsToRepository(ArrayList<String[]> rows) {
        for (String[] row : rows) {
            try {
                if (row.length != 4) throw new IllegalArgumentException();
                saveDataRowToRepository(row);
            } catch (Exception e) {
                logger.error("Wrong data in row");
            }
        }
    }

    @Override
    public void saveDataRowToRepository(String[] row) throws ParseException {
        String speaker = row[0].trim();
        String topic = row[1].trim();
        Calendar date = DateConverter.convertStringToCalendarFormat(row[2].trim());
        int words = Integer.parseInt(row[3].trim());
        this.save(new PoliticalSpeech(speaker, topic, date, words));
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
}
