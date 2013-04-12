package eshop.local.valueobjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import eshop.local.exception.MonatExistiertNichtException;
import eshop.local.exception.DatumExistiertNichtException;
import eshop.local.exception.DatumInVergangenheitException;
import eshop.local.exception.UnterEinerWocheException;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 11.04.13
 * Time: 03:13
 * To change this template use File | Settings | File Templates.
 */
public class Lieferadresse extends Adresse{


    public void anderesLieferdatum(int yyyy, int MM, int dd)throws MonatExistiertNichtException,
    DatumExistiertNichtException, DatumInVergangenheitException, UnterEinerWocheException{
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(yyyy, MM-1, dd);
        Date d = cal.getTime();
        Date heute = new Date();
        if(MM < 1 || MM > 12) {
            throw new MonatExistiertNichtException(MM);
        } else if((MM == 4 || MM == 6 || MM == 9 || MM == 11) && (dd < 1 || dd > 30)) {
            throw new DatumExistiertNichtException(dd, MM, yyyy);
        } else if(MM == 2 && yyyy % 4 == 0 && (dd < 1 || dd > 29) || MM == 2 && yyyy % 4 != 0 && (dd < 1 || dd > 28)){
            throw new DatumExistiertNichtException(dd, MM, yyyy);
        } else if(dd < 1 || dd > 31) {
            throw new DatumExistiertNichtException(dd, MM, yyyy);
        } else if(d.before(heute)){
            throw new DatumInVergangenheitException(ft.format(d));
        } else if (d.getTime() - heute.getTime() < 604875149){
            throw new UnterEinerWocheException();
        } else {
            System.out.println("Neues Lieferdatum: " + ft.format(d));
        }
    }


    public Lieferadresse(String vorname, String nachname, String straße, String plz, String ort) {
        super(vorname, nachname, straße, plz, ort);
    }
}
