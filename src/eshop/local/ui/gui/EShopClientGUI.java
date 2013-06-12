package eshop.local.ui.gui;

// Imports der eigenen Klassen
import eshop.local.domain.EShopVerwaltung;
import eshop.local.exception.*;
import eshop.local.ui.gui.comp.*;
import eshop.local.ui.gui.comp.mitarbeiterMenue.*;
import eshop.local.ui.gui.comp.mitarbeiterMenue.artikel.MitarbeiterArtikelBestandAendernDialog;
import eshop.local.ui.gui.comp.mitarbeiterMenue.artikel.MitarbeiterArtikelListePanel;
import eshop.local.ui.gui.comp.mitarbeiterMenue.artikel.MitarbeiterArtikelPopup;
import eshop.local.ui.gui.comp.mitarbeiterMenue.kunden.MitarbeiterKundenListePanel;
import eshop.local.ui.gui.comp.mitarbeiterMenue.kunden.MitarbeiterKundenPopup;
import eshop.local.ui.gui.comp.mitarbeiterMenue.mitarbeiter.MitarbeiterMitarbeiterListePanel;
import eshop.local.ui.gui.comp.mitarbeiterMenue.mitarbeiter.MitarbeiterMitarbeiterPopup;
import eshop.local.ui.gui.comp.mitarbeiterMenue.rechnung.MitarbeiterRechnungsListePanel;
import eshop.local.ui.gui.comp.mitarbeiterMenue.rechnung.MitarbeiterRechnungsPopup;
import eshop.local.ui.gui.comp.registrierung.*;
import eshop.local.ui.gui.comp.tableModels.ArtikelTableModel;
import eshop.local.valueobjects.Adresse;

// Imports der von Java bereit gestellten Klassen
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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

        // Erzeugt das MitarbeiterPanel
        addMitarbeiterPanel = new MitarbeiterPanel();

        // Erzeugt das ArtikellistePanel
        addMitarbeiterArtikelListePanel = new MitarbeiterArtikelListePanel(eShopVerwaltung.giballeArtikelHashMapZurueckgeben());

        // Erzeugt ein Popup für das Artikelmenü
        addMitarbeiterArtikelPopup = new MitarbeiterArtikelPopup();

        // Erzeugt das MitarbeiterMitarbeiterListePanel
        addMitarbeiterMitarbeiterListePanel = new MitarbeiterMitarbeiterListePanel(eShopVerwaltung.gibAlleMitarbeiter());

        // Erzeugt das KundenListenPanel
        addMitarbeiterKundenListePanel = new MitarbeiterKundenListePanel(eShopVerwaltung.gibAlleKunden());

        // Erzeugt das RechnungsListenPanel
        addMitarbeiterRechnungsListePanel = new MitarbeiterRechnungsListePanel(eShopVerwaltung.gibAlleRechnungen());

        // Erzeugt ein MitarbeiterArtieklBestandAndernDialog
        addMitarbeiterArtikelBestandAendernDialog = new MitarbeiterArtikelBestandAendernDialog();


    }

    /**
     * zeichnet das switchPanel neu und validiert es
     */
    public void switchPanelRepainter() {
        switchPanel.repaint();
        switchPanel.revalidate();
    }


    /**
     * Dient zum neu Laden der jeweiligen Listen Panels
     * als Paramter muss ein char übergeben werden damit die Funktion weiß welche Listen Panels aktulasiert werden sollen
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
                // fuegt das MitarbeiterArtikelListePanel hinzu
                switchPanel.add(addMitarbeiterArtikelListePanel, BorderLayout.CENTER);
                // setzt die JTextfields des MitarbeiterArtikelListePanel zurueck
                addMitarbeiterArtikelListePanel.resetAllJTextfields();
                // aktualisiert die ArtikelListe
                addMitarbeiterArtikelListePanel.getTmodel().fireTableDataChanged();
                switchPanelRepainter();
                break;


            case 'm':
                switchPanel.removeAll();
                addMitarbeiterMitarbeiterListePanel = new MitarbeiterMitarbeiterListePanel(eShopVerwaltung.gibAlleMitarbeiter());
                switchPanel.add(addMitarbeiterPanel, BorderLayout.WEST);
                switchPanel.add(addMitarbeiterMitarbeiterListePanel, BorderLayout.CENTER);
                switchPanelRepainter();
                initListeners();
                break;


            case 'k':
                switchPanel.removeAll();
                addMitarbeiterKundenListePanel = new MitarbeiterKundenListePanel(eShopVerwaltung.gibAlleKunden());
                switchPanelRepainter();
                switchPanel.add(addMitarbeiterPanel, BorderLayout.WEST);
                switchPanel.add(addMitarbeiterKundenListePanel, BorderLayout.CENTER);
                switchPanelRepainter();
                initListeners();
                break;

            case 'r':
                switchPanel.removeAll();
                addMitarbeiterRechnungsListePanel = new MitarbeiterRechnungsListePanel(eShopVerwaltung.gibAlleRechnungen());
                switchPanelRepainter();
                switchPanel.add(addMitarbeiterPanel, BorderLayout.WEST);
                switchPanel.add(addMitarbeiterRechnungsListePanel, BorderLayout.CENTER);
                switchPanelRepainter();
                initListeners();
                break;

            case 'd':
                break;

        }
    }


    /**
     * Intialisiert die Listener für die verschiedenen Action & Mouse-Events
     */
    private void initListeners() {
        // Finale Selbstreferenz (damit GUI-Referenz "this" auch im ActionListener-Kontext verfuegbar ist)
        final EShopClientGUI eShopClientGUI = this;

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
                        switchPanel.remove(addLoginPanel);
                        mitarbeiterPanelReloader('a');
                        switchPanel.add(addMitarbeiterArtikelListePanel, BorderLayout.CENTER);
                        switchPanel.add(addMitarbeiterPanel, BorderLayout.WEST);
                        initListeners();

                      // Überprüft ob der Kunde exestier und wechselt bei einem positiven Ergebnis in das Kundenmenue
                    } else if (eShopVerwaltung.findeKunden(bName, bPasswort)) {
                        System.out.println("Ihr Login war erfolgreich.");
                        System.out.println("Willkommen Kunde");

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

                        switchPanel.remove(addLoginPanel);
                        switchPanelRepainter();
                        switchPanel.add(addMitarbeiterRegistrierungPanel, BorderLayout.CENTER);

                      // Wird im Dialog Kunde ausgewählt wird der Nutzer zur Kunden Registrierung weiter geleitet
                    } else if (result == JOptionPane.NO_OPTION) {


                        switchPanel.remove(addLoginPanel);
                        switchPanelRepainter();
                        switchPanel.add(addKundenRegistrierungPanel, BorderLayout.CENTER);

                    }


                }

            }
        });

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
                    addKundenRegistrierungPanel.resetAllJTextfields();
                    switchPanel.remove(addKundenRegistrierungPanel);
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

                            switchPanel.remove(addMitarbeiterRegistrierungPanel);
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
                    addMitarbeiterRegistrierungPanel.resetAllJTextfields();
                    switchPanel.remove(addMitarbeiterRegistrierungPanel);
                    switchPanelRepainter();
                    switchPanel.add(addLoginPanel, BorderLayout.CENTER);
                }


            }


        });


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
                    switchPanel.removeAll();
                    switchPanelRepainter();
                    mitarbeiterPanelReloader('m');


                } else if (source == addMitarbeiterPanel.getKundenButton()) {
                    switchPanel.removeAll();
                    switchPanelRepainter();
                    mitarbeiterPanelReloader('k');


                } else if (source == addMitarbeiterPanel.getRechnungenButton()) {
                    switchPanel.removeAll();
                    switchPanelRepainter();
                    mitarbeiterPanelReloader('r');


                } else if (source == addMitarbeiterPanel.getLogoutButton()) {
                    aktuellerMitarbeiter = 0;
                    switchPanel.removeAll();
                    switchPanelRepainter();
                    switchPanel.add(addLoginPanel, BorderLayout.CENTER);

                }

            }
        });

        /**
         * ActionListener für MitarbeiterArtikelListePanel
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
                        System.out.println("Der Artikel wurde hinzugefügt!");

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


                                        ausgewaehlterArtikel = -1;

                                    case JOptionPane.NO_OPTION:


                                        ausgewaehlterArtikel = -1;
                                }

                            }

                        }
                    });


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


                    // Erzeugt ein Popup für das Mitarbeitermenü
                    addMitarbeiterMitarbeiterPopup = new MitarbeiterMitarbeiterPopup();

                    // setzt das Popup Menue an die Position des MouseEvents
                    addMitarbeiterMitarbeiterPopup.show(e.getComponent(), e.getX(), e.getY());

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

                                    case JOptionPane.NO_OPTION:


                                        ausgewaehlterMitarbeiter = -1;
                                }

                            }

                        }
                    });


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


                    // Erzeugt ein Popup für das Kundenmenü
                    addMitarbeiterKundenPopup = new MitarbeiterKundenPopup();

                    // setzt das Popup Menue an die Position des MouseEvents
                    addMitarbeiterKundenPopup.show(e.getComponent(), e.getX(), e.getY());

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


                    // Erzeugt ein Popup für das Rechnungsmenü
                    addMitarbeiterRechnungsPopup = new MitarbeiterRechnungsPopup();

                    // setzt das Popup Menue an die Position des MouseEvents
                    addMitarbeiterRechnungsPopup.show(e.getComponent(), e.getX(), e.getY());

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
                    // TODO Auto-generated catch block
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