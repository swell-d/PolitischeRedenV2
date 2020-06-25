package com.website.backend.speech.actions;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PoliticianStatisticTest {

    private final PoliticianStatistic politicianStatistic = new PoliticianStatistic() {
        @Override
        protected BufferedInputStream getStream(URL url) throws IOException {
            return new BufferedInputStream(new FileInputStream(new File("csv.csv")));
        }
    };

    @Test
    public void shouldReturnStatisticResponse() throws MalformedURLException {
        StatisticResponse expected = new StatisticResponse(
                null,
                "Alexander Abel",
                "Caesare Collins"
        );
        ArrayList<URL> fakeUrls = new ArrayList<>() {{
            add(new URL("http://localhost"));
        }};
        assertEquals(expected, politicianStatistic.getStatistic(fakeUrls));
    }

}