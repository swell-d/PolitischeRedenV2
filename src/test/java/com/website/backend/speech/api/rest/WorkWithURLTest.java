package com.website.backend.speech.api.rest;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class WorkWithURLTest {

    private final WorkWithURL workWithURL = new WorkWithURL();

    @Test
    public void shouldReturnCorrectUrls() throws MalformedURLException {
        Map<String, String> parameters = new HashMap<String, String>() {{
            put("url1", "https%3A%2F%2Fmywebsite%2Fdocs%2Fenglish%2Fsite%2Fmybook.do%3Frequest_type");
            put("url2", "https%3A%2F%2Fwww.google.com%2Fsearch%3Fq%3Djava%2Bunqoute%2Burl%26oq%3Djava%2Bunqoute%2Burl%26aqs%3Dchrome..69i57.5963j0j7%26sourceid%3Dchrome%26ie%3DUTF-8");
        }};
        URL url1 = new URL("https://mywebsite/docs/english/site/mybook.do?request_type");
        URL url2 = new URL("https://www.google.com/search?q=java+unqoute+url&oq=java+unqoute+url&aqs=chrome..69i57.5963j0j7&sourceid=chrome&ie=UTF-8");
        assertTrue(workWithURL.parseParameters(parameters).contains(url1));
        assertTrue(workWithURL.parseParameters(parameters).contains(url2));
    }

}