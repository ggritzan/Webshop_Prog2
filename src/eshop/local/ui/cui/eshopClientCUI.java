/*
/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 27.03.13
 * Time: 07:17
 * To change this template use File | Settings | File Templates.


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

    private void loginAusgabe() {
        System.out.print("Befehle: \n  Artikelmenue: 'a'");
        System.out.println("         \n  Beenden:        'q'");
        System.out.print("> "); // Prompt
        System.out.flush(); // ohne NL ausgeben
    }

    private char login() throws IOException {


        String loginName = liesEingabe();
        String login
        // Eingabe bearbeiten:
        if (loginName.equals("a")) {
            //für Artikel
            return '';

        } else if (line.equals("k")) {
            //für Kunden
            return 'k';

        } else if (line.equals("r")) {
            // für Rechnungen
            return 'r';
        }

        return 'm';
    }


    private void run() {

        // Variable für Eingaben von der Konsole
        String input = "";

        //Hauptschleife
        do {

            do {
                login();
            } while (istKunde != true | istMitarbeiter != true);


        } while (!input.equals("q"))
    }


    // Main
    public static void main(String[] args) {

        public static void main (String[]args)throws ClassNotFoundException {

            EshopClientCUI cui;
            try {
                cui = new EshopClientCUI("EShop");
                cui.run();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}

*/