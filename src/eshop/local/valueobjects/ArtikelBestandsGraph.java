package eshop.local.valueobjects;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 05.06.13
 * Time: 09:20
 * To change this template use File | Settings | File Templates.
 */
public class ArtikelBestandsGraph {

    //Eigenschaften zur Beschreibung eines Artikelbestandsgraphen
    private String name;
    private int kennNr;
    private Date d;
    private int bestand;
    private SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz");

    //Konstruktor

    /**
     * Konstruktor um einen neuen ArtikelBestandsgraphen anzulegen
     *
     * @param name
     * @param kennNr
     * @param d
     * @param bestand
     */
    public ArtikelBestandsGraph(String name, int kennNr, Date d, int bestand) {
        this.name = name;
        this.kennNr = kennNr;
        this.d = d;
        this.bestand = bestand;
    }

    // Getter

    /**
     * Getter für den Namen
     *
     * @return String
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter für die Kennnummer
     *
     * @return int
     */
    public int getKennNr(){
        return this.kennNr;
    }

    /**
     * Getter für das Datum
     *
     * @return Date
     */
    public Date getD(){
        return this.d;
    }

    /**
     * Getter für den Bestand des Artikels
     *
     * @return int
     */
    public int getBestand(){
        return this.bestand;
    }

    /**
     * Standard-Methode vom Object überschrieben.
     * Methode wird immer automatisch aufgerufen, wenn ein Artikel-Objekt als String
     * benutzt wird (z.B. in println(Artikel);)
     *
     * @see java.lang.Object#toString()
     */
    public String toString(){
        String string = "Datum und Zeit der letzten Änderung des Tages: " + this.ft.format(this.getD()) + "\nArtikelname: " + this.getName() + "\nArtikelnummer: " + this.getKennNr() + "\nBestand: " + this.getBestand() + "\n";
        return string;
    }

}
