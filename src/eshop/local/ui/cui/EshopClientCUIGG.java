package eshop.local.ui.cui;

import com.sun.xml.internal.bind.v2.TODO;
import eshop.local.domain.EShopVerwaltung;
import eshop.local.valueobjects.Adresse;
import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Kunde;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 11.04.13
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public class EshopClientCUIGG {
    private EShopVerwaltung eShopVerwaltung;
    private BufferedReader in;


    public EshopClientCUIGG(String datei) throws IOException, ClassNotFoundException {
        // erzeugt eine eShopVerwaltung
        eShopVerwaltung = new EShopVerwaltung(datei);
        // Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private void gibmainMenue() {
        System.out.print("Befehle: \n  Artikelmenue: 'a'");
        System.out.print("         \n  Kundenmenue: 'k'");
        System.out.print("         \n  Rechnungsmenue: 'r'");
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
        System.out.print("         \n  Artikel in den Warenkorb legen:  'w'");
        System.out.print("         \n  Daten sichern:  's'");
        System.out.print("         \n  zurück ins Hauptmenue: 'm'");
        System.out.print("> "); // Prompt
        System.out.flush(); // ohne NL ausgeben
    }

    private void gibRechnungsMenueAus() {
        System.out.print("Befehle: \n  Rechnung hinzufuegen: 'h'");
        System.out.print("         \n  Rechnung löschen: 'l");
        System.out.print("         \n  Rechnungen ausgeben:  'a'");
        System.out.print("         \n  Rechnung suchen:  'f'");
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
            //für Artikel
            return 'a';

        } else if (line.equals("k")) {
            //für Kunden
            return 'k';

        } else if (line.equals("r")) {
            // für Rechnungen
            return 'r';
        }

        return 'm';

    }

    private void verarbeiteArtikelEingabe(String line) throws IOException {

        // Eingabe bearbeiten:
        if (line.equals("h")) {
            //hinzufügen
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
            //bearbeiten
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
            //löschen
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
            //alle ausgeben
            System.out.println(eShopVerwaltung.gibAlleArtikel());

        } else if (line.equals("f")) {
            //suchen
            System.out.print("Artikelname:  > ");
            String titel = liesEingabe();
            System.out.println(eShopVerwaltung.sucheNachName(titel));


        } else if (line.equals("s")) {
            //sichern
            eShopVerwaltung.schreibeArtikel();
        }


    }

    private void verarbeiteKundenEingabe(String line) throws IOException {

        if (line.equals("h")) {
            //hinzufügen
            // lese die notwendigen Parameter, einzeln pro Zeile
            boolean ok = false;
            try {
                System.out.print("Vorname > ");
                String vorname = liesEingabe();
                System.out.print("Nachname > ");
                String nachname = liesEingabe();
                System.out.print("Benutzername > ");
                String benutzername = liesEingabe();
                System.out.print("Passwort  > ");
                String passwort = liesEingabe();
                System.out.print("E-mail > ");
                String email = liesEingabe();
                System.out.print("Telefon > ");
                String telefon = liesEingabe();
                System.out.print("Straße > ");
                String straße = liesEingabe();
                System.out.print("PLZ > ");
                String plz = liesEingabe();
                System.out.print("Ort > ");
                String ort = liesEingabe();
                Adresse adresse = new Adresse(vorname, nachname, straße, plz, ort);

                ok = eShopVerwaltung.fuegeKundeEin(vorname, nachname, benutzername, passwort, email, telefon, adresse);

            } catch (NumberFormatException e) {

            }
            if (ok)
                System.out.println("Einfügen ok");
            else
                System.out.println("Der Kunde konnte leider nicht angelegt werden !");
        } else if (line.equals("l")) {
            //löschen
            boolean ok = false;
            try {
                System.out.println(eShopVerwaltung.gibAlleKunden());
                System.out.print("Kundennummer: > ");
                String kNr = liesEingabe();
                int kNrInt = Integer.parseInt(kNr);
                ok = eShopVerwaltung.loescheKunde(kNrInt);
            } catch (NumberFormatException e) {

            }

            if (ok)
                System.out.println("Kunde wurde geloescht !");
            else
                System.out.println("Beim löschen des Kunden ist ein Fehler aufgetreten !");


        } else if (line.equals("a")) {
            //alle ausgeben
            System.out.println(eShopVerwaltung.gibAlleKunden());

        } else if (line.equals("s")) {
            //sichern
            eShopVerwaltung.schreibeKunden();
        } else if (line.equals("w")) {
            //artikel in warenkorb packen
            boolean ok = false;

            try {
                System.out.println(eShopVerwaltung.gibAlleKunden());
                System.out.print("Kundennummer: > ");
                String kNr = liesEingabe();
                int kNrInt = Integer.parseInt(kNr);
                System.out.println(eShopVerwaltung.gibAlleArtikel());
                System.out.print("Artikelnummer: > ");
                String aNr = liesEingabe();
                int aNrInt = Integer.parseInt(aNr);
                System.out.print("Menge: > ");
                String menge = liesEingabe();
                int mengeInt = Integer.parseInt(menge);
                Artikel a = eShopVerwaltung.getArtikel(aNrInt);
                System.out.print("" + a );
                ok = eShopVerwaltung.inWarenkorbLegen(a, kNrIntk);
            } catch (NumberFormatException e) {

            }

            if (ok) {
                System.out.println("Artikel wurde in den Warenkorb gelegt !");
            } else {
                System.out.println("Beim einfügen des Artikels in den Warenkorb ist ein Fehler aufgetreten !");
            }
        }

    }

    private void verarbeiteRechnungsEingabe(String line) throws IOException {

        // Eingabe bearbeiten:
        if (line.equals("h")) {
            //hinzufügen
            // lese die notwendigen Parameter, einzeln pro Zeile
            boolean ok = false;
            int kNrInt = 0;
            try {
                System.out.print("Kundennummer > ");
                String kNr = liesEingabe();
                kNrInt = Integer.parseInt(kNr);
                Kunde kunde = eShopVerwaltung.getKunde(kNrInt);
                ok = eShopVerwaltung.fuegeRechnungEin(kunde);


            } catch (NumberFormatException e) {

            }

            if (ok & kNrInt != 0) {
                eShopVerwaltung.resetWarenkorb(kNrInt);
                System.out.println("Rechnung wurde erstellt !");
            } else {
                System.out.println("Die Rechnung konnte leider nicht erstellt werden !");
            }

        } else if (line.equals("l")) {
            //löschen Rechnung
            boolean ok = false;
            try {
                System.out.println(eShopVerwaltung.gibAlleRechnungen());
                System.out.print("Rechnungsnummer: > ");
                String rNr = liesEingabe();
                int rNrInt = Integer.parseInt(rNr);
                ok = eShopVerwaltung.loescheRechnung(eShopVerwaltung.sucheNachRechnungsnummer(rNrInt));
            } catch (NumberFormatException e) {

            }

            if (ok)
                System.out.println("Kunde wurde geloescht !");
            else
                System.out.println("Beim löschen des Kunden ist ein Fehler aufgetreten !");

        } else if (line.equals("a")) {
            //ale ausgeben
            System.out.println(eShopVerwaltung.gibAlleRechnungen());

        } else if (line.equals("f")) {
            //suchen
            System.out.print("Rechnungsnummer:  > ");
            String rNr = liesEingabe();
            int rNrInt = Integer.parseInt(rNr);
            System.out.println(eShopVerwaltung.sucheNachRechnungsnummer(rNrInt));


        } else if (line.equals("s")) {
            //sichern
            eShopVerwaltung.schreibeRechung();
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
                    //Artikelmenue
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

                if (var == 'k') {
                    //Kundenmenue
                    do {

                        try {
                            gibKundenMenueAus();
                            input = liesEingabe();
                            verarbeiteKundenEingabe(input);

                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();


                        }
                    } while (!input.equals("m"));

                }

                if (var == 'r') {
                    //Rechnungsmenue
                    do {

                        try {
                            gibRechnungsMenueAus();
                            input = liesEingabe();
                            verarbeiteRechnungsEingabe(input);

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
