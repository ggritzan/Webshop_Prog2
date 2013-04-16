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
    private int kNr;
    private String passwort;



    // Konstruktor
    public Kunde (String vorname, String nachname, int kNr, String email, Adresse adresse, String telefon, String passwort) {
        super(vorname, nachname, email, telefon, adresse);
        this.kNr = kNr;
        this.passwort = passwort;
    }


    // Methoden der Klasse Kunde


    // Setter

    public void setkNr(int kNr){
        this.kNr = kNr;
    }

    public void setPasswort(String passwort){
        this.passwort = passwort;
    }

    // Getter

    public int getkNr() {
        return this.kNr;
    }

    public String getPasswort(){
        return this.passwort;
    }
}
