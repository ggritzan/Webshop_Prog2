/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */

package eshop.local.valueobjects;

import java.io.IOException;
import java.io.Serializable;

public class Artikel implements Serializable {

    // serialVersionUID um ein Artikel Objekt auch nach einer Änderung des Artikel Objekts wieder einlesen zu können
    private static final long serialVersionUID = 2109392536657810497L;

    // Attribute zur Beschreibung eines Artikels
    private String name;
    private String beschreibung;
    private static int zaehler = 1000;
    private int nummer;
    private double preis;
    private int bestand = 0;
    private int bestellteMenge = 0;

    /**
    * Methode zum erstellen einees neuen Artikelobjekts
    * Wenn ein Artikel angelegt wird, wird auch automatisch der zaehler gesetzt, wodurch die Artikelnummern
    * fortlaufen können
    * @param name -> Der Name des neuen Artikels
    * @param beschreibung -> Eine beliebig detailierte Beschreibung des anzulegenden Artikels
    * @param preis -> Der Preis des Artikels
    * @throws IOException
    */
    public Artikel(String name, String beschreibung, double preis ) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.preis = preis;
        this.nummer = this.zaehler;
        this.zaehler  ++;

    }

// Methoden

    /**
    * Standard-Methode vom Object überschrieben.
    * Methode wird immer automatisch aufgerufen, wenn ein Artikel-Objekt als String
    * benutzt wird (z.B. in println(Artikel);)
    *
    * @see java.lang.Object#toString()
    */
    public String toString() {
        String string = "\tNummer: " + getNummer()  + "\tName:  " + getName() + "\t\tBeschreibung: " + getBeschreibung() + "\t\tPreis: " + getPreis() + "\t Bestand: " + getBestand() + "\n" + "\t BestellteMenge: " + getBestellteMenge() + "\n\n" ;
        return string;
    }

// Setter

    /**
    * Methode zum ändern des Artikelnames
    *
    * @param name -> neuer Name den der Artikel annehmen soll
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
    * Methode zum ändern der Artikelbeschreibung
    *
    * @param beschreibung -> neuer Beschreibung des Artikels
    */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
    * Methode zum ändern des Preises eines Artikels
    *
    * @param preis -> der neue Preis des Artikels
    */
    public void setPreis(double preis) {
        this.preis = preis;
    }

    /**
    * Methode zum ändern des Bestands eines Artikels
    *
    * @param bestand -> der neue Bestand des Artikels
    */
    public void setBestand(int bestand) {
        this.bestand = bestand;
    }

    /**
    * Methode um angeben zu können in welcher Stückzahl ein Artikel bestellt wurde
    *
    * @param menge -> Anzahl der bestellten Artikel
    */
    public void setBestellteMenge(int menge){
        this.bestellteMenge = menge;
    }

    /**
    * Methode zum ändern der Atikelnummer
    * Wird benötigt damit die Kopien der echten Artikeln in den Warenkörben keine fortlaufenden Artikelnummern,
    * sondern dieselbe Nummer wie ihr Original erhalten
    * @param nummer -> die neue Artikelnummer
    */
    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    /**
    * Methode zum ändern des Artikelzaehler
    *
    * @param zaehler -> Stand es neuen Zaehlers
    */
    public void setZaehler(int zaehler){
        this.zaehler = zaehler;
    }

// Getter

    /**
    * Methode liefert den Namen des Artikels zurück
    *
    * @return String -> Der Artikelname
    */
    public String getName() {
        return this.name;
    }

    /**
    * Methode liefert die Beschreibung des Artikels zurück
    *
    * @return String -> Die Artikelbeschreibung
    */
    public String getBeschreibung() {
        return this.beschreibung;
    }

    /**
    * Methode liefert die Nummer des Artikels zurück
    *
    * @return int -> Die Nummer des Artikels
    */
    public int getNummer() {
        return this.nummer;
    }

    /**
    * Methode liefert den Preis des Artikels zurück
    *
    * @return double -> Den Preis des Artikels
    */
    public double getPreis() {
        return this.preis;
    }

    /**
    * Methode liefert den Bestand des Artikels zurück
    *
    * @return int -> Der Bestand des Artikels
    */
    public int getBestand() {
        return this.bestand;
    }

    /**
    * Methode liefert die bestellte Menge des Artikels zurück
    *
    * @return int -> Die Menge der bestellten Exemplare des Artikels
    */
    public int getBestellteMenge() {
        return this.bestellteMenge;
    }

    /**
    * Methode liefert den Zaehler
    *
    * @return int -> Der Zaehler des Artikels
    */
    public int getZaehler() {
        return this.zaehler;
    }

}

