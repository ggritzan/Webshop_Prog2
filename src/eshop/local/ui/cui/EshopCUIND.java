package eshop.local.ui.cui;

import eshop.local.domain.EShopVerwaltung;
import eshop.local.valueobjects.*;

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

    private Person gibLoginMenue() throws IOException {
        Person p;
        System.out.println("Benutzername:");
        String bName = liesEingabe();
        System.out.println("Passwort:");
        String bPasswort = liesEingabe();
        if (eShopVerwaltung.findeMitarbeiter(bName, bPasswort)) {
            System.out.println("Ihr Login war erfolgreich.");
            p = eShopVerwaltung.rufeMitarbeiter(bName);
            System.out.println("Willkommen Mitarbeiter " + p.getBenutzername());
            return p;
        }else if(eShopVerwaltung.findeKunden(bName, bPasswort)) {
            System.out.println("Ihr Login war erfolgreich.");
            System.out.println("Willkommen Kunde");
            p = eShopVerwaltung.rufeKunden(bName);
            return p;
        }else{
        System.out.println("Ihr Login war leider nicht erfolgreich.");
        Adresse x = new Adresse("","","","","");
        p = new Kunde("","","","","","",x);
        return p;
        }
    }

    private String liesEingabe() throws IOException {
        // einlesen von der Konsole
        return in.readLine();
    }

    private void neuenArtikelAnlegen() throws IOException {
        boolean ok = false;
        System.out.println("Bitte geben sie die Daten des Artikels ein, den Sie anlegen wollen.");
        try {
            System.out.print("Name > ");
            String name = liesEingabe();
            System.out.print("Nachname > ");
            String beschreibung = liesEingabe();
            System.out.print("Preis > ");
            String Spreis = liesEingabe();
            int preis = Integer.parseInt(Spreis);
            ok = eShopVerwaltung.fuegeArtikelEin(name, beschreibung, preis);
        }catch (NumberFormatException e) {
            System.out.println(e);
        }
        if (ok)
            System.out.println("Einfügen ok");
        else
            System.out.println("Der Artikel konnte leider nicht angelegt werden !");
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

    private char verarbeiteMainEingabe(String line) throws IOException {

        // Eingabe bearbeiten:
        if (line.equals("l")) {
            //zum Login
            return 'l';
        } else if (line.equals("k")) {
            //zum anlegen neuer Kunden, Neuanmeldung
            return 'k';
        } else if (line.equals("n")) {
            //zum anlegen neuer Kunden, Neuanmeldung
            return 'n';
        } else if (line.equals("q")) {
            //zum anlegen neuer Kunden, Neuanmeldung
            return 'q';
        }
        return 'm';
    }

    public void verarbeiteMitarbeiterEingabe(String line) throws IOException {
        if(line.equals("h")){
            boolean ok = false;
            System.out.print("Vorname > ");
            String vorname = liesEingabe();
            if(vorname.isEmpty()){
                System.out.println("Ungueltige Eingabe");
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
        int durchlauf = 0;
        Person eingeloggt;

        // Hauptschleife der Benutzungsschnittstelle
        do {
            try {
                gibStartMenue();
                input = liesEingabe();
                char var = verarbeiteMainEingabe(input);
                switch(var) {
                    case 'l': eingeloggt = gibLoginMenue();
                        if(eingeloggt instanceof Mitarbeiter) {
                            System.out.println("Ein Mitarbeiter hat sich eingeloggt");
                        } else if(eingeloggt instanceof Kunde && !eingeloggt.getBenutzername().isEmpty()){
                            System.out.println("Ein Kunde hat sich eingeloggt");
                        } else{
                            System.out.println("Der eingeloggte Account konnte leider nicht zugeordnet werden.");
                        }
                        break;
                    case 'k': neuenKundenAnlegen();
                        eShopVerwaltung.schreibeKunden();
                        break;
                    case 'q': System.out.println("Vielen Dank für die Verwendung des E-Shops.");
                        break;
                    case 'n': neuenArtikelAnlegen();
                        eShopVerwaltung.schreibeArtikel();
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

