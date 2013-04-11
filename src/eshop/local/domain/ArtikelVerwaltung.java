package eshop.local.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
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
    private HashMap<String, Integer> artikelBestandName;


    // Konstruktor
    public ArtikelVerwaltung() {

        // verknüpft die Artikelnummern mit den Artikel Objekten
        artikelBestandNr = new HashMap<Integer, Artikel>();

        // verknüpft die Artikelnamen mit den Artikelnummern
        artikelBestandName = new HashMap<String, Integer>();
    }

    // Methoden

        // Artikel hinzufügen
        public boolean artikelHinzufuegen(String name, String beschreibung, double preis) {

            // Wenn der Name des Artikels bereits vorhanden ist wird false zurück gegeben
            if (artikelBestandName.containsKey(name)) {
                return false;

            // Ist der Artikelname noch nicht vorhanden wird er neu angelegt und in den beiden
            // HasMaps gespeicher
            } else {

                Artikel artikel = new Artikel(name, beschreibung, preis);
                artikelBestandNr.put(artikel.getNummer(), artikel);
                artikelBestandName.put(name, artikel.getNummer());
                return true;
            }

        }



        // alle Artikel des Shops zurueckgeben
        public  Vector alleArtikelZurueckgeben(){
            Vector<Artikel> ergebnis = new Vector();
            for ( Artikel elem : artikelBestandNr.values() )
                 ergebnis.add(elem);

            return ergebnis;

        }

        // durchsucht die Artikel nach dem übergebenden Artikelnamen und gibt den Artikel wenn vorhanden zurück
        public Vector sucheArtikel(String name) {
            int nummer = 0;
            Vector<Artikel> ergebnis = new Vector();
            Vector<String> error = new Vector();
            if (artikelBestandName.containsKey(name)) {
                nummer = artikelBestandName.get(name);
                ergebnis.add(artikelBestandNr.get(nummer));

                return ergebnis;

            } else {
                error.add("Der Artikel ist nicht vorhanden !");
                return   error;
            }

	    }


        // public Artikel
        // Artikel Ausgabe anhand der Artikelnummer
        // artikelBestand.get()

}
