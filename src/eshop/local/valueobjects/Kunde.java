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

    /**
     * Methode zum anlegen eines neuen Kundenobjekts
     *
     * @param vorname -> Der Vorname des Kunden
     * @param nachname -> Der Nachname des Kunden
     * @param benutzername -> Der Benutzername des Kunden
     * @param passwort -> Das Passwort des Kundenaccounts
     * @param email -> Die email-Adresse des Kunden
     * @param telefon -> Die Telefonnummer des Kunden
     * @param adresse -> Die komplette Adresse des Kunden
     */
    public Kunde(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) {
        super(vorname, nachname, benutzername, passwort, email, telefon, adresse);
        this.nummer = this.zaehler;
        this.zaehler++;
        this.wk = new HashMap<Integer, Artikel>();

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
        String string = "Kundennummer: " + getNummer() +  "\tVorname: " + getVorname()  + "\tNachname:  " + getNachname() + "\t\tBenutzername: " + getBenutzername() + "\t\tPasswort: " + getPasswort() + "\t E-mail: " +getEmail() + "\t Telefon: " +getTelefon() + "\n" + getAdresse() + "\n" + getWarenkorb() ;
        return string;
    }

    /**
     * Methode zum einfügen von Artikeln in den Warenkorb des Kunden
     *
     * @param a -> Der Artikel, der in den Warenkorb gelegt werden soll
     */
    public void inWarenkorbLegen(Artikel a) {
        // fügt die bestellte Menge ein und packt das Artikelobjekt in den Warenkorb
        wk.put(a.getNummer(),a);
    }

    /**
     * Methode zum entfernen eines Artikels aus dem Warenkorb
     *
     * @param a -> Der Artikel, der aus dem Warenkorb entfernt werden soll
     *
     * @return boolean -> Wenn der Artikel erfolgreich enfernt wurde true, ansonsten false
     */
    public boolean ausWarenkorbEntfernen(Artikel a) {
        wk.remove(a.getNummer());
        return true;
    }

    /**
     * Methode löschen des gesamten Warenkorbs
     *
     * @return boolean -> true, wenn der Warenkorb erfolgreich geleert wurde, ansonsten false
     */
    public boolean resetWarenkorb() {
        this.wk.clear();
        return true;
    }

    /**
     * Methode stellt fest ob ein Artikel bereits im Warenkorb ist
     *
     * @param aNr -> Artikelnummer des zu überprüfenden Artikels
     *
     * @return boolean -> true, wenn der Artikel bereits im Warenkorb ist, ansonten false
     */
    public boolean istImWarenkorb(int aNr) {
        if (this.wk.containsKey(aNr)&& this.wk.size() > 0){
            return true;
        } else {
            return false;
        }
    }

// Setter

    /**
     * Setter für den Zaehler
     *
     * @param zaehler -> Wert des neuen Zaehlers
     */
    public static void setZaehler(int zaehler) {
        Kunde.zaehler = zaehler;
    }

    /**
     * Methode zum ändern der Kundennummer
     *
     * @param nummer -> Die neue Kundennummer
     */
    public void setkNummer(int nummer) {
        this.nummer = nummer;
    }

// Getter

    /**
     * Getter für den Zaehler
     *
     * @return int -> Der aktuelle Zahler des Kunden
     */
    public static int getZaehler() {
        return zaehler;
    }

    /**
     * Methode liefert die Kundennummer zurück
     *
     * @return int -> Die Kundennummer
     */
    public int getNummer() {
        return this.nummer;
    }

    /**
     * Methode liefert den gesamten Warenkorb zurück
     *
     * @return HashMap<Integer, Artikel> -> Der Warenkorb
     */
    public HashMap<Integer, Artikel> getWarenkorb() {
        return this.wk;
    }

}


