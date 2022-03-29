import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;


public class ProduktTests {

    Produkt produkt;
    UUID usedUUIdProdukt1;

    @BeforeEach
    public void setUp() {
        usedUUIdProdukt1 = UUID.randomUUID();
        produkt = new Produkt(usedUUIdProdukt1, "Herr der Ringe", 4999);
    }

    @Test
    public void testNameHolen() {
        Assertions.assertEquals("Herr der Ringe", produkt.getName());
    }

    @Test
    public void testPreisHolen() {
        Assertions.assertEquals(4999, produkt.getPreis());
    }

    @Test
    public void testIDholen() {
        Assertions.assertEquals(usedUUIdProdukt1, produkt.getNummer());
    }

    @Test
    public void testErzeugungNeuesProdukt() {
        UUID id = UUID.randomUUID();
        Produkt produkt2 = new Produkt(id, "Herr der Ringe", 4999);
        Assertions.assertEquals(id, produkt2.getNummer());
        Assertions.assertEquals("Herr der Ringe", produkt2.getName());
        Assertions.assertEquals(4999, produkt2.getPreis());
    }

    @Test
    public void testErzeugungNeuesProduktMitFalschenDaten() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Produkt(null, "Herr der Ringe", 4999));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Produkt(UUID.randomUUID(), null, 4999));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Produkt(UUID.randomUUID(), "", 4999));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Produkt(UUID.randomUUID(), "Herr der Ringe", -1));
    }

    /*
    public void testErzeugungMitMehrernFailsGesammeltInListe()
    {

        ArrayList<Throwable> al = new ArrayList<>();
        try
        {
            Assertions.assertEquals(1,1);
        } catch(AssertionError e) {
            al.add(e);
        }
        try
        {
            Assertions.assertEquals(1,2);
        } catch(AssertionError e) {
            al.add(e);
        }

        if(al.size()>0)
        {
            System.out.println(al);
            fail();
        }
    }
    */

    @TestFactory
    Collection<DynamicTest> testZufaelligKommentareMitFalscherOderRichtigerBewertungHinzufuegen() {

        Produkt p1 = new Produkt(UUID.randomUUID(), "Herr der Ringe", 4999);

        List<DynamicTest> testListe = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int bewertung = random.nextInt(10)+1;
            System.out.println(bewertung);
            if (bewertung > 5) {
                testListe.add(
                        DynamicTest.dynamicTest(
                                "Falsch-Wert-Test",
                                () -> Assertions.assertFalse(p1.kommentarHinzufuegen("Autor", "text", bewertung))));
            } else {
                testListe.add(
                        DynamicTest.dynamicTest(
                                "Korrekt-Wert-Test",
                                () -> Assertions.assertTrue(p1.kommentarHinzufuegen("Autor", "text", bewertung))));
            }
        }

        return testListe;
    }

    @Test
    public void testKommentarMitKorrektenDatenHinzufuegen() {
        Assertions.assertEquals(0, produkt.anzahlKommentare());
        Assertions.assertEquals(true, produkt.kommentarHinzufuegen("Claudio", "Kommentartext", 3));
        Assertions.assertEquals(1, produkt.anzahlKommentare());
    }

    @ParameterizedTest
    @CsvSource({"Claudio,Kommentartext,4", "Hella,Kommentartext 2,1", "Bruno,Mein bester Kommentar,5"})
    public void testKommentareMitDiversenKorrektenDatenHinzufuegen(String autor, String kommentar, int bewertung) {
        Assertions.assertEquals(0, produkt.anzahlKommentare());
        Assertions.assertEquals(true, produkt.kommentarHinzufuegen(autor, kommentar, bewertung));
        Assertions.assertEquals(1, produkt.anzahlKommentare());
        Assertions.assertEquals(bewertung, produkt.findeKommentarVon(autor).gibBewertung());
    }

    @Test
    public void testInfoAusgebenAufKommandozeile() { //Test von CLI-Output
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final ByteArrayOutputStream outErr = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(outErr));

        UUID id = UUID.randomUUID();
        Produkt produkt2 = new Produkt(id, "Herr der Ringe", 4999);
        produkt2.kommentarHinzufuegen("Claudio", "Kommentar", 2);
        produkt2.kommentarHinzufuegen("Hella", "Mein erster Kommentar", 3);
        produkt2.infoAnzeigen();
        Assertions.assertTrue(outContent.toString().contains("Herr der Ringe"));
        Assertions.assertTrue(outContent.toString().contains("Claudio"));
        Assertions.assertTrue(outContent.toString().contains("**"));
        Assertions.assertTrue(outContent.toString().contains("***"));
        //... etc.

        System.setOut(System.out);
        System.setErr(System.err);

        //Hinweis: Test von CLI-Input über Scanner-Klasse:
        /*
         *         String input = "11\n" + "10\n";
         *         cli.start(new Scanner(input)); //Scanner-Objekt mit Input aus dem definierten String
         *         //es wird an die CLI ein Scanner-Objekt übergeben, das von der CLI-Klasse für den Kommandozeilen-Input verwendet wird.
         *         //dieses Scanner-Objekt bekommt den Input aber eben von dem mitgegeben String und nicht von System.in.
         *         Assertions.assertTrue(outContent.toString().contains("Give a number"));
         *         Assertions.assertTrue(outErr.toString().contains("Wrong number, try again!"));
         */
    }
}
