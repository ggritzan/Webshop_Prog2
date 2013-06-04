package eshop.local.persistence;

import eshop.local.exception.KennNummerExistiertNichtException;

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

    public void writeLog (File dateiName, String text) throws IOException {
        BufferedWriter schreibeStrom = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dateiName, true)));
        schreibeStrom.write(text);
        schreibeStrom.newLine();
        schreibeStrom.newLine();
        schreibeStrom.close();
    }

    public Vector<String> printLog (String fileName, int daysInPast, String kennNr) throws FileNotFoundException, ParseException, KennNummerExistiertNichtException{

        SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'um' HH:mm:ss zzz':'");
        Calendar cal = new GregorianCalendar();
        Date today = new Date();
        Vector<Date> convertedDate = new Vector<Date>();
        Vector<String[]> eintraege = new Vector<String[]>();
        Vector<String> string = new Vector<String>();

        cal.setTime(today);

        if(daysInPast > 0){
            cal.add(Calendar.DAY_OF_YEAR, -daysInPast);
        } else {
            cal.add(Calendar.DAY_OF_YEAR, daysInPast);
        }

        Scanner filescan = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))).useDelimiter("\n");
        Vector<String> log = new Vector<String>();
        while (filescan.hasNext()){
            log.add(filescan.next());
        }

        filescan.close();

        // alle Datumseingaben
        for (int i = 0; i < log.size(); i++){
            if (i % 3 == 0){
                convertedDate.add((Date) formatter.parse(log.elementAt(i)));
            }
        }

        // alle Einträge

        for (int i = 0; i < log.size(); i++){
            if (i % 3 == 1){
                String[] splitResult = log.elementAt(i).split(" ");
                eintraege.add(splitResult);
            }
        }

        for (int i = 0; i < eintraege.size(); i++){
            for (int j = 0; j < eintraege.elementAt(i).length; j++){
                if (fileName.equals("Eshop_ArtikelLog.txt") && eintraege.elementAt(i)[j].equals("Artikelnummer") || fileName.equals("Eshop_KundenLog.txt") && eintraege.elementAt(i)[j].equals("Kundennummer") || fileName.equals("Eshop_MitarbeiterLog.txt") && eintraege.elementAt(i)[j].equals("Mitarbeiternummer") || fileName.equals("Eshop_RechnungsLog.txt") && eintraege.elementAt(i)[j].equals("Rechnungsnummer")){
                    if(eintraege.elementAt(i)[j+1].equals(kennNr) && convertedDate.elementAt(i).after(cal.getTime()) || eintraege.elementAt(i)[j+1].equals(kennNr) && convertedDate.elementAt(i).equals(cal.getTime())){
                        string.add(formatter.format(convertedDate.elementAt(i)) + "\n" + log.elementAt(i * 3 + 1) + "\n");
                    }
                }
            }
        }

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

    public String printLog(String fileName) throws FileNotFoundException{
        return new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))).useDelimiter("\\A").next();
    }
}
