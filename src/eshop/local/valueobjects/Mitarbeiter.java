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

    private static final long serialVersionUID = 1169397538857810497L;

    // Attribute zur Beschreibung eines Mitarbeiters
    private int mNr;
    private static int zaehler = 1000;

    // Konstruktor
    public Mitarbeiter(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) {
        super(vorname, nachname, benutzername, passwort, email, telefon, adresse);
        this.mNr = this.zaehler;
        this.zaehler++;
    }


    //Methoden

    public String toString() {
        String string = "Mitarbeiternummer: " + getmNr() + "\tVorname: " + getVorname() + "\tNachname:  " + getNachname() + "\t\tBenutzername: " + getBenutzername() + "\t\tPasswort: " + getPasswort() + "\t E-mail: " + getEmail() + "\t E-mail: " + getTelefon() + "\n" + getAdresse();
        return string;
    }

    //Setzt den Bestand des Artikels auf den gewünschten Wert
    public void bestandErhoehen(Artikel a, int wert) {
        a.setBestand(wert);
    }

    //Aendert den Preis des Artikels auf den neuen Preis
    public void preisAendern(Artikel a, double wert) {
        a.setPreis(wert);
    }

    // Methoden der Klasse Mitarbeiter

    /* Nicht mehr funktionierende/benötigte Funkion?)
    public Artikel neuerArtikel(String bezeichnung, int nummer, double preis, int bestand) {
        Artikel  x = new Artikel(bezeichnung,nummer,preis,bestand);
        return x;
    }
    */

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

