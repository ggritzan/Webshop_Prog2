package eshop.local.valueobjects;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 29.05.13
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */

public class MassengutArtikel extends Artikel{

    // Attribute zur Beschreibung eines MassengutArtikels
    private int packungsgroesse;

    // Konstruktor für neue MassengutArtikel
    public MassengutArtikel(String name, String beschreibung, double preis, int packungsgroesse ) {

         super(name, beschreibung, preis);
         this.packungsgroesse = packungsgroesse;

    }

    // Setter & Getter
    public int getPackungsgroesse() {
        return packungsgroesse;
    }

    public void setPackungsgroesse(int packungsgroesse) {
        this.packungsgroesse = packungsgroesse;
    }
}
