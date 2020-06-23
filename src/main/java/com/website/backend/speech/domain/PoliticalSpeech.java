package com.website.backend.speech.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PoliticalSpeech {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public String topic;
    public Calendar date;
    public int words;
    public Speaker speaker;

    public String getSpeakerName() {
        return this.speaker.name;
    }

    public String getDateAsText() {
        return dateFormat.format(this.date.getTime());
    }
}
