package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 29.05.13
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */

public class RechnungKeineVorhandenException extends Exception {

    public RechnungKeineVorhandenException() {

        super("Es exestiert keine Rechnung!");

    }
}

