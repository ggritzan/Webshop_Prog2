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

    private HashMap<Integer, Artikel> artikelBestand;


    // Konstruktor

    public ArtikelVerwaltung( ) {

    }

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
