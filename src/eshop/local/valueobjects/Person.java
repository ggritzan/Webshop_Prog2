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
    String vorname;
    String nachname;

    // Konstruktor
    public Person(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    // Methoden der Klasse Person

    // Setter

    // Getter
    public String getVorname() {
        return vorname;
    }

    public String getNachname(){
        return nachname;
    }

}
