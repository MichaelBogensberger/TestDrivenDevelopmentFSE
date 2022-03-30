package at.itkolleg.ase.tdd.kino;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class TestFactory {

    private KinoSaal kinosaal;
    private Vorstellung vorstellung;
    private Vorstellung vorstellung2;
    private KinoVerwaltung kinoVerwaltung;



    @BeforeEach
    void setup() {
        //Saal anlegen
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 20);
        map.put('B', 10);
        map.put('C', 15);
        kinosaal = new KinoSaal("KS2", map);

        //Vorsellung anlegen
        LocalDate localDate = LocalDate.now();
        vorstellung = new Vorstellung(kinosaal, Zeitfenster.NACHT, localDate, "Der Pate", 10.50F);
        vorstellung2 = new Vorstellung(kinosaal, Zeitfenster.ABEND, localDate, "GoodFellas", 7.50F);

        //KinoVerwaltung anlegen
        kinoVerwaltung = new KinoVerwaltung();
        kinoVerwaltung.einplanenVorstellung(vorstellung);
        kinoVerwaltung.einplanenVorstellung(vorstellung2);
    }


    @org.junit.jupiter.api.TestFactory
    public Collection<DynamicTest> kaufeTicketCollection() {

        List<DynamicTest> testListe = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            Vorstellung vorstellung = kinoVerwaltung.getVorstellungen().get(i % 2);
            char reihe = (char) ((i % 3) + 65);
            int platz = i % 12;
            int geld = i;

            testListe.add(dynamicTest(vorstellung.getFilm() + ", " + reihe + platz + ", " + geld + "€",
                    () -> {
                        try {
                            kinoVerwaltung.kaufeTicket(vorstellung, reihe, platz, geld);
                            System.out.println("Erfolgreich");
                        } catch (IllegalArgumentException e) {
                            boolean errGeld = "Nicht ausreichend Geld.".equals(e.getMessage());
                            boolean errPlatz = e.getMessage().contains("existiert nicht");
                            assertTrue(errGeld || errPlatz);
                            System.out.println("Execption: " + e.getMessage());
                        } catch (IllegalStateException e) {
                            assertTrue(e.getMessage().contains("ist bereits belegt."));
                            System.out.println("Execption: " + e.getMessage());
                        }
                    }));
        }

        /*
        for (DynamicTest dynamicTest : testListe) {
            System.out.println(dynamicTest.getDisplayName());
        }
        */


        return testListe;
    }






}
