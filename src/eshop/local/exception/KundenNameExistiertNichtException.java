package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 28.05.13
 * Time: 17:06
 * To change this template use File | Settings | File Templates.
 */
public class KundenNameExistiertNichtException extends Exception {
    public KundenNameExistiertNichtException(String benutzername){
        super("Der Kundenname " + benutzername + " existiert nicht.");
    }
}
