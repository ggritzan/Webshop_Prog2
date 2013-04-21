/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 27.03.13
 * Time: 07:07
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

import java.io.Serializable;

public class Adresse implements Serializable {

    // Attribute zur Beschreibung einer Adresse
    private String vorname;
    private String nachname;
    private String straße;
    private String plz;
    private String ort;

    // @TODO eventuell müsste noch eine Hausnummer implementiert werden

// Konstruktor
    public Adresse(String vorname, String nachname, String straße, String plz, String ort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.straße = straße;
        this.plz = plz;
        this.ort = ort;
    }


// Methoden

    /**
     * Standard-Methode vom Object überschrieben.
     * Methode wird immer automatisch aufgerufen, wenn ein Kunden-Objekt als String
     * benutzt wird (z.B. in println(Kunde);)
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String string = new String();
        string = "\n" +	"Anschrift: " + "\n" + "\t" + getVorname() + "\n" + "\t" + getNachname() + "\n" + "\t" + getStraße() + "\n" + "\t" + getPlz() + "\n" + "\t" + getOrt() + "\n";
        return string;
    }

// Setter

    public void setVorname(String vorname){
        this.vorname = vorname;
    }

    public void setNachname(String nachname){
        this.nachname = nachname;
    }

    public void setStraße(String straße){
        this.straße = straße;
    }

    public void setPlz(String plz){
        this.plz = plz;
    }

    public void setOrt(String ort){
        this.ort = ort;
    }

// Getter

    public String getVorname() {
        return this.vorname;
    }

    public String getNachname(){
        return this.nachname;
    }

    public String getStraße() {
        return this.straße;
    }

    public String getPlz() {
        return this.plz;
    }

    public String getOrt() {
        return this.ort;
    }
}
