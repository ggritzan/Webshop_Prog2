package eshop.local.persistence;
import java.io.IOException;
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
         * @return Artikel-Objekt, wenn Einlesen erfolgreich, false null
         */
        public Artikel ladeArtikel() throws IOException, ClassNotFoundException;

        /**
         * Methode zum Schreiben der Artikeldaten in eine externe Datenquelle.
         *
         * @param a Artikel-Objekt, das gespeichert werden soll
         * @return true, wenn Schreibvorgang erfolgreich, false sonst
         */
        public boolean speichereArtikel(Artikel a) throws IOException;

        public Kunde ladeKunde() throws IOException, ClassNotFoundException;

        public boolean speichereKunde(Kunde k) throws IOException;

        public Mitarbeiter ladeMitarbeiter() throws IOException, ClassNotFoundException;

        public boolean speichereMitarbeiter(Mitarbeiter m) throws IOException;

        public Rechnung ladeRechnung() throws IOException, ClassNotFoundException;

        public boolean speichereRechnung(Rechnung r) throws IOException;

        public ArtikelLog ladeArtikelLog() throws IOException, ClassNotFoundException;

        public boolean speichereArtikelLog(ArtikelLog al) throws IOException;
}


