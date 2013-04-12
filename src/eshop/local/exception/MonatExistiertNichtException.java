package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 12.04.13
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */
public class MonatExistiertNichtException extends Exception {
    public MonatExistiertNichtException(int MM) {
        super("Der Monat " + MM +" existiert nicht.");
    }
}
