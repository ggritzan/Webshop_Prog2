package eshop.local.exception;

import eshop.local.valueobjects.Artikel;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 10.04.13
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class ArtikelExestiertBereitsException extends Exception {


    public BuchExistiertBereitsException(Artikel artikel) {
        super("Buch mit Titel " + buch.getTitel() + " und Nummer " + buch.getNummer()
                + " existiert bereits" + zusatzMsg);
    }

 }






