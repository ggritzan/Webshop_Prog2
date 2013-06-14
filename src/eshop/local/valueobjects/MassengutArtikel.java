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

    // SerialVersionUID die es möglich macht einen MassengutArtikel zu speichern und wieder einzulesen
    private static final long serialVersionUID = 2109398534357810497L;

    // Attribute zur Beschreibung eines MassengutArtikels
    private int packungsgroesse;

    /**
     * Methode zum anlegen eines neuen MassengutArtikel Objekts
     *
     * @param name -> Der Name des Massengutartikels
     * @param beschreibung -> Die Beschreibung des Massengutartikels
     * @param preis -> Der Preis des Massengutartikels pro Stück
     * @param packungsgroesse -> Die Packungsgroesse des MassengutArtikels
     */
    public MassengutArtikel(String name, String beschreibung, double preis, int packungsgroesse ) {
        super(name, beschreibung, preis);
        this.packungsgroesse = packungsgroesse;
    }

    /**
     * Standard-Methode vom Object überschrieben.
     * Methode wird immer automatisch aufgerufen, wenn ein Kunden-Objekt als String
     * benutzt wird (z.B. in println(Kunde);)
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String string = "\tNummer: " + getNummer()  + "\tName:  " + getName() + "\t\tBeschreibung: " + getBeschreibung() + "\t\tPreis: " + getPreis() +"\t\tPackungsgroesse : " + getPackungsgroesse() + "\t Bestand: " + getBestand() + "\n" + "\t BestellteMenge: " + getBestellteMenge() + "\n" ;
        return string;
    }

    // Setter

    /**
     * Methode ändert die Packungsgroesse eines Massengutartikels
     *
     * @param packungsgroesse -> Die neue Packunsggroesse
     */
    public void setPackungsgroesse(int packungsgroesse) {
        this.packungsgroesse = packungsgroesse;
    }

    // Getter

    /**
     * Methode liefert die Packungsgroesse eines MassengutArtikels zurück
     *
     * @return int -> Die Packungsgroesse
     */
    public int getPackungsgroesse() {
        return packungsgroesse;
    }

}
