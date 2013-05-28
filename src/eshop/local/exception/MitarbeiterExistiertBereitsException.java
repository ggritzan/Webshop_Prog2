package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ninadoge
 * Date: 28.05.13
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterExistiertBereitsException extends Exception {
    public MitarbeiterExistiertBereitsException(String name){
        super("Der Mitarbeiter "+name+" existiert bereits, bitte wählen Sie einen anderen Benutzernamen!");
    }
}
