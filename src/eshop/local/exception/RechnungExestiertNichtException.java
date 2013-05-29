package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 29.05.13
 * Time: 11:12
 * To change this template use File | Settings | File Templates.
 */

public class RechnungExestiertNichtException extends Exception {

    public RechnungExestiertNichtException(int rNr) {

        super("Die Rechnung mit der Nummer " + rNr + " existiert nicht !");

    }

    public RechnungExestiertNichtException() {

        super("Die Rechnung mit der Nummer existiert nicht !");

    }
}


