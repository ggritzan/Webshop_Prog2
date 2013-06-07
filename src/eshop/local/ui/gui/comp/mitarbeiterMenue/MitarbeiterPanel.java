package eshop.local.ui.gui.comp.mitarbeiterMenue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 31.05.13
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterPanel extends JPanel {
    private JButton artikelButton;
    private JButton mitarbeiterButton;
    private JButton kundenButton;
    private JButton rechnungenButton;
    private JButton logsButton;
    private JButton logoutButton;


    public MitarbeiterPanel() {
        super();

        artikelButton = new JButton("Artikel");
        mitarbeiterButton = new JButton("Mitarbeiter");
        kundenButton = new JButton("Kunden");
        rechnungenButton = new JButton("Rechnungen");
        logsButton = new JButton("Logs");
        logoutButton = new JButton("Logout");




        // Layout Ebene 0
        GridLayout layoutEbene0 = new GridLayout(1, 2);
        this.setLayout(layoutEbene0);	// 1 Zeilen, 2 Spalte

        // Layout Ebene 1
        JPanel frameEbene1 = new JPanel();
        GridLayout layoutEbene1 = new GridLayout(15, 1);
        layoutEbene1.setHgap(5);        // Lass' 5px Abstand zwischen den Komponenten
        frameEbene1.setLayout(layoutEbene1);	// 15 Zeilen, 1 Spalte

        // Hinzufügen der Elemente für die Ebene 3
        frameEbene1.add(new JPanel());
        frameEbene1.add(artikelButton);
        frameEbene1.add(new JPanel());
        frameEbene1.add(mitarbeiterButton);
        frameEbene1.add(new JPanel());
        frameEbene1.add(kundenButton);
        frameEbene1.add(new JPanel());
        frameEbene1.add(rechnungenButton);
        frameEbene1.add(new JPanel());
        frameEbene1.add(logsButton);
        frameEbene1.add(new JPanel());
        frameEbene1.add(logoutButton);
        frameEbene1.add(new JPanel());
        frameEbene1.add(new JPanel());



        // Hinzufügen der Elemente für die Ebene 0
        this.add(new JPanel());
        this.add(frameEbene1);

    }

    public JButton getArtikelButton() {
        return artikelButton;
    }

    public JButton getMitarbeiterButton() {
        return mitarbeiterButton;
    }

    public JButton getKundenButton() {
        return kundenButton;
    }

    public JButton getRechnungenButton() {
        return rechnungenButton;
    }

    public JButton getLogsButton() {
        return logsButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public void addActionListener(ActionListener a) {
        artikelButton.addActionListener(a);
        mitarbeiterButton.addActionListener(a);
        kundenButton.addActionListener(a);
        rechnungenButton.addActionListener(a);
        logsButton.addActionListener(a);
        logoutButton.addActionListener(a);
    }

}

