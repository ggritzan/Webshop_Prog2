package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ninadoge
 * Date: 29.05.13
 * Time: 11:01
 * To change this template use File | Settings | File Templates.
 */
public class LeereEingabeException extends Exception {
    public LeereEingabeException(){
        super("Leere Eingaben sind ungültig!");
    }
    public LeereEingabeException(String para) {
        super("Bitte gültigen Parameter bei " + para + " eingeben");
    }

}
