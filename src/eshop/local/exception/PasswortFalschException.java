package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 28.05.13
 * Time: 17:45
 * To change this template use File | Settings | File Templates.
 */
public class PasswortFalschException extends Exception {
    public PasswortFalschException(){
        super("Sie haben ein falsches Passwort eingegeben.");
    }
}
