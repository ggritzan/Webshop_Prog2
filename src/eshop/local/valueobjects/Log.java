package eshop.local.valueobjects;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 11.05.13
 * Time: 13:20
 * To change this template use File | Settings | File Templates.
 */
public class Log {

    //Attribute zur Beschreibung eines Log-Objektes

    private Date dNow;
    private SimpleDateFormat ft;

    //Konstruktoren

    public Log(Date dNow, SimpleDateFormat ft){
        this.dNow = dNow;
        this.ft = ft;
    }

    public Log(Log l){
        this.dNow = l.dNow;
        this.ft = l.ft;
    }

    //Getter

    public Date getdNow(){
        return dNow;
    }

    public SimpleDateFormat getFt(){
        return ft;
    }

}
