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
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;


public class eshopClientCUI {

    private EShopVerwaltung eShopVerwaltung;
    private BufferedReader in;


// Konstruktor

    public eshopClientCUI(String datei) throws IOException, ClassNotFoundException {
        // erzeugt eine eShopVerwaltung
        eShopVerwaltung = new EShopVerwaltung(datei);
        // Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
        in = new BufferedReader(new InputStreamReader(System.in));
    }

// Methoden

    /**
     * Methode öffnet eine readline und speichert diese in einem String
     *
     * @throws IOException
     */
    private String liesEingabe() throws IOException {
        // einlesen von der Konsole
        return in.readLine();
    }

// Menues

    private void gibMainMenue() throws IOException {
        System.out.println("Willkommen im E-Shop!");
        System.out.print("Befehle: \n  Fortfahren zum Login: 'l'");
        System.out.print("         \n  Neue Kundenregistrierung: 'k'");
        System.out.print("         \n  Neue Mitarbeiterregistrierung: 'm'");
        System.out.print("         \n  Daten sichern:  's'");
        System.out.println("       \n  Programm beenden 'q'");
    }

    private void gibMitarbeiterMenueAus() {
        System.out.print("Mitarbeiter: \n     hinzufuegen: 'mh'");
        System.out.print("               \n     löschen: 'ml");
        System.out.println("               \n     ausgeben:  'ma'");

        System.out.print("Kunden:     \n     hinzufuegen: 'kh'");
        System.out.print("              \n     Kunde löschen: 'kl");
        System.out.println("              \n     Kunden ausgeben:  'ka'");

        System.out.print("Artikel:    \n  hinzufuegen: 'ah'");
        System.out.print("              \n  Bestand ändern: 'ab'");
        System.out.print("              \n  löschen: al");
        System.out.print("              \n  ausgeben:  'aa'");
        System.out.println("              \n  suchen:  'af'");

        System.out.println("Rechnung:    \n  : löschen 'rl'");


        System.out.print("         \n  zurück ins Hauptmenue: 'o'");
        System.out.print("> "); // Prompt
        System.out.flush(); // ohne NL ausgeben
    }

    private void gibKundenMenueAus() {
        System.out.print("         \n  Liste aller Artikel anzeigen:  'l'");
        System.out.print("         \n  Artikel in den Warenkorb legen:  'w'");
        System.out.print("         \n  Artikel aus dem Warenkorb löschen:  'e'");
        System.out.print("         \n  Warenkorb anzeigen lassen:  'a'");
        System.out.print("         \n  Artikel aus dem Warenkorb bestellen & Rechnung erstellen :  'b'");
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

        } else if (line.equals("m")) {
            //für Mitarbeiterregistrierung
            return 'm';

        } else if (line.equals("s")) {
            // für das Sichern der Änderungen
            return 's';
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
        if (line.equals("l")) {
            //Für die Liste aller Artikel
            return 'l';

        } else if (line.equals("w")) {
            //Artikel in den Warenkorb packen
            return 'w';

        } else if (line.equals("e")) {
            //Artikel aus dem Warenkorb löschen
            return 'e';

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

                // Liste der Artikel ausgeben
                if (var == 'l') {


                    try {

                        System.out.print("Wie soll die Ausgabe sortiert werden ?: > ");
                        System.out.print("1) nach Bezeichnung sortiert  > ");
                        System.out.print("2) nach Artikelnummer sortiert  > ");
                        String sort = liesEingabe();
                        int sortInt = Integer.parseInt(sort);
                        // nach Artikelnummer sortiert
                        if (sortInt == 1) {

                            Vector<Artikel> sorted = eShopVerwaltung.gibAlleArtikel();
                            Collections.sort(sorted);
                            System.out.print(sorted);


                        } else if (sortInt == 2) {

                            System.out.print(eShopVerwaltung.gibAlleArtikel());

                        }


                    } catch (NumberFormatException e) {

                    }

                    // Artikel in den Warenkorb legen
                } else if (var == 'w') {
                    boolean ok = false;

                    try {

                        System.out.println(eShopVerwaltung.gibAlleArtikel());
                        System.out.print("Artikelnummer: > ");
                        String aNr = liesEingabe();
                        int aNrInt = Integer.parseInt(aNr);
                        System.out.print("Menge: > ");
                        String menge = liesEingabe();
                        int mengeInt = Integer.parseInt(menge);

                        Artikel a = new Artikel(eShopVerwaltung.getArtikel(aNrInt));



                        a.setBestellteMenge(mengeInt);

                        System.out.print("" + a);


                        if (a.getBestand() >= mengeInt && mengeInt > 0) {

                            ok = eShopVerwaltung.inWarenkorbLegen(a, kNr);
                        } else {
                            ok = false;
                        }
                    } catch (NumberFormatException e) {

                    }

                    if (ok) {
                        System.out.println("Artikel wurde in den Warenkorb gelegt !");
                    } else {
                        System.out.println("Beim einfügen des Artikels in den Warenkorb ist ein Fehler aufgetreten !");
                    }

                    // Artikel aus dem Warenkorb löschen
                } else if (var == 'e') {
                    boolean ok = false;

                    try {

                        System.out.println(eShopVerwaltung.getKunde(kNr).getWarenkorb().values());
                        System.out.print("Artikelnummer: > ");
                        String aNr = liesEingabe();
                        int aNrInt = Integer.parseInt(aNr);
                        Artikel a = eShopVerwaltung.getArtikel(aNrInt);
                        ok = eShopVerwaltung.ausWarenkorbEntfernen(a, kNr);

                    } catch (NumberFormatException e) {

                    }

                    if (ok) {
                        System.out.println("Artikel wurde aus den Warenkorb gelöscht !");
                    } else {
                        System.out.println("Beim löschen des Artikels aus dem Warenkorb ist ein Fehler aufgetreten !");
                    }

                    // Zeigt den Warenkorb eines eingeloggten Kunden an
                } else if (var == 'a') {
                    boolean ok = false;

                    try {

                        Kunde kunde = eShopVerwaltung.getKunde(kNr);

                        if (kunde.getWarenkorb().size() > 0) {

                            System.out.println(eShopVerwaltung.getKunde(kNr).getWarenkorb().values());

                        } else {
                            System.out.println("Ihr Warenkorb ist leer !");
                        }


                    } catch (NumberFormatException e) {

                    }

                    // Erstellt für den aktuellen Warenkorb eines Nutzers eine Rechnung
                } else if (var == 'b') {
                    boolean ok = false;

                    try {


                        Kunde kunde = eShopVerwaltung.getKunde(kNr);

                        if (kunde.getWarenkorb().size() > 0) {

                            /*
                            Der Bestand muss angepasst werden
                            ok = eShopVerwaltung.fuegeRechnungEin(kunde);
                            do {
                                eShopVerwaltung.setBestand()
                            }while ();
                            */

                            kunde.resetWarenkorb();

                        }

                    } catch (NumberFormatException e) {

                    }

                    if (ok) {
                        System.out.println("Ihre Artikel wurden erfolgreich bestellt und eine Rechnung wurde erstellt !");
                    } else {
                        System.out.println("Bei der Bestellung und der Rechnungserstellung ist ein Fehler aufgetreten !!");
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


                // Liste der Artikel ausgeben
                if (input.equals("mh")) {
                    boolean ok = false;

                    try {


                        System.out.print("Vorname > ");
                        String vorname = liesEingabe();
                        if (vorname.isEmpty()) {
                            System.out.println("Ungueltige Eingabe");
                            return;
                        }
                        System.out.print("Nachname > ");
                        String nachname = liesEingabe();
                        if (nachname.isEmpty()) {
                            System.out.println("Ungueltige Eingabe");
                            return;
                        }
                        System.out.print("Benutzername > ");
                        String benutzername = liesEingabe();
                        if (benutzername.isEmpty()) {
                            System.out.println("Ungueltige Eingabe");
                            return;
                        }
                        System.out.print("Passwort  > ");
                        String passwort = liesEingabe();
                        if (passwort.isEmpty()) {
                            System.out.println("Ungueltige Eingabe");
                            return;
                        }
                        System.out.print("E-mail > ");
                        String email = liesEingabe();
                        if (email.isEmpty()) {
                            System.out.println("Ungueltige Eingabe");
                            return;
                        }
                        System.out.print("Telefon > ");
                        String telefon = liesEingabe();
                        if (telefon.isEmpty()) {
                            System.out.println("Ungueltige Eingabe");
                            return;
                        }
                        System.out.print("Straße > ");
                        String straße = liesEingabe();
                        if (straße.isEmpty()) {
                            System.out.println("Ungueltige Eingabe");
                            return;
                        }
                        System.out.print("PLZ > ");
                        String plz = liesEingabe();
                        if (plz.isEmpty()) {
                            System.out.println("Ungueltige Eingabe");
                            return;
                        }
                        System.out.print("Ort > ");
                        String ort = liesEingabe();
                        if (ort.isEmpty()) {
                            System.out.println("Ungueltige Eingabe");
                            return;
                        }
                        Adresse adresse = new Adresse(vorname, nachname, straße, plz, ort);
                        ok = eShopVerwaltung.fuegeMitarbeiterEin(vorname, nachname, benutzername, passwort, email, telefon, adresse);
                        if (ok) {
                            System.out.println("Der Mitarbeiter wurde erfolgreich eingefuegt.");
                        } else {
                            System.out.println("Beim anlegen des Mitarbeiters ist ein Fehler aufgetreten.");
                        }


                    } catch (NumberFormatException e) {

                    }

                    // Mitarbeiter loeschen
                } else if (input.equals("ml")) {
                    System.out.println("Bitte geben Sie die Nummer des Mitarbeiters an, der gelöscht werden soll.");
                    String mitNr = liesEingabe();
                    int mNrInt = Integer.parseInt(mitNr);
                    boolean ok = eShopVerwaltung.loescheMitarbeiter(mNrInt);
                    if (ok) {
                        System.out.println("Der Mitarbeiter wurde erfolgreich gelöscht.");
                    } else {
                        System.out.println("Beim löschen des Mitarbeiters ist ein Fehler aufgetreten.");
                    }

                    // Liste aller Mitarbeiter ausgeben
                } else if (input.equals("ma")) {
                    System.out.println(eShopVerwaltung.gibAlleMitarbeiter());

                    // Kunden hinzufuegen
                } else if (input.equals("kh")) {
                    neuenKundenAnlegen();

                    // Kunden loeschen
                } else if (input.equals("kl")) {

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

                    // alle Kunden ausgeben
                } else if (input.equals("ka")) {

                    System.out.println(eShopVerwaltung.gibAlleKunden());

                    // Artikel hinzufuegen
                } else if (input.equals("ah")) {

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

                } else if (input.equals("ab")) {

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

                    // Artikel loeschen
                } else if (input.equals("al")) {
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

                    // Alle Artikel ausgeben
                } else if (input.equals("aa")) {

                    try {

                        System.out.print("Wie soll die Ausgabe sortiert werden ?: > ");
                        System.out.print("1) nach Bezeichnung sortiert  > ");
                        System.out.print("2) nach Artikelnummer sortiert  > ");
                        String sort = liesEingabe();
                        int sortInt = Integer.parseInt(sort);
                        // nach Artikelnummer sortiert
                        if (sortInt == 1) {

                            Vector<Artikel> sorted = eShopVerwaltung.gibAlleArtikel();
                            Collections.sort(sorted);
                            System.out.print(sorted);


                        } else if (sortInt == 2) {

                            System.out.print(eShopVerwaltung.gibAlleArtikel());

                        }


                    } catch (NumberFormatException e) {

                    }

                  // Artikel suchen
                } else if (input.equals("af")) {

                    System.out.print("Artikelname:  > ");
                    String titel = liesEingabe();
                    System.out.println(eShopVerwaltung.sucheNachName(titel));


                } else if (input.equals("rl")) {
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
                        System.out.println("Rechnung wurde geloescht !");
                    else
                        System.out.println("Beim löschen der Rechnung ist ein Fehler aufgetreten !");
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } while (!input.equals("o"));


    }

    private void neuenKundenAnlegen() throws IOException {
        boolean ok = false;
        System.out.println("Willkommen bei der Kundenregistrierung.");
        System.out.println("Bitte geben sie ihre Daten ein.");
        try {
            System.out.print("Vorname > ");
            String vorname = liesEingabe();
            if (vorname.isEmpty()) {
                System.out.println("Ungueltige Eingabe");
                return;
            }

            System.out.print("Nachname > ");
            String nachname = liesEingabe();
            if (nachname.isEmpty()) {
                System.out.println("Ungueltige Eingabe");
                return;
            }

            System.out.print("Benutzername > ");
            String benutzername = liesEingabe();
            if (benutzername.isEmpty()) {
                System.out.println("Ungueltige Eingabe");
                return;
            }

            System.out.print("Passwort  > ");
            String passwort = liesEingabe();
            if (passwort.isEmpty()) {
                System.out.println("Ungueltige Eingabe");
                return;
            }

            System.out.print("E-mail > ");
            String email = liesEingabe();
            if (email.isEmpty()) {
                System.out.println("Ungueltige Eingabe");
                return;
            }

            System.out.print("Telefon > ");
            String telefon = liesEingabe();
            if (telefon.isEmpty()) {
                System.out.println("Ungueltige Eingabe");
                return;
            }

            System.out.print("Straße > ");
            String straße = liesEingabe();
            if (straße.isEmpty()) {
                System.out.println("Ungueltige Eingabe");
                return;
            }

            System.out.print("PLZ > ");
            String plz = liesEingabe();
            if (plz.isEmpty()) {
                System.out.println("Ungueltige Eingabe");
                return;
            }

            System.out.print("Ort > ");
            String ort = liesEingabe();
            if (ort.isEmpty()) {
                System.out.println("Ungueltige Eingabe");
                return;
            }

            Adresse adresse = new Adresse(vorname, nachname, straße, plz, ort);
            ok = eShopVerwaltung.fuegeKundeEin(vorname, nachname, benutzername, passwort, email, telefon, adresse);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        if (ok)
            System.out.println("Einfügen ok");
        else
            System.out.println("Der Kunde konnte leider nicht angelegt werden !");
    }

    private void neuenMitarbeiterAnlegen() throws IOException {

        boolean ok = false;
        System.out.print("Vorname > ");
        String vorname = liesEingabe();
        if (vorname.isEmpty()) {
            System.out.println("Ungueltige Eingabe");
            return;
        }
        System.out.print("Nachname > ");
        String nachname = liesEingabe();
        if (nachname.isEmpty()) {
            System.out.println("Ungueltige Eingabe");
            return;
        }
        System.out.print("Benutzername > ");
        String benutzername = liesEingabe();
        if (benutzername.isEmpty()) {
            System.out.println("Ungueltige Eingabe");
            return;
        }
        System.out.print("Passwort  > ");
        String passwort = liesEingabe();
        if (passwort.isEmpty()) {
            System.out.println("Ungueltige Eingabe");
            return;
        }
        System.out.print("E-mail > ");
        String email = liesEingabe();
        if (email.isEmpty()) {
            System.out.println("Ungueltige Eingabe");
            return;
        }
        System.out.print("Telefon > ");
        String telefon = liesEingabe();
        if (telefon.isEmpty()) {
            System.out.println("Ungueltige Eingabe");
            return;
        }
        System.out.print("Straße > ");
        String straße = liesEingabe();
        if (straße.isEmpty()) {
            System.out.println("Ungueltige Eingabe");
            return;
        }
        System.out.print("PLZ > ");
        String plz = liesEingabe();
        if (plz.isEmpty()) {
            System.out.println("Ungueltige Eingabe");
            return;
        }
        System.out.print("Ort > ");
        String ort = liesEingabe();
        if (ort.isEmpty()) {
            System.out.println("Ungueltige Eingabe");
            return;
        }
        Adresse adresse = new Adresse(vorname, nachname, straße, plz, ort);
        ok = eShopVerwaltung.fuegeMitarbeiterEin(vorname, nachname, benutzername, passwort, email, telefon, adresse);
        if (ok) {
            System.out.println("Der Mitarbeiter wurde erfolgreich eingefuegt.");
        } else {
            System.out.println("Beim anlegen des Mitarbeiters ist ein Fehler aufgetreten.");
        }

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


                } else if (var == 'k') {
                    // Sichern des Datenstandy für Kunden, Artikel, Mitarbeiter, Rechnung
                    try {

                        neuenKundenAnlegen();

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();


                    }
                } else if (var == 'm') {
                    // Sichern des Datenstandy für Kunden, Artikel, Mitarbeiter, Rechnung
                    try {

                        neuenMitarbeiterAnlegen();

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();


                    }
                } else if (var == 's') {
                    // Sichern des Datenstandy für Kunden, Artikel, Mitarbeiter, Rechnung
                    try {
                        eShopVerwaltung.schreibeKunden();
                        eShopVerwaltung.schreibeArtikel();
                        eShopVerwaltung.schreibeMitarbeiter();
                        eShopVerwaltung.schreibeRechung();

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

        eshopClientCUI cui;
        try {
            cui = new eshopClientCUI("EShop");
            cui.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

