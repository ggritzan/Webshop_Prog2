/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

import java.io.Serializable;

public class Mitarbeiter extends Person implements Serializable {

    // SerialVersionUID zum speichern und einlesen
    private static final long serialVersionUID = 1169397538857810497L;

    // Attribute zur Beschreibung eines Mitarbeiters
    private int mNr;
    private static int zaehler = 1000;

    // Konstruktor

    /**
     * Methode zum anlegen eines neuen Personenobjekts
     *
     * @param vorname -> Voname der Person
     * @param nachname -> Nachname der Person
     * @param benutzername -> Benutzername der Person
     * @param passwort -> Passwort der Person
     * @param email -> Emailadresse der Person
     * @param telefon -> Telefonnummer der Person
     * @param adresse -> Adresse der Person
     */
    public Mitarbeiter(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) {
        super(vorname, nachname, benutzername, passwort, email, telefon, adresse);
        this.mNr = this.zaehler;
        this.zaehler++;
    }

    //Methoden

    public String toString() {
        String string = "Mitarbeiternummer: " + getmNr() + "\tVorname: " + getVorname() + "\tNachname:  " + getNachname() + "\t\tBenutzername: " + getBenutzername() + "\t\tPasswort: " + getPasswort() + "\t E-mail: " + getEmail() + "\t E-mail: " + getTelefon() + "\n" + getAdresse()+"\n\n";
        return string;
    }

    //Methoden

    /**
     * Setter um den Bestand um einen Beitrag zu erhöhen
     *
     * @param a -> Der Artikel dessen Bestand erhöht werden soll
     * @param wert -> Der Wert um den der Bestand erhöht werden soll
     */
    public void bestandErhoehen(Artikel a, int wert) {
        a.setBestand(wert);
    }

    /**
     * Setter um den Preis eines Artikels zu ändern
     *
     * @param a -> Der Artikel dessen Preis geändert werden soll
     * @param wert -> Der neue Preis des Artikels
     */
    public void preisAendern(Artikel a, double wert) {
        a.setPreis(wert);
    }

    // Setter

    /**
     * Setter für den Zaehler
     * @param zaehler
     */
    public static void setZaehler(int zaehler) {
        Mitarbeiter.zaehler = zaehler;
    }

    /**
     * Setter für MitarbeiterNr
     * @param mNr
     */
    public void setmNr(int mNr) {
        this.mNr = mNr;
    }

    // Getter

    /**
     * Getter für den Zaehler
     * @return
     */
    public static int getZaehler() {
        return zaehler;
    }

    /**
     * Getter für die MitarbeiterNr
     * @return
     */
    public int getmNr() {
        return this.mNr;
    }
}

