package eshop.local.persistence;

import java.io.*;

import java.util.Vector;

import eshop.local.valueobjects.Artikel;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 11.04.13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class FilePersistenceManager implements PersistenceManager {

    //private BufferedReader reader = null;
    private PrintWriter writer = null;

    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;


    public void openForReading(String datei) throws IOException {
        // reader = new BufferedReader(new FileReader(datei));

           ois = new ObjectInputStream(new FileInputStream(datei));



    }


    public void openForWriting(String datei) throws IOException {
        // writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));

        oos = new ObjectOutputStream(new FileOutputStream(datei));

    }

    public boolean close() {
        if (writer != null)
            writer.close();

        if (oos != null) {
            try {
                oos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

                return false;
            }
        }

        if (ois != null) {
            try {
                ois.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

                return false;
            }
        }

        return true;
    }


    /**
     * Methode zum Einlesen der Artikeldaten aus einer externen Datenquelle.
     */
    public Artikel ladeArtikel() throws IOException, ClassNotFoundException {

            /*
            // Name einlesen
            String name = liesZeile();
            if (name == null) {
                // keine Daten mehr vorhanden
                return null;
            }
            // Beschreibung einlesen
            String beschreibung  = liesZeile();
            if (beschreibung == null) {
                // keine Daten mehr vorhanden
                return null;
            }

            // Nummer einlesen
            String nummerString = liesZeile();
            // String in int konvertieren
            int nummer = Integer.parseInt(nummerString);
            if (nummerString == null) {
                // keine Daten mehr vorhanden
                return null;
            }

            // Preis einlesen
            String preisString  = liesZeile();
            // String in double konvertieren
            double preis = Double.parseDouble(preisString);
            if (preisString == null) {
                // keine Daten mehr vorhanden
                return null;
            }

            // Bestand einlesen
            String bestandString  = liesZeile();
            // String in int konvertieren
            int bestand = Integer.parseInt(bestandString);
            if (bestandString == null) {
                // keine Daten mehr vorhanden
                return null;
            }





            // neues Buch-Objekt anlegen und zurückgeben
            return new Artikel(name, beschreibung, nummer, preis, bestand);
            */


        Artikel artikel = liesObjekt(ois);

        return artikel;
    }


    /**
     * Methode zum Schreiben der Artikeldaten in eine externe Datenquelle.
     *
     * @param a Artikel-Objekt, das gespeichert werden soll
     * @return true, wenn Schreibvorgang erfolgreich, false sonst
     */
    public boolean speichereArtikel(Artikel a) throws IOException {
        // Titel, Nummer und Verfügbarkeit schreiben

            /*
            schreibeZeile(a.getName());
            schreibeZeile(a.getBeschreibung());
            schreibeZeile(Integer.valueOf(a.getNummer()).toString());
            schreibeZeile(Double.valueOf(a.getPreis()).toString());
            schreibeZeile(Integer.valueOf(a.getBestand()).toString());
            */

        try {

            oos.writeObject(a);
            return true;
        } catch (IOException e) {
            return false;
        }


    }

	/*
     *  Wenn später mal eine Kundenverwaltung ergänzt wird:

	public Kunde ladeKunde() throws IOException {
		// TODO: Implementieren
		return null;
	}

	public boolean speichereKunde(Kunde k) throws IOException {
		// TODO: Implementieren
		return false;
	}

	*/

	/*
     * Private Hilfsmethoden
	 */

    private Artikel liesObjekt(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        try {

            Artikel a = new Artikel((Artikel) ois.readObject());


            return a;
        } catch (EOFException exc) {
            return null;
        }


    }


}