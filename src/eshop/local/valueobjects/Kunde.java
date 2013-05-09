/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

import com.sun.xml.internal.bind.v2.TODO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class Kunde extends Person implements Serializable {

    // serialVersionUID um ein Kunden Objekt auch nach einer Änderung des Kunden Objekts wieder einlesen zu können
    private static final long serialVersionUID = 360633788222335463L;


    // Attribute zur Beschreibung eines Kunden
    private int nummer;
    private HashMap<Integer, Artikel> wk;
    private static int zaehler = 1000;

// Konstruktor

    // Konstruktor für neue Kunden
    public Kunde(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) {
        super(vorname, nachname, benutzername, passwort, email, telefon, adresse);
        this.nummer = this.zaehler;
        this.zaehler++;
        this.wk = new HashMap<Integer, Artikel>();

    }



    // Konstruktor für das einlesen von Kunden
    public Kunde(Kunde kunde) {
        super(kunde.getVorname(), kunde.getNachname(), kunde.getBenutzername(), kunde.getPasswort(), kunde.getEmail(), kunde.getTelefon(), kunde.getAdresse());
        this.nummer = kunde.getNummer();
        this.wk = kunde.getWarenkorb();
        this.zaehler = (kunde.getNummer() +1);
        this.wk = kunde.getWarenkorb();
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
        string = 	"Kundennummer: " + getNummer() +  "\tVorname: " + getVorname()  + "\tNachname:  " + getNachname() + "\t\tBenutzername: " + getBenutzername() + "\t\tPasswort: " + getPasswort() + "\t E-mail: " +getEmail() + "\t E-mail: " +getTelefon() + "\n" + getAdresse() + "\n" + getWarenkorb() ;
        return string;
    }

    //fügt einen Artikel dem Warenkorb hinzu
    public boolean inWarenkorbLegen(Artikel a) {
        // fügt die bestellte Menge ein und packt das Artikelobjekt in den Warenkorb


        wk.put(a.getNummer(),a);

        return true;
    }

    //Entfernt einen Artikel aus dem Warenkorb
    public boolean ausWarenkorbEntfernen(Artikel a) {
        wk.remove(a.getNummer());
        return true;
    }

// Setter

    public void setkNummer(int nummer) {
        this.nummer = nummer;
    }

    // leert den Warenkorb eines Kunden
    public boolean resetWarenkorb() {
        this.wk.clear();
        return true;
    }

// Getter

    // Gibt die Kundennummer zurueck
    public int getNummer() {
        return this.nummer;
    }

    // Gibt den Warenkorb des Kunden zurueck
    public HashMap<Integer, Artikel> getWarenkorb() {
        return this.wk;
    }



}


