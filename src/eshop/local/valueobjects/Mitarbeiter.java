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
    public Mitarbeiter (String vorname, String nachname, int mNr) {
        super(vorname, nachname);
        this.mNr = mNr;
    }

    // Methoden der Klasse Mitarbeiter

    //Erhoeht den Bestand des Artikels um den gewünschten Wert
    public void bestandErhoehen(Artikel a, int wert)   {
        a.setBestand(wert);
    }

    //Aendert den Preis des Artikels auf den neuen Preis
    public void preisAendern(Artikel a, double wert) {
        a.setPreis(wert);
    }

    /* Nicht mehr funktionierende/benötigte Funkion?)
    public Artikel neuerArtikel(String bezeichnung, int nummer, double preis, int bestand) {
        Artikel  x = new Artikel(bezeichnung,nummer,preis,bestand);
        return x;
    }
    */

    // Setter


    // Getter
}
