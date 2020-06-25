package com.website.backend.speech.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class PoliticalSpeech {
    public String speaker;
    public String topic;
    public Calendar date = Calendar.getInstance();
    public int words;

    public PoliticalSpeech(String speaker, String topic, String date, String words) throws ParseException {
        this.speaker = speaker.trim();
        this.topic = topic.trim();
        this.date.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date.trim()));
        this.words = Integer.parseInt(words.trim());
    }

    public PoliticalSpeech(String... row) throws ParseException {
        this.speaker = row[0].trim();
        this.topic = row[1].trim();
        this.date.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(row[2].trim()));
        this.words = Integer.parseInt(row[3].trim());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoliticalSpeech that = (PoliticalSpeech) o;
        return words == that.words &&
                Objects.equals(speaker, that.speaker) &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speaker, topic, date, words);
    }
}

