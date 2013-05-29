package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 29.05.13
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */

public class ArtikelBestandNegativException extends Exception {

    public ArtikelBestandNegativException() {

        super("Es wurde versucht den Bestand des Artikels ins negative zu aendern!");

    }
}