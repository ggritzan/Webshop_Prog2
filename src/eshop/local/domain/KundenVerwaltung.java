package eshop.local.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import eshop.local.valueobjects.Artikel;
import eshop.local.persistence.FilePersistenceManager;
import eshop.local.persistence.PersistenceManager;
import eshop.local.valueobjects.Kunde;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 18.04.13
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
public class KundenVerwaltung {

    // Hashmap zum speichern des Kundenbestands als Key dienen die Kundennummern
    private HashMap<Integer, Kunde> kundenBestandNr;

    // Hashmap zum verknüpfen des Kundennamens mit der Kundennummer
    private HashMap<String, Integer> kundenBestandName;

    // Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
    private PersistenceManager pm = new FilePersistenceManager();


// Konstruktor

    public KundenVerwaltung() {

        // verknüpft die Kundennummern mit den Kunden Objekten
        kundenBestandNr = new HashMap<Integer, Kunde>();

        // verknüpft die Kundennamen mit den Kundennummern
        kundenBestandName = new HashMap<String, Integer>();
    }
}
