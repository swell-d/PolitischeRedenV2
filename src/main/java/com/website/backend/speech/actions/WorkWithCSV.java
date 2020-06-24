package com.website.backend.speech.actions;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class WorkWithCSV {

    public ArrayList<URL> parseParameters(Map<String, String> parameters) {
        ArrayList<URL> urls = new ArrayList<>();
        for (String url : parameters.values()) {
            try {
                String urlAsText = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8);
                urls.add(new URL(urlAsText));
            } catch (Exception e) {
            }
        }
        return urls;
    }
}
