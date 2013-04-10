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
    private String bezeichnung;
    private static int nummer = 1000;
    private double preis;
    private int bestand = 0;

    // Konstruktor
    public Artikel(String bezeichnung, double preis ) {
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.nummer  ++;
        this.bestand = 0;
     }


    // Methoden



    // Setter

        public void neueBezeichnung(String bezeichnung) {
            this.bezeichnung = bezeichnung;
        }

        public void neuerPreis(double preis) {
            this.preis = preis;
        }


        public void setBestand(int wert) {
            this.bestand = wert;
        }



    // Getter

        // gibt die Artikelnummer zurück
        public int getNummer() {
            return nummer;
        }

        // gibt die Bezeichnung des Artikels zurück
        public String getBezeichnung() {
            return bezeichnung;
        }

         // gibt den Preis des Artikels zurück
        public double getPreis() {
            return preis;
        }


        public int getBestand() {
            return bestand;
        }


}

