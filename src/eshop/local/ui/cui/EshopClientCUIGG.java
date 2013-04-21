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
import eshop.local.domain.KundenVerwaltung;
import eshop.local.valueobjects.Artikel;
import sun.org.mozilla.javascript.internal.ast.IfStatement;


public class EshopClientCUIGG {

    private ArtikelVerwaltung artVer;
    private KundenVerwaltung kunVer;
    private EShopVerwaltung eShopVerwaltung;
    private BufferedReader in;


    public EshopClientCUIGG(String datei) throws IOException, ClassNotFoundException {

        // erzeug eine neue Artikelverwaltung für den eShop
        artVer = new ArtikelVerwaltung();
        // erzeugt eine neue Kundenverwaltung für den eShop
        kunVer = new KundenVerwaltung();

        eShopVerwaltung = new EShopVerwaltung(datei);

        // Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private void gibmainMenue() {
        System.out.print("Befehle: \n  Artikelmenue: 'a'");
        System.out.print("         \n  Kundenmenue: 'k'");
        System.out.println("         \n  Beenden:        'q'");
        System.out.print("> "); // Prompt
        System.out.flush(); // ohne NL ausgeben
    }

    private void gibArtikelMenueAus() {
        System.out.print("Befehle: \n  Artikel hinzufuegen: 'h'");
        System.out.print("         \n  Bestand ändern: 'b'");
        System.out.print("         \n  Artikel löschen: 'l");
        System.out.print("         \n  Artikel ausgeben:  'a'");
        System.out.print("         \n  Artikel suchen:  'f'");
        System.out.print("         \n  Daten sichern:  's'");
        System.out.print("         \n  zurück ins Hauptmenue: 'm'");

        System.out.print("> "); // Prompt
        System.out.flush(); // ohne NL ausgeben
    }

    private void gibKundenMenueAus() {
        System.out.print("Befehle: \n  Kunde hinzufuegen: 'h'");
        System.out.print("         \n  Kunde löschen: 'l");
        System.out.print("         \n  Kunden ausgeben:  'a'");
        // System.out.print("         \n  Artikel suchen:  'f'");
        System.out.print("         \n  Daten sichern:  's'");
        System.out.print("         \n  zurück ins Hauptmenue: 'm'");
        System.out.print("> "); // Prompt
        System.out.flush(); // ohne NL ausgeben
    }

    private String liesEingabe() throws IOException {
        // einlesen von der Konsole
        return in.readLine();
    }

    private char verarbeiteMainEingabe(String line) throws IOException {

        // Eingabe bearbeiten:
        if (line.equals("a")) {

            return 'a';

        } else if (line.equals("k")) {

            return 'k';

        } else if (line.equals("s")) {
            eShopVerwaltung.schreibeArtikel();
            eShopVerwaltung.schreibeKunden();
        }

        return 'm';

    }

    private void verarbeiteArtikelEingabe(String line) throws IOException {

        // Eingabe bearbeiten:
        if (line.equals("h")) {
            // lese die notwendigen Parameter, einzeln pro Zeile
            boolean ok = false;
            try {
                System.out.print("Artikel Name > ");
                String aName = liesEingabe();
                System.out.print("Artikel Beschreibung > ");
                String aBeschreibung = liesEingabe();
                System.out.print("Artikel Preis  > ");
                String aPreisEingabe = liesEingabe();
                double aPreis = Double.parseDouble(aPreisEingabe);


                ok = eShopVerwaltung.fuegeArtikelEin(aName, aBeschreibung, aPreis);

            } catch (NumberFormatException e) {

            }


            if (ok)
                System.out.println("Einfügen ok");
            else
                System.out.println("Der Artikel konnte leider nicht angelegt werden !");


        } else if (line.equals("b")) {

            boolean ok = false;
            try {
                System.out.println(eShopVerwaltung.gibAlleArtikel());
                System.out.print("Artikelnummer: > ");
                String aNr = liesEingabe();
                int aNrInt = Integer.parseInt(aNr);
                System.out.print("Bestand > ");
                String aBestand = liesEingabe();
                int aBestandInt = Integer.parseInt(aBestand);
                ok = eShopVerwaltung.setBestand(aNrInt, aBestandInt);

            } catch (NumberFormatException e) {

            }


            if (ok)
                System.out.println("Bestand wurde geändert !");
            else
                System.out.println("Der Bestand konnte nicht geändert werden da ein Fehler aufgetreten ist !");

        } else if (line.equals("l")) {

            boolean ok = false;
            try {
                System.out.println(eShopVerwaltung.gibAlleArtikel());
                System.out.print("Artikelnummer: > ");
                String aNr = liesEingabe();
                int aNrInt = Integer.parseInt(aNr);
                ok = eShopVerwaltung.loescheArtikel(aNrInt);
            } catch (NumberFormatException e) {

            }

            if (ok)
                System.out.println("Artikel wurde geloescht !");
            else
                System.out.println("Beim löschen des Artikels ist ein Fehler aufgetreten !");


        } else if (line.equals("a")) {
            System.out.println(eShopVerwaltung.gibAlleArtikel());

        } else if (line.equals("f")) {

            System.out.print("Artikelname:  > ");
            String titel = liesEingabe();
            System.out.println(eShopVerwaltung.sucheNachName(titel));


        } else if (line.equals("s")) {
            eShopVerwaltung.schreibeArtikel();
        }


    }

    public void run() {
        // Variable für Eingaben von der Konsole
        String input = "";

        // Hauptschleife der Benutzungsschnittstelle
        do {

            try {
                gibmainMenue();
                input = liesEingabe();
                char var = verarbeiteMainEingabe(input);
                if (var == 'a') {
                    do {

                        try {
                            gibArtikelMenueAus();
                            input = liesEingabe();
                            verarbeiteArtikelEingabe(input);

                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();


                        }
                    } while (!input.equals("m"));

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();


            }
        } while (!input.equals("q"));
    }


// Main

    public static void main(String[] args) throws ClassNotFoundException {

        EshopClientCUIGG cui;
        try {
            cui = new EshopClientCUIGG("EShop");
            cui.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}


