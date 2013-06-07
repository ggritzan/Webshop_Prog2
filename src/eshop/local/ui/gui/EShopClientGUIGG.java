package eshop.local.ui.gui;

// Imports der eigenen Klassen

import eshop.local.domain.EShopVerwaltung;
import eshop.local.exception.*;
import eshop.local.ui.gui.comp.*;
import eshop.local.ui.gui.comp.mitarbeiterMenue.*;
import eshop.local.ui.gui.comp.registrierung.*;

// Imports der von Java bereit gestellten Klassen
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class EShopClientGUIGG extends JFrame {

    private EShopVerwaltung eShopVerwaltung;
    private MenuItem menuDateiSpeichern;
    private MenuItem menuDateiQuit;
    private JPanel switchPanel; // damit die Panels ausgetausch werden können
    private LoginPanel addLoginPanel;
    private KundenRegistrierungPanel addKundenRegistrierungPanel;
    private MitarbeiterRegistrierungPanel addMitarbeiterRegistrierungPanel;
    private MitarbeiterPanel addMitarbeiterPanel;
    private MitarbeiterArtikelListePanel addMitarbeiterArtikelListePanel;
    private MitarbeiterArtikelPopup addMitarbeiterArtikelPopup;
    private MitarbeiterArtikelBestandAendernDialog addMitarbeiterArtikelBestandAendernDialog;
    private int aktuellerMitarbeiter;
    private int aktuellerKunde;
    private int aktuelleMenuePositionX;
    private int aktuelleMenuePositionY;
    private int ausgewaehlterArtikel;
    private int ausgewaehlterKunde;
    private int ausgewaehlterMitarbeiter;
    private int ausgewaehlteRechnung;


    /**
     * Konstructor der GUI
     */
    public EShopClientGUIGG(String datei) throws IOException, ClassNotFoundException {
        super(datei);

        // erzeugt eine eShopVerwaltung
        eShopVerwaltung = new EShopVerwaltung(datei);

        // Initialiisert die Komponenten
        this.initComponents();

        // Initialisiert die Listener
        this.initListeners();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // macht das Frame sichtbar
        setVisible(true);
    }

    /**
     * Initialisiert die Komponenten
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
        menuDateiSpeichern = new MenuItem("Speichern");
        menuDateiQuit = new MenuItem("Beenden");
        menuDatei.add(menuDateiSpeichern);
        menuDatei.add(menuDateiQuit);
        // fügt den Menuereiter Datei hinzu
        mbar.add(menuDatei);

        // Zweiter Menuereiter "Ansicht"
        Menu menuAnsicht = new Menu("Ansicht");
        mbar.add(menuAnsicht);

        // fügt die MenuBar dem Frame hinzu
        this.setMenuBar(mbar);


        // Erzeugt das SwitchPanel damit die Panels ausgetausch werden können
        switchPanel = new JPanel();
        BorderLayout b2 = new BorderLayout();
        switchPanel.setLayout(b2);
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

        // Erzeugt die ArtikellistePanel
        addMitarbeiterArtikelListePanel = new MitarbeiterArtikelListePanel(eShopVerwaltung.gibAlleArtikel());

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
     * 'l' für das Rechnungslog
     *
     * @param param
     */
    public void mitarbeiterPanelReloader(char param) {
        switch (param) {
            case 'a':
                switchPanel.remove(addMitarbeiterArtikelListePanel);
                addMitarbeiterArtikelListePanel = new MitarbeiterArtikelListePanel(eShopVerwaltung.gibAlleArtikel());
                switchPanelRepainter();
                switchPanel.add(addMitarbeiterArtikelListePanel, BorderLayout.CENTER);
                initListeners();

            case 'm':

            case 'k':

            case 'r':

            case 'l':

        }
    }

    /**
     * Intialisiert die Listener für die verschiedenen Events
     */
    private void initListeners() {
        // Finale Selbstreferenz (damit GUI-Referenz "this" auch im ActionListener-Kontext verfuegbar ist)
        final EShopClientGUIGG eShopClientGUIGG = this;

        // Actionlistener für das LoginPanel
        addLoginPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                // Wenn der Actionlistener durch den LoginButton ausgelöst wurde wird die folgende Methode augerufen
                Object source = ae.getSource();
                if (source == addLoginPanel.getLoginButton()) {
                    try {
                        char erg = addLoginPanel.verarbeiteLogin(eShopVerwaltung);
                        if (erg == 'm') {
                            aktuellerMitarbeiter = eShopVerwaltung.getMnr(addLoginPanel.getName());
                            switchPanel.remove(addLoginPanel);
                            switchPanelRepainter();
                            switchPanel.add(addMitarbeiterPanel, BorderLayout.WEST);


                        } else if (erg == 'k') {
                            addLoginPanel.setVisible(false);

                        } else if (erg == 'u') {

                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (source == addLoginPanel.getRegisterButton()) {
                    switchPanel.remove(addLoginPanel);
                    switchPanelRepainter();
                    switchPanel.add(addKundenRegistrierungPanel, BorderLayout.CENTER);

                }

            }
        });

        // Actionlistener für das KundenRegistrierungPanel
        addKundenRegistrierungPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                Object source = ae.getSource();
                if (source == addKundenRegistrierungPanel.getKundenRegisterButton()) {
                    try {

                        addKundenRegistrierungPanel.neuenKundenAnlegen(eShopVerwaltung);


                        switchPanel.remove(addKundenRegistrierungPanel);
                        switchPanelRepainter();
                        switchPanel.add(addLoginPanel, BorderLayout.CENTER);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (LeereEingabeException lee) {
                        System.err.println(lee.getMessage());


                    }
                }

            }
        });

        // ActionListener für das MitarbeiterPanel
        addMitarbeiterPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                Object source = ae.getSource();
                if (source == addMitarbeiterPanel.getArtikelButton()) {
                    switchPanelRepainter();
                    switchPanel.add(addMitarbeiterArtikelListePanel, BorderLayout.CENTER);


                } else if (source == addMitarbeiterPanel.getLogoutButton()) {
                    aktuellerMitarbeiter = 0;
                    switchPanel.removeAll();
                    switchPanelRepainter();
                    switchPanel.add(addLoginPanel, BorderLayout.CENTER);
                }

            }
        });

        // ActionListener für MitarbeiterArtikelListePanel
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
                        System.out.print("Der Artikel wurde hinzugefügt!");

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

        // MouseListener für MitarbeiterArtikelListePanel erzeugt ein Popup bei Rechtsklick
        addMitarbeiterArtikelListePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    aktuelleMenuePositionX = e.getXOnScreen();
                    aktuelleMenuePositionY = e.getYOnScreen();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());
                    // Artikelnummer des ausgewählten Artikels
                    ausgewaehlterArtikel = (Integer) source.getValueAt(row, 0);


                    // Erzeugt ein Popup für das Artikelmenü
                    addMitarbeiterArtikelPopup = new MitarbeiterArtikelPopup();
                    // setzt das Popup Menue an die Position des MouseEvents
                    addMitarbeiterArtikelPopup.setLocation(aktuelleMenuePositionX, aktuelleMenuePositionY);
                    // macht das Popup Menue sichtbar
                    addMitarbeiterArtikelPopup.setVisible(true);

                    // ActionListener für MitarbeiterArtikelPopup
                    addMitarbeiterArtikelPopup.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {


                            Object source = ae.getSource();
                            // Ändert den Bestand des entsprechenden Artikels im E-Shop
                            if (source == addMitarbeiterArtikelPopup.getBestandAendern()) {


                                // blendet das MitarbeiterArtikelPopup aus
                                addMitarbeiterArtikelPopup.setVisible(false);
                                // setzt das Popup Menue an die Position des MouseEvents
                                addMitarbeiterArtikelBestandAendernDialog.setLocation(aktuelleMenuePositionX, aktuelleMenuePositionY);
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


                                        addMitarbeiterArtikelPopup.setVisible(false);
                                        ausgewaehlterArtikel = -1;

                                    case JOptionPane.NO_OPTION:

                                        addMitarbeiterArtikelPopup.setVisible(false);
                                        ausgewaehlterArtikel = -1;
                                }

                            }

                        }
                    });


                }
            }
        });


        // ActionListener für MitarbeiterArtikelListePanel
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


        // ActionListener für das Speichern des EShops
        menuDateiSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                // Sichern des Datenstandy für Kunden, Artikel, Mitarbeiter, Rechnung
                try {
                    eShopVerwaltung.schreibeKunden();
                    eShopVerwaltung.schreibeArtikel();
                    eShopVerwaltung.schreibeMitarbeiter();
                    eShopVerwaltung.schreibeRechung();
                    System.out.println("Alle Daten des EShops wurden gesichert!");

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                }

            }
        });

        // ActionListener für das Beenden des EShops
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
        EShopClientGUIGG gui;
        try {
            gui = new EShopClientGUIGG("EShop");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}