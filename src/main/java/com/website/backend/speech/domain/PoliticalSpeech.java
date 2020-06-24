package com.website.backend.speech.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class PoliticalSpeech {
    public String speaker;
    public String topic;
    public Calendar date;
    public int words;

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PoliticalSpeech(String speaker, String topic, Calendar date, int words) {
        this.speaker = speaker;
        this.topic = topic;
        this.date = date;
        this.words = words;
    }

    public String getDateAsText() {
        return dateFormat.format(this.date.getTime());
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
