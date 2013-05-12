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
import eshop.local.persistence.FilePersistenceManager;
import eshop.local.persistence.PersistenceManager;
import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Kunde;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 18.04.13
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
public class KundenVerwaltung {

    // Dokument zum Speichern des Logs
    private File dateiName = new File("Eshop_KundenLog.txt");

    // Hashmap zum speichern des Kundenbestands als Key dienen die Kundennummern
    private HashMap<Integer, Kunde> kundenBestandNr;

    // Hashmap zum verknüpfen des Benutzernamens mit der Kundennummer
    private HashMap<String, Integer> kundenBestandBenutzername;

    // Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
    private PersistenceManager pm = new FilePersistenceManager();

    // Anderes Datums-Format
    private final SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz");

    private Log l = new Log();


// Konstruktor

    public KundenVerwaltung() {

        // verknüpft die Kundennummern mit den Kunden Objekten
        kundenBestandNr = new HashMap<Integer, Kunde>();

        // verknüpft die Benutzernamen  mit den Kundennummern
        kundenBestandBenutzername = new HashMap<String, Integer>();
    }

// Methoden

    /**
     * Methode zum Einlesen der Kundendaten aus einer Datei.
     *
     * @param datei
     * @throws IOException,ClassNotFoundException
     *
     */
    public void liesDaten(String datei) throws IOException, ClassNotFoundException {

        // Erstellung eines Kunden Objekts
        Kunde einKunde;

        try {

            // PersistenzManager für Lesevorgänge wird geöffnet
            pm.openForReading(datei);

            do {
                // Kunden-Objekt einlesen
                einKunde = pm.ladeKunde();
                if (einKunde != null) {

                    // Kunde einfügen
                    kundeHinzufuegen(einKunde);

                }

            } while (einKunde != null);

            // PersistenzManager für Lesevorgänge wird wieder geschlossen
            pm.close();
        } catch (IOException e) {
            System.out.println("Fehler beim einlesen der Kundendaten !");
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

        // Kunden-Objekte aus der Hashmap kundenBestandNr einlesen und in die Datei schreiben
        if (!kundenBestandNr.isEmpty()) {
            Iterator iter = kundenBestandNr.values().iterator();
            while (iter.hasNext()) {
                Kunde k = (Kunde) iter.next();
                pm.speichereKunde(k);
            }
        }

        //Persistenz-Schnittstelle wieder schließen
        pm.close();
    }

    /**
     * Methode zum hinzufuegen von Kunden
     *
     * @param vorname
     * @param nachname
     * @param benutzername
     * @param passwort
     * @param email
     * @param telefon
     */
    public boolean kundeHinzufuegen(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) throws IOException{

        // Wenn der Benutzername eines Kunden bereits vorhanden ist wird false zurück gegeben und kein neuer Kunde angelegt
        if (kundenBestandBenutzername.containsKey(benutzername)) {
            return false;


        } else {
            // Ist der Benutzername noch nicht vergeben wird er neu angelegt und in den beiden HasMaps gespeichert (kundenBestandNr, kundenBestandBenutzername)
            Kunde kunde = new Kunde(vorname, nachname, benutzername, passwort, email, telefon, adresse);
            kundenBestandNr.put(kunde.getNummer(), kunde);
            kundenBestandBenutzername.put(benutzername, kunde.getNummer());
            Date dNow = new Date();
            String text = ft.format(dNow) + ": Der Kunde '" + benutzername + "' mit der Kundennummer " + kunde.getNummer() + " wurde hinzugefügt.";
            l.write(dateiName, text);
            return true;
        }


    }

    /**
     * Methode zum hinzufuegen von Kunden durch den PersistenceManager
     *
     * @param kunde
     */
    public void kundeHinzufuegen(Kunde kunde) {

        // Erzeugt einen Kunden mit seiner bisherigen Kundennummer
        Kunde k = new Kunde(kunde);
        kundenBestandNr.put(k.getNummer(), k);
        kundenBestandBenutzername.put(kunde.getBenutzername(), k.getNummer());


    }

    /**
     * Methode zum löschen von Kunden
     *
     * @param kundenNr
     * @return boolean
     */
    public boolean kundenLoeschen(int kundenNr) throws IOException{

        if (kundenBestandNr.containsKey(kundenNr)) {

            Kunde k = kundenBestandNr.get(kundenNr);
            kundenBestandNr.remove(kundenNr);
            kundenBestandBenutzername.remove(k.getBenutzername());
            Date dNow = new Date();
            String text = ft.format(dNow) + ": Der Kunde '" + k.getBenutzername() + "' mit der Kundennummer " + kundenNr + " wurde gelöscht.";
            l.write(dateiName, text);

            return true;

        } else {

            return false;
        }

    }

   /**
     * Methode gibt einen Vector zurueck der alle vorhandenen Kunden enthaelt
     */
    public Vector alleKundenZurueckgeben() {

        Vector<Kunde> ergebnis = new Vector<Kunde>();

        for (Kunde elem : kundenBestandNr.values())
            ergebnis.add(elem);


        return ergebnis;

    }

    /**
     * Methode durchsucht alle Rechnungen nach einer Rechnungsnummer
     *
     * @param kNr
     */
    public Kunde getKunde(int kNr) {
        if (kundenBestandNr.containsKey(kNr)) {
            return kundenBestandNr.get(kNr);
        } else {
            return null;
        }
    }

    /**
     * Methode durchsucht alle Kunden nach dem Benutzername und gibt die Kundennummer zurück
     *
     * @param bName
     */
    public int getKnr(String bName) {
        if (kundenBestandBenutzername.containsKey(bName)) {
            return kundenBestandBenutzername.get(bName);
        } else {
            return 0;
        }
    }

    public boolean inWarenkorbLegen(Artikel a, int kNr) {
         Kunde k = kundenBestandNr.get(kNr);
        boolean ok =  k.inWarenkorbLegen(a);
        return ok ;
    }

    public boolean ausWarenkorbEtfernen(Artikel a, int kNr){
        Kunde k = kundenBestandNr.get(kNr);
        boolean ok = k.ausWarenkorbEntfernen(a);
        return ok;
    }

    public boolean resetWarenkorb(int kNr) {

        if(kundenBestandNr.containsKey(kNr)){
        Kunde k = kundenBestandNr.get(kNr);
        k.resetWarenkorb();
        kundenBestandNr.put(kNr,k);
        return true;
        }
        return false;
    }
    /**
     * Methode durchsucht alle Kunden nach dem Parameter Nachname und gibt die entsprechenden Kunden in einem Vektor zurueck
     *
     * @param

    public Vector sucheArtikel(String name) {


    }
     */
    /*

     */
    public boolean findeKunden(String benutzername, String passwort) {
        if (kundenBestandBenutzername.containsKey(benutzername)) {
            int knr = kundenBestandBenutzername.get(benutzername);
            Kunde k = kundenBestandNr.get(knr);
            String p = k.getPasswort();
            if (p.equals(passwort)) {
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }
    public Kunde getKunden(String bName){
        Kunde k;
        if(kundenBestandBenutzername.containsKey(bName)){
            int knr = kundenBestandBenutzername.get(bName);
            k = kundenBestandNr.get(knr);
            return k;
        }else{
            System.out.println("Der Kundenaccount konnte leider nicht gefunden werden.");
            int mnr = kundenBestandBenutzername.get(bName);
            k = kundenBestandNr.get(mnr);
            return k;
        }
    }
}


