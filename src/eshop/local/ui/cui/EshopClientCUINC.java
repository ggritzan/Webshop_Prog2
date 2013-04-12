package eshop.local.ui.cui.eshop;

import eshop.local.exception.MonatExistiertNichtException;
import eshop.local.valueobjects.Lieferadresse;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 11.04.13
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public class EshopClientCUINC {

    public static void main(String[] args) {
        String v = "v";
        String n = "n";
        String s = "s";
        String p = "p";
        String o = "o";
        Lieferadresse l = new Lieferadresse(v,n,s,p,o);
        try {
            l.anderesLieferdatum(13,13,11);
        } catch (Exception e){
            System.out.println("Fehler:");
            System.out.println(e.getMessage());
        }
    }
}
