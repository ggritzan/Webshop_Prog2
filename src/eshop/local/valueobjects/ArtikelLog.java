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

    //Konstruktoren

    public ArtikelLog(Date dNow, SimpleDateFormat ft, int aNr, String name, int bestand){
        super(dNow, ft);
        this.aNr = aNr;
        this.name = name;
        this.bestand = bestand;
    }

    public ArtikelLog(ArtikelLog al) {
        super(al.getdNow(),al.getFt());
        this.aNr = al.getaNr();
        this.name = al.getName();
        this.bestand = al.getBestand();
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
}
