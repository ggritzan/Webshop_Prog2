package eshop.local.ui.gui;

import eshop.local.domain.EShopVerwaltung;
import eshop.local.exception.LeereEingabeException;
import eshop.local.ui.gui.comp.KundenRegistrierungPanel;
import eshop.local.ui.gui.comp.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class EShopClientGUIGG extends JFrame {

    private EShopVerwaltung eShopVerwaltung;
    private MenuItem menuDateiSpeichern;
    private MenuItem menuDateiQuit;
    private JPanel switchPanel; // damit die Panels ausgetausch werden können
    private LoginPanel addLoginPanel;
    private KundenRegistrierungPanel addKundenRegistrierungPanel;


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
        this.setSize(800, 600);

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




        // Erzeugt das LoginPanel und fügt es in der Mitte dem Layoutmanager hinzu
        addLoginPanel = new LoginPanel();
        switchPanel.add(addLoginPanel, BorderLayout.CENTER);




    }

    /**
     * Intialisiert die Listener für die verschiedenen Events
     */
    private void initListeners() {
        // Finale Selbstreferenz (damit GUI-Referenz "this" auch im ActionListener-Kontext verf�gbar ist)
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
                            addLoginPanel.setVisible(false);

                        } else if (erg == 'k') {
                            addLoginPanel.setVisible(false);

                        } else if (erg == 'u') {

                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (source == addLoginPanel.getRegisterButton()) {
                    addLoginPanel.setVisible(false);
                    switchPanel.add(addKundenRegistrierungPanel, BorderLayout.CENTER);
                }

            }
        });

        // Actionlistener für das KundenRegistrierungPanel
        addKundenRegistrierungPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {


                Object source = ae.getSource();
                if (source == addKundenRegistrierungPanel.getRegisterButton()) {
                    try {

                       addKundenRegistrierungPanel.neuenKundenAnlegen(eShopVerwaltung);
                       addKundenRegistrierungPanel.setVisible(false);
                       addLoginPanel.setVisible(true);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (LeereEingabeException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        // ActionListener für das Speichern des EShops
        menuDateiSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // 1. Möglichkeit
                //GUI.this.setVisible(false);

                // 2. Möglichkeit
                //gui.setVisible(false);

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