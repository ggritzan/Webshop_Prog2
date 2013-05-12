
package eshop.local.ui.cui;

import eshop.local.domain.EShopVerwaltung;
import eshop.local.valueobjects.Adresse;
import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Kunde;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Collections;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: ninadoge
 * Date: 21.04.13
 * Time: 18:39
 * To change this template use File | Settings | File Templates.
 */
public class EshopCUIND {
    private EShopVerwaltung eShopVerwaltung;
    private BufferedReader in;


    public EshopCUIND(String datei) throws IOException, ClassNotFoundException {
        // erzeugt eine eShopVerwaltung
        eShopVerwaltung = new EShopVerwaltung(datei);
        // Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    private void gibStartMenue() throws IOException {
        System.out.println("Willkommen im E-Shop!");
        System.out.print("Befehle: \n  Fortfahren zum Login: 'l'");
        System.out.print("         \n  Neue Kundenregistrierung: 'k'");
        System.out.println("       \n  Programm beenden 'q'");
    }

    private void gibLoginMenue() throws IOException {
        System.out.println("Benutzername:");
        String bName = liesEingabe();
        System.out.println("Passwort:");
        String bPasswort = liesEingabe();
        if (eShopVerwaltung.findeMitarbeiter(bName, bPasswort)) {
            System.out.println("Ihr Login war erfolgreich.");
            System.out.println("Willkommen Mitarbeiter");
            gibMitarbeiterMenueAus();
        }else if(eShopVerwaltung.findeKunden(bName, bPasswort)) {
            System.out.println("Ihr Login war erfolgreich.");
            System.out.println("Willkommen Kunde");
            gibKundenMenueAus();
        }else{
            System.out.println("Ihr Login war leider nicht erfolgreich.");
        }
    }

    private void gibmainMenue() {
        System.out.print("Befehle: \n  Artikelmenue: 'a'");
        System.out.print("         \n  Kundenmenue: 'k'");
        System.out.print("         \n  Rechnungsmenue: 'r'");
        System.out.print("         \n  Mitarbeitermenue:  'b'");
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

    private void gibMitarbeiterMenueAus() {
        System.out.print("Befehle: \n  Mitarbeiter hinzufuegen: 'h'");
        System.out.print("         \n  Mitarbeiter löschen: 'l");
        System.out.print("         \n  Mitarbeiter ausgeben:  'a'");
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
        if (line.equals("l")) {
            //zum Login
            return 'l';
        } else if (line.equals("k")) {
            //zum anlegen neuer Kunden, Neuanmeldung
            return 'k';
        } else if (line.equals("q")) {
        //zum anlegen neuer Kunden, Neuanmeldung
        return 'q';
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

    private void neuenKundenAnlegen() throws IOException {
        boolean ok = false;
        System.out.println("Willkommen bei der Kundenregistrierung.");
        System.out.println("Bitte geben sie ihre Daten ein.");
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
         }catch (NumberFormatException e) {
            System.out.println(e);
         }
        if (ok)
            System.out.println("Einfügen ok");
        else
            System.out.println("Der Kunde konnte leider nicht angelegt werden !");
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
                ok = eShopVerwaltung.inWarenkorbLegen(a, kNrInt);
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

    private boolean pruefeEingabe(String input) {
        if(input.isEmpty()){
            System.out.println("Ungueltige Eingabe");
            return true;
        }
        return false;
    }

    public void verarbeiteMitarbeiterEingabe(String line) throws IOException {
        if(line.equals("h")){
            boolean ok = false;
            System.out.print("Vorname > ");
            String vorname = liesEingabe();
            if(pruefeEingabe(vorname)){
                return;
            }
            System.out.print("Nachname > ");
            String nachname = liesEingabe();
            if(nachname.isEmpty()){
                System.out.println("Ungueltige Eingabe");
                return;
            }
            System.out.print("Benutzername > ");
            String benutzername = liesEingabe();
            if(benutzername.isEmpty()){
                System.out.println("Ungueltige Eingabe");
                return;
            }
            System.out.print("Passwort  > ");
            String passwort = liesEingabe();
            if(passwort.isEmpty()){
                System.out.println("Ungueltige Eingabe");
                return;
            }
            System.out.print("E-mail > ");
            String email = liesEingabe();
            if(email.isEmpty()){
                System.out.println("Ungueltige Eingabe");
                return;
            }
            System.out.print("Telefon > ");
            String telefon = liesEingabe();
            if(telefon.isEmpty()){
                System.out.println("Ungueltige Eingabe");
                return;
            }
            System.out.print("Straße > ");
            String straße = liesEingabe();
            if(straße.isEmpty()){
                System.out.println("Ungueltige Eingabe");
                return;
            }
            System.out.print("PLZ > ");
            String plz = liesEingabe();
            if(plz.isEmpty()){
                System.out.println("Ungueltige Eingabe");
                return;
            }
            System.out.print("Ort > ");
            String ort = liesEingabe();
            if(ort.isEmpty()){
                System.out.println("Ungueltige Eingabe");
                return;
            }
            Adresse adresse = new Adresse(vorname, nachname, straße, plz, ort);
            ok = eShopVerwaltung.fuegeMitarbeiterEin(vorname, nachname, benutzername, passwort, email, telefon, adresse);
            if(ok){
                System.out.println("Der Mitarbeiter wurde erfolgreich eingefuegt.");
            }  else {
                System.out.println("Beim anlegen des Mitarbeiters ist ein Fehler aufgetreten.");
            }
        } else if(line.equals("l")){
            System.out.println("Bitte geben Sie die Nummer des Mitarbeiters an, der gelöscht werden soll.");
            String mNr = liesEingabe();
            int mNrInt = Integer.parseInt(mNr);
            boolean ok = eShopVerwaltung.loescheMitarbeiter(mNrInt);
            if(ok){
                System.out.println("Der Mitarbeiter wurde erfolgreich gelöscht.");
            } else {
                System.out.println("Beim löschen des Mitarbeiters ist ein Fehler aufgetreten.");
            }
        } else if(line.equals("a")){
            System.out.println(eShopVerwaltung.gibAlleMitarbeiter());
        } else if(line.equals("s")){
            eShopVerwaltung.schreibeMitarbeiter();
        }
    }


    public void run() {
        // Variable für Eingaben von der Konsole
        String input = "";

        // Hauptschleife der Benutzungsschnittstelle
        do {

            try {
                Vector<Artikel> artList = eShopVerwaltung.gibAlleArtikel();
                System.out.println(artList);
                Collections.sort(artList);
                System.out.println(artList);
                gibStartMenue();
                input = liesEingabe();
                char var = verarbeiteMainEingabe(input);
                switch(var) {
                    case 'l': gibLoginMenue();
                        break;
                    case 'k': neuenKundenAnlegen();
                              eShopVerwaltung.schreibeKunden();
                        break;
                    case 'q': System.out.println("Vielen Dank für die Verwendung des E-Shops.");
                        break;
                    case 'a': eShopVerwaltung.
                    default : System.out.println("Unbekannte Eingabe!");
                        break;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } while (!input.equals("q"));
    }


// Main

    public static void main(String[] args) throws ClassNotFoundException {

        EshopCUIND cui;
        try {
            cui = new EshopCUIND("EShop");
            cui.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

