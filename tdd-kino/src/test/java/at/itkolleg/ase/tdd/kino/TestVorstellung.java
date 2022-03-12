package at.itkolleg.ase.tdd.kino;

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
    }

    @Test
    void testCreateNewVorstellung() {
        vorstellung = new Vorstellung(kinoSaal,Zeitfenster.ABEND, LocalDate.parse("2020-01-08"),"Pulp Fiction", 10.3f);


        assertNotNull(vorstellung, "Vortstellung ist NULL");

        assertEquals(kinoSaal, vorstellung.getSaal(), "Fehler beim Kinosaal checken");
        assertEquals(Zeitfenster.ABEND, vorstellung.getZeitfenster(), "Fehler beim Zeitfenster checken");
        assertEquals(LocalDate.parse("2020-01-08"), vorstellung.getDatum(), "Fehler beim Datum checken");
        assertEquals("Pulp Fiction", vorstellung.getFilm(), "Fehler beim Film checken");
        assertEquals(10.3f, vorstellung.getPreis(), "Fehler beim Preis checken");


    }



}
