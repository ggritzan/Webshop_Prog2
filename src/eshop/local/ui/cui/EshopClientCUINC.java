package eshop.local.ui.cui.eshop;

import eshop.local.exception.MonatExistiertNichtException;
import eshop.local.valueobjects.Lieferadresse;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 11.04.13
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public class EshopClientCUINC {

    public static void main(String[] args) {
        Lieferadresse l = new Lieferadresse("v","n","s","p","o");
        try {
            l.anderesLieferdatum(2013,4,13);
        } catch (Exception e){
            System.out.println("Fehler:");
            System.out.println(e.getMessage());
        }

        Vector<String> vc=new Vector<String>();

        //<E> it is return type of Vector

        //     add vector elements
        vc.add("Vector Element 1");
        vc.add("Vector Element 2");
        vc.add("Vector Element 3");
        vc.add("Vector Element 4");
        vc.add("Vector Element 5");

        // remove Vector element by index number
        vc.remove(3);

        // remove Vector element by Object value
        vc.remove("Vector Element 5");

        // get elements of Vector
        for(int i=0;i<vc.size();i++)
        {
            System.out.println("Vector Element "+i+" :"+vc.get(i));
        }
    }
}
