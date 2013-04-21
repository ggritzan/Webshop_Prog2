/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

import java.io.Serializable;
import java.util.Vector;

public class Kunde extends Person implements Serializable {
    // serialVersionUID um ein Kunden Objekt auch nach einer Änderung des Kunden Objekts wieder einlesen zu können
    private static final long serialVersionUID = 360633788222335463L;




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
        super(kunde.getVorname(), kunde.getNachname(), kunde.getBenutzername(), kunde.getPasswort(), kunde.getEmail(), kunde.getTelefon());
        this.nummer = kunde.getNummer();
        this.zaehler = (kunde.getNummer() +1);

    }


// Methoden

    /**
     * Standard-Methode vom Object überschrieben.
     * Methode wird immer automatisch aufgerufen, wenn ein Kunden-Objekt als String
     * benutzt wird (z.B. in println(Kunde);)
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String string = new String();
        string = 	"Kundennummer: " + getNummer() +  "\tVorname: " + getVorname()  + "\tNachname:  " + getNachname() + "\t\tBenutzername: " + getBenutzername() + "\t\tPasswort: " + getPasswort() + "\t E-mail: " +getEmail() + "\t E-mail: " +getTelefon() + "\n" ;
        return string;
    }

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
