package com.website.backend.speech.db.memory;

public class InMemoryPoliticalSpeechesTest {

//    private PoliticalSpeechRepository speeches;
//    private final PoliticalSpeech politicalSpeech = new MockPoliticalSpeechFactory().getSpeech();
//
//    @Before
//    public void setUp() throws Exception {
//        speeches = new MockPoliticalSpeechesFactory().getRepository();
//    }
//
//    @Test
//    public void shouldSaveSpeeches() {
//        assertEquals(4, speeches.getSpeeches().size());
//        speeches.save(politicalSpeech);
//        assertEquals(5, speeches.getSpeeches().size());
//    }
//
//    @Test
//    public void shouldReturnSavedSpeeches() {
//        speeches.save(politicalSpeech);
//        assertTrue(speeches.getSpeeches().contains(politicalSpeech));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void shouldThrowErrorIfSpeechIsAlreadyInRepository() {
//        speeches.save(politicalSpeech);
//        speeches.save(politicalSpeech);
//    }
//
//    @Test
//    public void shouldReturnSpeechesForOneYear() throws ParseException {
//        assertEquals(4, speeches.getSpeechesInYear(2012).size());
//        assertEquals(0, speeches.getSpeechesInYear(2020).size());
//    }
//
//    @Test
//    public void shouldReturnTopicsCountForCertainTopic() throws ParseException {
//        assertEquals(1, speeches.getSpeechesWithTopic("Innere Sicherheit").size());
//        assertEquals(0, speeches.getSpeechesWithTopic("Test topic").size());
//    }
//
//    @Test
//    public void shouldCreateRepositoryFromExternalFile() throws MalformedURLException {
//        speeches.importCSV(null);
//        assertEquals(4, speeches.getSpeeches().size());
//    }
}