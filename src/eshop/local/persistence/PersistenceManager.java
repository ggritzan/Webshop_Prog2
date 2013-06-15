package eshop.local.persistence;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import eshop.local.valueobjects.*;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 11.04.13
 * Time: 14:29
 * To change this template use File | Settings | File Templates.
 */
public interface PersistenceManager {


        public void openForReading(String datei) throws IOException;

        public void openForWriting(String datei) throws IOException;

        public boolean close();

        /**
         * Methode zum Einlesen der Artikeldaten aus einer externen Datenquelle.
         *
         * @return Artikel, wenn Einlesen erfolgreich, ansonsten null
         */

        public Artikel ladeArtikel() throws IOException, ClassNotFoundException;

        /**
         * Methode zum Schreiben der Artikeldaten in eine externe Datenquelle.
         *
         * @param a Artikel-Objekt, das gespeichert werden soll
         * @return boolean, true, wenn Schreibvorgang erfolgreich, ansonsten false
         */
        public boolean speichereArtikel(Artikel a) throws IOException;

        /**
         * Methode zum Schreiben der MassengutArtikeldaten in eine externe Datenquelle.
         *
         * @param m MassenzgutArtikel-Objekt, das gespeichert werden soll
         * @return boolean, true, wenn Schreibvorgang erfolgreich, ansonsten false
         */
        public boolean speichereMassengutArtikel(MassengutArtikel m) throws IOException;

        /**
        * Methode zum auslesen der Kundendaten aus externer Datenquelle
        *
        * @return Kunde, wenn kein Kundenobjekt vorhanden ist null
        */
        public Kunde ladeKunde() throws IOException, ClassNotFoundException;

        /**
        * Methode zum speichern eines Kundenobjekts in eine externe Datei
        *
        * @return boolean, true, wenn speichern erfolgreich, ansonsten false
        */
        public boolean speichereKunde(Kunde k) throws IOException;

        /**
        * Methode zum ausleesen der Mitarbeiterdaten aus einer externen Datei
        *
        * @return Mitarbeiter-Objekt, ansonsten null
        */
        public Mitarbeiter ladeMitarbeiter() throws IOException, ClassNotFoundException;

        /**
        * Methode zum speichern eines Mitarbeiterobjekts in eine externe Datei
        *
        * @return boolean, true, wenn speichern erfolgreich, ansonsten false
        */
        public boolean speichereMitarbeiter(Mitarbeiter m) throws IOException;

        /**
        * Methode zum auslesen eines Rechnungs-Objekts aus einer externen Datei
        *
        * @return Rechnung, ansonsten null
        */
        public Rechnung ladeRechnung() throws IOException, ClassNotFoundException;

        /**
        * Methode zum speichern eines Rechnungobjekts in eine externe Datei
        *
        * @return boolean, true, wenn speichern erfolgreich, ansonsten false
        */
        public boolean speichereRechnung(Rechnung r) throws IOException;
}


