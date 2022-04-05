package at.itkolleg.ase.tdd.kino;

import com.sun.jdi.IncompatibleThreadStateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;


public class TestVorstellung {

    private KinoSaal kinoSaal;
    private KinoSaal kinoSaal1;
    private Vorstellung vorstellung;


    @BeforeEach
    void setup() {
        // Kinosaal anlegen
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 10);
        map.put('C', 15);
        kinoSaal = new KinoSaal("KS1", map);
        kinoSaal1 = new KinoSaal("KS2", map);

        vorstellung = new Vorstellung(kinoSaal,Zeitfenster.ABEND, LocalDate.parse("2020-01-08"),"Pulp Fiction", 10.3f);
    }

    @Test
    void testCreateNewVorstellung() {

        Vorstellung vorstellung1 = new Vorstellung(kinoSaal,Zeitfenster.ABEND, LocalDate.parse("2020-01-08"),"Pulp Fiction", 10.3f);

        assertNotNull(vorstellung1, "Vortstellung ist NULL");
        assertTrue(vorstellung1 instanceof Vorstellung, "Vorstellung ist nicht eine Insatz einer Vorstellung");


        assertAll("check parameters",
                () ->  assertEquals(kinoSaal, vorstellung1.getSaal(), "Fehler beim Kinosaal"),
                () -> assertEquals(Zeitfenster.ABEND, vorstellung1.getZeitfenster(), "Fehler beim Zeitfenster"),
                () -> assertEquals(LocalDate.parse("2020-01-08"), vorstellung1.getDatum(), "Fehler beim Datum"),
                () -> assertEquals("Pulp Fiction", vorstellung1.getFilm(), "Fehler beim Film"),
                () -> assertEquals(10.3f, vorstellung1.getPreis(), "Fehler beim Preis")
        );



    }

    @Test
    void testKaufeTicket() {
        Ticket ticket = vorstellung.kaufeTicket('A',7,30);
        assertTrue(ticket instanceof Ticket);

        // Teste Assertions
        assertAll("Execption",
                () -> assertThrows(IllegalArgumentException.class, () -> vorstellung.kaufeTicket('A',7,2),
                        "Nicht genug Geld Fehlermeldung nicht zurückgegeben"),
                () -> assertThrows(IllegalArgumentException.class, () -> vorstellung.kaufeTicket('A',30,30),
                        "Platz Fehlermeldung nicht zurückgegeben"),
                () -> assertThrows(IllegalArgumentException.class, () -> vorstellung.kaufeTicket('G',3,30),
                        "Reihe Fehlermeldung nicht zurückgegeben"),
                () -> assertThrows(IllegalStateException.class, () -> vorstellung.kaufeTicket('A',7,30),
                        "Platz bereits belegt Meldung nicht zurückgegeben")

        );

    }


    @Test
    void testIfVorstellungIsInstanceOfVorstellung() {
        assertTrue(vorstellung.equals(vorstellung));
        assertFalse(vorstellung.equals(new Object()));
    }




}
