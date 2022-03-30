package at.itkolleg.ase.tdd.kino.old;

import at.itkolleg.ase.tdd.kino.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestKinoVerwaltunParamOld {

    private static KinoSaal kinoSaal;
    private static Vorstellung vorstellung;
    private static Vorstellung vorstellung2;
    private static KinoVerwaltung kinoVerwaltung;

    @BeforeAll
    static void setup() {
        // Kinosaal anlegen
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 10);
        map.put('C', 15);
        kinoSaal = new KinoSaal("KS1", map);

        kinoVerwaltung = new KinoVerwaltung();

        vorstellung = new Vorstellung(kinoSaal, Zeitfenster.ABEND, LocalDate.parse("2020-01-08"),"Pulp Fiction", 10.3f);
        vorstellung2 = new Vorstellung(kinoSaal,Zeitfenster.NACHMITTAG, LocalDate.parse("2022-01-18"),"Reservoir Dogs", 8.3f);
    }


    private static List values() {

        ArrayList<Arguments> values = new ArrayList<>();

        values.add(arguments(vorstellung, 'A', 3, 20.3f));
        values.add(arguments(vorstellung, 'B', 1, 34.0f));
        values.add(arguments(vorstellung2, 'C', 11, 24.2f));

        return values;

        /*
        return new Object[][] {
                {vorstellung, 'A', 3, 20.3f},
                {vorstellung, 'B', 1, 34.0f},
                {vorstellung2, 'C', 11, 24.2f},
        };*/
    }



    /*
    private static Stream values() {

        return Stream.of(
                arguments(vorstellung, 'A', 3, 20.3f),
                arguments(vorstellung, 'B', 1, 34.0f),
                arguments(vorstellung2, 'C', 11, 24.2f)
        );
    } */


    @ParameterizedTest
    @MethodSource("values")
    void testKaufeTicketsParameterized(Vorstellung vorstellungP, char reihe, int platz, float geld) {
        Ticket ticket = kinoVerwaltung.kaufeTicket(vorstellungP, reihe, platz, geld);
        assertTrue(ticket instanceof Ticket);

        System.out.println("---- Parameter ----");
        System.out.println("Vorstellung: " + vorstellungP);
        System.out.println("Reihe: " + reihe);
        System.out.println("Platz: " + platz);
        System.out.println("Geld: " + geld);
        System.out.println("-------------------");

        assertEquals(reihe, ticket.getReihe(), "Fehler bei Reihe");
        assertEquals(platz,ticket.getPlatz(), "Fehler beim Platz");
        assertEquals(ticket.getDatum(), vorstellungP.getDatum(), "Fehler beim Datum");
        assertEquals(ticket.getZeitfenster(), vorstellungP.getZeitfenster(), "Fehler beim Zeitfenster");
        assertEquals(ticket.getSaal(), vorstellungP.getSaal().getName(), "Fehler beim Saal");

    }



}
