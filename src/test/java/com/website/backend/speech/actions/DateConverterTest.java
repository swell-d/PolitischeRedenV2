package com.website.backend.speech.actions;

import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class DateConverterTest {
    @Test
    public void shouldReturnCorrectValue() throws ParseException {
        assertEquals(
                new GregorianCalendar(2020, Calendar.JUNE, 23),
                DateConverter.convertStringToCalendarFormat("2020-06-23")
        );
    }
}