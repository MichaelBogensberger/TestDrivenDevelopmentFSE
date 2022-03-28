package at.itkolleg.ase.tdd.kino;

import org.junit.Assume;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestFactory {

    private static KinoSaal kinoSaal;
    private static Vorstellung vorstellung;
    private static KinoVerwaltung kinoVerwaltung;

    @BeforeAll
    static void setup() {
        // Kinosaal anlegen
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 34);
        map.put('B', 22);
        map.put('C', 24);
        map.put('D', 23);
        map.put('E', 34);
        map.put('F', 15);
        map.put('G', 15);
        kinoSaal = new KinoSaal("KS1", map);

        kinoVerwaltung = new KinoVerwaltung();

        vorstellung = new Vorstellung(kinoSaal,Zeitfenster.ABEND, LocalDate.parse("2020-01-08"),"Pulp Fiction", 10.3f);
    }



    private static List values() {

        ArrayList<Arguments> values = new ArrayList<>();


        Random rd = new Random();
        //String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String abc = "ABCDEFGHIJKLMNO";

        for(int i = 0; i < 2000; i++) {
            char letter = abc.charAt(rd.nextInt(abc.length()));
            int platz = rd.nextInt(20);

            float geld = rd.nextFloat(35);
            geld = ((int) ((geld + 0.005f) * 100)) / 100f;

            System.out.println(letter + "  " + platz + "  " + geld);



            values.add(arguments(vorstellung, letter, platz, geld));
        }




        return values;

    }






    @ParameterizedTest
    @MethodSource("values")
    void testKaufeTicketsBomb(Vorstellung vorstellungP, char reihe, int platz, float geld) {

        try {
            Ticket ticket = kinoVerwaltung.kaufeTicket(vorstellungP, reihe, platz, geld);
            System.out.println("Erfolgreich");
        } catch (IllegalArgumentException e) {
            System.out.println("Exeption thrown: " + e);

        } catch (IllegalStateException e) {
            System.out.println("Exeption thrown: " + e);

        }




        /*
        System.out.println("---- Parameter ----");
        System.out.println("Vorstellung: " + vorstellungP);
        System.out.println("Reihe: " + reihe);
        System.out.println("Platz: " + platz);
        System.out.println("Geld: " + geld);
        System.out.println("-------------------");
        */


    /*
        assertEquals(reihe, ticket.getReihe(), "Fehler bei Reihe");
        assertEquals(platz,ticket.getPlatz(), "Fehler beim Platz");
        assertEquals(ticket.getDatum(), vorstellungP.getDatum(), "Fehler beim Datum");
        assertEquals(ticket.getZeitfenster(), vorstellungP.getZeitfenster(), "Fehler beim Zeitfenster");
        assertEquals(ticket.getSaal(), vorstellungP.getSaal().getName(), "Fehler beim Saal");
    */


    }





}
