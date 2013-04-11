/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */

package eshop.local.valueobjects;

import java.io.IOException;

public class Artikel {

    // Attribute zur Beschreibung eines Artikels
    private String name;
    private String beschreibung;
    private static int zaehler = 1000;
    private int nummer;
    private double preis;
    private int bestand = 0;

    // Konstruktor
        // Konstruktor für neue Artikel
        public Artikel(String name, String beschreibung, double preis ) {
            this.name = name;
            this.beschreibung = beschreibung;
            this.preis = preis;
            this.nummer = this.zaehler;
            this.zaehler  ++;
            this.bestand = 0;
         }

        // Konstruktor für das Artikeleinlesen
        public Artikel(String name, String beschreibung, int nummer, double preis, int bestand ) {
            this.name = name;
            this.beschreibung = beschreibung;
            this.preis = preis;
            this.nummer = nummer;
            this.zaehler ++;
            this.bestand = 0;
        }

    // Methoden





        // überschreibt die Standart to String Methode
        public String toString() {
            String string = new String();
            string = "\tNummer: " + getNummer()  + "\tName:  " + getName() + "\t\tBeschreibung: " + getBeschreibung() + "\t\tPreis: " + getPreis() + "\t Bestand: " + getBestand() + "\n" ;
            return string;
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


        public void setBestand(int wert) {
            this.bestand = wert;
        }



    // Getter

        // gibt den Name des Artikels zurück
        public String getName() {
            return this.name;
        }

        // gibt die Beschreibung des Artikels zurück
        public String getBeschreibung() {
            return this.beschreibung;
        }

        // gibt die Artikelnummer zurück
        public int getNummer() {
            return this.nummer;
        }

        // gibt den Preis des Artikels zurück
        public double getPreis() {
            return this.preis;
        }


        // gibt den Bestand des Artikels zurück
        public int getBestand() {
            return this.bestand;
        }



}

