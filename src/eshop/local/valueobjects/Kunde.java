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
    private Warenkorb wk;

    // Konstruktor
    public Kunde (String vorname, String nachname, String benutzername, String passwort, int kNr, String email, String telefon, Adresse adresse, Warenkorb wk) {
        super(vorname, nachname, benutzername, passwort, email, telefon, adresse);
        this.kNr = kNr;
        this.wk = wk;
    }


    // Methoden der Klasse Kunde

    //Fügt einen Artikel dem Warenkorb hinzu
    public void inWarenkorbLegen(Artikel a){
        wk.addArtikel(a);
    }

    //Entfernt einen Artikel aus dem Warenkorb
    public void ausWarenkorbEntfernen(Artikel a){
        wk.remArtikel(a);
    }

    // Setter

    public void setkNr(int kNr){
        this.kNr = kNr;
    }

    // Getter

    public int getkNr() {
        return this.kNr;
    }

}
