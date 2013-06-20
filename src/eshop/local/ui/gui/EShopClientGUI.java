package eshop.local.ui.gui;

// Imports der eigenen Klassen

import eshop.local.domain.EShopVerwaltung;
import eshop.local.exception.*;
import eshop.local.ui.gui.comp.*;
import eshop.local.ui.gui.comp.kundenMenue.KundenPanel;
import eshop.local.ui.gui.comp.kundenMenue.artikel.KundenArtikelListePanel;
import eshop.local.ui.gui.comp.kundenMenue.artikel.KundenArtikelPopup;
import eshop.local.ui.gui.comp.kundenMenue.rechnung.KundenRechnungsListePanel;
import eshop.local.ui.gui.comp.kundenMenue.warenkorb.KundenWarenkorbBestellteMengeAendern;
import eshop.local.ui.gui.comp.kundenMenue.warenkorb.KundenWarenkorbListePanel;
import eshop.local.ui.gui.comp.kundenMenue.warenkorb.KundenWarenkorbPopup;
import eshop.local.ui.gui.comp.mitarbeiterMenue.*;
import eshop.local.ui.gui.comp.mitarbeiterMenue.artikel.MitarbeiterArtikelBestandAendernDialog;
import eshop.local.ui.gui.comp.mitarbeiterMenue.artikel.MitarbeiterArtikelListePanel;
import eshop.local.ui.gui.comp.mitarbeiterMenue.artikel.MitarbeiterArtikelPopup;
import eshop.local.ui.gui.comp.mitarbeiterMenue.bestandsDiagram.MitarbeiterBestandsDiagramPanel;
import eshop.local.ui.gui.comp.mitarbeiterMenue.bestandsDiagram.MitarbeiterBestandsDiagramWieVieleTageDialog;
import eshop.local.ui.gui.comp.mitarbeiterMenue.kunden.MitarbeiterKundenListePanel;
import eshop.local.ui.gui.comp.mitarbeiterMenue.kunden.MitarbeiterKundenPopup;
import eshop.local.ui.gui.comp.mitarbeiterMenue.mitarbeiter.MitarbeiterMitarbeiterListePanel;
import eshop.local.ui.gui.comp.mitarbeiterMenue.mitarbeiter.MitarbeiterMitarbeiterPopup;
import eshop.local.ui.gui.comp.mitarbeiterMenue.rechnung.MitarbeiterRechnungsListePanel;
import eshop.local.ui.gui.comp.mitarbeiterMenue.rechnung.MitarbeiterRechnungsPopup;
import eshop.local.ui.gui.comp.registrierung.*;
import eshop.local.valueobjects.Adresse;

// Imports der von Java bereit gestellten Klassen
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * GUI des E-Shops
 */
public class EShopClientGUI extends JFrame {

    private EShopVerwaltung eShopVerwaltung; // erzeugt eine EShopVerwaltung
    private MenuItem menuDateiSpeichern;     // Menüeintrag zum speichern der Daten
    private MenuItem menuDateiQuit;          // Menüeintrag zum schließen des EShops
    private JPanel switchPanel; // ist das Panel dem die Elemente hinzugefügt und entzogen werden

    // ==> Alle Panels und Popups
    private LoginPanel addLoginPanel;
    private KundenRegistrierungPanel addKundenRegistrierungPanel;
    private MitarbeiterRegistrierungPanel addMitarbeiterRegistrierungPanel;
    // MitarbeiterPanel
    private MitarbeiterPanel addMitarbeiterPanel;
    private MitarbeiterArtikelListePanel addMitarbeiterArtikelListePanel;
    private MitarbeiterArtikelPopup addMitarbeiterArtikelPopup;
    private MitarbeiterMitarbeiterListePanel addMitarbeiterMitarbeiterListePanel;
    private MitarbeiterMitarbeiterPopup addMitarbeiterMitarbeiterPopup;
    private MitarbeiterKundenListePanel addMitarbeiterKundenListePanel;
    private MitarbeiterKundenPopup addMitarbeiterKundenPopup;
    private MitarbeiterRechnungsListePanel addMitarbeiterRechnungsListePanel;
    private MitarbeiterRechnungsPopup addMitarbeiterRechnungsPopup;
    private MitarbeiterArtikelBestandAendernDialog addMitarbeiterArtikelBestandAendernDialog;
    private MitarbeiterBestandsDiagramPanel addMitarbeiterBestandsDiagramPanel;
    private MitarbeiterBestandsDiagramWieVieleTageDialog addMitarbeiterBestandsDiagramWieVieleTageDialog;
    // KundenPanel
    private KundenPanel addKundenPanel;
    private KundenArtikelListePanel addKundenArtikelListePanel;
    private KundenArtikelPopup addKundenArtikelPopup;
    private KundenWarenkorbListePanel addKundenWarenkorbListePanel;
    private KundenWarenkorbPopup addKundenWarenkorbPopup;
    private KundenWarenkorbBestellteMengeAendern addKundenWarenkorbBestellteMengeAendern;
    private KundenRechnungsListePanel addKundenRechnungsListePanel;
    // <== Ende der Panels und Popups


    private int aktuellerMitarbeiter;
    private int aktuellerKunde;
    private int aktuellePositionX;
    private int aktuellePositionY;
    private int ausgewaehlterArtikel;
    private int ausgewaehlterKunde;
    private int ausgewaehlterMitarbeiter;
    private int ausgewaehlteRechnung;


    /**
     * Konstructor der GUI
     */
    public EShopClientGUI(String datei) throws IOException, ClassNotFoundException {
        super(datei);

        // erzeugt eine eShopVerwaltung
        eShopVerwaltung = new EShopVerwaltung(datei);

        // Initialiisert die Komponenten
        this.initComponents();

        // Initialisiert die Listener
        this.initListeners();

        // ermöglicht dass das Frame beim click auf das Kreuz geshlossen wird
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // macht das Frame sichtbar
        setVisible(true);
    }

    /**
     * Initialisiert alle benoetigten Komponenten
     */
    public void initComponents() {

        // definiert die Groesse des Fensters
        this.setSize(1024, 768);

        // definiert wo das Fenster angezeigt werden soll
        this.setLocation(400, 100);

        // definiert das Layout des ersten Frames
        BorderLayout bl = new BorderLayout();
        this.setLayout(bl);

        // Menueleiste einrichten
        MenuBar mbar = new MenuBar();

        // Erster Menuereiter "Datei" mit "Beenden" & "Speichern"
        Menu menuDatei = new Menu("Datei");
        // - erstellen
        menuDateiSpeichern = new MenuItem("Speichern");
        menuDateiQuit = new MenuItem("Beenden");
        // - hinzufuegen
        menuDatei.add(menuDateiSpeichern);
        menuDatei.add(menuDateiQuit);
        // fügt den Menuereiter Datei hinzu
        mbar.add(menuDatei);

        // Zweiter Menuereiter "Ansicht"
        // - erstellen
        Menu menuAnsicht = new Menu("Ansicht");
        // - hinzufügen
        mbar.add(menuAnsicht);

        // fügt die MenuBar dem Frame hinzu
        this.setMenuBar(mbar);


        // Erzeugt das SwitchPanel damit die Panels ausgetausch werden können
        switchPanel = new JPanel();
        BorderLayout layout = new BorderLayout();
        switchPanel.setLayout(layout);
        this.add(switchPanel, BorderLayout.CENTER);

        // Erzeugt das KundenRegistrierungsPanel
        addKundenRegistrierungPanel = new KundenRegistrierungPanel();

        // Erzeugt das KundenRegistrierungsPanel
        addMitarbeiterRegistrierungPanel = new MitarbeiterRegistrierungPanel();

        // Erzeugt das LoginPanel und fügt es in der Mitte dem Layoutmanager hinzu
        addLoginPanel = new LoginPanel();
        switchPanel.add(addLoginPanel, BorderLayout.CENTER);

// MitarbeiterPanel
        // Erzeugt das MitarbeiterPanel
        addMitarbeiterPanel = new MitarbeiterPanel();

        // Erzeugt das ArtikellistePanel
        addMitarbeiterArtikelListePanel = new MitarbeiterArtikelListePanel(eShopVerwaltung.gibAlleArtikelHashMapZurueckgeben());

        // Erzeugt ein Popup für das Artikelmenü
        addMitarbeiterArtikelPopup = new MitarbeiterArtikelPopup();

        // Erzeugt ein Popup für das Mitarbeitermenü
        addMitarbeiterMitarbeiterPopup = new MitarbeiterMitarbeiterPopup();

        // Erzeugt das MitarbeiterMitarbeiterListePanel
        addMitarbeiterMitarbeiterListePanel = new MitarbeiterMitarbeiterListePanel(eShopVerwaltung.gibAlleMitarbeiterHashMapZurueckgeben());

        // Erzeugt das KundenListenPanel
        addMitarbeiterKundenListePanel = new MitarbeiterKundenListePanel(eShopVerwaltung.gibAlleKundenHashMapZurueckgeben());

        // Erzeugt ein Popup für das Kundenmenü
        addMitarbeiterKundenPopup = new MitarbeiterKundenPopup();

        // Erzeugt das RechnungsListenPanel
        addMitarbeiterRechnungsListePanel = new MitarbeiterRechnungsListePanel(eShopVerwaltung.gibAlleRechnungenHashMapZurueckgeben());

        // Erzeugt ein Popup für das Rechnungsmenü
        addMitarbeiterRechnungsPopup = new MitarbeiterRechnungsPopup();

        // Erzeugt ein MitarbeiterArtieklBestandAndernDialog
        addMitarbeiterArtikelBestandAendernDialog = new MitarbeiterArtikelBestandAendernDialog();

        // Erzeugt ein MitarbeiterBestandsDiagramPanel
        addMitarbeiterBestandsDiagramPanel = new MitarbeiterBestandsDiagramPanel(eShopVerwaltung.gibAlleArtikelHashMapZurueckgeben());
        // Erzeugt einen MitarbeiterBestandsDiagramWieVieleTageDialog
        addMitarbeiterBestandsDiagramWieVieleTageDialog = new MitarbeiterBestandsDiagramWieVieleTageDialog();

// KundenPanel
        // Erzeugt das MitarbeiterPanel
        addKundenPanel = new KundenPanel();

        // Erzeugt das ArtikellistePanel
        addKundenArtikelListePanel = new KundenArtikelListePanel(eShopVerwaltung.gibAlleArtikelHashMapZurueckgeben());

        // Erzeugt ein Popup ffuer die KundenWarenkorbListe
        addKundenWarenkorbPopup = new KundenWarenkorbPopup();

        // Erzeugt ein KundenBestellteMengeAendernDialog
        addKundenWarenkorbBestellteMengeAendern = new KundenWarenkorbBestellteMengeAendern();

        // Erzeugt ein KundenArtikelPopup
        addKundenArtikelPopup = new KundenArtikelPopup();


    }

    /**
     * zeichnet das switchPanel neu und validiert es
     */
    public void switchPanelRepainter() {
        switchPanel.repaint();
        switchPanel.revalidate();
    }


    /**
     * Dient zum neu Laden der jeweiligen Panels des Mitarbeiter
     * als Paramter muss ein char übergeben werden damit die Funktion weiß welche Panels aktualsiert werden sollen
     * 'a' für das ArtikelListePanel
     * 'm' für das MitarbeiterListePanel
     * 'k' für das KundenListePanel
     * 'r' für das RechnungListePanel
     * 'd' für das BestandsDiagram
     *
     * @param param
     */
    public void mitarbeiterPanelReloader(char param) {
        switch (param) {


            case 'a':
                //leert das SwitchPanel
                switchPanel.removeAll();
                // fuegt das MitarbeiterPanel hinzu
                switchPanel.add(addMitarbeiterPanel, BorderLayout.WEST);
                // aktualisiert die ArtikelListe
                addMitarbeiterArtikelListePanel.getTmodel().fireTableDataChanged();
                // fuegt das MitarbeiterArtikelListePanel hinzu
                switchPanel.add(addMitarbeiterArtikelListePanel, BorderLayout.CENTER);
                // setzt die JTextfields des MitarbeiterArtikelListePanel zurueck
                addMitarbeiterArtikelListePanel.resetAllJTextfields();
                switchPanelRepainter();
                break;


            case 'm':
                //leert das SwitchPanel
                switchPanel.removeAll();
                // fuegt das MitarbeiterPanel hinzu
                switchPanel.add(addMitarbeiterPanel, BorderLayout.WEST);
                // aktualisiert die KundenListe
                addMitarbeiterMitarbeiterListePanel.getTmodel().fireTableDataChanged();
                // fuegt das MitarbeiterMitarbeiterListePanel hinzu
                switchPanel.add(addMitarbeiterMitarbeiterListePanel, BorderLayout.CENTER);
                switchPanelRepainter();
                break;


            case 'k':
                //leert das SwitchPanel
                switchPanel.removeAll();
                // fuegt das MitarbeiterPanel hinzu
                switchPanel.add(addMitarbeiterPanel, BorderLayout.WEST);
                // aktualisiert die KundenListe
                addMitarbeiterKundenListePanel.getTmodel().fireTableDataChanged();
                // fuegt das MitarbeiterKundenListePanel hinzu
                switchPanel.add(addMitarbeiterKundenListePanel, BorderLayout.CENTER);
                switchPanelRepainter();
                break;


            case 'r':
                //leert das SwitchPanel
                switchPanel.removeAll();
                // fuegt das MitarbeiterPanel hinzu
                switchPanel.add(addMitarbeiterPanel, BorderLayout.WEST);
                // aktualisiert die RechnungsListe
                addMitarbeiterRechnungsListePanel.getTmodel().fireTableDataChanged();
                // fuegt das MitarbeiterRechnungsListePanel hinzu
                switchPanel.add(addMitarbeiterRechnungsListePanel, BorderLayout.CENTER);
                switchPanelRepainter();
                break;


            case 'd':
                //leert das SwitchPanel
                switchPanel.removeAll();
                // fuegt das MitarbeiterPanel hinzu
                switchPanel.add(addMitarbeiterPanel, BorderLayout.WEST);
                // aktualisiert die ArtikelListe auf dem MitarbeiterBestandsDiagramPanel
                addMitarbeiterBestandsDiagramPanel.getTModel().fireTableDataChanged();
                // fuegt das MitarbeiterBestandsDiagramPanel hinzu
                switchPanel.add(addMitarbeiterBestandsDiagramPanel, BorderLayout.CENTER);
                switchPanelRepainter();
                break;

        }
    }


    /**
     * Dient zum neu Laden der jeweiligen Panels des Kunden
     * als Paramter muss ein char übergeben werden damit die Funktion weiß welche Panels aktualsiert werden sollen
     * 'a' für das ArtikelListePanel
     * 'w' für das WarenkorbListePanel
     * 'r' für das RechnungListePanel
     *
     * @param param
     */
    public void kundenPanelReloader(char param) {
        switch (param) {


            case 'a':
                //leert das SwitchPanel
                switchPanel.removeAll();
                // fuegt das KundenPanel hinzu
                switchPanel.add(addKundenPanel, BorderLayout.WEST);
                // aktualisiert die ArtikelListe
                addKundenArtikelListePanel.getTmodel().fireTableDataChanged();
                // fuegt das KundenArtikelListePanel hinzu
                switchPanel.add(addKundenArtikelListePanel, BorderLayout.CENTER);
                // setzt die JTextfields des KundenArtikelListePanel zurueck
                //addMitarbeiterArtikelListePanel.resetAllJTextfields();
                switchPanelRepainter();
                break;


            case 'w':

                if (addKundenWarenkorbListePanel == null) {

                    // Erzeugt KundenWarenkorbListePanel
                    try {
                        //leert das SwitchPanel
                        switchPanel.removeAll();
                        // fuegt das KundenPanel hinzu
                        switchPanel.add(addKundenPanel, BorderLayout.WEST);
                        // erzeugt ein neues KundenWarenkorbListePanel
                        addKundenWarenkorbListePanel = new KundenWarenkorbListePanel(eShopVerwaltung.getKunde(aktuellerKunde).getWarenkorb());
                        // fuegt die Mouselistener hinzu
                        initWarenkorbListener();
                    } catch (KundenNummerExistiertNichtException knene) {
                        System.err.println(knene.getMessage());
                    }
                    // fuegt das KundenWarenkorbListePanel hinzu
                    switchPanel.add(addKundenWarenkorbListePanel, BorderLayout.CENTER);
                    switchPanelRepainter();


                } else {

                    //leert das SwitchPanel
                    switchPanel.removeAll();
                    // fuegt das KundenPanel hinzu
                    switchPanel.add(addKundenPanel, BorderLayout.WEST);
                    // aktualisiert die KundenWarenkorbListePanel
                    addKundenWarenkorbListePanel.getTmodel().fireTableDataChanged();
                    // fuegt das KundenWarenkorbListePanel hinzu
                    switchPanel.add(addKundenWarenkorbListePanel, BorderLayout.CENTER);
                    switchPanelRepainter();

                }
                break;


            case 'r':

                if (addKundenRechnungsListePanel == null) {

                    // Erzeugt KundenRechnungsListePanel

                    //leert das SwitchPanel
                    switchPanel.removeAll();
                    // fuegt das KundenPanel hinzu
                    switchPanel.add(addKundenPanel, BorderLayout.WEST);
                    // Erzeugt ein KundenRechnungsListePanel
                    addKundenRechnungsListePanel = new KundenRechnungsListePanel(eShopVerwaltung.giballeRechnungenEinesKundenZurueckgeben(aktuellerKunde));
                    initKundenRechnungenListener();
                    switchPanel.add(addKundenRechnungsListePanel, BorderLayout.CENTER);
                    switchPanelRepainter();


               } else {
                    System.out.println("funtzt");
                    //leert das SwitchPanel
                    switchPanel.removeAll();
                    // fuegt das KundenPanel hinzu
                    switchPanel.add(addKundenPanel, BorderLayout.WEST);
                    // aktualisiert die KundenWarenkorbListePanel
                    addKundenRechnungsListePanel.getTmodel().fireTableDataChanged();
                    // fuegt das KundenWarenkorbListePanel hinzu
                    switchPanel.add(addKundenRechnungsListePanel, BorderLayout.CENTER);
                    switchPanelRepainter();

                }
                break;



        }
    }


    /**
     * Intialisiert die Listener für die verschiedenen Action & Mouse-Events
     */
    private void initListeners() {
        // Finale Selbstreferenz (damit GUI-Referenz "this" auch im ActionListener-Kontext verfuegbar ist)
        final EShopClientGUI eShopClientGUI = this;

// LOGIN

        /**
         *  Actionlistener - LoginPanel
         */
        addLoginPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Object source = ae.getSource();

                // LoginButton - reagiert wenn der LoginButton gedrueckt wird
                if (source == addLoginPanel.getLoginButton()) {

                    // liest Benutzername und Benutzerpasswort aus de JTextfield aus
                    String bName = addLoginPanel.getLoginName();
                    String bPasswort = addLoginPanel.getLoginPassword();

                    // Überprüft ob der Mitarbeiter exestier und wechselt bei einem positiven Ergebnis in das Mitarbeitermenue
                    if (eShopVerwaltung.findeMitarbeiter(bName, bPasswort)) {
                        System.out.println("Ihr Login war erfolgreich.");
                        System.out.println("Willkommen Mitarbeiter");
                        try {
                            aktuellerMitarbeiter = eShopVerwaltung.getMitarbeiterNr(addLoginPanel.getLoginName());
                        } catch (MitarbeiterExistiertNichtException mene) {
                            System.err.println(mene.getMessage());
                        }
                        addLoginPanel.resetJTextfields();
                        mitarbeiterPanelReloader('a');


                        // Überprüft ob der Kunde exestier und wechselt bei einem positiven Ergebnis in das Kundenmenue
                    } else if (eShopVerwaltung.findeKunden(bName, bPasswort)) {
                        System.out.println("Ihr Login war erfolgreich.");
                        System.out.println("Willkommen Kunde");
                        try {
                            aktuellerKunde = eShopVerwaltung.getKnr(addLoginPanel.getLoginName());
                        } catch (BenutzernameExistiertNichtException bene) {
                            System.err.println(bene.getMessage());
                        }
                        addLoginPanel.resetJTextfields();
                        kundenPanelReloader('a');


                        // Wenn kein Kunde & kein Mitarbeiter mit dem Benutzernamen exestiert dann erfolgt diese Ausgabe
                    } else {
                        System.err.println("Benutzername oder Passwort leider falsch!");
                        addLoginPanel.resetJTextfields();


                    }

                    // RegisterButton - reagiert wenn der RegisterButton gedrueckt wird
                } else if (source == addLoginPanel.getRegisterButton()) {

                    // Der Nutzer hat nun die Möglichkeit sich in einem Dialog für die Kunden oder Mitarbeiterregistrierung zu entscheiden
                    Object[] options = {"Mitarbeiter", "Kunde"};
                    int result = JOptionPane.showOptionDialog(switchPanel,
                            "Welche Art der Registrierung wollen sie durchführen?",
                            "Registrierung",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,     //do not use a custom Icon
                            options,  //the titles of buttons
                            options[0]); //default button title

                    // Wird im Dialog Mitarbeiter ausgewählt wird der Nutzer zur Mitarbeiter Registrierung weiter geleitet
                    if (result == JOptionPane.YES_OPTION) {

                        switchPanel.removeAll();
                        switchPanelRepainter();
                        switchPanel.add(addMitarbeiterRegistrierungPanel, BorderLayout.CENTER);

                        // Wird im Dialog Kunde ausgewählt wird der Nutzer zur Kunden Registrierung weiter geleitet
                    } else if (result == JOptionPane.NO_OPTION) {


                        switchPanel.removeAll();
                        switchPanelRepainter();
                        switchPanel.add(addKundenRegistrierungPanel, BorderLayout.CENTER);

                    }


                }

            }
        });

// Registrierung
        /**
         * Actionlistener - KundenRegistrierungPanel
         */
        addKundenRegistrierungPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Object source = ae.getSource();

                // KundenRegisterButton - reagiert wenn der KundenRegisterButton gedrueckt wird
                if (source == addKundenRegistrierungPanel.getKundenRegisterButton()) {

                    // Legt einen neuen Kunden an wenn die Eingaben vollständig eingetragen werden
                    try {
                        String vorname = addKundenRegistrierungPanel.getVorname().getText();

                        String nachname = addKundenRegistrierungPanel.getNachname().getText();

                        String benutzername = addKundenRegistrierungPanel.getBenutzername().getText();

                        String passwort = addKundenRegistrierungPanel.getPasswort().getText();

                        String email = addKundenRegistrierungPanel.getEmail().getText();

                        String telefon = addKundenRegistrierungPanel.getTelefon().getText();

                        String straße = addKundenRegistrierungPanel.getStraße().getText();

                        String plz = addKundenRegistrierungPanel.getPlz().getText();

                        String ort = addKundenRegistrierungPanel.getOrt().getText();

                        // Wenn alle Felder ausgefüllt sind
                        if (!(vorname.isEmpty() | nachname.isEmpty() | benutzername.isEmpty() | passwort.isEmpty() | email.isEmpty() | email.isEmpty() | telefon.isEmpty() | straße.isEmpty() | plz.isEmpty() | ort.isEmpty())) {
                            Adresse adresse = new Adresse(vorname, nachname, straße, plz, ort);

                            // einfügen eines Kunden
                            eShopVerwaltung.fuegeKundeEin(vorname, nachname, benutzername, passwort, email, telefon, adresse);

                            // setzt alle Felder des Panel zurück
                            addKundenRegistrierungPanel.resetAllJTextfields();
                            // leert das switchPanel
                            switchPanel.removeAll();
                            switchPanelRepainter();
                            //setzt alle Felder des Logins zurueck
                            addLoginPanel.resetJTextfields();
                            // fügt dem SwitchPanel das LoginPanel hinzu
                            switchPanel.add(addLoginPanel, BorderLayout.CENTER);

                        } else {
                            throw new LeereEingabeException();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    } catch (BenutzernameExistiertBereitsException beb) {
                        System.err.println(beb.getMessage());
                    } catch (LeereEingabeException le) {
                        System.err.println(le.getMessage());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    // BackToLoginButton - reagiert wenn der BackToLoginButton gedrueckt wird
                } else if (source == addKundenRegistrierungPanel.getBackToLoginButton()) {
                    // setzt alle Felder des Panel zurück
                    addKundenRegistrierungPanel.resetAllJTextfields();
                    switchPanel.removeAll();
                    switchPanelRepainter();
                    switchPanel.add(addLoginPanel, BorderLayout.CENTER);
                }


            }


        });

        /**
         * Actionlistener für das MitarbeiterRegistrierungPanel
         */
        addMitarbeiterRegistrierungPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                Object source = ae.getSource();
                if (source == addMitarbeiterRegistrierungPanel.getMitarbeiterRegisterButton()) {
                    try {
                        String vorname = addMitarbeiterRegistrierungPanel.getVorname().getText();

                        String nachname = addMitarbeiterRegistrierungPanel.getNachname().getText();

                        String benutzername = addMitarbeiterRegistrierungPanel.getBenutzername().getText();

                        String passwort = addMitarbeiterRegistrierungPanel.getPasswort().getText();

                        String email = addMitarbeiterRegistrierungPanel.getEmail().getText();

                        String telefon = addMitarbeiterRegistrierungPanel.getTelefon().getText();

                        String straße = addMitarbeiterRegistrierungPanel.getStraße().getText();

                        String plz = addMitarbeiterRegistrierungPanel.getPlz().getText();

                        String ort = addMitarbeiterRegistrierungPanel.getOrt().getText();

                        if (!(vorname.isEmpty() | nachname.isEmpty() | benutzername.isEmpty() | passwort.isEmpty() | email.isEmpty() | email.isEmpty() | telefon.isEmpty() | straße.isEmpty() | plz.isEmpty() | ort.isEmpty())) {
                            Adresse adresse = new Adresse(vorname, nachname, straße, plz, ort);

                            eShopVerwaltung.fuegeMitarbeiterEin(vorname, nachname, benutzername, passwort, email, telefon, adresse);

                            addMitarbeiterRegistrierungPanel.resetAllJTextfields();

                            switchPanel.removeAll();
                            switchPanelRepainter();
                            switchPanel.add(addLoginPanel, BorderLayout.CENTER);

                        } else {
                            throw new LeereEingabeException();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    } catch (LeereEingabeException le) {
                        System.err.println(le.getMessage());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (MitarbeiterExistiertBereitsException mebe) {
                        System.err.println(mebe.getMessage());
                    }

                } else if (source == addMitarbeiterRegistrierungPanel.getBackToLoginButton()) {
                    // setzt alle Felder des Panel zurück
                    addMitarbeiterRegistrierungPanel.resetAllJTextfields();
                    switchPanel.removeAll();
                    switchPanelRepainter();
                    switchPanel.add(addLoginPanel, BorderLayout.CENTER);
                }


            }


        });


//  Mitarbeiter
        /**
         * ActionListener für das MitarbeiterPanel
         */
        addMitarbeiterPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                Object source = ae.getSource();
                if (source == addMitarbeiterPanel.getArtikelButton()) {
                    mitarbeiterPanelReloader('a');

                } else if (source == addMitarbeiterPanel.getMitarbeiterButton()) {
                    mitarbeiterPanelReloader('m');

                } else if (source == addMitarbeiterPanel.getKundenButton()) {
                    mitarbeiterPanelReloader('k');

                } else if (source == addMitarbeiterPanel.getRechnungenButton()) {
                    mitarbeiterPanelReloader('r');

                } else if (source == addMitarbeiterPanel.getBestandsDiagramButton()) {
                   mitarbeiterPanelReloader('d');

                } else if (source == addMitarbeiterPanel.getLogoutButton()) {
                    aktuellerMitarbeiter = 0;
                    switchPanel.removeAll();
                    switchPanelRepainter();
                    switchPanel.add(addLoginPanel, BorderLayout.CENTER);

                }

            }
        });

        /**
         * ActionListener für MitarbeiterArtikelListePanel - ArtikelHinzufuegen
         */
        addMitarbeiterArtikelListePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                // Artikel hinzufügen
                Object source = ae.getSource();
                if (source == addMitarbeiterArtikelListePanel.getArtikelHinzufuegenButton()) {
                    try {

                        String aName = addMitarbeiterArtikelListePanel.getArtikelName();
                        String aBeschreibung = addMitarbeiterArtikelListePanel.getArtikelBeschreibung();
                        double aPreis = addMitarbeiterArtikelListePanel.getArtikelPreis();

                        eShopVerwaltung.fuegeArtikelEin(aName, aBeschreibung, aPreis, eShopVerwaltung.getMitarbeiter(aktuellerMitarbeiter));

                        mitarbeiterPanelReloader('a');


                    } catch (NumberFormatException e) {

                    } catch (ArtikelExestierBereitsException aeb) {
                        System.err.println(aeb.getMessage());

                    } catch (MitarbeiterExistiertNichtException me) {
                        System.err.println(me.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }
        });


        /**
         * ActionListener für MitarbeiterArtikelListePanel - MassengutArtikelHinzufuegen
         */
        addMitarbeiterArtikelListePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                // MassengutArtikel hinzufügen
                Object source = ae.getSource();
                if (source == addMitarbeiterArtikelListePanel.getMassengutartikelHinzufuegenButton()) {
                    try {

                        String aName = addMitarbeiterArtikelListePanel.getMassengutArtikelName();
                        String aBeschreibung = addMitarbeiterArtikelListePanel.getMassengutArtikelBeschreibung();
                        double aPreis = addMitarbeiterArtikelListePanel.getMassengutartikelPreis();
                        int aPackungsgroesse = addMitarbeiterArtikelListePanel.getMassengutartikelPackungsgroesse();

                        eShopVerwaltung.fuegeMassengutArtikelEin(aName, aBeschreibung, aPreis, aPackungsgroesse, eShopVerwaltung.getMitarbeiter(aktuellerMitarbeiter));

                        mitarbeiterPanelReloader('a');


                    } catch (NumberFormatException e) {

                    } catch (ArtikelExestierBereitsException aeb) {
                        System.err.println(aeb.getMessage());

                    } catch (MitarbeiterExistiertNichtException me) {
                        System.err.println(me.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }
        });


        /**
         * MouseListener für MitarbeiterArtikelListePanel erzeugt ein Popup bei Rechtsklick
         */
        addMitarbeiterArtikelListePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    aktuellePositionX = e.getXOnScreen();
                    aktuellePositionY = e.getYOnScreen();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    // Artikelnummer des ausgewählten Artikels
                    ausgewaehlterArtikel = (Integer) source.getValueAt(row, 0);

                    // setzt das Popup Menue an die Position des MouseEvents
                    addMitarbeiterArtikelPopup.show(e.getComponent(), e.getX(), e.getY());


                }
            }
        });

        /**
         * ActionListener für MitarbeiterArtikelPopup
         */
        addMitarbeiterArtikelPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                Object source = ae.getSource();
                // Ändert den Bestand des entsprechenden Artikels im E-Shop
                if (source == addMitarbeiterArtikelPopup.getBestandAendern()) {


                    // setzt das Popup Menue an die Position des MouseEvents
                    addMitarbeiterArtikelBestandAendernDialog.setLocation(aktuellePositionX, aktuellePositionY);

                    // macht das Popup Menue sichtbar
                    addMitarbeiterArtikelBestandAendernDialog.setVisible(true);


                    // Löscht den entsprechenden Artikel aus dem E-Shop wenn im Popupmenue löschen ausgewählt wird
                } else if (source == addMitarbeiterArtikelPopup.getLoeschen()) {


                    // JOptionPane das nachfragt ob etwas echt gelöscht werden soll
                    int result = JOptionPane.showConfirmDialog(null, "Wollen den Artikel wirklich löschen", "Artikel löschen", JOptionPane.YES_NO_OPTION);

                    switch (result) {


                        case JOptionPane.YES_OPTION:

                            try {

                                eShopVerwaltung.loescheArtikel(ausgewaehlterArtikel, eShopVerwaltung.getMitarbeiter(aktuellerMitarbeiter));
                                mitarbeiterPanelReloader('a');


                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (ArtikelExestiertNichtException aen) {
                                System.err.println(aen.getMessage());
                            } catch (MitarbeiterExistiertNichtException men) {
                                System.err.println(men.getMessage());
                            }


                        case JOptionPane.NO_OPTION:


                            ausgewaehlterArtikel = -1;
                    }

                }

            }

        });


        /**
         * MouseListener für MitarbeiterMitarbeiterListePanel erzeugt ein Popup bei Rechtsklick
         */
        addMitarbeiterMitarbeiterListePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    aktuellePositionX = e.getXOnScreen();
                    aktuellePositionY = e.getYOnScreen();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());
                    // Mitarbeiternummer des ausgewählten Artikels
                    ausgewaehlterMitarbeiter = (Integer) source.getValueAt(row, 0);

                    // setzt das Popup Menue an die Position des MouseEvents
                    addMitarbeiterMitarbeiterPopup.show(e.getComponent(), e.getX(), e.getY());


                }
            }
        });


        /**
         * ActionListener für MitarbeiterMitarbeiterPopup
         */
        addMitarbeiterMitarbeiterPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                Object source = ae.getSource();
                // Wenn der loeschen Menueintrag ausgewaehlt wird
                if (source == addMitarbeiterMitarbeiterPopup.getLoeschen()) {

                    // JOptionPane das nachfragt ob etwas echt gelöscht werden soll
                    int result = JOptionPane.showConfirmDialog(null, "Wollen den Mitarbeiter wirklich löschen", "Mitarbeiter löschen", JOptionPane.YES_NO_OPTION);
                    switch (result) {
                        case JOptionPane.YES_OPTION:

                            try {
                                eShopVerwaltung.loescheMitarbeiter(ausgewaehlterMitarbeiter, eShopVerwaltung.getMitarbeiter(aktuellerMitarbeiter));
                                mitarbeiterPanelReloader('m');

                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (MitarbeiterExistiertNichtException men) {
                                System.err.println(men.getMessage());
                            }


                            ausgewaehlterMitarbeiter = -1;
                            break;
                        case JOptionPane.NO_OPTION:


                            ausgewaehlterMitarbeiter = -1;
                            break;
                    }


                }

            }
        });


        /**
         * MouseListener für MitarbeiterKundenListePanel erzeugt ein Popup bei Rechtsklick
         */
        addMitarbeiterKundenListePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    aktuellePositionX = e.getXOnScreen();
                    aktuellePositionY = e.getYOnScreen();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());
                    // Kundennummer des ausgewählten Kunden
                    ausgewaehlterKunde = (Integer) source.getValueAt(row, 0);

                    // setzt das Popup Menue an die Position des MouseEvents
                    addMitarbeiterKundenPopup.show(e.getComponent(), e.getX(), e.getY());


                }
            }
        });


        /**
         * ActionListener für MitarbeiterKundenPopup
         */
        addMitarbeiterKundenPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                Object source = ae.getSource();
                // Wenn der loeschen Menueintrag ausgewaehlt wird
                if (source == addMitarbeiterKundenPopup.getLoeschen()) {

                    // JOptionPane das nachfragt ob etwas echt gelöscht werden soll
                    int result = JOptionPane.showConfirmDialog(null, "Wollen den Kunden wirklich löschen", "Kunden löschen", JOptionPane.YES_NO_OPTION);
                    switch (result) {
                        case JOptionPane.YES_OPTION:

                            try {
                                eShopVerwaltung.loescheKunde(ausgewaehlterKunde, eShopVerwaltung.getMitarbeiter(aktuellerMitarbeiter));
                                mitarbeiterPanelReloader('k');

                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (MitarbeiterExistiertNichtException men) {
                                System.err.println(men.getMessage());
                            } catch (KundenNummerExistiertNichtException knene) {
                                System.err.println(knene.getMessage());
                            }


                            ausgewaehlterKunde = -1;

                        case JOptionPane.NO_OPTION:


                            ausgewaehlterKunde = -1;
                    }

                }

            }
        });


        /**
         * MouseListener für MitarbeiterRechnungsListePanel erzeugt ein Popup bei Rechtsklick
         */
        addMitarbeiterRechnungsListePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    aktuellePositionX = e.getXOnScreen();
                    aktuellePositionY = e.getYOnScreen();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());
                    // Rechnungsnummer des ausgewählten Rechnung
                    ausgewaehlteRechnung = (Integer) source.getValueAt(row, 0);

                    // setzt das Popup Menue an die Position des MouseEvents
                    addMitarbeiterRechnungsPopup.show(e.getComponent(), e.getX(), e.getY());


                }
            }

            /**
             * MouseListener für MitarbeiterRechnungsListePanel erzeugt die Artikelliste zu der jeweiligen Rechnung
             */
            @Override
            public void mouseClicked(MouseEvent emc) {


                JTable source = (JTable) emc.getSource();
                aktuellePositionX = emc.getXOnScreen();
                aktuellePositionY = emc.getYOnScreen();
                int row = source.rowAtPoint(emc.getPoint());
                int column = source.columnAtPoint(emc.getPoint());
                // Rechnungsnummer des ausgewählten Rechnung
                ausgewaehlteRechnung = (Integer) source.getValueAt(row, 0);

                // erzeugt die Artikelliste zu der jeweiligen Rechnung
                try {
                    addMitarbeiterRechnungsListePanel.updateTableData(eShopVerwaltung.sucheNachRechnungsnummer(ausgewaehlteRechnung).getBestellteArtikel());
                    mitarbeiterPanelReloader('r');
                } catch (RechnungExestiertNichtException rene) {
                    System.err.println(rene.getMessage());
                }


            }
        });


        /**
         * ActionListener für MitarbeiterRechnungsPopup
         */
        addMitarbeiterRechnungsPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                Object source = ae.getSource();
                // Wenn der loeschen Menueintrag ausgewaehlt wird                               sa
                if (source == addMitarbeiterRechnungsPopup.getLoeschen()) {

                    // JOptionPane das nachfragt ob etwas echt gelöscht werden soll
                    int result = JOptionPane.showConfirmDialog(null, "Wollen die Rechnung wirklich löschen", "Rechnung löschen", JOptionPane.YES_NO_OPTION);
                    switch (result) {
                        case JOptionPane.YES_OPTION:

                            try {
                                eShopVerwaltung.loescheRechnung(eShopVerwaltung.sucheNachRechnungsnummer(ausgewaehlteRechnung));
                                mitarbeiterPanelReloader('r');

                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (RechnungExestiertNichtException rene) {
                                System.err.println(rene.getMessage());
                            }


                            ausgewaehlteRechnung = -1;

                        case JOptionPane.NO_OPTION:


                            ausgewaehlteRechnung = -1;
                    }

                }

            }
        });


        /**
         * ActionListener für MitarbeiterArtikelListePanel
         */
        addMitarbeiterArtikelBestandAendernDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Object source = ae.getSource();
                // Ändert den Bestand des entsprechenden Artikels im E-Shop
                if (source == addMitarbeiterArtikelBestandAendernDialog.getBestandAendern()) {

                    try {

                        int neuerBestand = Integer.parseInt(addMitarbeiterArtikelBestandAendernDialog.getNeuerBestand().getText());
                        eShopVerwaltung.setBestand(ausgewaehlterArtikel, neuerBestand, eShopVerwaltung.getMitarbeiter(aktuellerMitarbeiter));
                        addMitarbeiterArtikelBestandAendernDialog.setVisible(false);
                        mitarbeiterPanelReloader('a');


                    } catch (MitarbeiterExistiertNichtException me) {
                        System.err.println(me.getMessage());
                    } catch (ArtikelBestandNegativException abn) {
                        System.err.println(abn.getMessage());
                    } catch (ArtikelExestiertNichtException aen) {
                        System.err.println(aen.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }


        });

        /**
         * MouseListener für MitarbeiterBestandsDiagramWieVieleTageDialog JDialog
         */
        addMitarbeiterBestandsDiagramPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                    JTable source = (JTable) e.getSource();
                    aktuellePositionX = e.getXOnScreen();
                    aktuellePositionY = e.getYOnScreen();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    // Artikelnummer des ausgewählten Artikels
                    ausgewaehlterArtikel = (Integer) source.getValueAt(row, 0);


                    // setzt den Dialog an die Position des MouseEvents
                    addMitarbeiterBestandsDiagramWieVieleTageDialog.setLocation(aktuellePositionX, aktuellePositionY);

                    // macht den Dialog sichtbar
                    addMitarbeiterBestandsDiagramWieVieleTageDialog.setVisible(true);



            }
        });


        /**
         * ActionListener für das MitarbeiterBestandsDiagramWieVieleTageDialog
         */
        addMitarbeiterBestandsDiagramWieVieleTageDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                Object source = ae.getSource();
                // Waehlt den anzuzeigenden Artikel für den Bestandsgraphen mit den eigegebenden Tagen aus
                if (source == addMitarbeiterBestandsDiagramWieVieleTageDialog.getbestandsGraphenAnzeigenButton()) {



                        int anzuzeigendeTage = addMitarbeiterBestandsDiagramWieVieleTageDialog.getanzuzeigendeTage();

                    try {

                            addMitarbeiterBestandsDiagramPanel.artikelBestandGraphenzeichnen(eShopVerwaltung.getArtikelGraph(anzuzeigendeTage,String.valueOf(ausgewaehlterArtikel),eShopVerwaltung.getArtikel(ausgewaehlterArtikel).getName()));

                        } catch (FileNotFoundException fnfe) {
                            System.err.println(fnfe.getMessage());
                        } catch (ParseException pe) {
                            System.err.println(pe.getMessage());
                        } catch (KennNummerExistiertNichtException knene) {
                            System.err.println(knene.getMessage());
                        } catch (ArtikelExestiertNichtException aene) {
                            System.err.println(aene.getMessage());
                    }
                    addMitarbeiterBestandsDiagramWieVieleTageDialog.resetJTextfield();
                        addMitarbeiterBestandsDiagramWieVieleTageDialog.setVisible(false);
                        ausgewaehlterArtikel = -1;




                }

            }


        });


// Kunden

        /**
         * ActionListener für das KundenPanel
         */
        addKundenPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                Object source = ae.getSource();
                if (source == addKundenPanel.getArtikelButton()) {
                    kundenPanelReloader('a');

                } else if (source == addKundenPanel.getWarenkorbButton()) {
                    kundenPanelReloader('w');

                } else if (source == addKundenPanel.getRechnungenButton()) {
                    kundenPanelReloader('r');

                } else if (source == addKundenPanel.getLogoutButton()) {
                    aktuellerKunde = 0;
                    switchPanel.removeAll();
                    addKundenWarenkorbListePanel = null;
                    switchPanelRepainter();
                    switchPanel.add(addLoginPanel, BorderLayout.CENTER);


                }

            }
        });


        /**
         * MouseListener für KundenArtikelListePanel erzeugt ein Popup bei Rechtsklick
         */
        addKundenArtikelListePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    aktuellePositionX = e.getXOnScreen();
                    aktuellePositionY = e.getYOnScreen();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    // Artikelnummer des ausgewählten Artikels
                    ausgewaehlterArtikel = (Integer) source.getValueAt(row, 0);

                    // setzt das Popup Menue an die Position des MouseEvents
                    addKundenArtikelPopup.show(e.getComponent(), e.getX(), e.getY());


                }
            }

            @Override
            public void mouseClicked(MouseEvent emc) {


                JTable source = (JTable) emc.getSource();
                aktuellePositionX = emc.getXOnScreen();
                aktuellePositionY = emc.getYOnScreen();
                int row = source.rowAtPoint(emc.getPoint());
                int column = source.columnAtPoint(emc.getPoint());

                // Artikelnummer des ausgewählten Artikels
                ausgewaehlterArtikel = (Integer) source.getValueAt(row, 0);

                // Updatet die KundenArtikelDetails
                try {
                    addKundenArtikelListePanel.updateKundenArtikelDetailsPanel(eShopVerwaltung.getArtikel(ausgewaehlterArtikel).getBeschreibung());
                    kundenPanelReloader('a');
                } catch (ArtikelExestiertNichtException aene) {
                    System.err.print(aene.getMessage());
                }


            }

        });





        /**
         * ActionListener für das KundenArtikelPopup
         */
        addKundenArtikelPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object source = ae.getSource();
                if (source == addKundenArtikelPopup.getdemWarenkorbHinzufuegen()) {

                    try {
                        eShopVerwaltung.inWarenkorbLegen(eShopVerwaltung.getArtikel(ausgewaehlterArtikel), aktuellerKunde);
                    } catch (KundenNummerExistiertNichtException knene) {
                        System.err.println(knene.getMessage());
                    } catch (ArtikelExestiertNichtException aene) {
                        System.err.println(aene.getMessage());
                    }

                }


            }
        });


        /**
         * ActionListener für KundenWarenkorbPopup
         */
        addKundenWarenkorbPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                Object source = ae.getSource();
                // Ändert die bestellte menge des entsprechenden Artikels im Warenkorb
                if (source == addKundenWarenkorbPopup.getBestellteMengeAendern()) {


                    // setzt das Popup Menue an die Position des MouseEvents
                    addKundenWarenkorbBestellteMengeAendern.setLocation(aktuellePositionX, aktuellePositionY);

                    // macht den JDialog sichtbar
                    addKundenWarenkorbBestellteMengeAendern.setVisible(true);


                    // Löscht den entsprechenden Artikel aus dem Warenkorb wenn im Popupmenue löschen ausgewählt wird
                } else if (source == addKundenWarenkorbPopup.getLoeschen()) {


                    // JOptionPane das nachfragt ob etwas echt gelöscht werden soll
                    int result = JOptionPane.showConfirmDialog(null, "Wollen den Artikel wirklich aus ihrem Warenkorb löschen", "Artikel löschen", JOptionPane.YES_NO_OPTION);

                    switch (result) {


                        case JOptionPane.YES_OPTION:

                            try {

                                eShopVerwaltung.ausWarenkorbEntfernen(eShopVerwaltung.getKunde(aktuellerKunde).getWarenkorb().get(ausgewaehlterArtikel), aktuellerKunde);
                                kundenPanelReloader('w');


                            } catch (KundenNummerExistiertNichtException knene) {
                                System.err.println(knene.getMessage());
                            }


                        case JOptionPane.NO_OPTION:


                            ausgewaehlterArtikel = -1;
                    }

                    // bestellt den entsprechenden Warenkorb und erstellt dazu eine Rechnung
                } else if (source == addKundenWarenkorbPopup.getWarenkorbBestellen()) {


                    // JOptionPane das nachfragt ob etwas echt gelöscht werden soll
                    int result = JOptionPane.showConfirmDialog(null, "Wollen den Warenkorb wirklich bestellen ?", "Warenkorb bestellen", JOptionPane.YES_NO_OPTION);

                    switch (result) {


                        case JOptionPane.YES_OPTION:

                            try {
                                eShopVerwaltung.rechnungsBestandCheckKaufen(eShopVerwaltung,aktuellerKunde);
                                kundenPanelReloader('w');
                                addKundenRechnungsListePanel = null;

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (KundenNummerExistiertNichtException knene) {
                                 System.err.println(knene.getMessage());
                            } catch (ArtikelBestandNegativException abne) {
                                System.err.println(abne.getMessage());
                            } catch (ArtikelBestandZuNiedrigException abzne) {
                                System.err.println(abzne.getMessage());
                            } catch (ArtikelExestiertNichtException aene) {
                                System.err.println(aene.getMessage());
                            } catch (RechnungExestiertNichtException rene) {
                                System.err.println(rene.getMessage());
                            }
                            break;

                        case JOptionPane.NO_OPTION:
                            ausgewaehlterArtikel = -1;
                            break;
                    }

                }

            }

        });


        /**
         * ActionListener für KundenWarenkorbBestellteMengeAendern
         */
        addKundenWarenkorbBestellteMengeAendern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                Object source = ae.getSource();
                // Ändert die bestellte menge des entsprechenden Artikels im Warenkorb
                if (source == addKundenWarenkorbBestellteMengeAendern.getBestellteMengeAendern()) {

                    try {
                        eShopVerwaltung.getKunde(aktuellerKunde).getWarenkorb().get(ausgewaehlterArtikel).setBestellteMenge(addKundenWarenkorbBestellteMengeAendern.getNeuebetellteMenge());
                        addKundenWarenkorbBestellteMengeAendern.setVisible(false);
                        addKundenWarenkorbBestellteMengeAendern.resetAllJTextfields();
                        kundenPanelReloader('w');
                    } catch (KundenNummerExistiertNichtException knene) {
                        System.err.println(knene.getMessage());
                    }


                }

            }

        });


        /**
         * ActionListener für das Speichern des EShops
         */
        menuDateiSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                // Sichern des Datenstandy für Kunden, Artikel, Mitarbeiter, Rechnung
                try {
                    eShopVerwaltung.schreibeKunden();
                    eShopVerwaltung.schreibeArtikel();
                    eShopVerwaltung.schreibeMitarbeiter();
                    eShopVerwaltung.schreibeRechung();


                } catch (IOException e) {

                    e.printStackTrace();

                }

            }
        });

        /**
         * ActionListener für das Beenden des EShops
         */
        menuDateiQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                // Beendet das Programm und fragt vorher noch ob das beenden gewünscht ist
                int result = JOptionPane.showConfirmDialog(null, "Wollen sie das Programm wirklich beenden", "Programm beenden", JOptionPane.YES_NO_OPTION);
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        System.exit(0);
                    case JOptionPane.NO_OPTION:
                }


            }
        });


    }

    /**
     * Intialisiert die Listener für den Warenkorb eines Kunden
     */
    private void initWarenkorbListener() {

        /**
         * MouseListener für KundenWarenkorbListePanel erzeugt ein Popup bei Rechtsklick
         */
        addKundenWarenkorbListePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    aktuellePositionX = e.getXOnScreen();
                    aktuellePositionY = e.getYOnScreen();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    // Artikelnummer des ausgewählten Artikels des Warenkorbs
                    ausgewaehlterArtikel = (Integer) source.getValueAt(row, 0);

                    // setzt das Popup Menue an die Position des MouseEvents
                    addKundenWarenkorbPopup.show(e.getComponent(), e.getX(), e.getY());


                }
            }

            @Override
            public void mouseClicked(MouseEvent emc) {


                JTable source = (JTable) emc.getSource();
                aktuellePositionX = emc.getXOnScreen();
                aktuellePositionY = emc.getYOnScreen();
                int row = source.rowAtPoint(emc.getPoint());
                int column = source.columnAtPoint(emc.getPoint());

                // Artikelnummer des ausgewählten Artikels
                ausgewaehlterArtikel = (Integer) source.getValueAt(row, 0);

                // Updatet die KundenArtikelDetails
                try {
                    addKundenWarenkorbListePanel.updateKundenArtikelDetailsPanel(eShopVerwaltung.getArtikel(ausgewaehlterArtikel).getBeschreibung());
                    kundenPanelReloader('w');
                } catch (ArtikelExestiertNichtException aene) {
                    System.err.print(aene.getMessage());
                }


            }


        });


    }

    /**
     * Intialisiert die Listener KundenRechnungsListePanel
     */
    private void initKundenRechnungenListener() {

        /**
         * MouseListener für KundenRechnungsListePanel erzeugt ein Popup bei Rechtsklick
         */
        addKundenRechnungsListePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent emc) {


                JTable source = (JTable) emc.getSource();
                aktuellePositionX = emc.getXOnScreen();
                aktuellePositionY = emc.getYOnScreen();
                int row = source.rowAtPoint(emc.getPoint());
                int column = source.columnAtPoint(emc.getPoint());

                // Artikelnummer des ausgewählten Artikels
                ausgewaehlteRechnung = (Integer) source.getValueAt(row, 0);

                // Updatet die KundenArtikelDetails
                try {
                    addKundenRechnungsListePanel.updateTableData(eShopVerwaltung.sucheNachRechnungsnummer(ausgewaehlteRechnung).getBestellteArtikel());
                    kundenPanelReloader('r');
                } catch (RechnungExestiertNichtException rene) {
                    System.err.println(rene.getMessage());
                }


            }


        });


    }


    /**
     * Die Main der E-Shop GUI
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws UnsupportedLookAndFeelException
     *
     */
    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException {

        // Look and Feel der Anwendung setzen
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        // Neues GUI-Objekt erzeugen
        EShopClientGUI gui;
        try {
            gui = new EShopClientGUI("EShop");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}