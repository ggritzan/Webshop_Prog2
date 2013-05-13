package eshop.local.domain;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import eshop.local.persistence.Log;
import eshop.local.valueobjects.Adresse;
import eshop.local.valueobjects.Artikel;
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
    private final SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz");

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
        } finally {
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
     */
    public boolean mitarbeiterHinzufuegen(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) throws IOException{

        // Wenn der Name eines Mitarbeiters bereits vorhanden ist wird false zurück gegeben und kein neuer Mitarbeiter angelegt
        if (mitarbeiterBestandName.containsKey(benutzername)) {
            return false;

        } else {
            // Ist der Mitarbeitername noch nicht vorhanden wird er neu angelegt und in den beiden HashMaps gespeichert (artikelBestandNr, artikelBestandName)
            Mitarbeiter mitarbeiter = new Mitarbeiter(vorname, nachname, benutzername, passwort, email, telefon, adresse);
            mitarbeiterBestandNr.put(mitarbeiter.getmNr(), mitarbeiter);
            mitarbeiterBestandName.put(benutzername, mitarbeiter.getmNr());
            Date dNow = new Date();
            String text = ft.format(dNow) + ": Der Mitarbeiter '" + benutzername + "' mit der Mitarbeiternummer " + mitarbeiter.getmNr() + " wurde hinzugefügt.";
            l.write(dateiName, text);
            return true;
        }

    }

    /**
     * Methode zum hinzufuegen von Mitarbeitern durch den PersistenceManager
     * @param mitarbeiter
     */
    public void mitarbeiterHinzufuegen(Mitarbeiter mitarbeiter) {

        // Erzeugt Mitarbeiter mit ihrer bisherigen Mitarbeiternummer
        Mitarbeiter m = new Mitarbeiter(mitarbeiter);
        mitarbeiterBestandNr.put(m.getmNr(), m);
        mitarbeiterBestandName.put(mitarbeiter.getBenutzername(), m.getmNr());

    }

    /**
     * Methode zum löschen von Mitarbeitern
     * @param maNr
     * @return boolean
     */
    public boolean mitarbeiterLoeschen(int maNr) throws IOException{

        if (mitarbeiterBestandNr.containsKey(maNr)) {

            Mitarbeiter m = mitarbeiterBestandNr.get(maNr);
            mitarbeiterBestandNr.remove(maNr);
            mitarbeiterBestandName.remove(m.getBenutzername());
            Date dNow = new Date();
            String text = ft.format(dNow) + ": Der Mitarbeiter '" + m.getBenutzername() + "' mit der Mitarbeiternummer " + maNr + " wurde gelöscht.";
            l.write(dateiName, text);
            return true;

        } else {

            return false;
        }

    }

    /**
     * Methode gibt einen Vector zurueck der alle vorhandenen Mitarbeiter enthaelt
     */
    public Vector alleMitarbeiterZurueckgeben() {

        Vector<Mitarbeiter> ergebnis = new Vector<Mitarbeiter>();

        for (Mitarbeiter elem : mitarbeiterBestandNr.values())
            ergebnis.add(elem);

        return ergebnis;

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
            error.add("Der Mitarbeiter ist nicht vorhanden !");

            return error;
        }

    }

    public boolean findeMitarbeiter(String benutzername, String passwort) {
        if (mitarbeiterBestandName.containsKey(benutzername)) {
            int mnr = mitarbeiterBestandName.get(benutzername);
            Mitarbeiter m = mitarbeiterBestandNr.get(mnr);
            String p = m.getPasswort();
            if (p.equals(passwort)) {
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

    public Mitarbeiter getMitarbeiter(String bName){
        Mitarbeiter m;
        if(mitarbeiterBestandName.containsKey(bName)){
            int mnr = mitarbeiterBestandName.get(bName);
            m = mitarbeiterBestandNr.get(mnr);
            return m;
        }else{
            System.out.println("Der Mitarbeiteraccount konnte leider nicht gefunden werden.");
            int mnr = mitarbeiterBestandName.get(bName);
            m = mitarbeiterBestandNr.get(mnr);
            return m;
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

    public Mitarbeiter getMitarbeiter(int mNr){
        if (mitarbeiterBestandNr.containsKey(mNr)) {
            return mitarbeiterBestandNr.get(mNr);
        } else {
            return null;
        }
    }


}

