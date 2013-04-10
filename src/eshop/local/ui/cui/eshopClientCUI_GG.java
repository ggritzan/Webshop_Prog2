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
import java.util.HashMap;
import eshop.local.domain.ArtikelVerwaltung;
import eshop.local.valueobjects.Artikel;


public class eshopClientCUI_GG {

    private ArtikelVerwaltung eShop;
    private BufferedReader in;


    public eshopClientCUI_GG() {

        // erzeug eine neue Artikelvberwaltung für den eShop
        eShop = new ArtikelVerwaltung();

        // Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private void gibMenueAus() {
        System.out.print("Befehle: \n  Artikel hinzufuegen: 'h'");
        System.out.print("         \n  Artikel ausgeben:  'a'");
        System.out.print("         \n  Artikel suchen:  'f'");
        System.out.print("         \n  Daten sichern:  's'");
        System.out.println("         \n  Beenden:        'q'");
        System.out.print("> "); // Prompt
        System.out.flush(); // ohne NL ausgeben
    }

    private String liesEingabe()  {
        // einlesen von Konsole
        return in.readLine();
    }

    private void verarbeiteEingabe(String line)  {

        // Eingabe bearbeiten:
        if (line.equals("h")) {
            // lese die notwendigen Parameter, einzeln pro Zeile
            System.out.print("Artikel Bezeichnung > ");
            String aBez = liesEingabe();
            System.out.print("Artikel Preis  > ");
            double aPreis = liesEingabe();
            boolean ok = eShop.ArtikelHinzufuegen(aBez,aPreis);

            if (ok)
                System.out.println("Einfügen ok");
            else
                System.out.println("Fehler beim Einfügen");
        }
        /*
        else if (line.equals("a")) {
            Vector liste = bib.gibAlleBuecher();
            gibBuecherlisteAus(liste);
        }
        else if (line.equals("f")) {
            System.out.print("Buchtitel  > ");
            String titel = liesEingabe();
            Vector liste = bib.sucheNachTitel(titel);
            gibBuecherlisteAus(liste);
        }
        else if (line.equals("s")) {
            bib.schreibeBuecher();
        }

        */
    }



    // Main
    public static void main (String[] args)   {



        }




}
