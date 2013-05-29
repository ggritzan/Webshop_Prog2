package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 29.05.13
 * Time: 12:44
 * To change this template use File | Settings | File Templates.
 */


import eshop.local.valueobjects.Artikel;





/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 29.05.13
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
public class ArtikelBestandZuNiedrigException extends Exception {

    public ArtikelBestandZuNiedrigException(Artikel artikel) {

        super("Der Bestand des Artikels " + artikel.getName() + " ist zu niedrig!");

    }

}

