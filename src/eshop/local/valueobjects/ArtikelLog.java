package eshop.local.valueobjects;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 11.05.13
 * Time: 13:12
 * To change this template use File | Settings | File Templates.
 */
public class ArtikelLog extends Log{

    //Attribute zur Beschreibung eines ArtikelLog-Objektes

    private int aNr;
    private String name;
    private int bestand;
    private int menge;

    //Konstruktoren

    public ArtikelLog(Date dNow, SimpleDateFormat ft, int aNr, String name, int bestand, int menge){
        super(dNow, ft);
        this.aNr = aNr;
        this.name = name;
        this.bestand = bestand;
        this.menge = menge;
    }

    public ArtikelLog(ArtikelLog al) {
        super(al.getdNow(),al.getFt());
        this.aNr = al.getaNr();
        this.name = al.getName();
        this.bestand = al.getBestand();
        this.menge = al.getMenge();
    }


    //Getter

    public int getaNr(){
        return aNr;
    }

    public String getName(){
        return name;
    }

    public int getBestand(){
        return bestand;
    }

    public int getMenge(){
        return menge;
    }
    // Methoden

    /**
     * Standard-Methode vom Object überschrieben.
     * Methode wird immer automatisch aufgerufen, wenn ein ArtikelLog-Objekt als String
     * benutzt wird (z.B. in println(ArtikelLog);)
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String string = "Zeit und Datum der Veränderung: "+ getFt().format(getdNow())+"\nDer Bestand des Artikels " + getName()  + " mit der Artikelnummer " + getaNr() + " wurde um die Menge " + getMenge() + " verändert.\nDer jetzige Bestand ist " + getBestand()+ ".";
        return string;
    }
}
