/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 27.03.13
 * Time: 07:17
 * To change this template use File | Settings | File Templates.
 */

package eshop.local.ui.cui;




import eshop.local.valueobjects.*;


public class eshopClientCUI {

    // Main
    public static void main (String[] args)   {
            Artikel art1 = new Artikel("Maus","Eine ganz normale Maus",2.50);
            System.out.print("Artikel 1:  " + art1.getBeschreibung());
            System.out.println("Bestand " + art1.getBestand());
            Adresse ad1 = new Adresse("a","b","Straße","0315","Ort");
            Mitarbeiter m1 = new Mitarbeiter("a","b","Test","test","abmail","0011223",ad1);

            Kunde k1 = new Kunde("Peter","Nana","PNa","test","mail","99989",ad1);
            m1.bestandErhoehen(art1, 5);
            System.out.println("Neuer Bestand " + art1.getBestand());
            System.out.println("Preis "+ art1.getPreis());
            m1.preisAendern(art1, 7.55);
            k1.inWarenkorbLegen(art1);
            System.out.println(k1.getNummer());
            System.out.println(m1.getmNr());
    }


}
