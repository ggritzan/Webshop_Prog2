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

    /**
     * Konstruktor um ein neues Rechnungsobjekt anzulegen
     *
     * @param kNr -> Die Nummer des Kunden zu dem die Rechnung gehört
     * @param bestellteArtikel -> Der warenkorb des Kunden
     * @param gesamtPreis -> Der Gesamtpreis aller Artikel im Warenkorb des Kunden
     */
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

    /**
     * Methode um den Gesamtpreis um einen bestimmten Wert zu erhöhen
     *
     * @param wert -> Der Wert um den der Gesamtpreis erhöht werden soll
     */
    public void plusGesamtPreis(double wert) {
        this.gesamtPreis = this.gesamtPreis+ wert;
    }

    /**
     * Setter um den Gesamtpreis der Rechnung zu verringern
     *
     * @param wert -> Der Wert um den der Gesamtpreis verringert werden soll
     */
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

    /**
     * Setter um das Datum der Rechnung zu ändern
     *
     * @param dNow -> Das neue Datum der Rechnung
     */
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

    /**
     * Getter
     *
     * @return SimpleDateFormat
     */
    public SimpleDateFormat getFt() {
        return this.ft;
    }

    /**
     * Getter für das Datum
     *
     * @return Date
     */
    public Date getdNow() {
        return this.dNow;
    }

    /**
     * Getter für die Rechnungsnummer
     *
     * @return int -> Die Rechnungsnummer
     */
    public int getrNr() {
        return this.rNr;
    }

    /**
     * Getter für die Kundennummer des Kunden dem die Rechnung ausgestellt wird
     *
     * @return int -> Die Kundennummer
     */
    public int getkNr() {
        return this.kNr;
    }

    /**
     * Getter für die Liste der bestellten Artikel
     *
     * @return Vector<Artikel> -> Der Vector die die Artikelliste aus dem Warenkorb des Kunden enthält
     */
    public Vector<Artikel> getBestellteArtikel() {
        return this.bestellteArtikel;
    }

    /**
     * Getter für den Gesamtpreis
     *
     * @return double -> Deb Gesamtpreis der Rechnung
     */
    public double getGesamtPreis() {
        return this.gesamtPreis;
    }
}
