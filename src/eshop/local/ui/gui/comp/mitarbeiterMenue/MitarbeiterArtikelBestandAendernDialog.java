package eshop.local.ui.gui.comp.mitarbeiterMenue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 07.06.13
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterArtikelBestandAendernDialog extends JDialog {

    private JTextField neuerBestand;
    private JButton    bestandAendern;


    /**
     * Konstruktor
     */
    public MitarbeiterArtikelBestandAendernDialog() {
        super();

        neuerBestand = new JTextField();
        bestandAendern = new JButton("Bestand ändern");

        // Eigenschaften

        this.setResizable(false);

        // Layout außen
        GridLayout layout = new GridLayout(2, 2);
        layout.setHgap(5);        // Lass' 5px Abstand zwischen den Komponenten
        this.setLayout(layout);    // 2 Zeilen, 2 Spalten




        // Hinzufuegen der Komponenten (von oben links nach unten rechts)
        this.add(new JLabel("neuer Bestand"));
        this.add(new JPanel());
        this.add(neuerBestand);
        this.add(bestandAendern);
        pack();
    }

    /**
     * Fuegt dem Button einen ActionListener hinzu
     *
     * @param a
     */
    public void addActionListener(ActionListener a) {
        bestandAendern.addActionListener(a);

    }

    /**
     * Getter für den JButton bestandAendern
     * @return
     */
    public JButton getBestandAendern() {
        return bestandAendern;
    }

    /**
     * Getter für das JTextfield neuerBestand
     * @return
     */
    public JTextField getNeuerBestand() {
        return neuerBestand;
    }
}
