/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 27.03.13
 * Time: 07:17
 * To change this template use File | Settings | File Templates.
 */

package eshop.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Iterator;
import java.util.Vector;




import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Adresse;
import eshop.local.valueobjects.Mitarbeiter;




public class eshopClientCUI {

    // Main
    public static void main (String[] args)   {
            Artikel art1 = new Artikel("Maus",1,2.50);
            System.out.print("Artiekl 1:  " + art1.getBezeichnung());

            Adresse ar1 = new Adresse(2345, "Hans", "Rumsdibums", "Komikerweg", "27356", "Albernhausen", "0414183479", "HansRumsdibums@yahoo.de");

            System.out.println("Bestand " + art1.getBestand());
            Mitarbeiter m1 = new Mitarbeiter("Hans", 1);
            m1.bestandErhoehen(art1, 5);
            System.out.println("Neuer Bestand " + art1.getBestand());
            System.out.println("Preis "+ art1.getPreis());
            m1.preisAendern(art1, 7.55);
            System.out.println("Neuer Preis " + art1.getPreis());
            Artikel art2 = m1.neuerArtikel("Tastertur",2,10.99,6);
            System.out.println("Artikel 2: " + art2.getBezeichnung());
            art2.printArtikel();


        }




}
