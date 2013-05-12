package eshop.local.valueobjects;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 22.04.13
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
public class Rechnung implements Serializable {

    // serialVersionUID um ein Rechnungs Objekt auch nach einer Änderung des Rechnungs Objekts wieder einlesen zu können
    private static final long serialVersionUID = 1858183826686679982L;

    //Attribute zur Beschreibung eines Rechnungsobjektes

    private int rNr;
    private int kNr;
    private Vector<Artikel> bestellteArtikel;
    private static int zaehler = 1000;
    private static final SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss a zzz");
    private Date dNow;

    //Konstruktor für neue Rechnungen

    public Rechnung(int kNr, Vector<Artikel> bestellteArtikel) {
        this.kNr = kNr;
        this.bestellteArtikel = bestellteArtikel;
        this.rNr = this.zaehler;
        this.zaehler++;
    }

    // Konstruktor für das einlesen von Rechnungen
    public Rechnung(Rechnung r) {
        this.kNr = r.getkNr();
        this.rNr = r.getrNr();
        this.bestellteArtikel = r.getBestellteArtikel();
        if(r.getrNr() >= zaehler){
            this.zaehler = (r.getrNr() +1);
        }

    }

    //Methoden

    /**
     * Standard-Methode vom Object überschrieben.
     * Methode wird immer automatisch aufgerufen, wenn ein Rechnungs-Objekt als String
     * benutzt wird (z.B. in println(Rechnung);)
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String string = new String();
        string = "\tRechnungsnummer: " + getrNr()  + "\tKunde:  " + getkNr() + "\tBestellte Artikel:  " + getBestellteArtikel() +  "\tDatum und Zeit als die Rechnung erstellt wurde: " + getFt().format(getdNow()) +"\n" ;
        return string;
    }

    //Setter

    public void setDate(Date dNow){
        this.dNow = dNow;
    }

    //Getter

    public SimpleDateFormat getFt(){
        return this.ft;
    }

    public Date getdNow () {
        return this.dNow;
    }

    public int getrNr(){
        return this.rNr;
    }

    public int getkNr(){
        return this.kNr;
    }

    public Vector<Artikel> getBestellteArtikel(){
        return this.bestellteArtikel;
    }
}
