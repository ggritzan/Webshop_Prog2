package eshop.local.domain;

import eshop.local.persistence.FilePersistenceManager;
import eshop.local.persistence.PersistenceManager;
import eshop.local.valueobjects.ArtikelLog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 11.05.13
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class ArtikelLogVerwaltung {

    //Vector zum Speichern des ArtikelLogs in eine dynamische Liste
    private Vector<ArtikelLog> artikelLogs;

    // Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
    private PersistenceManager pm = new FilePersistenceManager();

    //Konstruktor
    public ArtikelLogVerwaltung(){
        this.artikelLogs = new Vector<ArtikelLog>();
    }

    // Methoden

    /**
     * Methode zum Einlesen der ArtikelLog Daten aus einer Datei.
     *
     * @param datei Datei, die den einzulesenden ArtikelLogBestand enthaelt
     * @throws java.io.IOException,ClassNotFoundException
     *
     */
    public void liesDaten(String datei) throws IOException, ClassNotFoundException {

        // Erstellung eines ArtikelLog Objekts
        ArtikelLog aLog;

        try {

            // PersistenzManager für Lesevorgänge wird geöffnet
            pm.openForReading(datei);

            do {
                // Artikel-Objekt einlesen
                aLog = pm.ladeArtikelLog();
                if (aLog != null) {

                    // Artikel einfügen
                    artikelLogHinzufuegen(aLog);

                }

            } while (aLog != null);

            // PersistenzManager für Lesevorgänge wird wieder geschlossen
            pm.close();
        } catch (IOException e) {
            System.out.println("Fehler beim einlesen der ArtikelLog-Daten !");
        } finally {
            pm.close();
        }
    }

    /**
     * Methode zum Schreiben der ArtikelLog-Daten in eine Datei.
     *
     * @param datei Datei, in die der ArtikelLogBetstand geschrieben werden soll
     * @throws IOException
     */
    public void schreibeDaten(String datei) throws IOException {

        // PersistenzManager für Schreibvorgänge öffnen
        pm.openForWriting(datei);

        // ArtikelLog-Objekte aus dem Vector artikelLogs einlesen und in die Datei schreiben
        if (!artikelLogs.isEmpty()) {
            Iterator iter = artikelLogs.iterator();
            while (iter.hasNext()) {
                ArtikelLog al = (ArtikelLog) iter.next();
                pm.speichereArtikelLog(al);
            }
        }

        //Persistenz-Schnittstelle wieder schließen
        pm.close();
    }

    /**
     * Methode zum Hinzufuegen von ArtikelLogs
     *
     * @param aNr
     * @param name
     * @param bestand
     * @param menge
     */

    public void artikelLogHinzufuegen(int aNr, String name, int bestand, int menge) {

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        ArtikelLog al = new ArtikelLog(dNow, ft,aNr,name,bestand,menge);
        artikelLogs.add(al);

    }

    /**
     * Methode zum Hinzufuegen von ArtikelLogs durch den PersistenceManager
     *
     * @param aLog
     */
    public void artikelLogHinzufuegen(ArtikelLog aLog) {

        ArtikelLog al = new ArtikelLog(aLog);
        artikelLogs.add(al);

    }

    /**
     * Methode gibt einen Vector zurueck der alle vorhandenen ArtikelLogs enthaelt
     */
    public Vector alleArtikelLogsZurueckgeben() {

        return artikelLogs;

    }
}
