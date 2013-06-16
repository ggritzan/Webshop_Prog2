package eshop.local.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import eshop.local.exception.*;
import eshop.local.persistence.Log;
import eshop.local.valueobjects.Adresse;
import eshop.local.persistence.FilePersistenceManager;
import eshop.local.persistence.PersistenceManager;
import eshop.local.valueobjects.Mitarbeiter;

/**
 * Created with IntelliJ IDEA.
 * User: ninadoge
 * Date: 21.04.13
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterVerwaltung {

    // Dokument zum Speichern des Logs
    private File dateiName = new File("Eshop_MitarbeiterLog.txt");

    // Hashmap zum speichern des Mitarbeiterbestands als Key dienen die Mitarbeiternummern
    private HashMap<Integer, Mitarbeiter> mitarbeiterBestandNr;

    // Hashmap zum verknüpfen des Mitarbeiterbenutzernamens mit der Mitarbeiternummer
    private HashMap<String, Integer> mitarbeiterBestandName;

    // Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
    private PersistenceManager pm = new FilePersistenceManager();

    // Anderes Datums-Format
    private final SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz':'");

    private Log l = new Log();


// Konstruktor

    public MitarbeiterVerwaltung() {

        // verknüpft die Mitarbeiternummern mit den Mitarbeiter Objekten
        mitarbeiterBestandNr = new HashMap<Integer, Mitarbeiter>();

        // verknüpft Mitarbeiternamen mit den Mitarbeiternummern
        mitarbeiterBestandName = new HashMap<String, Integer>();
    }

// Methoden

    /**
     * Methode zum Einlesen der Mitarbeiterdaten aus einer Datei.
     *
     * @param datei Datei, die den einzulesenden Artikelbestand enthaelt
     * @throws IOException,ClassNotFoundException
     *
     */
    public void liesDaten(String datei) throws IOException, ClassNotFoundException {

        // Erstellung eines Artikel Objekts
        Mitarbeiter einMitarbeiter;

        try {
            pm.openForReading(datei);
            do {
                einMitarbeiter = pm.ladeMitarbeiter();
                if (einMitarbeiter != null) {
                    mitarbeiterHinzufuegen(einMitarbeiter);

                }

            }
            while (einMitarbeiter != null);
        } catch (IOException e) {
            System.out.println("Der Datenbestand ist leer");
        } catch (MitarbeiterExistiertBereitsException meb) {
            System.err.println(meb.getMessage());
        }
        finally {
            pm.close();
        }
    }

    /**
     * Methode zum Schreiben der Mitarbeiterdaten in eine Datei.
     * @param datei Datei, in die der Mitarbeiterbestand geschrieben werden soll
     * @throws IOException
     */
    public void schreibeDaten(String datei) throws IOException {
        pm.openForWriting(datei);
        // Artikel-Objekte aus der Hashmap mitarbeiterBestandNr einlesen und in die Textdatei schreiben
        if (!mitarbeiterBestandNr.isEmpty()) {
            Iterator iter = mitarbeiterBestandNr.values().iterator();
            while (iter.hasNext()) {
                Mitarbeiter m = (Mitarbeiter) iter.next();
                pm.speichereMitarbeiter(m);
            }
        }
        //Persistenz-Schnittstelle wieder schließen
        pm.close();
    }


    /**
     * Methode zum hinzufuegen von Mitarbeitern
     * @param vorname
     * @param nachname
     * @param benutzername
     * @param passwort
     * @param email
     * @param telefon
     * @param adresse
     * @throws IOException
     * @throws MitarbeiterExistiertBereitsException
     */
    public void mitarbeiterHinzufuegen(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) throws IOException, MitarbeiterExistiertBereitsException{

        if(mitarbeiterBestandName.containsKey(benutzername)){
            throw new MitarbeiterExistiertBereitsException(benutzername);
        } else {
            // Ist der Mitarbeitername noch nicht vorhanden wird er neu angelegt und in den beiden HashMaps gespeichert (artikelBestandNr, artikelBestandName)
            Mitarbeiter mitarbeiter = new Mitarbeiter(vorname, nachname, benutzername, passwort, email, telefon, adresse);
            mitarbeiterBestandNr.put(mitarbeiter.getmNr(), mitarbeiter);
            mitarbeiterBestandName.put(benutzername, mitarbeiter.getmNr());
            Date dNow = new Date();
            String text = ft.format(dNow) + "\nDer Mitarbeiter '" + benutzername + "' mit der Mitarbeiternummer " + mitarbeiter.getmNr() + " wurde hinzugefügt.";
            l.writeLog(dateiName, text);

        }

    }

    /**
     * Methode zum hinzufuegen von Mitarbeitern durch den PersistenceManager
     * @param m
     * @throws MitarbeiterExistiertBereitsException
     */
    public void mitarbeiterHinzufuegen(Mitarbeiter m) throws MitarbeiterExistiertBereitsException{

        mitarbeiterBestandNr.put(m.getmNr(), m);
        mitarbeiterBestandName.put(m.getBenutzername(), m.getmNr());
        if (m.getZaehler() <= m.getmNr()) {
            m.setZaehler(m.getmNr() + 1);
        }


    }

    /**
     * Methode zum löschen von Mitarbeitern
     * @param maNr
     * @param mitarbeiter
     * @throws IOException
     * @throws MitarbeiterExistiertNichtException
     */
    public void mitarbeiterLoeschen(int maNr, Mitarbeiter mitarbeiter) throws IOException, MitarbeiterExistiertNichtException{

        if (mitarbeiterBestandNr.containsKey(maNr)) {

            Mitarbeiter m = mitarbeiterBestandNr.get(maNr);
            mitarbeiterBestandNr.remove(maNr);
            mitarbeiterBestandName.remove(m.getBenutzername());
            Date dNow = new Date();
            String text = ft.format(dNow) + "\nDer Mitarbeiter '" + m.getBenutzername() + "' mit der Mitarbeiternummer " + maNr + " wurde vom Mitarbeiter " + mitarbeiter.getBenutzername() + " mit der Mitarbeiternummer " + mitarbeiter.getmNr() + " gelöscht.";
            l.writeLog(dateiName, text);

        } else {
            throw new MitarbeiterExistiertNichtException();
        }

    }

    /**
     * Methode gibt einen Vector zurueck der alle vorhandenen Mitarbeiter enthaelt
     * @return
     */
    public Vector alleMitarbeiterZurueckgeben() {

        Vector<Mitarbeiter> ergebnis = new Vector<Mitarbeiter>();

        for (Mitarbeiter elem : mitarbeiterBestandNr.values())
            ergebnis.add(elem);

        return ergebnis;



    }

    /**
     * Methode gibt die HashMap zurueck die alle vorhandenen Mitarbeiter enthaelt
     * @return
     */
    public HashMap<Integer, Mitarbeiter> alleMitarbeiterHashMapZurueckgeben() {
        return mitarbeiterBestandNr;
    }

    /**
     * Methode durchsucht alle Mitarbeiter nach dem Parameter Bentzername und gibt den entsprechenden Mitarbeiter in einem Vektor zurueck
     *
     * @param benutzername
     */
    public Vector sucheMitarbeiter(String benutzername) {
        int nummer = 0;
        Vector<Mitarbeiter> ergebnis = new Vector<Mitarbeiter>();

        if (mitarbeiterBestandName.containsKey(benutzername)) {
            nummer = mitarbeiterBestandName.get(benutzername);
            ergebnis.add(mitarbeiterBestandNr.get(nummer));

            return ergebnis;

        } else {
            Vector<String> error = new Vector<String>();
            error.add("Der Mitarbeiter "+benutzername+ " ist nicht vorhanden !");

            return error;
        }

    }

    /**
     * Gibt die entsprechende MitarbeiterNr zu einem Benutzernamen zurück
     * @return
     */
    public int getMitarbeiterNr(String benutzername) throws MitarbeiterExistiertNichtException{

        if (mitarbeiterBestandName.containsKey(benutzername)) {
            int mnr = mitarbeiterBestandName.get(benutzername);
            Mitarbeiter m = mitarbeiterBestandNr.get(mnr);

            return m.getmNr();

        } else {

            throw new MitarbeiterExistiertNichtException();
        }
    }

    /**
     * Funktion für den Login wenn der Mitarbeiter mit der entsprechenden passwort vorhanden ist
     * liefert die Funktion ein true zurueck ansonsten false
     * @param benutzername
     * @param passwort
     * @return
     */
    public boolean findeMitarbeiter(String benutzername, String passwort) {
        if (mitarbeiterBestandName.containsKey(benutzername)) {
            int mnr = mitarbeiterBestandName.get(benutzername);
            Mitarbeiter m = mitarbeiterBestandNr.get(mnr);
            String p = m.getPasswort();
            if (p.equals(passwort)) {
                return true;
            }else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Sucht einen Mitarbeiter anhand des Benutzernamens
     * @param bName
     * @return
     * @throws MitarbeiterExistiertNichtException
     */
    public Mitarbeiter getMitarbeiter(String bName) throws MitarbeiterExistiertNichtException {
        Mitarbeiter m;
        if(mitarbeiterBestandName.containsKey(bName)){
            int mnr = mitarbeiterBestandName.get(bName);
            m = mitarbeiterBestandNr.get(mnr);
            return m;
        }else{
            throw new MitarbeiterExistiertNichtException();
        }
    }

    /**
     * Methode durchsucht alle Mitarbeiter nach dem Benutzernamen und gibt die Mitarbeiternummer zurück
     *
     * @param bName
     */
    public int getMnr(String bName) {
        if (mitarbeiterBestandName.containsKey(bName)) {
            return mitarbeiterBestandName.get(bName);
        } else {
            return 0;
        }
    }

    /**
     * liefert ein Mitarbeiter Objekt wenn ein Mitarbeiter mit der MitarbeiterNummer exestiert
     * @param mNr
     * @return
     */
    public Mitarbeiter getMitarbeiter(int mNr){
        if (mitarbeiterBestandNr.containsKey(mNr)) {
            return mitarbeiterBestandNr.get(mNr);
        } else {
            return null;
        }
    }

    //TODO @Noshaba Bitte kommentieren
    public Vector<String> printMitarbeiterLog(int daysInPast, String mNr) throws FileNotFoundException, ParseException, KennNummerExistiertNichtException {
        return l.printLog("Eshop_MitarbeiterLog.txt", daysInPast, mNr);
    }

    public Vector<String> printMitarbeiterLog(int daysInPast) throws FileNotFoundException, KeineEintraegeVorhandenException, ParseException{
        return l.printLog("Eshop_MitarbeiterLog.txt", daysInPast);
    }

    public Vector<String> printMitarbeiterLog(String mNr) throws FileNotFoundException, ParseException, KennNummerExistiertNichtException{
        return l.printLog("Eshop_MitarbeiterLog.txt", mNr);
    }

    public String printMitarbeiterLog() throws FileNotFoundException, KeineEintraegeVorhandenException{
        return l.printLog("Eshop_MitarbeiterLog.txt");
    }

    /**
     * Methode zum Passwortaendern für Mirarbeiter
     *
     * @param m -> Mitarbeiter der sein Passwort ändern möchte
     * @param p1 -> das neue Passwort
     * @param p2 -> Kontrollpasswort
     *
     * @throws NeuesPasswortFehlerhaftException
     */
    public void passwortAendern(Mitarbeiter m, String p1, String p2) throws NeuesPasswortFehlerhaftException{
        if (p1.equals(p2)) {
            m.setPasswort(p1);
        } else {
            throw new NeuesPasswortFehlerhaftException();
        }
    }
}

