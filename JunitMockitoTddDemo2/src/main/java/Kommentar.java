/**
 * Diese Klasse repräsentiert Kommentare, die auf der Website eines Online-Shops zu einem Produkt
 * geschrieben wurden. Ein Kommentar besteht aus einem Text, einer Bewertung und dem Namen des Autors.
 * Andere Besucher können angeben, ob der Kommentar hilfreich für sie war (wir sprechen von 'zustimmen' bzw.
 * 'ablehnen'). Das Verhältnis zwischen Zustimmungen und Ablehnungen wird zusammen mit den Kommentaren gespeichert.
 * Ein negatives Verhältnis bedeutet, dass es für den Kommentar mehr Ablehnungen als Zustimmungen gibt.
 */
public class Kommentar
{

    private String text;
    private String autor;
    private int bewertung;
    private int stimmen;

    /**
     * Erzeuge einen Kommentar mit allen notwendigen Angaben. Das anfängliche Stimmenverhältnis ist 0.
     */
    public Kommentar(String text, String autor, int bewertung) {
        this.text = text;
        this.autor = autor;
        this.bewertung = bewertung;
        this.stimmen = 0;

        if(bewertung>=1&&bewertung<=5)
        {
            this.bewertung = bewertung;
        } else
        {
            throw new IllegalArgumentException("Bewertung darf nur zwischen 1 und 5 Sterne sein!");
        }

        if(text!=null && text.length()>0)
        {
            this.text = text;
        } else
        {
            throw new IllegalArgumentException("Text darf nicht leer sein!");
        }

        if(autor!=null && autor.length()>0)
        {
            this.text = text;
        } else
        {
            throw new IllegalArgumentException("Autor darf nicht leer sein!");
        }
    }

    /**
     * Zeige an, dass der Kommentar hilfreich ist ('zustimmen'). Wird verwendet, wenn ein Besucher nach dem
     * Lesen der "War dieser Kommentar hilfreich?"-Frage auf die'Ja'-Schaltfläche klickt .
     */

    public void zustimmen(){
        this.stimmen++;
    }

    /**
     * Zeige an, dass der Kommentar nicht hilfreich ist ('ablehnen'). Wird verwendet, wenn ein Besucher nach dem
     * Lesen der "War dieser Kommentar hilfreich?"-Frage auf die'Nein'-Schaltfläche klickt .
     */
    public void ablehnen()
    {
        this.stimmen--;
    }

    /**
     * Liefere den Autor des Kommentars.
     */

    public String gibAutor()
    {
        return this.autor;
    }

    /**
     * Liefere die Bewertung des Kommentars.
     */

    public int gibBewertung()
    {
        return this.bewertung;
    }
    
    /**
     * Liefere das Stimmenverhältnis (Verhältnis von zustimmenden zu ablehnenden Stimmen).
     */

    public int gibStimmen()
    {
        return this.stimmen;
    }
    
    /**
     * Liefere den vollständigen Text und alle Angaben zu dem Kommentar, einschließlich
     * des Kommentartextes, des Autors und der Bewertung.
     */
    public String toString()
    {
        return "Bewertung: " + "*****".substring(0,this.bewertung) + "\n von " + this.autor + ":\n " + this.text;
    }
}
