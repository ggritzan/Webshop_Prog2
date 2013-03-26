/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
// package bib.local.valueobjects;


/**
 * Klasse zur Repräsentation einzelner Artikel.
 *
 * @author Giacomo
 */
public class Artikel {

    // Attribute zur Beschreibung eines Artikels:
    private String bezeichnung;
    private int nummer;
    private int bestand;

    public Artikel(String bezeichnung, int nummer ) {
        this.bezeichnung = bezeichnung;
        this.nummer = nummer;
        this.bestand = 0;
    }

    public boolean equals(Object andererArtikel) {
        if (andererArtikel instanceof Artikel)
            return ((this.nummer == ((Artikel) andererArtikel).nummer)
                    && (this.bezeichnung.equals(((Artikel) andererArtikel).bezeichnung)));
        else
            return false;
    }

    // Setter


    // Getter
    public int getNummer() {
        return nummer;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public int getBestand() {
        return bestand;
    }




}

