package eshop.local.domain;

import eshop.local.persistence.FilePersistenceManager;
import eshop.local.persistence.PersistenceManager;
import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Kunde;
import eshop.local.valueobjects.Rechnung;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 22.04.13
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
public class RechnungsVerwaltung {
    // Hashmap zum speichern des Rechnungsbestandes als Key dienen die Rechnungsnummern
    private HashMap<Integer, Rechnung> rechnungsBestandNr;

    // Hashmap zum verknüpfen der Kundennummer mit den Rechnungsnummern
    private HashMap<Integer, Vector<Integer>> rechnungsBestandKundenNr;

    // Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
    private PersistenceManager pm = new FilePersistenceManager();

    // Konstruktor
    public RechnungsVerwaltung() {

        // verknüpft die Rechungsnummern mit den Rechnungsobjekten
        rechnungsBestandNr = new HashMap<Integer, Rechnung>();

        // verknüpft die Kundennummer mit einem Vector von Rechnungsnummern
        rechnungsBestandKundenNr = new HashMap<Integer, Vector<Integer>>();
    }

    /**
     * Methode zum Einlesen der Rechnungen aus einer Datei.
     *
     * @param datei
     * @throws IOException,ClassNotFoundException
     *
     */
    public void liesDaten(String datei) throws IOException, ClassNotFoundException {

        // Erstellung eines Rechnungs Objekts
        Rechnung rechnung;

        try {
            // PersistenzManager für Lesevorgänge wird geöffnet
            pm.openForReading(datei);
            do {
                // Rechnungs-Objekt einlesen
                rechnung = pm.ladeRechnung();
                if (rechnung != null) {
                    // Rechnung einfügen
                    rechnungHinzufuegen(rechnung);
                }
            } while (rechnung != null);
            // PersistenzManager für Lesevorgänge wird wieder geschlossen
        } catch (IOException e) {
            System.out.println("Fehler beim einlesen der Rechnungsdaten !");
            e.printStackTrace();
        }    finally{
            pm.close();
        }
    }

    /**
     * Methode zum Schreiben der Kundendaten in eine Datei.
     *
     * @param datei
     * @throws IOException
     */
    public void schreibeDaten(String datei) throws IOException {

        // PersistenzManager für Schreibvorgänge öffnen
        pm.openForWriting(datei);

        // Rechnungs-Objekte aus der Hashmap kundenBestandNr einlesen und in die Datei schreiben
        if (!rechnungsBestandNr.isEmpty()) {
            Iterator iter = rechnungsBestandNr.values().iterator();
            while (iter.hasNext()) {
                Rechnung rechnung = (Rechnung) iter.next();
                pm.speichereRechnung(rechnung);
            }
        }

        //Persistenz-Schnittstelle wieder schließen
        pm.close();
    }

    /**
     * Methode zum Hinzufuegen von Rechnungen
     *
     * @param kunde
     */

    public boolean rechnungHinzufuegen(Kunde kunde) {

        // sollte irgendwann schon einmal eine Rechnung für den Kundne erstellt worden sein
        if (rechnungsBestandKundenNr.containsKey(kunde.getNummer())) {
            Vector<Artikel> wkV = new Vector<Artikel>();

            for (Artikel elem : kunde.getWarenkorb().values())

                wkV.add(elem);

            Rechnung rechnung = new Rechnung(kunde.getNummer(), wkV);
            rechnungsBestandNr.put(rechnung.getrNr(), rechnung);
            Vector<Integer> vI = rechnungsBestandKundenNr.get(kunde.getNummer());
            vI.add(rechnung.getrNr());
            rechnungsBestandKundenNr.put(kunde.getNummer(), vI);
            if (rechnung.getdNow() == null){
                Date dNow = new Date();
                setDate(rechnung.getrNr(), dNow);

                return true;
            }

            return false;

        } else if (!rechnungsBestandKundenNr.containsKey(kunde.getNummer())) {
            Vector<Artikel> wkV = new Vector<Artikel>();

            for (Artikel elem : kunde.getWarenkorb().values())
                wkV.add(elem);
            Rechnung rechnung = new Rechnung(kunde.getNummer(), wkV);
            rechnungsBestandNr.put(rechnung.getrNr(), rechnung);
            Vector<Integer> vI = new Vector<Integer>();
            vI.add(rechnung.getrNr());
            rechnungsBestandKundenNr.put(kunde.getNummer(), vI);
            if (rechnung.getdNow() == null){
                Date dNow = new Date();
                setDate(rechnung.getrNr(), dNow);

                return true;
            }
            return false;
        } else {
            return false;
        }

    }

    /**
     * Methode zum Hinzufuegen von Rechnungen durch den PersistenceManager
     *
     * @param rechnung
     */
    public void rechnungHinzufuegen(Rechnung rechnung) {

        if (rechnungsBestandKundenNr.containsKey(rechnung.getkNr())) {
            Rechnung r = new Rechnung(rechnung);
            rechnungsBestandNr.put(rechnung.getrNr(), rechnung);
            Vector<Integer> vI = rechnungsBestandKundenNr.get(rechnung.getkNr());
            vI.add(rechnung.getrNr());
            rechnungsBestandKundenNr.put(rechnung.getkNr(), vI);

        } else {
            // Erzeugt eine Rechnung mit seiner bisherigen Rechnungsnummer
            Rechnung r = new Rechnung(rechnung);
            rechnungsBestandNr.put(r.getrNr(), r);
            Vector<Integer> vI = new Vector<Integer>();
            vI.add(rechnung.getrNr());
            rechnungsBestandKundenNr.put(rechnung.getkNr(), vI);
        }

    }

    /**
     * Methode zum loeschen von Kunden durch den PersistenceManager
     *
     * @param rechnung
     */
    public boolean rechnungLoeschen(Rechnung rechnung) {

        Rechnung r = rechnung;
        Vector<Integer> vI = rechnungsBestandKundenNr.get(r.getkNr());
        //
        if (rechnungsBestandNr.containsKey(r.getrNr()) & vI.contains(r.getrNr())) {
            rechnungsBestandNr.remove(r.getrNr());

            vI.removeElement(r.getrNr());
            rechnungsBestandKundenNr.put(r.getkNr(), vI);
            return true;
        } else {
            return false;
        }


    }

    /**
     * Methode gibt einen Vector zurueck der alle vorhandenen Rechnungen enthaelt
     */
    public Vector alleRechnungenZurueckgeben() {

        Vector<Rechnung> ergebnis = new Vector<Rechnung>();

        for (Rechnung elem : rechnungsBestandNr.values())
            ergebnis.add(elem);

        return ergebnis;

    }

    /**
     * Methode durchsucht alle Rechnungen nach einer Rechnungsnummer
     *
     * @param rNr
     */
    public Rechnung sucheRechnung(int rNr) {
        if (rechnungsBestandNr.containsKey(rNr)) {
            return rechnungsBestandNr.get(rNr);
        } else {
            return null;
        }
    }

    /**
     * Methode aendert den Bestand des gewuenschten Artikels wenn er exestiert
     *
     * @param rNr, dNow
     */
    public boolean setDate(int rNr, Date dNow) {

        // Wenn die Artikelnummer exestiert wird der Bestand angepasst
        if (rechnungsBestandNr.containsKey(rNr)) {

            Rechnung r = rechnungsBestandNr.get(rNr);

            r.setDate(dNow);
            rechnungsBestandNr.put(rNr, r);

            return true;

        } else {

            return false;
        }

    }

}

