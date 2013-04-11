package eshop.local.persistence;
import java.io.IOException;
import eshop.local.valueobjects.Artikel;
/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 11.04.13
 * Time: 14:29
 * To change this template use File | Settings | File Templates.
 */
public interface PersistenceManager {


        public void openForReading(String datenquelle) throws IOException;

        public void openForWriting(String datenquelle) throws IOException;

        public boolean close();

        /**
         * Methode zum Einlesen der Artikeldaten aus einer externen Datenquelle.
         *
         * @return Atikel-Objekt, wenn Einlesen erfolgreich, false null
         */
        public Artikel ladeArtikel() throws IOException;

        /**
         * Methode zum Schreiben der Buchdaten in eine externe Datenquelle.
         *
         * @param a Buch-Objekt, das gespeichert werden soll
         * @return true, wenn Schreibvorgang erfolgreich, false sonst
         */
        public boolean speichereArtikel(Artikel a) throws IOException;

	/*
	 *  Wenn später mal eine Kundenverwaltung ergänzt wird:

	public Kunde ladeKunde() throws IOException;

	public boolean speichereKunde(Kunde k) throws IOException;

	*/
}
