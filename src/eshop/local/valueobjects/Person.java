/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

public abstract class Person{

    // Attribute zur Beschreibung einer Person
    private String vorname;
    private String nachname;
    private String benutzername;
    private String passwort;
    private String email;
    private String telefon;
    private Adresse adresse;


    // Konstruktor
    public Person(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.email = email;
        this.telefon = telefon;
        this.adresse = adresse;
    }

    // Methoden der Klasse Person


    // Setter

    public void setVorname(String vorname){
        this.vorname = vorname;
    }

    public void setNachname(String nachname){
        this.nachname = nachname;
    }

    public void setBenutzername(String benutzername){
        this.benutzername = benutzername;
    }

    public void setPasswort(String passwort){
        this.passwort = passwort;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setTelefon(String telefon){
        this.telefon = telefon;
    }

    public void setAdresse(Adresse adresse){
        this.adresse = adresse;
    }

    // Getter
    public String getVorname() {
        return vorname;
    }

    public String getNachname(){
        return nachname;
    }

    public String getBenutzername(){
        return benutzername;
    }

    public String getPasswort(){
        return passwort;
    }

    public String getEmail(){
        return email;
    }

    public String getTelefon(){
        return telefon;
    }

    public Adresse getAdresse(){
        return adresse;
    }

}
