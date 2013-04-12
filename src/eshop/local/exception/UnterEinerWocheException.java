package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 12.04.13
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
public class UnterEinerWocheException extends Exception {
    public UnterEinerWocheException(){
        super("Ein anderes Lieferdatum sollte mindestens eine Woche nach dem Bestelldatum liegen, damit eine pünktliche Auslieferung am genauen Datum gewährleistet ist.");
    }
}
