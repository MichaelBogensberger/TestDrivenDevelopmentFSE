package at.itkolleg.ase.tdd.kino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class TestKinoSaal {

    @Mock
    private KinoSaal kinosaalMock;

    private KinoSaal kinoSaal;

    @BeforeEach
    void setup() {
        //Saal anlegen
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 10);
        map.put('B', 10);
        map.put('C', 15);
        kinoSaal = new KinoSaal("KS1", map);
    }


    @Test
    void testPruefePlatz() {

        assertAll("pruefePlatz",
                () -> assertTrue(kinoSaal.pruefePlatz('A', 8)),
                () -> assertFalse(kinoSaal.pruefePlatz('B',11)),
                () -> assertTrue(kinoSaal.pruefePlatz('C',1)),
                () -> assertFalse(kinoSaal.pruefePlatz('D',7))
        );


    }

    @Test
    void testAssertKinosaalName() {
        assertEquals("KS1", kinoSaal.getName());
    }

    @Test
    void testIfKinoSaalIsInstanceOfKinoSaal() {

        assertAll("equals",
                () -> assertTrue(kinoSaal.equals(kinoSaal)),
                () ->  assertFalse(kinoSaal.equals(new Object()))
        );


    }



}
