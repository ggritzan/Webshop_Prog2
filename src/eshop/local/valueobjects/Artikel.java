/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */

package eshop.local.valueobjects;


public class Artikel {

    // Attribute zur Beschreibung eines Artikels
    private String bezeichnung;
    private int aNummer;
    private int bestand;

    // Konstruktor
    public Artikel(String bezeichnung, int nummer ) {
        this.bezeichnung = bezeichnung;
        this.aNummer = nummer;
        this.bestand = 0;
    }

    // Methoden der Klasse Artikel


    // Setter


    // Getter
    public int getNummer() {
        return aNummer;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public int getBestand() {
        return bestand;
    }


}

