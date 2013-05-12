package eshop.local.domain;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    // Dokument zum Speichern
    private File dateiName = new File("Eshop_ArtikelLog.txt");

    // Hashmap zum speichern des Artikelbestands als Key dienen die Artikelnummern
    private HashMap<Integer, Artikel> artikelBestandNr;

    // Hashmap zum verknüpfen des Artikelnamens mit der Artikelnummer
    private HashMap<String, Integer> artikelBestandName;

    // Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
    private PersistenceManager pm = new FilePersistenceManager();

    // Anderes Datums-Format
    private final SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz");


// Konstruktor

    public ArtikelVerwaltung() {

        // verknüpft die Artikelnummern mit den Artikel Objekten
        artikelBestandNr = new HashMap<Integer, Artikel>();

        // verknüpft die Artikelnamen mit den Artikelnummern
        artikelBestandName = new HashMap<String, Integer>();
    }


// Methoden

    /**
     * Methode zum Einlesen der Artikeldaten aus einer Datei.
     *
     * @param datei Datei, die den einzulesenden Artikelbestand enthaelt
     * @throws IOException,ClassNotFoundException
     *
     */
    public void liesDaten(String datei) throws IOException, ClassNotFoundException {

        // Erstellung eines Artikel Objekts
        Artikel einArtikel;

        try {

            // PersistenzManager für Lesevorgänge wird geöffnet
            pm.openForReading(datei);

            do {
                // Artikel-Objekt einlesen
                einArtikel = pm.ladeArtikel();
                if (einArtikel != null) {

                    // Artikel einfügen
                    artikelHinzufuegen(einArtikel);

                }

            } while (einArtikel != null);

            // PersistenzManager für Lesevorgänge wird wieder geschlossen
            pm.close();
        } catch (IOException e) {
            System.out.println("Fehler beim einlesen der Artikeldaten !");
        } finally {
            pm.close();
        }
    }

    /**
     * Methode zum Schreiben der Artikeldaten in eine Datei.
     *
     * @param datei Datei, in die der Artikelbestand geschrieben werden soll
     * @throws IOException
     */
    public void schreibeDaten(String datei) throws IOException {

        // PersistenzManager für Schreibvorgänge öffnen
        pm.openForWriting(datei);

        // Artikel-Objekte aus der Hashmap artikelBestandNr einlesen und in die Datei schreiben
        if (!artikelBestandNr.isEmpty()) {
            Iterator iter = artikelBestandNr.values().iterator();
            while (iter.hasNext()) {
                Artikel a = (Artikel) iter.next();
                pm.speichereArtikel(a);
            }
        }

        //Persistenz-Schnittstelle wieder schließen
        pm.close();
    }

    /**
     * Methode zum hinzufuegen von Artikeln
     *
     * @param name
     * @param beschreibung
     * @param preis
     */
    public boolean artikelHinzufuegen(String name, String beschreibung, double preis) {

        // Wenn der Name eines Artikels bereits vorhanden ist wird false zurück gegeben und kein neuer Artikel angelegt
        if (artikelBestandName.containsKey(name)) {
            return false;


        } else {
            // Ist der Artikelname noch nicht vorhanden wird er neu angelegt und in den beiden HasMaps gespeichert (artikelBestandNr, artikelBestandName)
            Artikel artikel = new Artikel(name, beschreibung, preis);
            artikelBestandNr.put(artikel.getNummer(), artikel);
            artikelBestandName.put(name, artikel.getNummer());
            return true;
        }

    }

    /**
     * Methode zum hinzufuegen von Artikeln durch den PersistenceManager
     *
     * @param artikel
     */
    public void artikelHinzufuegen(Artikel artikel) {

        // Erzeugt Artikel mit ihrer bisherigen Artikelnummer
        Artikel a = new Artikel(artikel);
        artikelBestandNr.put(a.getNummer(), a);
        artikelBestandName.put(artikel.getName(), a.getNummer());

    }

    /**
     * Methode zum löschen von Artikel
     *
     * @param artNr
     * @return boolean
     */
    public boolean artikelLoeschen(int artNr) {

        if (artikelBestandNr.containsKey(artNr)) {

            Artikel a = artikelBestandNr.get(artNr);
            artikelBestandNr.remove(artNr);
            artikelBestandName.remove(a.getName());


            return true;

        } else {

            return false;
        }

    }

    /**
     * Methode gibt einen Vector zurueck der alle vorhandenen Artikeln enthaelt
     */
    public Vector alleArtikelZurueckgeben() {

        Vector<Artikel> ergebnis = new Vector<Artikel>();

        for (Artikel elem : artikelBestandNr.values())
            ergebnis.add(elem);


        return ergebnis;

    }

    /**
     * Methode durchsucht alle Artikel nach dem Parameter name und gibt den entsprechenden Artikel in einem Vektor zurueck
     *
     * @param name
     */
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

            return error;
        }

    }

    /**
     * Methode aendert den Bestand des gewuenschten Artikels wenn er exestiert
     *
     * @param artNr,wert
     */
    public boolean setBestand(int artNr, int wert) throws IOException{

        // Wenn die Artikelnummer exestiert wird der Bestand angepasst
        if (artikelBestandNr.containsKey(artNr)) {

            Artikel a = artikelBestandNr.get(artNr);
            int neuerBestand = a.getBestand() + wert;

            //Wenn der neue Bestand nicht ins negative geht.
            if(neuerBestand >= 0){
                a.setBestand(neuerBestand);
                artikelBestandNr.put(artNr, a);
                Date dNow = new Date();
                String text = ft.format(dNow) + ": Der Bestand des Artikels '" + a.getName() + "' mit der Artikelnummer " + artNr + " wurde um der Wert " + wert + " geaendert.\nDer jetzige Bestand betraegt: " + neuerBestand;
                BufferedWriter schreibeStrom = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dateiName, true)));
                schreibeStrom.write(text);
                schreibeStrom.newLine();
                schreibeStrom.close();

                return true;
            }
            return false;
        } else {

            return false;
        }
    }

    /**
     * Methode setzt den Wert wieviel Artikel bestellt werden sollen
     *
     * @param menge Integer
     */
    public boolean setBestellteMenge(int menge) {
        if (menge >= 0) {
            setBestellteMenge(menge);
            return true;
        } else {
            return false;
        }
    }

    public Artikel getArtikel(int aNr) {
        if (artikelBestandNr.containsKey(aNr)) {
            return artikelBestandNr.get(aNr);
        } else {
            return null;
        }
    }

}

