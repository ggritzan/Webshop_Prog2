/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 27.03.13
 * Time: 07:07
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

public class Adresse {

    // Attribute zur Beschreibung einer Adresse
    private int kNr;
    private String vorname;
    private String nachname;
    private String straße;
    private String plz;
    private String ort;
    private String telefon;
    private String email;

    // Konstruktor
    public Adresse (int kNr, String vorname, String nachname, String straße, String plz, String ort, String telefon, String eMail) {
        this.kNr = kNr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.straße = straße;
        this.plz = plz;
        this.ort = ort;
        this.telefon = telefon;
        this.email = email;
    }


    // Methoden der Klasse Adresse
    public void printAdresse() {
        System.out.print("Vorname: " + this.vorname);
        System.out.print("Nachname: " + this.nachname);
        System.out.print("Straße: " + this.straße);
        System.out.print("PLZ: " + this.plz);
        System.out.print("Ort: " + this.ort);
        System.out.print("Telefon: " + this.telefon);
        System.out.print("Email: " + this.email);
    }


    // Setter

    // Getter
    public String getVorname() {
        return this.vorname;
    }

    public String getNachname() {
        return this.nachname;
    }

    public String getStraße() {
        return this.straße;
    }

    public String getPlz() {
        return this.plz;
    }

    public String getOrt() {
        return this.ort;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public String getEmail() {
        return this.email;
    }
}
