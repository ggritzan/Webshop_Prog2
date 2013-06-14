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
    private JButton bestandsDiagramButton;
    private JButton logoutButton;

    /**
     * Konstruktor
     */
    public MitarbeiterPanel() {
        super();

        // erzeugt einen neuen artikelButton
        artikelButton = new JButton("Artikel");
        // erzeugt einen neuen mitarbeiterButtoon
        mitarbeiterButton = new JButton("Mitarbeiter");
        // erzeugt einen neuen kundenButton
        kundenButton = new JButton("Kunden");
        // erzeugt einen neuen rechnungenButton
        rechnungenButton = new JButton("Rechnungen");
        // erzeugt einen neuen bestandsDiagramButton
        bestandsDiagramButton = new JButton("Bestand-Diagram");
        // erzeugt einen neuen logoutButton
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
        frameEbene1.add(new JPanel());
        frameEbene1.add(artikelButton);
        frameEbene1.add(mitarbeiterButton);
        frameEbene1.add(kundenButton);
        frameEbene1.add(rechnungenButton);
        frameEbene1.add(bestandsDiagramButton);
        frameEbene1.add(new JPanel());
        frameEbene1.add(new JPanel());
        frameEbene1.add(logoutButton);
        frameEbene1.add(new JPanel());
        frameEbene1.add(new JPanel());
        frameEbene1.add(new JPanel());
        frameEbene1.add(new JPanel());



        // Hinzufuegen der Elemente für die Ebene 0
        this.add(new JPanel());
        this.add(frameEbene1);

    }

    /**
     * Getter fuer den ArtikelButton
     * @return
     */
    public JButton getArtikelButton() {
        return artikelButton;
    }

    /**
     * Getter fuer MitarbeiterButton
     * @return
     */
    public JButton getMitarbeiterButton() {
        return mitarbeiterButton;
    }

    /**
     * Getter fuer den KundenButton
     * @return
     */
    public JButton getKundenButton() {
        return kundenButton;
    }

    /**
     * Getter fuer den RechnungenButton
     * @return
     */
    public JButton getRechnungenButton() {
        return rechnungenButton;
    }

    /**
     * Getter fuer den BestandsDiagramButton
     * @return
     */
    public JButton getBestandsDiagramButton() {
        return bestandsDiagramButton;
    }

    /**
     * Getter fuer den LogoutButton
     * @return
     */
    public JButton getLogoutButton() {
        return logoutButton;
    }

    /**
     * Fuegt allen Buttons einen Actionlistener hinzu
     * @param a
     */
    public void addActionListener(ActionListener a) {
        artikelButton.addActionListener(a);
        mitarbeiterButton.addActionListener(a);
        kundenButton.addActionListener(a);
        rechnungenButton.addActionListener(a);
        bestandsDiagramButton.addActionListener(a);
        logoutButton.addActionListener(a);
    }

}

