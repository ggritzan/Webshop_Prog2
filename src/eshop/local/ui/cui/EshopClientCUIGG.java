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

import com.sun.corba.se.impl.logging.ORBUtilSystemException;
import eshop.local.domain.ArtikelVerwaltung;
import eshop.local.domain.EShopVerwaltung;
import eshop.local.valueobjects.Artikel;


public class EshopClientCUIGG {

    private ArtikelVerwaltung eShop;
    private EShopVerwaltung eShopVerwaltung;
    private BufferedReader in;


    public EshopClientCUIGG(String datei) throws IOException {

        // erzeug eine neue Artikelverwaltung für den eShop
        eShop = new ArtikelVerwaltung();
        eShopVerwaltung = new EShopVerwaltung(datei);

        // Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private void gibMenueAus() {
        System.out.print("Befehle: \n  Artikel hinzufuegen: 'h'");
        System.out.print("         \n  Bestand ändern: 'b'");
        System.out.print("         \n  Artikel ausgeben:  'a'");
        System.out.print("         \n  Artikel suchen:  'f'");
        System.out.print("         \n  Daten sichern:  's'");
        System.out.println("         \n  Beenden:        'q'");
        System.out.print("> "); // Prompt
        System.out.flush(); // ohne NL ausgeben
    }

    private String liesEingabe() throws IOException {
        // einlesen von Konsole
        return in.readLine();
    }

    private void verarbeiteEingabe(String line) throws IOException {

        // Eingabe bearbeiten:
        if (line.equals("h")) {
            // lese die notwendigen Parameter, einzeln pro Zeile
            System.out.print("Artikel Name > ");
            String aName = liesEingabe();
            System.out.print("Artikel Beschreibung > ");
            String aBeschreibung = liesEingabe();
            System.out.print("Artikel Preis  > ");
            String aPreisEingabe = liesEingabe();
            float aPreis = Float.parseFloat(aPreisEingabe);
            boolean ok = eShopVerwaltung.fuegeArtikelEin(aName, aBeschreibung, aPreis);

            if (ok)
                System.out.println("Einfügen ok");
            else
                System.out.println("Artikel ist bereits vorhanden !");
        }

        else if (line.equals("b")) {
            System.out.println(eShopVerwaltung.gibAlleArtikel());
            System.out.print("Artikelnummer: > ");
            String aNr = liesEingabe();
            int aNrInt = Integer.parseInt(aNr);
            System.out.print("Bestand > ");
            String aBestand = liesEingabe();
            int aBestandInt = Integer.parseInt(aBestand);
            boolean ok = eShopVerwaltung.setBestand(aNrInt,aBestandInt);


            if (ok)
                System.out.println("Bestand wurde geändert !");
            else
                System.out.println("Bestand konnte nicht geändert werden !");
        }

        else if (line.equals("a")) {
           System.out.println(eShopVerwaltung.gibAlleArtikel());
        }


        else if (line.equals("f")) {
            System.out.print("Artikelname  > ");
            String titel = liesEingabe();
            System.out.println(eShopVerwaltung.sucheNachName(titel));
        }


        else if (line.equals("s")) {
            eShopVerwaltung.schreibeArtikel();
        }


    }

    public void run() {
        // Variable für Eingaben von der Konsole
        String input = "";

        // Hauptschleife der Benutzungsschnittstelle
        do {
            gibMenueAus();
            try {
                input = liesEingabe();
                verarbeiteEingabe(input);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
         } while (!input.equals("q"));
    }



    // Main
    public static void main (String[] args)   {

        EshopClientCUIGG cui;
            try {
                cui = new EshopClientCUIGG("EShop");
                 cui.run();
                } catch (IOException e) {
                     e.printStackTrace();
            }

     }




 }


