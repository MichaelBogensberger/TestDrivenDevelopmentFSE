package at.itkolleg.ase.tdd.kino;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Dieses Beispiel stammt aus https://training.cherriz.de/cherriz-training/1.0.0/testen/junit5.html
 */
public class App 
{
    public static void main( String[] args )
    {
        KinoVerwaltung kinoVerwaltung = new KinoVerwaltung();

        //Saal anlegen
        Map<Character,Integer> map = new HashMap<>();
        map.put('A',10);
        map.put('B',10);
        map.put('C',15);
        KinoSaal kinoSaal = new KinoSaal("Saal1",map);


        Vorstellung vorstellung = new Vorstellung(kinoSaal,Zeitfenster.ABEND,LocalDate.parse("2020-01-08"),"Pulp Fiction", 10.3f);

        kinoVerwaltung.einplanenVorstellung(vorstellung);

        //System.out.println(vorstellung.toString());

        //Ticket ticket = new Ticket(kinoSaal.getName(),Zeitfenster.ABEND,LocalDate.parse("2020-01-08"),'A',10);
        //System.out.println(ticket.toString());

        Ticket ticket1 = kinoVerwaltung.kaufeTicket(vorstellung,'A',8,20);
        Ticket ticket2 = kinoVerwaltung.kaufeTicket(vorstellung,'B',2,34);

        System.out.println("Vorstellungen: " + kinoVerwaltung.getVorstellungen());



        //Platz prüfen
        //System.out.println(ks.pruefePlatz('A',9));
        //...

    }
}
