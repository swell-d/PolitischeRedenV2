package com.website.backend.speech.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class WorkWithURL {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ArrayList<URL> parseParameters(Map<String, String> parameters) {
        ArrayList<URL> urls = new ArrayList<>();
        if (parameters == null) return urls;
        for (String url : parameters.values()) {
            try {
                String urlAsText = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8);
                urls.add(new URL(urlAsText));
            } catch (Exception e) {
                logger.error("Can't decode link: " + url);
            }
        }
        return urls;
    }
}
