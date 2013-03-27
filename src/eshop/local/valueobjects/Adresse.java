/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 27.03.13
 * Time: 07:07
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

public class Adresse {

    // Attribute zur Beschreibung einer Adresse
    int kNr;
    String name;
    String straße;
    String plz;
    String ort;
    String telefon;
    String eMail;

    // Konstruktor
    public Adresse (int kNr, String straße, String plz, String ort, String telefon, String eMail) {
        this.kNr = kNr;
        this.straße = straße;
        this.plz = plz;
        this.ort = ort;
        this.telefon = telefon;
        this.eMail = eMail;
    }


    // Methoden der Klasse Adresse
    public adresseAnlegen() {


    }


    // Setter


    // Getter
}
