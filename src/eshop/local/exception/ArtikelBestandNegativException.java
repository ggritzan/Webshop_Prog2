package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 28.05.13
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */

public class ArtikelBestandNegativException extends Exception {

    public ArtikelBestandNegativException() {

        super("Es wurde versucht den Bestand ins negative zu aendern!");

    }

}