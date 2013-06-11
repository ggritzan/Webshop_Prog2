package eshop.local.valueobjects;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 29.05.13
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */

public class MassengutArtikel extends Artikel implements Serializable {

    private static final long serialVersionUID = 2109398534357810497L;
    // Attribute zur Beschreibung eines MassengutArtikels

    private int packungsgroesse;

    // Konstruktor für neue MassengutArtikel
    public MassengutArtikel(String name, String beschreibung, double preis, int packungsgroesse ) {

         super(name, beschreibung, preis);
         this.packungsgroesse = packungsgroesse;

    }
    /*
    public MassengutArtikel (MassengutArtikel artikel) {
        super(artikel);
        this.packungsgroesse = artikel.getPackungsgroesse();
    }
    */
    public String toString() {
        String string = "\tNummer: " + getNummer()  + "\tName:  " + getName() + "\t\tBeschreibung: " + getBeschreibung() + "\t\tPreis: " + getPreis() +"\t\tPackungsgroesse : " + getPackungsgroesse() + "\t Bestand: " + getBestand() + "\n" + "\t BestellteMenge: " + getBestellteMenge() + "\n" ;
        return string;
    }

    // Setter & Getter
    public int getPackungsgroesse() {
        return packungsgroesse;
    }

    public void setPackungsgroesse(int packungsgroesse) {
        this.packungsgroesse = packungsgroesse;
    }
}
