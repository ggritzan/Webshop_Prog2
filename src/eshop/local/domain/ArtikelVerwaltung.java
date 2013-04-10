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

    private HashMap<Integer, Artikel> artikelBestandNr;
    private HashMap<String, Integer> artikelBestandBez;


    // Konstruktor
    public ArtikelVerwaltung() {

        // verknüpft die Artikelnummern mit den Artikel Objekten
        artikelBestandNr = new HashMap<Integer, Artikel>();


        artikelBestandBez = new HashMap<String, Integer>();
    }

    // Setter

        // Artikel hinzufügen
        public boolean artikelHinzufuegen(String bezeichnung, double preis) {
            if (artikelBestandBez.containsKey(bezeichnung)) {
                return false;

            } else {

                Artikel artikel = new Artikel(bezeichnung, preis);
                artikelBestandNr.put(artikel.getNummer(), artikel);
                artikelBestandBez.put(bezeichnung, artikel.getNummer());
                return true;
            }

        }


    // Getter

        // Artikel Ausgabe anhand der Artikelnummer
        // artikelBestand.get()




   /* private int bestand;



    */
}
