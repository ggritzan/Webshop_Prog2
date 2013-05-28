package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 28.05.13
 * Time: 16:59
 * To change this template use File | Settings | File Templates.
 */
public class KundenNummerExistiertNichtException extends Exception{
    public KundenNummerExistiertNichtException(int kundenNr){
        super("Die Kundennummer " + kundenNr + " existiert nicht.");
    }
}
