/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

public class Mitarbeiter extends Person {

    // Attribute zur Beschreibung eines Mitarbeiters
    int mNr;

    // Konstruktor
    public Mitarbeiter (String name, int mNr) {
        super(name);
        this.mNr = mNr;
    }

    // Methoden der Klasse Mitarbeiter

    public void bestandErhoehen(Artikel a, int wert)   {
        a.bestandErhoehen(wert);
    }

    public void preisAendern(Artikel a, double wert) {
        a.neuerPreis(wert);
    }

    public Artikel neuerArtikel(String bezeichnung, int nummer, double preis, int bestand) {
        Artikel  x = new Artikel(bezeichnung,nummer,preis,bestand);
        return x;
    }
    // Setter


    // Getter
}
