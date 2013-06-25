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

    // SerialVersionUID die es ermöglicht Adressen zu speichern und auszulesen
    private static final long serialVersionUID = -4318609442967552645L;

    // Attribute zur Beschreibung einer Adresse
    private String vorname;
    private String nachname;
    private String straße;
    private String plz;
    private String ort;


// Konstruktor

    /**
     * Methode zum anlegen eines neuen Adressenobjekts für einen Kunden
     *
     * @param vorname -> Der Vorname des Kunden
     * @param nachname -> Der Nachname des Kunden
     * @param straße -> Die Straße in der der Kunde wohnt
     * @param plz -> Die Postleihzahl des Kunden
     * @param ort -> Der Name des Ortes in dem der Kunde wohnt
     */
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
        String string = "\n" +	"Anschrift: " + "\n" + "\t" + getVorname() + "\n" + "\t" + getNachname() + "\n" + "\t" + getStraße() + "\n" + "\t" + getPlz() + "\n" + "\t" + getOrt() + "\n";
        return string;
    }

// Setter

    /**
     * Methode zum ändern des Vornamens
     *
     * @param vorname -> Der neue Vorname
     */
    public void setVorname(String vorname){
        this.vorname = vorname;
    }

    /**
     * Methode zum ändern des Nachnamens
     *
     * @param nachname -> Der neue Nachname
     */
    public void setNachname(String nachname){
        this.nachname = nachname;
    }

    /**
     * Methode zum ändern des Straßennamens
     *
     * @param straße -> Der neue Name der Straße
     */
    public void setStraße(String straße){
        this.straße = straße;
    }

    /**
     * Methode zum ändern der Postleihzahl
     *
     * @param plz -> Die neue Postleihzahl
     */
    public void setPlz(String plz){
        this.plz = plz;
    }

    /**
     * Methode zum ändern des Orts
     *
     * @param ort -> Der neue Ort
     */
    public void setOrt(String ort){
        this.ort = ort;
    }

// Getter

    /**
     * Methode liefert den Vornamen des Kunden zurück
     *
     * @return String -> Der Vorname des Kunden
     */
    public String getVorname() {
        return this.vorname;
    }

    /**
     * Methode liefert den Nachnamen des Kunden zurück
     *
     * @return String -> Der Nachname des Kunden
     */
    public String getNachname(){
        return this.nachname;
    }

    /**
     * Methode liefert den Straßennamen des Kunden zurück
     *
     * @return String -> Der Name der Straße in der der Kunde wohnt
     */
    public String getStraße() {
        return this.straße;
    }

    /**
     * Methode liefert die Postleihzahl des Kunden zurück
     *
     * @return String -> Der Postleihzahl des Kunden
     */
    public String getPlz() {
        return this.plz;
    }

    /**
     * Methode liefert den Ort zurück in dem der Kunde wohnt
     *
     * @return String -> Der Ort
     */
    public String getOrt() {
        return this.ort;
    }
}

