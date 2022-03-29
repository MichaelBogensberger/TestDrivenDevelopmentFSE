import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ProduktServiceTests { //Mockito verwenden für die Tests

    Repository rep; //Gemocktes Repository, das vom Service verwendet wird
    Set<Produkt> produktliste; //Dummyliste als Rückgabe
    ProduktService produktService; //Produkt-Service, das getestet wird

    @BeforeEach
    public void setUp()
    {
        //Dummy-Produktliste aufbauen
        produktliste = new HashSet<>();
        Produkt p1 = new Produkt(UUID.randomUUID(),"NAme1",12399);
        p1.kommentarHinzufuegen("Claudio","Kommentar",2);
        Produkt p2 = new Produkt(UUID.randomUUID(),"Name2",144799);
        p2.kommentarHinzufuegen("Claudio 2","Kommentar 2",5);
        Produkt p3 = new Produkt(UUID.randomUUID(),"Name3",333299);
        produktliste.add(p1);
        produktliste.add(p2);
        produktliste.add(p3);

        //Repository mocken
        rep = Mockito.mock(Repository.class);//oder direkt InMemoryRepository.class

        //Produktservice mit dem Mock erstellen
        produktService = new ProduktService(rep);
    }

    @Test
    public void testProduktHinzufuegen()
    {
        Produkt p1 = new Produkt(UUID.randomUUID(),"Name des Produkts",12399);
        produktService.produktSpeichern(p1);
        Mockito.verify(rep).speichere(Mockito.any()); //Test, ob Methodenaufruf stattfand
        //Mockito.verify(rep,Mockito.atLeast(1)).speichere(Mockito.any()); //Alternative Verwendung für die Anzahl der Aufrufe
    }

    @Test
    public void testAlleProdukteHerausholen()
    {
        Mockito.when(rep.ladeAlle()).thenReturn(produktliste);
        Set<Produkt> produktListeZurück = produktService.ladeAlleProdukte();
        Mockito.verify(rep).ladeAlle();
        Assertions.assertEquals(3,produktListeZurück.size());
    }

    @Test
    public void testHoleProdukteMitKommentar()
    {
        Mockito.when(rep.ladeAlle()).thenReturn(produktliste);
        Set<Produkt> produktListeZurück = produktService.holeAlleProdukteMitKommentar();
        Mockito.verify(rep).ladeAlle();
        Assertions.assertEquals(2,produktListeZurück.size());
    }
}
