package eshop.local.domain;

import eshop.local.exception.RechnungExestiertNichtException;
import eshop.local.exception.RechnungKeineVorhandenException;
import eshop.local.persistence.FilePersistenceManager;
import eshop.local.persistence.Log;
import eshop.local.persistence.PersistenceManager;
import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Kunde;
import eshop.local.valueobjects.Rechnung;

import java.io.*;
import java.text.SimpleDateFormat;
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

    // Dokument zum Speichern des Logs
    private File dateiName = new File("Eshop_RechnungsLog.txt");

    // Hashmap zum speichern des Rechnungsbestandes als Key dienen die Rechnungsnummern
    private HashMap<Integer, Rechnung> rechnungsBestandNr;

    // Hashmap zum verknüpfen der Kundennummer mit den Rechnungsnummern
    private HashMap<Integer, Vector<Integer>> rechnungsBestandKundenNr;

    // Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
    private PersistenceManager pm = new FilePersistenceManager();

    // Anderes Datums-Format
    private final SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz");

    private Log l = new Log();

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
     * @throws IOException
     * @throws ClassNotFoundException
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
        } finally {
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
     * @throws IOException
     * @throws RechnungExestiertNichtException
     *
     */
    public void rechnungHinzufuegen(Kunde kunde) throws IOException, RechnungExestiertNichtException {

        // sollte irgendwann schon einmal eine Rechnung für den Kundne erstellt worden sein
        if (rechnungsBestandKundenNr.containsKey(kunde.getNummer())) {
            Vector<Artikel> wkV = new Vector<Artikel>();
            double gesamtPreis = 0;
            for (Artikel elem : kunde.getWarenkorb().values())

                wkV.add(elem);


            for (Artikel elem : kunde.getWarenkorb().values())

                gesamtPreis = gesamtPreis + (elem.getPreis() * elem.getBestellteMenge());


            Rechnung rechnung = new Rechnung(kunde.getNummer(), wkV, gesamtPreis);
            rechnungsBestandNr.put(rechnung.getrNr(), rechnung);
            Vector<Integer> vI = rechnungsBestandKundenNr.get(kunde.getNummer());
            vI.add(rechnung.getrNr());
            rechnungsBestandKundenNr.put(kunde.getNummer(), vI);
            if (rechnung.getdNow() == null) {
                Date dNow = new Date();
                setDate(rechnung.getrNr(), dNow);
                String text = ft.format(rechnung.getdNow()) + ": Die Rechnung mit der Rechnungsnummer " + rechnung.getrNr() + " wurde hinzugefügt.";
                l.write(dateiName, text);


            }


        } else if (!rechnungsBestandKundenNr.containsKey(kunde.getNummer())) {
            Vector<Artikel> wkV = new Vector<Artikel>();

            double gesamtPreis = 0;
            for (Artikel elem : kunde.getWarenkorb().values())

                wkV.add(elem);


            for (Artikel elem : kunde.getWarenkorb().values())

                gesamtPreis = gesamtPreis + (elem.getPreis() * elem.getBestellteMenge());
            Rechnung rechnung = new Rechnung(kunde.getNummer(), wkV, gesamtPreis);
            rechnungsBestandNr.put(rechnung.getrNr(), rechnung);
            Vector<Integer> vI = new Vector<Integer>();
            vI.add(rechnung.getrNr());
            rechnungsBestandKundenNr.put(kunde.getNummer(), vI);
            if (rechnung.getdNow() == null) {
                Date dNow = new Date();
                setDate(rechnung.getrNr(), dNow);
                String text = ft.format(rechnung.getdNow()) + ": Die Rechnung mit der Rechnungsnummer " + rechnung.getrNr() + " wurde hinzugefügt.";
                l.write(dateiName, text);


            }

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
     * @throws IOException
     * @throws RechnungExestiertNichtException
     *
     */
    public void rechnungLoeschen(Rechnung rechnung) throws IOException, RechnungExestiertNichtException {

        Rechnung r = rechnung;
        Vector<Integer> vI = rechnungsBestandKundenNr.get(r.getkNr());
        //
        if (rechnungsBestandNr.containsKey(r.getrNr()) & vI.contains(r.getrNr())) {
            rechnungsBestandNr.remove(r.getrNr());

            vI.removeElement(r.getrNr());
            rechnungsBestandKundenNr.put(r.getkNr(), vI);
            Date dNow = new Date();
            String text = ft.format(dNow) + ": Die Rechnung mit der Rechnungsnummer " + rechnung.getrNr() + " wurde gelöscht.";
            l.write(dateiName, text);

        } else if ((!(rechnungsBestandNr.containsKey(r.getrNr()))) & (!(vI.contains(r.getrNr())))) {
            throw new RechnungExestiertNichtException(r.getrNr());
        }


    }

    /**
     * Methode gibt einen Vector zurueck der alle vorhandenen Rechnungen enthaelt
     *
     * @return
     * @throws RechnungKeineVorhandenException
     *
     */
    public Vector alleRechnungenZurueckgeben() throws RechnungKeineVorhandenException {

        if ((!rechnungsBestandNr.values().isEmpty())) {
            Vector<Rechnung> ergebnis = new Vector<Rechnung>();

            for (Rechnung elem : rechnungsBestandNr.values())
                ergebnis.add(elem);

            return ergebnis;
        } else if (rechnungsBestandNr.values().isEmpty()) {
            throw new RechnungKeineVorhandenException();
        } else {
            return null;
        }
    }

    /**
     * Methode gibt ein Rechnungobject zurück
     *
     * @param kNr
     * @return
     * @throws RechnungExestiertNichtException
     *
     */
    public Rechnung letzteKundenrechnungAusgeben(int kNr) throws RechnungExestiertNichtException {

        if (rechnungsBestandKundenNr.containsKey(kNr)) {
            Vector<Integer> rechnungsnummern = rechnungsBestandKundenNr.get(kNr);

            int nr = rechnungsnummern.lastElement();
            Rechnung ergebnis = rechnungsBestandNr.get(nr);

            return ergebnis;
        } else if (!rechnungsBestandKundenNr.containsKey(kNr)) {
            throw new RechnungExestiertNichtException();
        } else {
            return null;
        }

    }

    /**
     * Methode durchsucht alle Rechnungen nach einer Rechnungsnummer
     *
     * @param rNr
     * @return
     * @throws RechnungExestiertNichtException
     *
     */
    public Rechnung sucheRechnung(int rNr) throws RechnungExestiertNichtException {
        if (rechnungsBestandNr.containsKey(rNr)) {
            return rechnungsBestandNr.get(rNr);
        } else if (!rechnungsBestandNr.containsKey(rNr)) {
            throw new RechnungExestiertNichtException(rNr);
        } else {
            return null;
        }
    }

    /**
     * Methode setzt das Datum einer Rechnung wenn sie exestiert
     *
     * @param rNr
     * @param dNow
     * @throws RechnungExestiertNichtException
     *
     */
    public void setDate(int rNr, Date dNow) throws RechnungExestiertNichtException {

        // Wenn die Artikelnummer exestiert wird der Bestand angepasst
        if (rechnungsBestandNr.containsKey(rNr)) {

            Rechnung r = rechnungsBestandNr.get(rNr);

            r.setDate(dNow);
            rechnungsBestandNr.put(rNr, r);



        } else if (!rechnungsBestandNr.containsKey(rNr)) {
          throw new RechnungExestiertNichtException(rNr);
        }

    }

}

