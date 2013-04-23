package eshop.local.domain;

import eshop.local.persistence.FilePersistenceManager;
import eshop.local.persistence.PersistenceManager;
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

    // Hashmap zum verknüpfen der Auftragsnamen mit der Rechnungsnummer
    private HashMap<String, Integer> rechnungsBestandName;

    // Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
    private PersistenceManager pm = new FilePersistenceManager();

    // Konstruktor

    public RechnungsVerwaltung() {

        // verknüpft die Rechungsnummern mit den Rechnungsobjekten
        rechnungsBestandNr = new HashMap<Integer, Rechnung>();

        // verknüpft die Aufträgsnamen mit den Rechnungsnummern
        rechnungsBestandName = new HashMap<String, Integer>();
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


    /*
     *  TODO: eine Methode die, die Auftragsnamen automatisch generiert als Beispiel:
     *  int zaehler = 1000;
     *  String warenkorbname;
     *  String auftragsname = warenkorbname + zaehler;
     *  auftragsname.toString();
     *  zaehler++;
     */

    /**
     * Methode zum hinzufuegen von Kunden
     *
     * @param auftragsname
     * @param rNr
     */

    public boolean rechnungHinzufuegen(String auftragsname) {

        // Wenn der Name eines Auftrages bereits vorhanden ist wird false zurück gegeben und kein neuer Auftrag angelegt
        if (rechnungsBestandName.containsKey(auftragsname)) {
            return false;

        } else {
            // Ist der Auftragsname noch nicht vorhanden, wird er neu angelegt und in den beiden HashMaps gespeichert (rechnungsBestandNr, rechnungBestandName)
            Rechnung rechnung = new Rechnung(auftragsname);
            rechnungsBestandNr.put(rechnung.getrNr(), rechnung);
            rechnungsBestandName.put(auftragsname, rechnung.getrNr());
            return true;
        }

    }

    /**
     * Methode zum hinzufuegen von Kunden durch den PersistenceManager
     *
     * @param rechnung
     */
    public void rechnungHinzufuegen(Rechnung rechnung) {

        // Erzeugt eine Rechnung mit seiner bisherigen Rechnungsnummer
        Rechnung r = new Rechnung(rechnung);
        rechnungsBestandNr.put(r.getrNr(), r);
        rechnungsBestandName.put(rechnung.getAuftragsname(), r.getrNr());

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
     * Methode durchsucht alle Rechnungen nach dem Parameter auftragsname und gibt die entsprechende Rechnung in einem Vektor zurueck
     *
     * @param auftragsname
     */
    public Vector sucheRechnungen(String auftragsname) {
        int rNr = 0;
        Vector<Rechnung> ergebnis = new Vector<Rechnung>();

        if (rechnungsBestandName.containsKey(auftragsname)) {
            rNr = rechnungsBestandName.get(auftragsname);
            ergebnis.add(rechnungsBestandNr.get(rNr));

            return ergebnis;

        } else {
            Vector<String> error = new Vector<String>();
            error.add("Die Rechnung ist nicht vorhanden !");

            return error;
        }

    }
}

