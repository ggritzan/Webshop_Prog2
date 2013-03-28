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
    private int nummer;
    private int bestand;
    private double preis;

    // Konstruktor
    public Artikel(String bezeichnung, int nummer, double preis ) {
        this.bezeichnung = bezeichnung;
        this.nummer = nummer;
        this.preis = preis;
        this.bestand = 0;
    }

    public Artikel(String bezeichnung, int nummer, double preis, int bestand) {
        this.bezeichnung = bezeichnung;
        this.nummer = nummer;
        this.preis = preis;
        this.bestand = bestand;
    }

    // Methoden der Klasse Artikel
    public void printArtikel() {
        System.out.println("Bezeichnung: " + this.bezeichnung);
        System.out.println("Nummer: " + this.nummer);
        System.out.println("Bestand: " + this.bestand);
        System.out.println("Preis: " + this.preis);

    }


    // Setter

    public int bestandErhoehen(int wert) {
        this.bestand = this.bestand + wert;
        return this.bestand;
    }

    public int bestandVerringern(int wert) {
        this.bestand = this.bestand - wert;
        return this.bestand;
    }

    public void neuerPreis(double wert) {
        this.preis = wert;
    }

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

    public double getPreis() {
        return preis;
    }


}

