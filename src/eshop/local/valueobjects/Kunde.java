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
    int kNr;

    // Konstruktor
    public Kunde (String name, int kNr, Adresse adresse, ) {
        super(name);
        this.kNr = kNr;
    }


    // Methoden der Klasse Kunde


    // Setter


    // Getter
}
