package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 28.05.13
 * Time: 17:07
 * To change this template use File | Settings | File Templates.
 */


public class ArtikelBestellteMengeNegativException extends Exception {

    public ArtikelBestellteMengeNegativException() {

        super("Es kann keine negative Menge bestellt werden! ");

    }

}
