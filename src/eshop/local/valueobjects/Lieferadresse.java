package eshop.local.valueobjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import eshop.local.exception.MonatExistiertNichtException;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 11.04.13
 * Time: 03:13
 * To change this template use File | Settings | File Templates.
 */
public class Lieferadresse extends Adresse{


    public void anderesLieferdatum(int yy, int MM, int dd)throws MonatExistiertNichtException{
        if(MM < 1 || MM > 12) {
            throw new MonatExistiertNichtException(MM);
        }
        SimpleDateFormat ft = new SimpleDateFormat("E dd.MM.yy");
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2000 + yy, MM-1, dd);
        Date d = cal.getTime();
        Date heute = new Date();
        System.out.println(ft.format(d));
    }


    public Lieferadresse(String vorname, String nachname, String straße, String plz, String ort) {
        super(vorname, nachname, straße, plz, ort);
    }
}
