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

    private String name;
    private int kennNr;
    private Date d;
    private int bestand;
    private SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz");

    public ArtikelBestandsGraph(String name, int kennNr, Date d, int bestand) {
        this.name = name;
        this.kennNr = kennNr;
        this.d = d;
        this.bestand = bestand;
    }

    public String getName(){
        return this.name;
    }

    public int getKennNr(){
        return this.kennNr;
    }

    public Date getD(){
        return this.d;
    }

    public int getBestand(){
        return this.bestand;
    }

    public String toString(){
        String string = "Datum und Zeit der letzten Änderung des Tages: " + this.ft.format(this.getD()) + "\nArtikelname: " + this.getName() + "\nArtikelnummer: " + this.getKennNr() + "\nBestand: " + this.getBestand() + "\n";
        return string;
    }

}
