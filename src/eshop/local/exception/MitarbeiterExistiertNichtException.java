package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ninadoge
 * Date: 28.05.13
 * Time: 17:38
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterExistiertNichtException extends Exception {
    public MitarbeiterExistiertNichtException(){
        super("Der angefragte Mitarbeiter existiert nicht!");
    }
}
