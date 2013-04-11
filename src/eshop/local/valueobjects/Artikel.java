/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */

package eshop.local.valueobjects;


public class Artikel {

    // Attribute zur Beschreibung eines Artikels
    private String name;
    private String beschreibung;
    private static int nummer = 1000;
    private double preis;
    private int bestand = 0;

    // Konstruktor
    public Artikel(String name, String beschreibung, double preis ) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.preis = preis;
        this.nummer  ++;
        this.bestand = 0;
     }


    // Methoden



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

         // gibt den Preis des Artikels zurück
        public double getPreis() {
            return this.preis;
        }

        // gibt die Artikelnummer zurück
        public int getNummer() {
            return this.nummer;
        }

        // gibt den Bestand des Artikels zurück
        public int getBestand() {
            return this.bestand;
        }


}

