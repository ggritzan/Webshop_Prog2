/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 27.03.13
 * Time: 07:17
 * To change this template use File | Settings | File Templates.
 */

package eshop.local.ui.cui;


import eshop.local.domain.EShopVerwaltung;
import eshop.local.valueobjects.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class EshopClientCUI {

    private EShopVerwaltung eShopVerwaltung;
    private BufferedReader in;
    boolean istMitarbeiter = false;
    boolean istKunde = false;


    // Konstruktor
    public EshopClientCUI(String datei) throws IOException, ClassNotFoundException {
        // erzeugt eine eShopVerwaltung
        eShopVerwaltung = new EShopVerwaltung(datei);
        // Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
        in = new BufferedReader(new InputStreamReader(System.in));
    }

// Methoden

    private String liesEingabe() throws IOException {
        // einlesen von der Konsole
        return in.readLine();
    }

    // Menues
    private void gibMainMenue() throws IOException {
        System.out.println("Willkommen im E-Shop!");
        System.out.print("Befehle: \n  Fortfahren zum Login: 'l'");
        System.out.print("         \n  Neue Kundenregistrierung: 'k'");
        System.out.println("       \n  Programm beenden 'q'");
    }

    private void gibMitarbeiterMenueAus() {
        System.out.print("Befehle: \n  Mitarbeiter hinzufuegen: 'h'");
        System.out.print("         \n  Mitarbeiter löschen: 'l");
        System.out.print("         \n  Mitarbeiter ausgeben:  'a'");
        System.out.print("Befehle: \n  Kunde hinzufuegen: 'p'");
        System.out.print("         \n  Kunde löschen: 'e");
        System.out.print("         \n  Kunden ausgeben:  'u'");
        System.out.print("         \n  Daten sichern:  's'");
        System.out.print("         \n  zurück ins Hauptmenue: 'm'");
        System.out.print("> "); // Prompt
        System.out.flush(); // ohne NL ausgeben
    }

    private void gibKundenMenueAus() {
        System.out.print("         \n  Artikel in den Warenkorb legen:  'w'");
        System.out.print("         \n  Artikel aus dem Warenkorb löschen:  'l'");
        System.out.print("         \n  Warenkorb anzeigen lassen:  'a'");
        System.out.print("         \n  Artikel aus dem Warenkorb bestellen:  'b'");
        System.out.print("         \n  zurück ins Hauptmenue: 'o'");
        System.out.print("> "); // Prompt
        System.out.flush(); // ohne NL ausgeben
    }

    // Methoden
    private char verarbeiteMainEingabe(String line) throws IOException {

        // Eingabe bearbeiten:
        if (line.equals("l")) {
            //für Login
            return 'l';

        } else if (line.equals("k")) {
            //für Kundenregistrierung
            return 'k';

        }

        return 'q';

    }

    private void verarbeiteLogin() throws IOException {
        System.out.println("Benutzernamen:");
        String bName = liesEingabe();
        System.out.println("Passwort:");
        String bPasswort = liesEingabe();
        if (eShopVerwaltung.findeMitarbeiter(bName, bPasswort)) {
            System.out.println("Ihr Login war erfolgreich.");
            System.out.println("Willkommen Mitarbeiter");
            MitarbeiterEingabe(eShopVerwaltung.getMnr(bName));


        } else if (eShopVerwaltung.findeKunden(bName, bPasswort)) {
            System.out.println("Ihr Login war erfolgreich.");
            System.out.println("Willkommen Kunde");
            KundenEingabe(eShopVerwaltung.getKnr(bName));



        } else {
            System.out.println("Ihr Login war leider nicht erfolgreich.");


        }
    }

    private char verarbeiteKundenEingabe(String line) throws IOException {
        // Eingabe bearbeiten:
        if (line.equals("w")) {
            //Artikel in den Warenkorb packen
            return 'w';

        } else if (line.equals("l")) {
            //Artikel aus dem Warenkorb löschen
            return 'l';

        } else if (line.equals("a")) {
            //Artikel aus dem Warenkorb anzeigen lassen
            return 'a';

        } else if (line.equals("b")) {
            //Artikel aus dem Warenkorb bestellen
            return 'b';

        } else if (line.equals("o")) {
            //Ausloggen
            return 'o';
        }

        return 'o';
    }

    private void KundenEingabe(int kNr) throws IOException {
        // Variable für Eingaben von der Konsole
        String input = "";
        do {

            try {

                gibKundenMenueAus();
                input = liesEingabe();
                char var = verarbeiteKundenEingabe(input);

                if (var == 'w') {
                    boolean ok = false;

                    try {

                        System.out.println(eShopVerwaltung.gibAlleArtikel());
                        System.out.print("Artikelnummer: > ");
                        String aNr = liesEingabe();
                        int aNrInt = Integer.parseInt(aNr);
                        System.out.print("Menge: > ");
                        String menge = liesEingabe();
                        int mengeInt = Integer.parseInt(menge);
                        Artikel a = eShopVerwaltung.getArtikel(aNrInt);
                        System.out.print("" + a );
                        ok = eShopVerwaltung.inWarenkorbLegen(a, kNr);
                    } catch (NumberFormatException e) {

                    }

                    if (ok) {
                        System.out.println("Artikel wurde in den Warenkorb gelegt !");
                    } else {
                        System.out.println("Beim einfügen des Artikels in den Warenkorb ist ein Fehler aufgetreten !");
                    }


                }


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } while (!input.equals("o"));



    }

    private void MitarbeiterEingabe(int mNr) throws IOException {
        // Variable für Eingaben von der Konsole
        String input = "";
        do {

            try {

                gibMitarbeiterMenueAus();
                input = liesEingabe();
                char var = verarbeiteKundenEingabe(input);







            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } while (!input.equals("o"));



    }

    private void run() {

        // Variable für Eingaben von der Konsole
        String input = "";

        //Hauptschleife
        do {

            try {

                gibMainMenue();
                input = liesEingabe();
                char var = verarbeiteMainEingabe(input);

                if (var == 'l') {
                    //Loginmenue

                    try {
                        verarbeiteLogin();

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();


                    }


                }


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } while (!input.equals("q"));

    }


// Main


    public static void main(String[] args) throws ClassNotFoundException {

        EshopClientCUI cui;
        try {
            cui = new EshopClientCUI("EShop");
            cui.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

