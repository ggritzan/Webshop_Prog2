package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 28.05.13
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 */
public class BenutzernameExistiertBereitsException extends Exception{
    public BenutzernameExistiertBereitsException(String benutzername){
        super("Der Benutzername " + benutzername + " existiert bereits.");
    }
}
