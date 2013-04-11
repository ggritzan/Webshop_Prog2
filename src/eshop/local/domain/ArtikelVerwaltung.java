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



        // alle Artikel des Shops ausgeben
        public void alleArtikelAusgeben(){
            for ( Integer Artikel : artikelBestandNr.keySet() )
                System.out.println(artikelBestandNr.get(Artikel));


        }
    /*

    // Getter

        // Artikel Ausgabe anhand der Artikelnummer
        // artikelBestand.get()




    private int bestand;



    */
}
