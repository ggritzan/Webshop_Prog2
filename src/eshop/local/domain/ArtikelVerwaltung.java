package eshop.local.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import eshop.local.valueobjects.Artikel;
import eshop.local.persistence.FilePersistenceManager;
import eshop.local.persistence.PersistenceManager;

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

    // Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
    private PersistenceManager pm = new FilePersistenceManager();

    /**
     * Methode zum Einlesen von Artikeldaten aus einer Datei.
     *
     * @param datei Datei, die einzulesenden Artikelbestand enthält
     * @throws IOException
     */
    public void liesDaten(String datei) throws IOException {
        // PersistenzManager für Lesevorgänge öffnen
        pm.openForReading(datei);

        Artikel einArtikel;
        do {
            // Artikel-Objekt einlesen
            einArtikel = pm.ladeArtikel();
            if (einArtikel != null) {
                // Buch in Liste einfügen

                artikelHinzufuegen(einArtikel.getName(), einArtikel.getBeschreibung(), einArtikel.getNummer(), einArtikel.getPreis(), einArtikel.getBestand());
            }

        } while (einArtikel != null);

        // Persistenz-Schnittstelle wieder schließen
        pm.close();
    }

    /**
     * Methode zum Schreiben der Artikeldaten in eine Datei.
     *
     * @param datei Datei, in die der Artikelbestand geschrieben werden soll
     * @throws IOException
     */
    public void schreibeDaten(String datei) throws IOException  {

        // PersistenzManager für Schreibvorgänge öffnen


        pm.openForWriting(datei);




        if (!artikelBestandNr.isEmpty()) {
            Iterator iter = artikelBestandNr.values().iterator();
            while (iter.hasNext()) {
                Artikel a = (Artikel) iter.next();
                pm.speichereArtikel(a);
            }
        }


        // Persistenz-Schnittstelle wieder schließen
        pm.close();
    }


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
            // HasMaps gespeichert
            } else {

                Artikel artikel = new Artikel(name, beschreibung, preis);
                artikelBestandNr.put(artikel.getNummer(), artikel);
                artikelBestandName.put(name, artikel.getNummer());
                return true;
            }

        }

        // Artikel hinzufügen
        public void artikelHinzufuegen(String name, String beschreibung, int nummer, double preis, int bestand) {

            Artikel artikel = new Artikel (name, beschreibung, nummer, preis, bestand);
            artikelBestandNr.put(artikel.getNummer(), artikel);
            artikelBestandName.put(name, artikel.getNummer());


        }



        // alle Artikel des Shops zurueckgeben
        public  Vector alleArtikelZurueckgeben(){
            Vector<Artikel> ergebnis = new Vector<Artikel>();
            for ( Artikel elem : artikelBestandNr.values() )
                 ergebnis.add(elem);

            return ergebnis;

        }

        // durchsucht die Artikel nach dem übergebenden Artikelnamen und gibt den Artikel wenn vorhanden zurück
        public Vector sucheArtikel(String name) {
            int nummer = 0;
            Vector<Artikel> ergebnis = new Vector<Artikel>();

            if (artikelBestandName.containsKey(name)) {
                nummer = artikelBestandName.get(name);
                ergebnis.add(artikelBestandNr.get(nummer));

                return ergebnis;

            } else {
                Vector<String> error = new Vector<String>();
                error.add("Der Artikel ist nicht vorhanden !");
                return   error;
            }

	    }


        // public Artikel
        // Artikel Ausgabe anhand der Artikelnummer
        // artikelBestand.get()

}
