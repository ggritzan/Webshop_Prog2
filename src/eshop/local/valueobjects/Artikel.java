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


public class Artikel implements Serializable, Comparable<Artikel> {

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

// Konstruktor

        // Konstruktor für neue Artikel
        public Artikel(String name, String beschreibung, double preis ) {
            this.name = name;
            this.beschreibung = beschreibung;
            this.preis = preis;
            this.nummer = this.zaehler;
            this.zaehler  ++;

         }

        // Konstruktor für das einlesen von Artikeln
        public Artikel(Artikel artikel) {
            this.name = artikel.getName();
            this.beschreibung = artikel.getBeschreibung();
            this.preis = artikel.getPreis();
            this.nummer = artikel.getNummer();
            this.bestand = artikel.getBestand();
            this.bestellteMenge = artikel.getBestellteMenge();
            if(artikel.getNummer() >= zaehler){
            this.zaehler = (artikel.getNummer() +1);
            }

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
            String string = new String();
            string = "\tNummer: " + getNummer()  + "\tName:  " + getName() + "\t\tBeschreibung: " + getBeschreibung() + "\t\tPreis: " + getPreis() + "\t Bestand: " + getBestand() + "\n" + "\t BestellteMenge: " + getBestellteMenge() + "\n" ;
            return string;
        }


        public int compareTo(Artikel a){
          //ordnet nach Name
            int result = name.compareTo(a.name);
            if(0==result){
                result = nummer - a.nummer;
            }
            return result;
        }



// Setter

        public void setName(String name) {
            this.name = name;
        }

        public void setBeschreibung(String beschreibung) {
            this.beschreibung = beschreibung;
        }

        public void setPreis(double preis) {
            this.preis = preis;
        }


        public void setBestand(int bestand) {
            this.bestand = bestand;
        }

        public void setBestellteMenge(int menge){
            this.bestellteMenge = menge;
        }


// Getter

        // gibt den Name des Artikels zurueck
        public String getName() {
            return this.name;
        }

        // gibt die Beschreibung des Artikels zurueck
        public String getBeschreibung() {
            return this.beschreibung;
        }

        // gibt die Artikelnummer zurueck
        public int getNummer() {
            return this.nummer;
        }

        // gibt den Preis des Artikels zurueck
        public double getPreis() {
            return this.preis;
        }


        // gibt den Bestand des Artikels zurueck
        public int getBestand() {
            return this.bestand;
        }

        // gibt die bestellte Menge eines Artikels zurück
        public int getBestellteMenge() {
            return this.bestellteMenge;
        }



}

