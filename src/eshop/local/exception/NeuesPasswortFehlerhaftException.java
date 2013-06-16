package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ninadoge
 * Date: 16.06.13
 * Time: 11:57
 * To change this template use File | Settings | File Templates.
 */
public class NeuesPasswortFehlerhaftException extends Exception {
    public NeuesPasswortFehlerhaftException() {
        super("Ihr neues Passwort konnte nicht angelegt werden, da die Bestätigung nicht korrekt eingegeben wurde.");
    }
}
