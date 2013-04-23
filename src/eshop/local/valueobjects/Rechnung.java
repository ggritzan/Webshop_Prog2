package eshop.local.valueobjects;

import java.io.Serializable;

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
    private String auftragsname;
    private static int zaehler = 1000;

    //Konstruktor für neue Rechnungen

    public Rechnung(String auftragsname) {
        this.auftragsname = auftragsname;
        this.rNr = this.zaehler;
        this.zaehler++;
    }

    // Konstruktor für das einlesen von Rechnungen
    public Rechnung(Rechnung r) {
        this.auftragsname = r.getAuftragsname();
        this.rNr = r.getrNr();
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
        string = "\tRechnungsnummer: " + getrNr()  + "\tAuftragsname:  " + getAuftragsname() +  "\n" ;
        return string;
    }

    //Setter

    public void setrNr(int rNr) {
        this.rNr = rNr;
    }

    public void setAuftragsname(String auftragsname){
        this.auftragsname = auftragsname;
    }

    //Getter

    public int getrNr(){
        return this.rNr;
    }

    public String getAuftragsname(){
        return this.auftragsname;
    }
}
