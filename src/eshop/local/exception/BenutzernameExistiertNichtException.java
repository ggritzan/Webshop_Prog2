package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 28.05.13
 * Time: 17:51
 * To change this template use File | Settings | File Templates.
 */
public class BenutzernameExistiertNichtException extends Exception{
    public BenutzernameExistiertNichtException(String benutzername){
        super("Der Benutzername " + benutzername + " existiert nicht.");
    }
}
