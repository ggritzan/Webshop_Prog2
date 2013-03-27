/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 27.03.13
 * Time: 07:17
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.ui.cui.eshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Vector;

// valueobjects


import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Adresse;
import eshop.local.valueobjects.Mitarbeiter;

import javax.net.ssl.SSLEngineResult;


public class eshopClientCUI {

        public static void main (String[] args)   {
            Artikel art1 = new Artikel("Maus",1,2.50);
            System.out.print("Test " + art1.getBezeichnung());

            Adresse ar1 = new Adresse(2345, "Hans", "Rumsdibums", "Komikerweg", "27356", "Albernhausen", "0414183479", "HansRumsdibums@yahoo.de");

            System.out.println("Bestand " + art1.getBestand());
            Mitarbeiter M1 = new Mitarbeiter("Hans", 1);
            M1.bestandErhoehen(art1, 5);
            System.out.println("Neuer Bestand " + art1.getBestand());
            System.out.println("Preis "+ art1.getPreis());
            M1.preisAendern(art1, 7.55);
            System.out.println("Neuer Preis " + art1.getPreis());
        }




}
