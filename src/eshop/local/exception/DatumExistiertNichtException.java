package eshop.local.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 12.04.13
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class DatumExistiertNichtException extends Exception {
    public DatumExistiertNichtException(int dd, int MM, int yyyy){
        super("Das Datum " + dd + "." + MM + "." + yyyy + " existiert nicht.");
    }
}
