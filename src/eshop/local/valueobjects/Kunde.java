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

    // Konstruktor
    public Kunde (String vorname, String nachname, int kNr) {
        super(vorname, nachname);
        this.kNr = kNr;
    }


    // Methoden der Klasse Kunde


    // Setter


    // Getter

    public int getkNr() {
        return kNr;
    }
}
