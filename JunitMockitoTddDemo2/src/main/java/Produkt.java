import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Diese Klasse repräsentiert Produkte einer Online-E-Commerce-Website (wie z.B. Amazon.com).
 * Produkt-Objekte speichern alle für das Produkt relevanten Daten, einschließlich der Produktnummer, eines Namens,
 * dem Preis, Kommentare der Kunden etc.
 */
public class Produkt
{

    private UUID nummer;
    private String name;
    private int preis; // in cent
    private List<Kommentar> kommentare;
    
    /**
     * Erzeuge ein neues Produkt.
     */
    public Produkt(UUID nummer, String name, int preis) {
        if(nummer==null) throw new IllegalArgumentException("Produktnummer darf nicht leer sein!");
        if(name==null || name.length()<1) throw new IllegalArgumentException("Produktname darf nicht null oder leer sein!");
        if(preis <0) throw new IllegalArgumentException("Preis darf nicht negativ sein!");
        this.nummer = nummer;
        this.name = name;
        this.preis = preis;
        this.kommentare = new ArrayList<>();
    }

    /**
     * Liefere die Nummer des Produktes
     */
    public UUID getNummer()
    {
        return this.nummer;
    }

    /**
     * Liefere den Namen des Produkts.
     */

    public String getName()
    {
        return this.name;
    }
    
    /**
     * Liefere den Preis des Produkts.
     */
    public double getPreis()
    {
        return this.preis;
    }
    
    /**
     * Liefere die Anzahl an Kundenkommentaren zu diesem Produkt.
     */
    public int anzahlKommentare()
    {
        return this.kommentare.size();
    }
    
    /**
     * Füge der Kommentarliste dieses Produkts einen Kommentar hinzu. Liefere im Erfolgsfall true, und
     * false, wenn der Kommentar abgelehnt wurde.
     * 
     * Der Kommentar wird abgelehnt, wenn der Autor oder Text leer ist, wenn der Autor
     * bereits einen Kommentar hinterlassen hat oder wenn
     * die Bewertung ungültig ist. Gültige Bewertungen liegen zwischen 1 und 5 (inklusive).
     */
    public boolean kommentarHinzufuegen(String autor, String text, int bewertung)
    {
        if (autor == null||text==null||autor.length()==0||text.length()==0||bewertungUngueltig(bewertung)) return false;
        this.kommentare.add(new Kommentar(text,autor,bewertung));
        return true;
    }

    private boolean gueltigerKommentarIndex(int index)
    {
        return index>=0 && index < this.kommentare.size();
    }

    /**
     * Lösche den Kommentar, der unter dem angegebenen Index gespeichert ist. Ist der Index ungültig, tue nichts.
     */
    public void loescheKommentarAnIndex(int index)
    {
        if(gueltigerKommentarIndex(index))
        {
            this.kommentare.remove(index);
        }
    }

    /**
     * Stimme dem Kommentar unter 'index' zu. Das heißt, stufe diesen Kommentar als hilfreich ein.
     * Ist der Index ungültig, tue nichts.
     */
    public void stimmeKommentarZu(int index)
    {
        if(gueltigerKommentarIndex(index))
        {
            this.kommentare.get(index).zustimmen();
        }
    }
    
    /**
     * Lehne den Kommentar unter 'index' ab. Das heißt, stufe diesen Kommentar als weniger hilfreich ein.
     * Ist der Index ungültig, tue nichts.
     */

    public void lehneKommentarAb(int index)
    {
        if(gueltigerKommentarIndex(index))
        {
            this.kommentare.get(index).ablehnen();
        }
    }
    
    /**
     * Gib alle Daten des Produktes incl. allen Kommentare aus.
     * (Zum Testen erfolgt die Ausgabe derzeit auf die Konsole.
     * Soll später überarbeitet werden, sodass die Ausgabe im Web erfolgt.)
     */
    public void infoAnzeigen()
    {
        System.out.println("*** " + name + " ***");
        System.out.println("Preis: " + preisString(preis));
        System.out.println();
        System.out.println("Kundenkommentare:");
        for(Kommentar kommentar : kommentare) {
            System.out.println("-------------------------------------------");
            System.out.println(kommentar);
        }
        System.out.println();
        System.out.println("===========================================");
    }

    /**
     * Liefere den Kommentar, der am hilfreichsten ist. Der nützlichste Kommentar ist derjenige mit dem höchsten Stimmenverhältnis.
     * Gibt es mehrere Kommentare mit dem höchsten Stimmenverhältnis, liefere irgendeinen von ihnen.
     */
    public Kommentar findeHilfreichstenKommentar()
    {
        Iterator<Kommentar> it = kommentare.iterator();
        Kommentar bester = it.next();
        while(it.hasNext())
        {
            Kommentar aktuell = it.next();
            if(aktuell.gibStimmen() > bester.gibStimmen()) {
                bester = aktuell;
            }
        }
        return bester;
    }
    
    /**
     * Prüfe, ob die gegebene Bewertung ungültig ist. Liefere true, wenn die Bewertung ungültig ist.
     * Gültige Bewertungen liegen im Bereich [1..5].
     */
    private boolean bewertungUngueltig(int bewertung)
    {
        return bewertung < 0 || bewertung > 5;
    }
    
    /**
     * Finde den Kommentar des angegebenen Autors.
     */
    public Kommentar findeKommentarVon(String autor)
    {
        for(Kommentar kommentar : kommentare) {
            if(kommentar.gibAutor().equals(autor)) {
                return kommentar;
            }
        }
        return null;
    }
    
    /**
     * Liefere für einen als int angegebenen Preis den zugehörigen String, der denselben Preis repräsentiert.
     * Der Preis wird in ganzen Cent angegeben. Für preis==12345 wird z.B. der folgende String zurückgeliefert: 
     * Euro 123,45
     */
    private String preisString(int preis)
    {
        int euro = preis / 100;
        int cents = preis - (euro*100);
        if(cents <= 9) {
            return "Euro" + euro + ",0" + cents;  // falls nötig 0 einfügen
        }
        else {
            return "Euro" + euro + "," + cents;
        }
    }
}
