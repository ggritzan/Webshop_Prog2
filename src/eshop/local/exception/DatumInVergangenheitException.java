package eshop.local.exception;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 12.04.13
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
public class DatumInVergangenheitException extends Exception {
    public DatumInVergangenheitException(String d){
        super("Das Datum " + d + " liegt in der Verganheit.");
    }
}
