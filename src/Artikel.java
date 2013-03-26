/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
// package bib.local.valueobjects;


/**
 * Klasse zur Repräsentation einzelner Artikel.
 *
 * @author Gritzan
 */
public class Artikel {

    // Attribute zur Beschreibung eines Artikels:
    private String name;
    private int nummer;
    private int bestand;

    public Artikel(String name, int nummer ) {
        this.name = name;
        this.nummer = nummer;
        this.bestand = 0;
    }


}
