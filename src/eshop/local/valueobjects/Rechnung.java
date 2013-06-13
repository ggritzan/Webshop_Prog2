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
    private double gesamtPreis = 0;
    private Vector<Artikel> bestellteArtikel;
    private static int zaehler = 1000;
    private final SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz");
    private Date dNow;

    //Konstruktor für neue Rechnungen

    public Rechnung(int kNr, Vector<Artikel> bestellteArtikel, double gesamtPreis) {
        this.kNr = kNr;
        this.bestellteArtikel = bestellteArtikel;
        this.gesamtPreis = gesamtPreis;
        this.rNr = this.zaehler;
        this.zaehler++;
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
        String string = "\tRechnungsnummer: " + getrNr() + "\tKunde:  " + getkNr() + "\tBestellte Artikel:  " + getBestellteArtikel() + " Gesamtpreis: " + getGesamtPreis() + "\tDatum und Zeit als die Rechnung erstellt wurde: " + getFt().format(getdNow())  + "\n";
        return string;
    }

    public void plusGesamtPreis(double wert) {
        this.gesamtPreis = this.gesamtPreis+ wert;
    }

    public void minusGesamtPreis(double wert) {
        this.gesamtPreis = this.gesamtPreis - wert;
    }

    //Setter

    /**
     * Setter für den Zaehler
     * @param zaehler
     */
    public static void setZaehler(int zaehler) {
        Rechnung.zaehler = zaehler;
    }

    public void setDate(Date dNow) {
        this.dNow = dNow;
    }



    //Getter

    /**
     * Getter für den Zaehler
     * @return
     */
    public static int getZaehler() {
        return zaehler;
    }

    public SimpleDateFormat getFt() {
        return this.ft;
    }

    public Date getdNow() {
        return this.dNow;
    }

    public int getrNr() {
        return this.rNr;
    }

    public int getkNr() {
        return this.kNr;
    }

    public Vector<Artikel> getBestellteArtikel() {
        return this.bestellteArtikel;
    }

    public double getGesamtPreis() {
        return this.gesamtPreis;
    }
}
