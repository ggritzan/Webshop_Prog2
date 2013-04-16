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
    private String email;
    private String telefon;
    private Adresse adresse;



    // Konstruktor
    public Kunde (String vorname, String nachname, int kNr, String email, Adresse adresse, String telefon) {
        super(vorname, nachname);
        this.kNr = kNr;
        this.email = email;
        this.adresse = adresse;
        this.telefon = telefon;
    }


    // Methoden der Klasse Kunde

    public void inWarenkorb(Artikel a)   {


    }

    // Setter


    // Getter

    public int getkNr() {
        return this.kNr;
    }

    public String getEmail(){
        return this.email;
    }

    public String getTelefon(){
        return this.telefon;
    }

    public Adresse getAdresse(){
        return this.adresse;
    }
}
