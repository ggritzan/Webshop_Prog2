package eshop.local.domain;

import java.io.IOException;
import java.util.Vector;

import com.sun.org.apache.xml.internal.security.utils.IgnoreAllErrorHandler;

import eshop.local.valueobjects.Artikel;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 10.04.13
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */

public class EShopVerwaltung {

    // Präfix für Namen der Dateien, in der die Artikeldaten gespeichert sind
    private String datei = "";
    // Erzeugt eine Artikelverwaltung
    private ArtikelVerwaltung meineArtikel;
    // Erzeugt eine Kundenverwaltung
    //private KundenVerwaltung meineKunden;

// Konstruktor

    /**
     * Namensmuster für Dateien:
     * datei+"_Artikel.ser" ist die Datei der Artikel
     * datei+"_Kunden.ser" ist die Datei der Kunden
     *
     * @param datei
     * @throws IOException,ClassNotFoundException z.B. wenn eine der Dateien nicht existiert.
     * @return Vector
     */
    public EShopVerwaltung(String datei) throws IOException, ClassNotFoundException {

        this.datei = datei;
        // Artikelbestand aus Datei einlesen
        meineArtikel = new ArtikelVerwaltung();
        meineArtikel.liesDaten(datei + "_Artikel.ser");
        // Kundenkartei aus Datei einlesen
        // meineKunden = new KundenVerwaltung();
        // meineKunden.liesDaten(datei+"_Kunden.ser");

    }

// Methoden

    /**
     * Methode, die eine Liste aller im Bestand befindlichen Artikel zurückgibt Aufgabe wird an die Artikelverwaltung delegiert
     *
     * @return Vector Liste aller Artikel im Bestand des EShops als Vector
     */
    public Vector gibAlleArtikel() {
        // delegiert an die Artikelverwaltung
        return meineArtikel.alleArtikelZurueckgeben();
    }

    /**
     * Methode zum Suchen von Artikel anhand des Namens
     *
     * @param name Name des gesuchten Artikels
     * @return Vector Rückgabe eines Vectors mit dem gefundenen Artikel
     */
    public Vector sucheNachName(String name) {

        return meineArtikel.sucheArtikel(name);
    }

    /**
     * Methode zum ändern des Bestands eines Artikels
     *
     * @param artNr,wert    Artikelnummer, Neuer Wert für Bestand des Artikels
     * @return boolean
     */
    public boolean setBestand(int artNr, int wert) {
        return meineArtikel.setBestand(artNr, wert);
    }

    /**
     * Methode zum Einfügen eines neuen Artikels in den Bestand.
     *
     * @param name,beschreibung,preis         Name des Artikels, Beschreibung des Artikels, Preis des Artikels
     *
     * @return boolean wenn Einfügen erfolgreich true, ansonsten false (wenn Artikel schon vorhanden ist)
     */
    public boolean fuegeArtikelEin(String name, String beschreibung, double preis) {
        return meineArtikel.artikelHinzufuegen(name, beschreibung, preis);

    }

    /**
     * Methode zum loeschen eines  Artikels aus dem  Bestand.
     *
     * @param artNr         Nummer des Artikels des Artikel der geloescht werden soll.
     *
     * @return boolean wenn loeschen erfolgreich true, ansonsten false (wenn der Artikel nicht gelöscht werden konnte)
     */
    public boolean loescheArtikel(int artNr) {
        return meineArtikel.artikelLoeschen(artNr);

    }

    /**
     * Methode zum Speichern des Artikelbestands in einer Datei.
     *
     * @throws IOException
     */
    public void schreibeArtikel() throws IOException {

        meineArtikel.schreibeDaten(datei + "_Artikel.ser");
    }

}


