package eshop.local.exception;

import eshop.local.valueobjects.MassengutArtikel;

/**
 * Created with IntelliJ IDEA.
 * User: ninadoge
 * Date: 12.06.13
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
public class BestellteMengeEntsprichtNichtderPackungsgroesseException extends  Exception {
    public BestellteMengeEntsprichtNichtderPackungsgroesseException (MassengutArtikel mA, int menge) {
        super("Die Packungsgroesse des Massengutartikels " + mA.getName() + " beträgt " + mA.getPackungsgroesse() + " sie haben versucht " + menge + " Stück zu kaufen.");
    }
}
