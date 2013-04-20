/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 28.03.13
 * Time: 09:55
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

import java.util.Vector;

public class Warenkorb {
    // Attribute zur Beschreibung eines Warenkorbs
    Vector warenkorb = new Vector();
    // Konstruktor
    // Methoden der Klasse Warenkorb

    //Fügt einen Artikel an den Vektor an
    public void addArtikel(Artikel a){
        warenkorb.add(a);
    }

    //Nimmt einen Artikel aus dem Vektor heraus
    public void remArtikel(Artikel a){
        warenkorb.remove(a);
    }
    // Setter
    // Getter

}

