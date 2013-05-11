package eshop.local.valueobjects;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 11.05.13
 * Time: 13:20
 * To change this template use File | Settings | File Templates.
 */
public abstract class Log implements Serializable {

    private static final long serialVersionUID = -8336812990053287916L;

    //Attribute zur Beschreibung eines Log-Objektes

    private Date dNow;
    private SimpleDateFormat ft;

    //Konstruktoren

    public Log(Date dNow, SimpleDateFormat ft){
        this.dNow = dNow;
        this.ft = ft;
    }

    public Log(Log l){
        this.dNow = l.getdNow();
        this.ft = l.getFt();
    }

    //Getter

    public Date getdNow(){
        return dNow;
    }

    public SimpleDateFormat getFt(){
        return ft;
    }

}
