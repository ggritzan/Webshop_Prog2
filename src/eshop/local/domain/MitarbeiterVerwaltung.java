package eshop.local.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

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
    // Hashmap zum speichern des Mitarbeiterbestands als Key dienen die Mitarbeiternummern
    private HashMap<Integer, Mitarbeiter> mitarbeiterBestandNr;

    // Hashmap zum verknüpfen des Mitarbeiternamens mit der Mitarbeiternummer
    private HashMap<String, Integer> mitarbeiterBestandName;

    // Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
    private PersistenceManager pm = new FilePersistenceManager();


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
            pm.close();
        } catch (IOException e) {
            System.out.println("Der Datenbestand ist leer");
        } finally {
            //schließt im Fall einer IO Exception bei geöffneter Datei diese wieder
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
                Artikel a = (Artikel) iter.next();
                pm.speichereArtikel(a);
            }
        }
        //Persistenz-Schnittstelle wieder schließen
        pm.close();
    }

    /**
     * Methode zum hinzufuegen von Mitarbeitern
     */
    public boolean mitarbeiterHinzufuegen(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) {

        // Wenn der Name eines Mitarbeiters bereits vorhanden ist wird false zurück gegeben und kein neuer Mitarbeiter angelegt
        if (mitarbeiterBestandName.containsKey(benutzername)) {
            return false;

        } else {
            // Ist der Mitarbeitername noch nicht vorhanden wird er neu angelegt und in den beiden HashMaps gespeichert (artikelBestandNr, artikelBestandName)
            Mitarbeiter mitarbeiter = new Mitarbeiter(vorname, nachname, benutzername, passwort, email, telefon, adresse);
            mitarbeiterBestandNr.put(mitarbeiter.getmNr(), mitarbeiter);
            mitarbeiterBestandName.put(benutzername, mitarbeiter.getmNr());
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
    public boolean mitarbeiterLoeschen(int maNr) {

        if (mitarbeiterBestandNr.containsKey(maNr)) {

            Mitarbeiter m = mitarbeiterBestandNr.get(maNr);
            mitarbeiterBestandNr.remove(maNr);
            mitarbeiterBestandName.remove(m.getBenutzername());
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

}
