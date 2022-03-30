package at.itkolleg.ase.tdd.kino;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.internal.Numbers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class TestKinoVerwaltung {


    private KinoSaal kinoSaal;
    private Vorstellung vorstellung;
    private Vorstellung vorstellung2;
    private KinoVerwaltung kinoVerwaltung;


    @BeforeEach
    void setup() {
        // Kinosaal anlegen
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 10);
        map.put('C', 15);
        kinoSaal = new KinoSaal("KS1", map);

        LocalDate localDate = LocalDate.now();
        kinoVerwaltung = new KinoVerwaltung();

        vorstellung = new Vorstellung(kinoSaal, Zeitfenster.ABEND, localDate, "Pulp Fiction", 10.3f);
        vorstellung2 = new Vorstellung(kinoSaal, Zeitfenster.NACHMITTAG, localDate, "Reservoir Dogs", 8.3f);
    }


    @Test
    void testVorstellungEinplanen() {
        //SoftAssertions soft = new SoftAssertions();

        List<Vorstellung> vorstellungList = kinoVerwaltung.getVorstellungen();

        assertTrue(vorstellungList.isEmpty());

        kinoVerwaltung.einplanenVorstellung(vorstellung);
        kinoVerwaltung.einplanenVorstellung(vorstellung2);

        assertThrows(IllegalArgumentException.class, () -> kinoVerwaltung.einplanenVorstellung(vorstellung), "Vorstellung bereits eingeplant Meldung nicht zurückgegeben");

        assertFalse(vorstellungList.isEmpty());

    }


    @Test
    void testKaufeTicket() {
        Ticket ticket = kinoVerwaltung.kaufeTicket(vorstellung, 'A', 3, 30);

        assertTrue(ticket instanceof Ticket);

        assertAll("tickets",
                () -> assertEquals('A', ticket.getReihe(), "Fehler bei Reihe"),
                () -> assertEquals(3, ticket.getPlatz(), "Fehler beim Platz"),
                () -> assertEquals(ticket.getDatum(), vorstellung.getDatum(), "Fehler beim Datum"),
                () -> assertEquals(ticket.getZeitfenster(), vorstellung.getZeitfenster(), "Fehler beim Zeitfenster"),
                () -> assertEquals(ticket.getSaal(), vorstellung.getSaal().getName(), "Fehler beim Saal")
        );


    }


    @ParameterizedTest
    @CsvSource({
            "A, 3, 25",
            "B, 1, 15",
            "C, 4, 14",
            "A, 5, 50",
            "A, 8, 30",
            "A, 2, 22"
    })
    void testKaufeTicketsParam(char reihe, int platz, float geld) {
        Ticket ticket = new Ticket(kinoSaal.getName(), Zeitfenster.ABEND, LocalDate.now(), reihe, platz);
        Ticket vorstellungTicket = vorstellung.kaufeTicket(reihe, platz, geld);

        assertAll("tickets",
                () -> assertEquals(ticket.getSaal(), vorstellungTicket.getSaal(), "falscher Saal"),
                () -> assertEquals(ticket.getZeitfenster(), vorstellungTicket.getZeitfenster(), "falsches Zeitfenster"),
                () -> assertEquals(ticket.getDatum(), vorstellungTicket.getDatum(), "falsches Datum"),
                () -> assertEquals(ticket.getReihe(), vorstellungTicket.getReihe(), "falsche Reuhe"),
                () -> assertEquals(ticket.getPlatz(), vorstellungTicket.getPlatz(), "falscher Platz")
        );
    }





    @ParameterizedTest
    @CsvSource({
            "A, 33, 22",
            "N, 1, 15",
            "B, 4, 1",
            "G, 5, 50",
            "J, 8, 30",
            "C, 20, 2"
    })
    void testExpectedExceptionIllegalArgument(char reihe, int platz, float geld) {

        assertThrows(IllegalArgumentException.class, () -> {
            vorstellung.kaufeTicket(reihe, platz, geld);
        });

    }


    @Test
    void testIllegalStateException() {
        vorstellung.kaufeTicket('A', 5, 33);

        assertThrows(IllegalStateException.class, () -> {
            vorstellung.kaufeTicket('A', 5, 33);
        });

    }




}
