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
import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Kunde;
import eshop.local.valueobjects.Mitarbeiter;

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
    private final SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz':'");

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
    public void kundeHinzufuegen(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) throws IOException, BenutzernameExistiertBereitsException {

        // Wenn der Benutzername eines Kunden bereits vorhanden ist wird false zurück gegeben und kein neuer Kunde angelegt
        if (kundenBestandBenutzername.containsKey(benutzername)) {
            throw new BenutzernameExistiertBereitsException(benutzername);


        } else if (!kundenBestandBenutzername.containsKey(benutzername)){
            // Ist der Benutzername noch nicht vergeben wird er neu angelegt und in den beiden HasMaps gespeichert (kundenBestandNr, kundenBestandBenutzername)
            Kunde kunde = new Kunde(vorname, nachname, benutzername, passwort, email, telefon, adresse);
            kundenBestandNr.put(kunde.getNummer(), kunde);
            kundenBestandBenutzername.put(benutzername, kunde.getNummer());
            Date dNow = new Date();
            String text = ft.format(dNow) + "\nDer Kunde '" + benutzername + "' mit der Kundennummer " + kunde.getNummer() + " wurde hinzugefügt.";
            l.writeLog(dateiName, text);
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
    public void kundenLoeschen(int kundenNr, Mitarbeiter m) throws IOException, KundenNummerExistiertNichtException{

        if (kundenBestandNr.containsKey(kundenNr)) {

            Kunde k = kundenBestandNr.get(kundenNr);
            kundenBestandNr.remove(kundenNr);
            kundenBestandBenutzername.remove(k.getBenutzername());
            Date dNow = new Date();
            String text = ft.format(dNow) + "\nDer Kunde '" + k.getBenutzername() + "' mit der Kundennummer " + kundenNr + " wurde vom Mitarbeiter " + m.getBenutzername() + " mit der Mitarbeiternummer " + m.getmNr() + " gelöscht.";
            l.writeLog(dateiName, text);

        } else if(!kundenBestandNr.containsKey(kundenNr)){
            throw new KundenNummerExistiertNichtException(kundenNr);
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
    public Kunde getKunde(int kNr) throws KundenNummerExistiertNichtException {
        if (kundenBestandNr.containsKey(kNr)) {
            return kundenBestandNr.get(kNr);
        } else if(!kundenBestandNr.containsKey(kNr)){
            throw new KundenNummerExistiertNichtException(kNr);
        } else {
            return null;
        }
    }

    /**
     * Methode durchsucht alle Kunden nach dem Benutzername und gibt die Kundennummer zurück
     *
     * @param bName
     */
    public int getKnr(String bName) throws BenutzernameExistiertNichtException{
        if (kundenBestandBenutzername.containsKey(bName)) {
            return kundenBestandBenutzername.get(bName);
        } else if (!kundenBestandBenutzername.containsKey(bName)){
            throw new BenutzernameExistiertNichtException(bName);
        } else {
            return 0;
        }
    }

    public void inWarenkorbLegen(Artikel a, int kNr) throws KundenNummerExistiertNichtException {
        if(kundenBestandNr.containsKey(kNr) ){
            Kunde k = kundenBestandNr.get(kNr);
            k.inWarenkorbLegen(a);
        } else if (!kundenBestandNr.containsKey(kNr)){
            throw new KundenNummerExistiertNichtException(kNr);
        }
    }

    public void ausWarenkorbEtfernen(Artikel a, int kNr) throws KundenNummerExistiertNichtException{

        if(kundenBestandNr.containsKey(kNr)){
            Kunde k = kundenBestandNr.get(kNr);
            k.ausWarenkorbEntfernen(a);
        } else if (!kundenBestandNr.containsKey(kNr)){
            throw new KundenNummerExistiertNichtException(kNr);
        }
    }

    public void resetWarenkorb(int kNr) throws KundenNummerExistiertNichtException{

        if(kundenBestandNr.containsKey(kNr)){
            Kunde k = kundenBestandNr.get(kNr);
            k.resetWarenkorb();
            kundenBestandNr.put(kNr,k);
        } else if(!kundenBestandNr.containsKey(kNr)){
            throw new KundenNummerExistiertNichtException(kNr);
        }
    }
    /**
     * Methode durchsucht alle Kunden nach dem Parameter Nachname und gibt die entsprechenden Kunden in einem Vektor zurueck
     *
     * @param
     */
    /*

     */
    public boolean findeKunden(String benutzername, String passwort) {
        if (kundenBestandBenutzername.containsKey(benutzername)) {
            int knr = kundenBestandBenutzername.get(benutzername);
            Kunde k = kundenBestandNr.get(knr);
            String p = k.getPasswort();
            System.out.println(p);
            System.out.println(k.getPasswort());
            if (p.equals(passwort)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public Kunde getKunden(String bName) throws BenutzernameExistiertNichtException{
        Kunde k;
        if(kundenBestandBenutzername.containsKey(bName)){
            int knr = kundenBestandBenutzername.get(bName);
            k = kundenBestandNr.get(knr);
            return k;
        }else if (!kundenBestandBenutzername.containsKey(bName)){
            throw new BenutzernameExistiertNichtException(bName);
        } else {
            return null;
        }
    }

    public boolean istImWarenkorb(Kunde k, int aNr){
        return k.istImWarenkorb(aNr);
    }

    public Vector<String> printKundenLog(int daysInPast, String kNr) throws FileNotFoundException, ParseException, KennNummerExistiertNichtException {
        return l.printLog("Eshop_KundenLog.txt", daysInPast, kNr);
    }

    public Vector<String> printKundenLog(int daysInPast) throws FileNotFoundException, KeineEintraegeVorhandenException, ParseException{
        return l.printLog("Eshop_KundenLog.txt", daysInPast);
    }

    public Vector<String> printKundenLog(String kNr) throws FileNotFoundException, ParseException, KennNummerExistiertNichtException{
        return l.printLog("Eshop_KundenLog.txt", kNr);
    }

    public String printKundenLog() throws FileNotFoundException, KeineEintraegeVorhandenException{
        return l.printLog("Eshop_KundenLog.txt");
    }
}


