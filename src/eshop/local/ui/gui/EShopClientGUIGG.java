package eshop.local.ui.gui;

import eshop.local.domain.EShopVerwaltung;
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
    private LoginPanel addLoginPanel;



    /**
     *  Konstructor Funktion der Gui
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
     *  Richtet die Grafischen Komponenten der GUI ein und fuegt sie dem Fenster hinzu
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

        // Erzeugt das LoginPanel in der Mitte
        addLoginPanel = new LoginPanel();
        this.add(addLoginPanel, BorderLayout.CENTER);
        this.add(new JPanel(), BorderLayout.WEST);

    }

    /**
     *  Intialisiert die Listener
     */
    private void initListeners() {
        // Finale Selbstreferenz (damit GUI-Referenz "this" auch im ActionListener-Kontext verf�gbar ist)
        final EShopClientGUIGG eShopClientGUIGG = this;

        addLoginPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // 1. Möglichkeit
                //GUI.this.setVisible(false);

                // 2. Möglichkeit
                //gui.setVisible(false);

                try {
                    addLoginPanel.verarbeiteLogin(eShopVerwaltung);


                } catch (IOException e) {
                    e.printStackTrace();
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