package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 04.06.13
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class KeineEintraegeVorhandenException extends Exception{

    public KeineEintraegeVorhandenException(){
        super("Es sind keine Einträge vorhanden");
    }

}
