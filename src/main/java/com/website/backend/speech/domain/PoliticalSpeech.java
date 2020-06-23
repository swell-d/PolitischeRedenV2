package com.website.backend.speech.domain;

import com.website.backend.speaker.domain.Speaker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PoliticalSpeech {
    public Speaker speaker;
    public String topic;
    public Calendar date;
    public int words;

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PoliticalSpeech(Speaker speaker, String topic, Calendar date, int words) {
        this.speaker = speaker;
        this.topic = topic;
        this.date = date;
        this.words = words;
    }

    public String getSpeakerName() {
        return this.speaker.name;
    }

    public String getDateAsText() {
        return dateFormat.format(this.date.getTime());
    }

}
