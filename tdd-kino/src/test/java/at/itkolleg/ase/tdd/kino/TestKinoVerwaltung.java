package at.itkolleg.ase.tdd.kino;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.internal.Numbers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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

        kinoVerwaltung = new KinoVerwaltung();

        vorstellung = new Vorstellung(kinoSaal,Zeitfenster.ABEND, LocalDate.parse("2020-01-08"),"Pulp Fiction", 10.3f);
        vorstellung2 = new Vorstellung(kinoSaal,Zeitfenster.NACHMITTAG, LocalDate.parse("2022-01-18"),"Reservoir Dogs", 8.3f);
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


        assertEquals('A', ticket.getReihe(), "Fehler bei Reihe");
        assertEquals(3,ticket.getPlatz(), "Fehler beim Platz");
        assertEquals(ticket.getDatum(), vorstellung.getDatum(), "Fehler beim Datum");
        assertEquals(ticket.getZeitfenster(), vorstellung.getZeitfenster(), "Fehler beim Zeitfenster");
        assertEquals(ticket.getSaal(), vorstellung.getSaal().getName(), "Fehler beim Saal");

    }












}
