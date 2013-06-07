package eshop.local.ui.gui;

import eshop.local.domain.EShopVerwaltung;
import eshop.local.exception.ArtikelExestierBereitsException;
import eshop.local.exception.ArtikelExestiertNichtException;
import eshop.local.exception.LeereEingabeException;
import eshop.local.exception.MitarbeiterExistiertNichtException;
import eshop.local.ui.gui.comp.*;
import eshop.local.ui.gui.comp.mitarbeiterMenue.MitarbeiterArtikelListePanel;
import eshop.local.ui.gui.comp.mitarbeiterMenue.MitarbeiterArtikelPopup;
import eshop.local.ui.gui.comp.mitarbeiterMenue.MitarbeiterPanel;
import eshop.local.ui.gui.comp.registrierung.KundenRegistrierungPanel;
import eshop.local.ui.gui.comp.registrierung.MitarbeiterRegistrierungPanel;

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
    private int aktuellerMitarbeiter;
    private int aktuellerKunde;
    private int ausgewaehlterArtikel;
    private int ausgewaehlterKunde;
    private int ausgewaehlterMitarbeiter;
    private int ausgewaehlteRechnung;


    /**
     * Konstructor Funktion der Gui
     */
    public EShopClientGUIGG(String datei) throws IOException, ClassNotFoundException {
        super(datei);

        // erzeugt eine eShopVerwaltung
        eShopVerwaltung = new EShopVerwaltung(datei);

        // Komponenten und Layout initialisieren
        this.initComponents();

        this.initListeners();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Richtet die Grafischen Komponenten der GUI ein und fuegt sie dem Fenster hinzu
     */
    public void initComponents() {

        // definiert die Groesse des Fensters
        this.setSize(1024, 768);

        // definiert wo das Fenster angezeigt werden soll
        this.setLocation(400, 100);

        // definiert das Layout des ersten Frames
        BorderLayout bl = new BorderLayout();
        this.setLayout(bl);

        // Men�leiste einrichten
        MenuBar mbar = new MenuBar();

        // Erster Menuereiter "Datei" mit "Beenden" & "Speichern"
        Menu menuDatei = new Menu("Datei");
        menuDateiSpeichern = new MenuItem("Speichern");
        menuDateiQuit = new MenuItem("Beenden");
        menuDatei.add(menuDateiSpeichern);
        menuDatei.add(menuDateiQuit);
        mbar.add(menuDatei);

        // Zweiter Menuereiter "Ansicht"
        Menu menuAnsicht = new Menu("Ansicht");
        mbar.add(menuAnsicht);


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


    }

    /**
     * zeichnet das switchPanel neu und validiert es
     */
    public void switchPanelRepainter() {
        switchPanel.repaint();
        switchPanel.revalidate();
    }

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
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());
                    // Artikelnummer des ausgewählten Artikels
                    ausgewaehlterArtikel = (Integer) source.getValueAt(row, 0);
                    System.out.print(" Artikelnummer: " + ausgewaehlterArtikel);


                    // Erzeugt ein Popup für das Artikelmenü
                    addMitarbeiterArtikelPopup = new MitarbeiterArtikelPopup();
                    // setzt das Popup Menue an die Position des MouseEvents
                    addMitarbeiterArtikelPopup.setLocation(e.getXOnScreen(), e.getYOnScreen());
                    // macht das Popup Menue sichtbar
                    addMitarbeiterArtikelPopup.setVisible(true);

                    // ActionListener für MitarbeiterArtikelPopup
                    addMitarbeiterArtikelPopup.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {


                            Object source = ae.getSource();
                            if (source == addMitarbeiterArtikelPopup.getBestandAendern()) {

                                System.out.print(" ändern: " + ausgewaehlterArtikel);
                                addMitarbeiterArtikelPopup.setVisible(false);
                                ausgewaehlterArtikel = -1;

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