package eshop.local.domain;

import java.io.IOException;
import java.util.Vector;

//import com.sun.org.apache.xml.internal.security.utils.IgnoreAllErrorHandler;
import eshop.local.valueobjects.*;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 10.04.13
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */

public class EShopVerwaltung {

    // Präfix für Namen der Dateien, in der die Artikeldaten gespeichert sind
    private String datei = "";
    // Erzeugt eine Artikelverwaltung
    private ArtikelVerwaltung meineArtikel;
    // Erzeugt eine Kundenverwaltung
    private KundenVerwaltung meineKunden;
    // Erzeugt eine Rechnungsverwaltung
    private RechnungsVerwaltung meineRechnungen;
    // Erzeugt eine Mitarbeuterverwaltung
    private MitarbeiterVerwaltung meineMitarbeiter;
    // Erzeugt eine ArtikelLogVerwaltung
    private ArtikelLogVerwaltung meineArtLogs;

// Konstruktor

    /**
     * Namensmuster für Dateien:
     * datei+"_Artikel.ser" ist die Datei der Artikel
     * datei+"_Kunden.ser" ist die Datei der Kunden
     * datei+"_Rechnungen.ser" ist die Datei der Rechnungen
     *
     * @param datei
     * @throws IOException,ClassNotFoundException z.B. wenn eine der Dateien nicht existiert.
     * @return Vector
     */
    public EShopVerwaltung(String datei) throws IOException, ClassNotFoundException {

        this.datei = datei;
        // Artikelbestand aus Datei einlesen
        meineArtikel = new ArtikelVerwaltung();
        meineArtikel.liesDaten(datei + "_Artikel.ser");
        // Kundenkartei aus Datei einlesen
        meineKunden = new KundenVerwaltung();
        meineKunden.liesDaten(datei + "_Kunden.ser");
        // Rechnungsbestand aus Datei einlesen
        meineRechnungen = new RechnungsVerwaltung();
        meineRechnungen.liesDaten(datei + "_Rechnungen.ser");
        //Mitarbeiterbestand aus Datei einlesen
        meineMitarbeiter = new MitarbeiterVerwaltung();
        meineMitarbeiter.liesDaten(datei + "_Mitarbeiter.ser");
        //ArtikelLogs aus Datei einlesen
        meineArtLogs = new ArtikelLogVerwaltung();
        meineArtLogs.liesDaten(datei + "_ArtikelLog.ser");
    }

// Methoden

    /**
     * Methode, die eine Liste aller im Bestand befindlichen Artikel zurückgibt Aufgabe wird an die Artikelverwaltung delegiert
     *
     * @return Vector Liste aller Artikel im Bestand des EShops als Vector
     */
    public Vector gibAlleArtikel() {
        // delegiert an die Artikelverwaltung
        return meineArtikel.alleArtikelZurueckgeben();
    }

    /**
     * Methode, die eine Liste aller im Bestand befindlichen Kunden zurückgibt Aufgabe wird an die Kundenverwaltung delegiert
     *
     * @return Vector Liste aller Kunden im Bestand des EShops als Vector
     */
    public Vector gibAlleKunden() {
        // delegiert an die Kundenverwaltung
        return meineKunden.alleKundenZurueckgeben();
    }

    /**
     * Methode, die eine Liste aller im Bestand befindlichen Rechnungen zurückgibt Aufgabe wird an die Rechnungsverwaltung delegiert
     *
     * @return Vector Liste aller Rechnungen im Bestand des EShops als Vector
     */
    public Vector gibAlleRechnungen() {
        // delegiert an die Rechnungsverwaltung
        return meineRechnungen.alleRechnungenZurueckgeben();
    }

    /**
     * Methode, die eine Liste aller im Bestand befindlichen Mitarbeiter zurückgibt Aufgabe wird an die Mitarbeiterverwaltung delegiert
     *
     * @return Vector Liste aller Mitarbeiter im Bestand des EShops als Vector
     */
    public Vector gibAlleMitarbeiter() {
        // delegiert an die Mitarbeiterverwaltung
        return meineMitarbeiter.alleMitarbeiterZurueckgeben();
    }

    /**
     * Methode, die eine Liste aller im Bestand befindlichen ArtikelLogs zurückgibt Aufgabe wird an die ArtikelLogVerwaltung delegiert
     *
     * @return Vector Liste aller ArtikelLogs im Bestand des EShops als Vector
     */
    public Vector gibAlleArtikelLogs() {
        // delegiert an die ArtikelLogVerwaltung
        return meineArtLogs.alleArtikelLogsZurueckgeben();
    }

    /**
     * Methode zum Suchen von Mitarbeitern anhand des Benutzernamens
     *
     * @param bName Benutzername des gesuchten Mitarbeiters
     * @return Vector Rückgabe eines Vectors mit dem gefundenen Mitarbeiter
     */
    public boolean findeMitarbeiter(String bName, String bPasswort) {
         return meineMitarbeiter.findeMitarbeiter(bName, bPasswort);
    }

    /**
     * Methode zum Suchen von Mitarbeitern anhand des Benutzernamens
     *
     * @param bName Benutzername des gesuchten Mitarbeiters
     * @return Vector Rückgabe eines Vectors mit dem gefundenen Mitarbeiter
     */
    public boolean findeKunden(String bName, String bPasswort) {
        return meineKunden.findeKunden(bName, bPasswort);
    }

    /**
     * Methode zum Suchen von Artikel anhand des Namens
     *
     * @param name Name des gesuchten Artikels
     * @return Vector Rückgabe eines Vectors mit dem gefundenen Artikel
     */
    public Vector sucheNachName(String name) {

        return meineArtikel.sucheArtikel(name);
    }

    /**
     * Methode zum Suchen von Rechnungen anhand des Rechnungsnummer
     *
     * @param rNr Rechnungsnummer der gesuchten Rechnung
     * @return Vector Rückgabe eines Vectors mit der gefundenen Rechnung
     */
    public Rechnung sucheNachRechnungsnummer(int rNr) {

        return meineRechnungen.sucheRechnung(rNr);
    }

    /**
     * Methode zum Suchen von Artikeln anhand der Artikelnummer
     *
     * @param aNr Artikelnummer des gesuchten Artikels
     * @return Vector Rückgabe eines Vectors mit der gefundenen Rechnung
     */
    public Artikel getArtikel(int aNr) {

        return meineArtikel.getArtikel(aNr);
    }


    public boolean inWarenkorbLegen(Artikel a, int kNr){
        return meineKunden.inWarenkorbLegen(a, kNr);
    }

    public boolean ausWarenkorbEntfernen(Artikel a, int kNr){
        return meineKunden.ausWarenkorbEtfernen(a, kNr);
    }

    /**
     * Methode zum zuruecksetzen des Warenkorbes eines Kunden
     *
     * @param kNr Kundennummer
     * @return boolean ob der Warenkorb zurück gestzt wurde oder nicht
     */
    public boolean resetWarenkorb(int kNr){
       return meineKunden.resetWarenkorb(kNr);
    }

    /**
     * Methode zum ändern des Bestands eines Artikels
     *
     * @param artNr,wert    Artikelnummer, Neuer Wert für Bestand des Artikels
     * @return boolean
     */
    public boolean setBestand(int artNr, int wert) {
        return meineArtikel.setBestand(artNr, wert);
    }

    /**
     * Methode zum ändern der Bestellmenge
     *
     * @param menge    Menge die vom Artikel bestellt werden soll
     * @return boolean
     */
    public boolean setBestellteMenge(int menge) {
        return meineArtikel.setBestellteMenge(menge);
    }

    /**
     * Methode zum Einfügen eines neuen Artikels in den Bestand.
     *
     * @param name,beschreibung,preis         Name des Artikels, Beschreibung des Artikels, Preis des Artikels
     *
     * @return boolean wenn Einfügen erfolgreich true, ansonsten false (wenn Artikel schon vorhanden ist)
     */
    public boolean fuegeArtikelEin(String name, String beschreibung, double preis) {
        return meineArtikel.artikelHinzufuegen(name, beschreibung, preis);

    }

    /**
     * Methode zum Einfügen eines neuen Kunden in den Bestand.
     *
     * @param vorname
     * @param nachname
     * @param benutzername
     * @param passwort
     * @param email
     * @param telefon
     *
     * @return boolean wenn Einfügen erfolgreich true, ansonsten false (wenn Artikel schon vorhanden ist)
     */
    public boolean fuegeKundeEin(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) {
        return meineKunden.kundeHinzufuegen(vorname, nachname, benutzername, passwort, email, telefon, adresse);

    }

    /**
     * Methode zum Einfügen eines neuen Mitarbeiters in den Bestand.
     *
     * @param vorname
     * @param nachname
     * @param benutzername
     * @param passwort
     * @param email
     * @param telefon
     *
     * @return boolean wenn Einfügen erfolgreich true, ansonsten false (wenn Mitarbeiter schon vorhanden ist)
     */
    public boolean fuegeMitarbeiterEin(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) {
        return meineMitarbeiter.mitarbeiterHinzufuegen(vorname, nachname, benutzername, passwort, email, telefon, adresse);
    }

    /**
     * Methode zum Einfügen eines neuen Kunden in den Bestand.
     *
     * @param kunde
     *
     * @return boolean wenn Einfügen erfolgreich true, ansonsten false (wenn Artikel schon vorhanden ist)
     */
    public boolean fuegeRechnungEin(Kunde kunde) {
        return meineRechnungen.rechnungHinzufuegen(kunde);

    }

    /**
     * Methode zum loeschen eines  Artikels aus dem  Bestand.
     *
     * @param artNr         Nummer des Artikels des Artikel der geloescht werden soll.
     *
     * @return boolean wenn loeschen erfolgreich true, ansonsten false (wenn der Artikel nicht gelöscht werden konnte)
     */
    public boolean loescheArtikel(int artNr) {
        return meineArtikel.artikelLoeschen(artNr);

    }

    /**
     * Methode zum loeschen eines  Kunden aus dem  Bestand.
     *
     * @param kunNr         Nummer des Kunden der geloescht werden soll.
     *
     * @return boolean wenn loeschen erfolgreich true, ansonsten false (wenn der Kunde nicht gelöscht werden konnte)
     */
    public boolean loescheKunde(int kunNr) {
        return meineKunden.kundenLoeschen(kunNr);

    }

    /**
     * Methode zum loeschen eines Mitarbeiters aus dem  Bestand.
     *
     * @param mNr Nummer des Mitarbeiters der geloescht werden soll.
     *
     * @return boolean wenn loeschen erfolgreich true, ansonsten false (wenn der Mitarbeiter nicht gelöscht werden konnte)
     */
    public boolean loescheMitarbeiter(int mNr) {
        return meineMitarbeiter.mitarbeiterLoeschen(mNr);

    }

    /**
     * Methode zum loeschen einer  Rechnung aus dem  Bestand.
     *
     * @param rechnung         Nummer des Kunden der geloescht werden soll.
     *
     * @return boolean wenn loeschen erfolgreich true, ansonsten false (wenn der Kunde nicht gelöscht werden konnte)
     */
    public boolean loescheRechnung(Rechnung rechnung) {
        return meineRechnungen.rechnungLoeschen(rechnung);

    }

    /**
     * Methode zum Speichern des Artikelbestands in einer Datei.
     *
     * @throws IOException
     */
    public void schreibeArtikel() throws IOException {

        meineArtikel.schreibeDaten(datei + "_Artikel.ser");
    }

    /**
     * Methode zum Speichern des Kundenbestands in einer Datei.
     *
     * @throws IOException
     */
    public void schreibeKunden() throws IOException {

        meineKunden.schreibeDaten(datei + "_Kunden.ser");
    }

    /**
     * Methode zum Speichern des Rechnungsbestanden in einer Datei.
     *
     * @throws IOException
     */
    public void schreibeRechung() throws IOException {

        meineRechnungen.schreibeDaten(datei + "_Rechnungen.ser");
    }

    /**
     * Methode zum Speichern des Mitarbeiterbestands in einer Datei.
     *
     * @throws IOException
     */
    public void schreibeMitarbeiter() throws IOException {

        meineMitarbeiter.schreibeDaten(datei + "_Mitarbeiter.ser");
    }

    /**
     * Methode zum Speichern der ArtikelLogs in einer Datei.
     *
     * @throws IOException
     */
    public void schreibeArtikelLogs() throws IOException {

        meineArtLogs.schreibeDaten(datei + "_ArtikelLog.ser");
    }

    public Kunde getKunde(int kNr) {
        return meineKunden.getKunde(kNr);
    }

    public int getKnr(String bName) {
        return meineKunden.getKnr(bName);
    }

    public int getMnr(String bName) {
        return meineMitarbeiter.getMnr(bName);
    }

    public Mitarbeiter rufeMitarbeiter(String bName) {
        return meineMitarbeiter.getMitarbeiter(bName);
    }

    public Kunde rufeKunden(String bName){
        return meineKunden.getKunden(bName);
    }

}



