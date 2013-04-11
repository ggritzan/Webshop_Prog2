package eshop.local.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 10.04.13
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class EShopVerwaltung {

    package bib.local.domain;

    import java.io.IOException;
    import java.util.Vector;

    import bib.local.domain.exceptions.BuchExistiertBereitsException;
    import bib.local.valueobjects.Buch;

    /**
     * Klasse zur Verwaltung einer (sehr einfachen) Bibliothek.
     * Bietet Methoden zum Zurückgeben aller Bücher im Bestand,
     * zur Suche nach Büchern, zum Einfügen neuer Bücher
     * und zum Speichern des Bestands.
     *
     * @author teschke
     * @version 2 (Verwaltung der Buecher in Vector)
     */
    public class BibliotheksVerwaltung {
        // Präfix für Namen der Dateien, in der die Bibliotheksdaten gespeichert sind
        private String datei = "";

        private BuecherVerwaltung meineBuecher;
        // private KundenVerwaltung meineKunden;
        // hier weitere Verwaltungsklassen, z.B. für Autoren oder Angestellte

        /**
         * Konstruktor, der die Basisdaten (Bücher, Kunden, Autoren) aus Dateien einliest
         * (Initialisierung der Bibliothek).
         *
         * Namensmuster für Dateien:
         *   datei+"_B.txt" ist die Datei der Bücher
         *   datei+"_K.txt" ist die Datei der Kunden
         *
         * @param datei
         * @throws IOException, z.B. wenn eine der Dateien nicht existiert.
         */
        public BibliotheksVerwaltung(String datei) throws IOException {
            this.datei = datei;

            // Buchbestand aus Datei einlesen
            meineBuecher = new BuecherVerwaltung();
            meineBuecher.liesDaten(datei+"_B.txt");
            // Kundenkartei aus Datei einlesen
//		meineKunden = new KundenVerwaltung();
//		meineKunden.liesDaten(datei+"_K.txt");
//		meineKunden.schreibeDaten(datei+"_K.txt");
        }


        /**
         * Methode, die eine Liste aller im Bestand befindlichen Bücher zurückgibt.
         *
         * @return Liste aller Bücher im Bestand der Bibliothek
         */
        public Vector gibAlleBuecher() {
            // einfach delegieren an meineBuecher
            return meineBuecher.getBuchBestand();
        }

        /**
         * Methode zum Suchen von Büchern anhand des Titels. Es wird eine Liste von Büchern
         * zurückgegeben, die alle Bücher mit exakt übereinstimmendem Titel enthält.
         *
         * @param titel Titel des gesuchten Buchs
         * @return Liste der gefundenen Bücher (evtl. leer)
         */
        public Vector sucheNachTitel(String titel) {
            // einfach delegieren an meineBuecher
            return meineBuecher.sucheBuecher(titel);
        }

        /**
         * Methode zum Einfügen eines neuen Buchs in den Bestand.
         * Wenn das Buch bereits im Bestand ist, wird der Bestand nicht geändert.
         *
         * @param titel Titel des Buchs
         * @param nummer Nummer des Buchs
         * @return true, wenn Einfügen erfolgreich, false sonst (z.B. wenn Buch bereits vorhanden)
         */
        public boolean fuegeBuchEin(String titel, int nummer) {
            Buch b = new Buch(titel, nummer);
            try {
                meineBuecher.einfuegen(b);
            } catch (BuchExistiertBereitsException e) {
                return false;
            }
            return true;
        }

        /**
         * Methode zum Speichern des Buchbestands in einer Datei.
         *
         * @throws IOException
         */
        public void schreibeBuecher() throws IOException {
            meineBuecher.schreibeDaten(datei+"_B.txt");
        }

        // TODO: Weitere Funktionen der Bibliotheksverwaltung, z.B. ausleihen, zurückgeben etc.
        // ...
    }

}
