package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 04.06.13
 * Time: 00:12
 * To change this template use File | Settings | File Templates.
 */
public class KennNummerExistiertNichtException extends Exception {

    public KennNummerExistiertNichtException(String bezeichnung, String kennNr){
        super("Es sind keine Einträge mit der " + bezeichnung + " " + kennNr + " vorhanden.");
    }
}
