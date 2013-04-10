package eshop.local.domain;

import java.io.IOException;
import java.util.HashMap;
import eshop.local.valueobjects.Artikel;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 10.04.13
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */


public class ArtikelVerwaltung {

    private HashMap<Integer, Artikel> artikelBestand;


    // Konstruktor
    public ArtikelVerwaltung() {
        artikelBestand = new HashMap<Integer, Artikel>();
    }

    // Setter

        // Artikel hinzufügen
        public boolean artikelHinzufuegen(String bezeichnung, double preis) {
            if (artikelBestand.containsValue(bezeichnung)) {
                return false;

            } else {
                Artikel artikel = new Artikel(bezeichnung, preis);
                artikelBestand.put(artikel.getNummer(), artikel);
                return true;
            }

        }


    // Getter

        // Artikel Ausgabe anhand der Artikelnummer
        // artikelBestand.get()




   /* private int bestand;

    public int bestandErhoehen(int wert) {
        this.bestand = this.bestand + wert;
        return this.bestand;
    }

    public int bestandVerringern(int wert) {
        this.bestand = this.bestand - wert;
        return this.bestand;
    }

    public int getBestand() {
        return bestand;
    }

    */
}
