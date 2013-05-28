package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 28.05.13
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
public class ArtikelExestierBereitsException extends Exception {

    public ArtikelExestierBereitsException(String name) {

        super("Der Artikel " + name + " existiert bereits!");

    }

}
