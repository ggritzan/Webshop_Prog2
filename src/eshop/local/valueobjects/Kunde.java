/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

public class Kunde extends Person {

    // Attribute zur Beschreibung eines Kunden
    private int kNr;
    private String email;
    private String passwort;
    private String email;
    private String telefon;
    private Adresse adresse;



    // Konstruktor
    public Kunde (String vorname, String nachname, int kNr) {
        super(vorname, nachname);
        this.kNr = kNr;
    }


    // Methoden der Klasse Kunde

    public void kaufen(Artikel a, int wert)   {

    }

    // Setter


    // Getter

    public int getkNr() {
        return kNr;
    }

    public String getEmail(){
        return email;
    }

    public String getTelefon(){
        return this.telefon;
    }
}
