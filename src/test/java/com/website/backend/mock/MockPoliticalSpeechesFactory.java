package com.website.backend.mock;

import com.website.backend.speaker.db.SpeakerRepository;
import com.website.backend.speech.db.PoliticalSpeechRepository;
import com.website.backend.speech.db.memory.InMemoryPoliticalSpeeches;
import com.website.backend.speech.domain.DateConverter;
import com.website.backend.speech.domain.PoliticalSpeech;

import java.text.ParseException;

public class MockPoliticalSpeechesFactory {

    private SpeakerRepository speakers = new MockSpeakersFactory().getSpeakerRepository();

    public PoliticalSpeechRepository getRepository() throws ParseException {
        PoliticalSpeechRepository speeches = new InMemoryPoliticalSpeeches();
        speeches.save(new PoliticalSpeech(
                speakers.findByName("Alexander Abel"),
                "Bildungspolitik",
                DateConverter.convertStringToCalendarFormat("2012-10-30"),
                5310
        ));
        speeches.save(new PoliticalSpeech(
                speakers.findByName("Bernhard Belling"),
                "Kohlesubventionen",
                DateConverter.convertStringToCalendarFormat("2012-11-05"),
                1210
        ));
        speeches.save(new PoliticalSpeech(
                speakers.findByName("Caesare Collins"),
                "Kohlesubventionen",
                DateConverter.convertStringToCalendarFormat("2012-11-06"),
                1119
        ));
        speeches.save(new PoliticalSpeech(
                speakers.findByName("Alexander Abel"),
                "Innere Sicherheit",
                DateConverter.convertStringToCalendarFormat("2012-12-11"),
                911
        ));
        return speeches;
    }

}
