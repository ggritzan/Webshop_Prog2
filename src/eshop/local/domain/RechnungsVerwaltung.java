package eshop.local.domain;

import eshop.local.persistence.FilePersistenceManager;
import eshop.local.persistence.PersistenceManager;
import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Kunde;
import eshop.local.valueobjects.Rechnung;

import java.io.IOException;
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

        // verknüpft die Aufträgsnamen mit den Rechnungsnummern
        rechnungsBestandKundenNr = new HashMap<Integer, Vector<Integer>>();
    }

    /**
     * Methode zum Einlesen der Rechnungsdaten aus einer Datei.
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
            pm.close();
        } catch (IOException e) {
            System.out.println("Fehler beim einlesen der Rechnungsdaten !");
            e.printStackTrace();
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
     * Methode zum hinzufuegen von Kunden
     *
     * @param kunde
     */

    public boolean rechnungHinzufuegen(Kunde kunde) {

        // Wenn der Name eines Auftrages bereits vorhanden ist wird false zurück gegeben und kein neuer Auftrag angelegt
        if (rechnungsBestandKundenNr.containsKey(kunde.getNummer())) {
            Rechnung rechnung = new Rechnung(kunde.getNummer(), kunde.getWarenkorb());
            rechnungsBestandNr.put(rechnung.getrNr(), rechnung);
            Vector<Integer> vI = rechnungsBestandKundenNr.get(kunde.getNummer());
            vI.add(rechnung.getrNr());
            rechnungsBestandKundenNr.put(kunde.getNummer(), vI);
            return true;

        } else if(!rechnungsBestandKundenNr.containsKey(kunde.getNummer())){
            // Ist der Auftragsname noch nicht vorhanden, wird er neu angelegt und in den beiden HashMaps gespeichert (rechnungsBestandNr, rechnungBestandName)
            Rechnung rechnung = new Rechnung(kunde.getNummer(), kunde.getWarenkorb());
            rechnungsBestandNr.put(rechnung.getrNr(), rechnung);
            Vector<Integer> vI = new Vector<Integer>();
            vI.add(rechnung.getrNr());
            rechnungsBestandKundenNr.put(kunde.getNummer(), vI);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Methode zum hinzufuegen von Kunden durch den PersistenceManager
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
}
