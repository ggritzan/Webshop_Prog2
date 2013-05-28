package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 28.05.13
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
public class ArtikelExestiertNichtException extends Exception {

    public ArtikelExestiertNichtException(int artNr) {

        super("Der Artikel mit der Nummer " + artNr + " existiert nicht !");

    }
}




