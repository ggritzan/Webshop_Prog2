/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

import java.util.Vector;

public class Kunde extends Person {

    // Attribute zur Beschreibung eines Kunden
    private int nummer;
    private Warenkorb wk;
    private static int zaehler = 1000;

// Konstruktor
    public Kunde(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse, Warenkorb wk) {
        super(vorname, nachname, benutzername, passwort, email, telefon, adresse);
        this.nummer = this.zaehler;
        this.zaehler++;
        this.wk = wk;
    }

    public Kunde(String vorname, String nachname, String benutzername, String passwort, String email, String telefon) {
        super(vorname, nachname, benutzername, passwort, email, telefon);
        this.nummer = this.zaehler;
        this.zaehler++;

    }

    public Kunde(Kunde kunde) {
        super(kunde.getVorname(), kunde.getNachname(), kunde.getBenutzername(), kunde.getPasswort(), kunde.getPasswort(), kunde.getTelefon());
    }


// Methoden

    //fügt einen Artikel dem Warenkorb hinzu
    public void inWarenkorbLegen(Artikel a) {
        wk.addArtikel(a);
    }

    //Entfernt einen Artikel aus dem Warenkorb
    public void ausWarenkorbEntfernen(Artikel a) {
        wk.remArtikel(a);
    }

// Setter

    public void setkNummer(int nummer) {
        this.nummer = nummer;
    }

// Getter

    public int getNummer() {
        return this.nummer;
    }

    public Warenkorb getWarenkorb() {
        return this.wk;
    }

}
