package eshop.local.persistence;

import java.io.*;
import java.util.Vector;

import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Kunde;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 11.04.13
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class FilePersistenceManager implements PersistenceManager {


    private PrintWriter writer = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

// Methoden

    /**
     * Methode oeffnet einen FileInputStream.
     *
     * @param datei Datei, welche die eizulesenden Daten enthält.
     * @throws IOException
     */
    public void openForReading(String datei) throws IOException {

        ois = new ObjectInputStream(new FileInputStream(datei));

    }

    /**
     * Methode oeffnet einen FileOutputStream.
     *
     * @param datei Datei, in welche die Daten geschrieben werden sollen.
     * @throws IOException
     */
    public void openForWriting(String datei) throws IOException {

        oos = new ObjectOutputStream(new FileOutputStream(datei));

    }

    /**
     * Methode schliest einen FileInputStream oder einen FileoutputStream.
     */
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
     *
     * @return Artikel
     * @throws IOException,ClassNotFoundException
     *
     */
    public Artikel ladeArtikel() throws IOException, ClassNotFoundException {

        Artikel artikel = liesArtikel(ois);

        return artikel;
    }

    /**
     * Methode zum Einlesen der Artikeldaten aus einer externen Datenquelle.
     *
     * @return Kunde
     * @throws IOException,ClassNotFoundException
     *
     */
    public Kunde ladeKunde() throws IOException, ClassNotFoundException {

        Kunde kunde = liesKunde(ois);

        return kunde;
    }

    /**
     * Methode zum Speichern der Artikeldaten in eine externe Datenquelle.
     *
     * @param a Artikel-Objekt, das gespeichert werden soll
     * @return boolean, wenn Schreibvorgang erfolgreich true, ansonsten false
     * @throws IOException
     */
    public boolean speichereArtikel(Artikel a) throws IOException {

        try {

            oos.writeObject(a);

            return true;

        } catch (IOException e) {

            return false;
        }


    }

    /**
     * Methode zum Speichern der Kundendaten in eine externe Datenquelle.
     *
     * @param k Kunden-Objekt, das gespeichert werden soll
     * @return boolean, wenn Schreibvorgang erfolgreich true, ansonsten false
     * @throws IOException
     */
    public boolean speichereKunde(Kunde k) throws IOException {

        try {

            oos.writeObject(k);

            return true;

        } catch (IOException e) {

            return false;
        }


    }

    /**
     * Methode zum auslesen von Artikel Objekten aus einem uebergebenden ObjectInputStream.
     *
     * @param ois ObjectInputStream, aus dem das Objekt ausgelesen wird.
     * @return Artikel, wenn sich noch ein Artikel Objekt im ObjectInputstream befindet.
     * @throws IOException,ClassNotFoundException
     */
    private Artikel liesArtikel(ObjectInputStream ois) throws IOException, ClassNotFoundException {

        try {

            Artikel a = new Artikel((Artikel) ois.readObject());


            return a;

        } catch (EOFException exc) {

            return null;
        }


    }

    /**
     * Methode zum auslesen von Kunden Objekten aus einem uebergebenden ObjectInputStream.
     *
     * @param ois ObjectInputStream, aus dem das Objekt ausgelesen wird.
     * @return Kunde, wenn sich noch ein Kunde Objekt im ObjectInputstream befindet.
     * @throws IOException,ClassNotFoundException
     */
    private Kunde liesKunde(ObjectInputStream ois) throws IOException, ClassNotFoundException {

        try {

            Kunde k = new Kunde((Kunde) ois.readObject());


            return k;

        } catch (EOFException exc) {

            return null;
        }


    }

}
