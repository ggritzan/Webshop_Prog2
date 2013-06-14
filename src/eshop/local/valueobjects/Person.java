/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

import java.io.Serializable;

public abstract class Person implements Serializable {

    // SerialVersionUID zum speichern und einlesen
    private static final long serialVersionUID = 4795760257516199004L;

    // Attribute zur Beschreibung einer Person
    private String vorname;
    private String nachname;
    private String benutzername;
    private String passwort;
    private String email;
    private String telefon;
    private Adresse adresse;


    // Konstruktor

    /**
     * Methode zum anlegen eines neuen Personenobjekts
     *
     * @param vorname -> Voname der Person
     * @param nachname -> Nachname der Person
     * @param benutzername -> Benutzername der Person
     * @param passwort -> Passwort der Person
     * @param email -> Emailadresse der Person
     * @param telefon -> Telefonnummer der Person
     * @param adresse -> Adresse der Person
     */
    public Person(String vorname, String nachname, String benutzername, String passwort, String email, String telefon, Adresse adresse) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.email = email;
        this.telefon = telefon;
        this.adresse = adresse;
    }

    // Setter

    /**
     * Methode zum Ändern des Vornamens der Person
     *
     * @param vorname -> Der neue Vorname
     */
    public void setVorname(String vorname){
        this.vorname = vorname;
    }

    /**
     * Methode zum Ändern des Nachnamens der Person
     *
     * @param nachname -> Der neue Nachname
     */
    public void setNachname(String nachname){
        this.nachname = nachname;
    }

    /**
     * Methode zum Ändern des Benutzernamens der Person
     *
     * @param benutzername -> Der neue Benutzername
     */
    public void setBenutzername(String benutzername){
        this.benutzername = benutzername;
    }

    /**
     * Methode zum Ändern des Passworts der Person
     *
     * @param passwort -> Das neue Passwort
     */
    public void setPasswort(String passwort){
        this.passwort = passwort;
    }

    /**
     * Methode zum Ändern der Emailadresse der Person
     *
     * @param email -> Die neue Emailadresse
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Methode zum Ändern der Telefonnummer der Person
     *
     * @param telefon -> Die neue Telefonnnummer
     */
    public void setTelefon(String telefon){
        this.telefon = telefon;
    }

    /**
     * Methode zum Ändern der Adresse der Person
     * Vorher muss also ein neues Adressenobjekt angelegt werden, welches das Alte überschreibt
     *
     * @param adresse -> Die neue Adresse
     */
    public void setAdresse(Adresse adresse){
        this.adresse = adresse;
    }

    // Getter

    /**
     * Methode liefert den Vornamen der Person zurück
     *
     * @return String -> Der Vorname der Person
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Methode liefert den Nachnamen der Person zurück
     *
     * @return String -> Der Nachname der Person
     */
    public String getNachname(){
        return nachname;
    }

    /**
     * Methode liefert den Benutzernamen der Person zurück
     *
     * @return String -> Der Benutzername der Person
     */
    public String getBenutzername(){
        return benutzername;
    }

    /**
     * Methode liefert das Passwort der Person zurück
     *
     * @return String -> Das Passwort der Person
     */
    public String getPasswort(){
        return passwort;
    }

    /**
     * Methode liefert die Emailadresse der Person zurück
     *
     * @return String -> Die Emailadresse der Person
     */
    public String getEmail(){
        return email;
    }

    /**
     * Methode liefert die Telefonnummer der Person zurück
     *
     * @return String -> Die Telefonnummer der Person
     */
    public String getTelefon(){
        return telefon;
    }

    /**
     * Methode liefert die Adresse der Person zurück
     *
     * @return Adresse -> Die Adresse der Person
     */
    public Adresse getAdresse(){
        return adresse;
    }

}

