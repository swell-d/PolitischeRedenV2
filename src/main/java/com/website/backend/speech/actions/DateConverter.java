package com.website.backend.speech.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateConverter {
    public static Calendar convertStringToCalendarFormat(String dateInTextFormat) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateInTextFormat));
        return cal;
    }
}
