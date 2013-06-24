package eshop.local.persistence;

import eshop.local.exception.KeineEintraegeVorhandenException;
import eshop.local.exception.KennNummerExistiertNichtException;
import eshop.local.valueobjects.ArtikelBestandsGraph;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 12.05.13
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */


public class Log {

    /**
     * Methode, die einen Text in eine Log-Datei schreibt. In dem Log wird festgehalten was, wann bei den Artikeln,
     * Kunden, Mitarbeitern oder Rechnungen geändert wurde.
     *
     * @param dateiName - Pfad zur Log-Datei: "Eshop_ArtikelLog.txt" || "Eshop_KundenLog.txt" ||
     *                    "Eshop_MitarbeiterLog.txt" || "Eshop_RechnungsLog.txt"
     * @param text - Text, der in die Datei geschrieben werden soll
     * @throws IOException
     */

    public void writeLog (File dateiName, String text) throws IOException {
        BufferedWriter schreibeStrom = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dateiName, true)));
        schreibeStrom.write(text);
        schreibeStrom.newLine();
        schreibeStrom.newLine();
        schreibeStrom.close();
    }

    /**
     * Methode, die Daten, die für den Artikel-Bestands-Graphen wichtig sind in eine externe Datei schreibt.
     *
     * @param dateiName - Pfad zur Datei in der die Daten geschrieben werden sollen: "Eshop_BestandsGraph.txt"
     * @param text - Daten, die in die Datei geschrieben werden: Datum, Artikelname und -nummer, aktueller Bestand
     * @throws IOException
     */

    public void writeGraphData (File dateiName, String text) throws IOException{
        BufferedWriter schreibeStrom = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dateiName, true)));
        schreibeStrom.write(text);
        schreibeStrom.newLine();
        schreibeStrom.close();
    }

    /**
     * Methode, die dazu dient Log-Objekte für den Artikel-Bestands-Graphen eines bestimmten Artikels ab einem
     * bestimmten Datum zu erzeugen.
     *
     * @param fileName - Datei aus der die Daten für die Log-Objekte ausgelesen werden sollen
     * @param daysInPast - Tage, die der Log zurückliegen soll
     * @param kennNr - Artikelnummer nach der der Artikel gesucht werden soll
     * @param name - Artikelname nach der der Artikel gesucht werden soll
     * @return - einen Vector aus ArtikelBestandsGraph-Objekten
     * @throws FileNotFoundException
     * @throws ParseException
     */

    public Vector<ArtikelBestandsGraph> getArtikelGraph(String fileName, int daysInPast, String kennNr, String name) throws FileNotFoundException, ParseException{
        // das Datumsformat nach dem die Datumseinträge geparst werden sollen
        SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz':'");
        Calendar cal = new GregorianCalendar();
        // das heutige Datum
        Date today = new Date();
        // der ganze Log als String-Vector
        Vector<String> log = new Vector<String>();
        // der ganze Log als Tokens, jeweils nach einem "%" getrennt
        Vector<String[]> eintraege = new Vector<String[]>();
        // alle Datumseinträge als Datum geparst
        Vector<Date> convertedDate = new Vector<Date>();
        // alle geparsten Datumseinträge als Tag des Jahres
        Vector<Integer> daysOfYear = new Vector<Integer>();
        // alle Einträge mit der gewünschten Artikelnummer und dem gewünschten Artikelnamen
        Vector<String[]> neededEntries = new Vector<String[]>();
        // alle Tage des Jahres in dem was mit dem gewünschten Artikel gemacht wurde
        Vector<Integer> neededDaysOfYear = new Vector<Integer>();
        // der letzte Zeit/Datums Eintrag am jeweiligen Tag von den 'neededDaysOfYear'
        Vector<Date> lastDateEntryOfDay = new Vector<Date>();
        // fertige ArtikelGraphobjekte aus den gewünschten Daten
        Vector<ArtikelBestandsGraph> abgObjects = new Vector<ArtikelBestandsGraph>();


        /*
         * scannen der Datei aus der ausgelesen werden soll, nach jedem Zeilenumbruch wird die Zeile in einen log-Vector
         * gespeichert
         */

        Scanner filescan = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("Eshop_BestandsGraph.txt")))).useDelimiter("\n");

        while (filescan.hasNext()){
            log.add(filescan.next());
        }

        // alle Einträge als Tokens jeweils nach einem "%" gesplittet

        for (int i = 0; i < log.size(); i++){
            String[] splitResult = log.elementAt(i).split("%");
            eintraege.add(splitResult);
        }

        // alle Datumseinträge als Datums-Objekte geparst

        for (int i = 0; i < eintraege.size(); i++){
            // eintraege.elementAt(i)[0] ist immer ein Datum
            convertedDate.add(formatter.parse(eintraege.elementAt(i)[0].toString()));
            // alle Datums-Objekte als Tag des Jahres
            cal.setTime(convertedDate.elementAt(i));
            daysOfYear.add(cal.get(Calendar.DAY_OF_YEAR));
        }

        // wie viele Tage von heute die zurückliegen sollen bei der Anzeige des Graphen

        cal.setTime(today);

        if(daysInPast > 0){
            cal.add(Calendar.DAY_OF_YEAR, -daysInPast);
        } else {
            cal.add(Calendar.DAY_OF_YEAR, daysInPast);
        }

        // alle benötigten Einträge, Datums und Tage des Jares

        for (int i = 0; i < eintraege.size(); i++){
            /*
             *  wenn Einträge nach oder am selben Tag wie das eingegebene zurückiegende Datum liegen und den gewünschten
             *  Artikelnamen und Artikelnummer haben, sollen diese Einträge und deren Datums und die jeweiligen Tage des
             *  Jahres in jeweils einen Vektor gespeichert werden
             */

            if(eintraege.elementAt(i)[2].equals(kennNr) && eintraege.elementAt(i)[1].equals(name) && convertedDate.elementAt(i).after(cal.getTime()) || eintraege.elementAt(i)[2].equals(kennNr) && eintraege.elementAt(i)[1].equals(name) && convertedDate.elementAt(i).equals(cal.getTime())){
                neededDaysOfYear.add(daysOfYear.elementAt(i));
                neededEntries.add(eintraege.elementAt(i));
                lastDateEntryOfDay.add(convertedDate.elementAt(i));

            }
        }

        /*
        * der jeweils letzte Eintrag des Tages des jeweiligen Artikels soll in ein ArtikelBestandsGraph-Objekt
        * gespeichert werden
        */

        for (int i = 0; i < neededDaysOfYear.size(); i++){
            if(i < neededDaysOfYear.size() - 1 && !neededDaysOfYear.elementAt(i).equals(neededDaysOfYear.elementAt(i+1)) || i == neededDaysOfYear.size() - 1){
                ArtikelBestandsGraph abg = new ArtikelBestandsGraph(neededEntries.elementAt(i)[1], Integer.parseInt(kennNr), lastDateEntryOfDay.elementAt(i), Integer.parseInt(neededEntries.elementAt(i)[3].toString()));
                abgObjects.add(abg);
            }
        }

        return abgObjects;

    }

    /**
     * Methode, die einen lesbaren Log eines/einer bestimmten Artikels || Kunden || Mitarbeiter || Rechnung ab einem
     * bestimmten Datum auf der Konsole ausgibt.
     *
     * @param fileName - Dateipfad der Datei aus der die jeweiligen Daten für die Logs ausgelesen werden sollen:
     *                 "Eshop_ArtikelLog.txt" || "Eshop_KundenLog.txt" || "Eshop_MitarbeiterLog.txt" ||
     *                 "Eshop_RechnungsLog.txt"
     * @param daysInPast - Tage, die der Log zurückliegen soll
     * @param kennNr - Kennnummer nach der der/die Artikel || Kunde || Mitarbeiter || Rechnung gesucht werden soll
     * @return - die gewünschten Teile des jeweiligen Logs als String-Vector
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws KennNummerExistiertNichtException
     */

    public Vector<String> printLog (String fileName, int daysInPast, String kennNr) throws FileNotFoundException, ParseException, KennNummerExistiertNichtException{
        // das Datumsformat nach dem die Datumseinträge geparst werden sollen
        SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz':'");
        Calendar cal = new GregorianCalendar();
        // das heutige Datum
        Date today = new Date();
        // der ganze Log als String-Vector
        Vector<String> log = new Vector<String>();
        // alle Datumseinträge als Datum geparst
        Vector<Date> convertedDate = new Vector<Date>();
        // der ganze Log als Tokens, jeweils nach einem Leerzeichen getrennt
        Vector<String[]> eintraege = new Vector<String[]>();
        // die gewünschten Strings in einem Vector, die ausgegeben werden sollen
        Vector<String> string = new Vector<String>();


        // wie viele Tage der Log zurückliegen soll vom heutigen Datum abgezogen

        cal.setTime(today);

        if(daysInPast > 0){
            cal.add(Calendar.DAY_OF_YEAR, -daysInPast);
        } else {
            cal.add(Calendar.DAY_OF_YEAR, daysInPast);
        }

        /*
         * scannen der Datei aus der ausgelesen werden soll, nach jedem Zeilenumbruch wird die Zeile in einen log-Vector
         * gespeichert
         */

        Scanner filescan = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))).useDelimiter("\n");

        while (filescan.hasNext()){
            log.add(filescan.next());
        }

        filescan.close();


        /*
         * alle Datumseinträge als Datums-Objekte geparst- jede dritte Zeile aus der gewählten Datei aus der ausgelesen
         * werden soll entspricht dabei einem Datumseintrag, vom Index 0 angefangen
         */
        for (int i = 0; i < log.size(); i+=3){
            convertedDate.add(formatter.parse(log.elementAt(i)));
        }

        /*
         * alle Einträge als Tokens jeweils nach einem Leerzeichen gesplittet - die Einträge entsprechen dabei jeweils
         * jeder dritten Zeilen aus der gewählten Datei aus der ausgelesen werden soll, vom Index 1 angefangen
         */

        for (int i = 1; i < log.size(); i+=3){
            String[] splitResult = log.elementAt(i).split(" ");
            eintraege.add(splitResult);
        }


        /*
         *  wenn Einträge nach oder am selben Tag wie das eingegebene zurückiegende Datum liegen und die gewünschte
         *  Artikelnummer || Mitarbeiternummer || Kundennummer || Rechnungsnummer haben, sollen diese Einträge und
         *  deren Datums in einen String-Vektor gespeichert werden
         */

        for (int i = 0; i < eintraege.size(); i++){
            /*
             * wenn die Datei aus der ausgelesen werden soll "Eshop_ArtikelLog.txt" entspricht, soll nach dem
             * Artikelnummer-Token geguckt werden
             *
             * wenn die Datei aus der ausgelesen werden soll "Eshop_KundenLog.txt" entspricht, soll nach dem
             * Kundennummer-Token geguckt werden
             *
             * wenn die Datei aus der ausgelesen werden soll "Eshop_MitarbeiterLog.txt" entspricht, soll nach dem
             * Mitarbeiternummer-Token geguckt werden
             *
             * wenn die Datei aus der ausgelesen werden soll "Eshop_RechnungsLog.txt" entspricht, soll nach dem
             * Rechnungsnummer-Token geguckt werden
             */
            for (int j = 0; j < eintraege.elementAt(i).length; j++){
                if (fileName.equals("Eshop_ArtikelLog.txt") && eintraege.elementAt(i)[j].equals("Artikelnummer") || fileName.equals("Eshop_KundenLog.txt") && eintraege.elementAt(i)[j].equals("Kundennummer") || fileName.equals("Eshop_MitarbeiterLog.txt") && eintraege.elementAt(i)[j].equals("Mitarbeiternummer") || fileName.equals("Eshop_RechnungsLog.txt") && eintraege.elementAt(i)[j].equals("Rechnungsnummer")){
                    /*
                     * eintraege.elementAt(i)[j+1] entspricht immer der jeweiligen Artikelnummer || Kundennummer ||
                     * Mitarbeiternummer || Rechnungsnummer
                     */
                    if(eintraege.elementAt(i)[j+1].equals(kennNr) && convertedDate.elementAt(i).after(cal.getTime()) || eintraege.elementAt(i)[j+1].equals(kennNr) && convertedDate.elementAt(i).equals(cal.getTime())){
                        /*
                         * zuerst wird das Datum an der Stelle i hinzugefügt und dann der dazugehörige Text, der der
                         * (i*3+1)-ten Stelle im log-Vector entspricht
                         */
                        string.add(formatter.format(convertedDate.elementAt(i)) + "\n" + log.elementAt(i * 3 + 1) + "\n");
                    }
                }
            }
        }

        /*
         * wenn der String-Vector, der zurückgegeben werden soll, keine Einträge mit der gewünschten Kennnummer hat,
         * soll eine KennNummerExistiertNicht-Exception geworfen werden
         */

        if (!string.isEmpty()){
            return string;
        } else {
            if (fileName.equals("Eshop_ArtikelLog.txt")){
                throw new KennNummerExistiertNichtException("Artikelnummer", kennNr);
            } else if(fileName.equals("Eshop_KundenLog.txt")){
                throw new KennNummerExistiertNichtException("Kundennummer", kennNr);
            } else if(fileName.equals("Eshop_MitarbeiterLog.txt")){
                throw new KennNummerExistiertNichtException("Mitarbeiternummer", kennNr);
            } else if(fileName.equals("Eshop_RechnungsLog.txt")){
                throw new KennNummerExistiertNichtException("Rechnungsnummer", kennNr);
            } else {
                return null;
            }
        }
    }

    /**
     * Methode, die einen lesbaren Log eines/einer bestimmten Artikels || Kunden || Mitarbeiter || Rechnung auf der
     * Konsole ausgibt mit allen Einträgen aus der Vergangenheit.
     *
     * @param fileName - Dateipfad der Datei aus der die jeweiligen Daten für die Logs ausgelesen werden sollen:
     *                 "Eshop_ArtikelLog.txt" || "Eshop_KundenLog.txt" || "Eshop_MitarbeiterLog.txt" ||
     *                 "Eshop_RechnungsLog.txt"
     * @param kennNr - Kennnummer nach der der/die Artikel || Kunde || Mitarbeiter || Rechnung gesucht werden soll
     * @return - die gewünschten Teile des jeweiligen Logs als String-Vector
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws KennNummerExistiertNichtException
     */

    public Vector<String> printLog (String fileName, String kennNr) throws FileNotFoundException, ParseException, KennNummerExistiertNichtException{
        // das Datumsformat nach dem die Datumseinträge geparst werden sollen
        SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz':'");
        // der ganze Log als String-Vector
        Vector<String> log = new Vector<String>();
        // alle Datumseinträge als Datum geparst
        Vector<Date> convertedDate = new Vector<Date>();
        // der ganze Log als Tokens, jeweils nach einem Leerzeichen getrennt
        Vector<String[]> eintraege = new Vector<String[]>();
        // die gewünschten Strings in einem Vector, die ausgegeben werden sollen
        Vector<String> string = new Vector<String>();

        /*
         * scannen der Datei aus der ausgelesen werden soll, nach jedem Zeilenumbruch wird die Zeile in einen log-Vector
         * gespeichert
         */

        Scanner filescan = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))).useDelimiter("\n");

        while (filescan.hasNext()){
            log.add(filescan.next());
        }

        filescan.close();

        /*
         * alle Datumseinträge als Datums-Objekte geparst - jede dritte Zeile aus der gewählten Datei aus der ausgelesen
         * werden soll entspricht dabei einem Datumseintrag, vom Index 0 angefangen
         */

        for (int i = 0; i < log.size(); i+=3){
            convertedDate.add(formatter.parse(log.elementAt(i)));
        }

        /*
         * alle Einträge als Tokens jeweils nach einem Leerzeichen gesplittet - die Einträge entsprechen dabei jeweils
         * jeder dritten Zeilen aus der gewählten Datei aus der ausgelesen werden soll, vom Index 1 angefangen
         */

        for (int i = 1; i < log.size(); i+=3){
            String[] splitResult = log.elementAt(i).split(" ");
            eintraege.add(splitResult);
        }


        /*
         *  wenn die gewünschte Artikelnummer || Mitarbeiternummer || Kundennummer || Rechnungsnummer haben, sollen
         *  diese Einträge und deren Datums in einen String-Vektor gespeichert werden
         */

        for (int i = 0; i < eintraege.size(); i++){
            for (int j = 0; j < eintraege.elementAt(i).length; j++){
                /*
                 * wenn die Datei aus der ausgelesen werden soll "Eshop_ArtikelLog.txt" entspricht, soll nach dem
                 * Artikelnummer-Token geguckt werden
                 *
                 * wenn die Datei aus der ausgelesen werden soll "Eshop_KundenLog.txt" entspricht, soll nach dem
                 * Kundennummer-Token geguckt werden
                 *
                 * wenn die Datei aus der ausgelesen werden soll "Eshop_MitarbeiterLog.txt" entspricht, soll nach dem
                 * Mitarbeiternummer-Token geguckt werden
                 *
                 * wenn die Datei aus der ausgelesen werden soll "Eshop_RechnungsLog.txt" entspricht, soll nach dem
                 * Rechnungsnummer-Token geguckt werden
                 */

                if (fileName.equals("Eshop_ArtikelLog.txt") && eintraege.elementAt(i)[j].equals("Artikelnummer") || fileName.equals("Eshop_KundenLog.txt") && eintraege.elementAt(i)[j].equals("Kundennummer") || fileName.equals("Eshop_MitarbeiterLog.txt") && eintraege.elementAt(i)[j].equals("Mitarbeiternummer") || fileName.equals("Eshop_RechnungsLog.txt") && eintraege.elementAt(i)[j].equals("Rechnungsnummer")){
                    /*
                     * eintraege.elementAt(i)[j+1] entspricht immer der jeweiligen Artikelnummer || Kundennummer ||
                     * Mitarbeiternummer || Rechnungsnummer
                     */
                    if(eintraege.elementAt(i)[j+1].equals(kennNr)){
                        /*
                         * wenn die gewünschte Kennnummer im Eintrag gefunden wurde, wird zuerst das Datum an der Stelle
                         * i hinzugefügt und dann der dazugehörige Text, der der (i*3+1)-ten Stelle im log-Vector
                         * entspricht
                         */
                        string.add(formatter.format(convertedDate.elementAt(i)) + "\n" + log.elementAt(i * 3 + 1) + "\n");
                    }
                }
            }
        }

        /*
         * wenn der String-Vector, der zurückgegeben werden soll, keine Einträge mit der gewünschten Kennnummer hat,
         * soll eine KennNummerExistiertNicht-Exception geworfen werden
         */

        if (!string.isEmpty()){
            return string;
        } else {
            if (fileName.equals("Eshop_ArtikelLog.txt")){
                throw new KennNummerExistiertNichtException("Artikelnummer", kennNr);
            } else if(fileName.equals("Eshop_KundenLog.txt")){
                throw new KennNummerExistiertNichtException("Kundennummer", kennNr);
            } else if(fileName.equals("Eshop_MitarbeiterLog.txt")){
                throw new KennNummerExistiertNichtException("Mitarbeiternummer", kennNr);
            } else if(fileName.equals("Eshop_RechnungsLog.txt")){
                throw new KennNummerExistiertNichtException("Rechnungsnummer", kennNr);
            } else {
                return null;
            }
        }
    }

    /**
     * Methode, die einen lesbaren Log aller Artikel || Kunden || Mitarbeiter || Rechnungen ab einem bestimmten Datum
     * ausgibt.
     *
     * @param fileName - Dateipfad der Datei aus der die jeweiligen Daten für die Logs ausgelesen werden sollen:
     *                 "Eshop_ArtikelLog.txt" || "Eshop_KundenLog.txt" || "Eshop_MitarbeiterLog.txt" ||
     *                 "Eshop_RechnungsLog.txt"
     * @param daysInPast - Tage, die der Log zurückliegen soll
     * @return - die gewünschten Teile des jeweiligen Logs als String-Vector
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws KeineEintraegeVorhandenException
     */

    public Vector<String> printLog(String fileName, int daysInPast)throws ParseException, FileNotFoundException, KeineEintraegeVorhandenException{
        // das Datumsformat nach dem die Datumseinträge geparst werden sollen
        SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz':'");
        Calendar cal = new GregorianCalendar();
        // das heutige Datum
        Date today = new Date();
        // der ganze Log als String-Vector
        Vector<String> log = new Vector<String>();
        // alle Datumseinträge als Datum geparst
        Vector<Date> convertedDate = new Vector<Date>();
        // die gewünschten Strings in einem Vector, die ausgegeben werden sollen
        Vector<String> string = new Vector<String>();

        // wie viele Tage der Log zurückliegen soll vom heutigen Datum abgezogen

        cal.setTime(today);

        if(daysInPast > 0){
            cal.add(Calendar.DAY_OF_YEAR, -daysInPast);
        } else {
            cal.add(Calendar.DAY_OF_YEAR, daysInPast);
        }

        /*
         * scannen der Datei aus der ausgelesen werden soll, nach jedem Zeilenumbruch wird die Zeile in einen log-Vector
         * gespeichert
         */

        Scanner filescan = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))).useDelimiter("\n");

        while (filescan.hasNext()){
            log.add(filescan.next());
        }

        filescan.close();

        /*
         * alle Datumseinträge als Datums-Objekte geparst - jede dritte Zeile aus der gewählten Datei aus der ausgelesen
         * werden soll entspricht dabei einem Datumseintrag, vom Index 0 angefangen
         */

        for (int i = 0; i < log.size(); i+=3){
            convertedDate.add(formatter.parse(log.elementAt(i)));
        }

        /*
         * wenn die Einträge im gewünschten Datums-Bereich liegen, wird zuerst das Datum an der Stelle i hinzugefügt und
         * dann der dazugehörige Text, der der (i*3+1)-ten Stelle im log-Vector entspricht
         */

        for(int i = 0; i < convertedDate.size(); i++){
            if(convertedDate.elementAt(i).after(cal.getTime()) || convertedDate.elementAt(i).equals(cal.getTime())){
                string.add(formatter.format(convertedDate.elementAt(i)) + "\n" + log.elementAt(i * 3 + 1) + "\n");
            }
        }

        /*
         * wenn der String-Vector, der zurückgegeben werden soll, leer ist, soll eine KeineEintraegeVorhandenException
         * geworfen werden
         */

        if (!string.isEmpty()){
            return string;
        } else {
            throw new KeineEintraegeVorhandenException();
        }
    }

    /**
     * Methode, die den gesamten Log eines/einer Artikels || Kunden || Mitarbeiters || Rechnug lesbar wiedergibt.
     *
     * @return - Rechungs-Log als String
     * @throws FileNotFoundException
     */

    public String printLog(String fileName) throws FileNotFoundException, KeineEintraegeVorhandenException{

        /*
         * scannen der Datei aus der ausgelesen werden soll, wenn das Ende der Datei ("\\A") erreicht wurde, soll dieser
         * String zurückgegeben werden
         */

        String string = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))).useDelimiter("\\A").next();

        /*
         * wenn der String, der zurückgegeben werden soll, leer ist, soll eine KeineEintraegeVorhandenException
         * geworfen werden
         */

        if(!string.isEmpty()){
            return string;
        } else {
            throw new KeineEintraegeVorhandenException();
        }
    }
}
