package eshop.local.ui.gui.comp.kundenMenue.warenkorb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 14.06.13
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */
public class KundenWarenkorbBestellteMengeAendern extends JDialog {

    private JTextField neuebetellteMenge;
    private JButton    bestellteMengeAendern;


    /**
     * Konstruktor
     */
    public KundenWarenkorbBestellteMengeAendern() {
        super();

        neuebetellteMenge = new JTextField();
        bestellteMengeAendern = new JButton("Bestellte Menge ändern");


        // Layout außen
        GridLayout layout = new GridLayout(2, 2);
        layout.setHgap(5);        // Lass' 5px Abstand zwischen den Komponenten
        this.setLayout(layout);    // 2 Zeilen, 2 Spalten




        // Hinzufuegen der Komponenten (von oben links nach unten rechts)
        this.add(new JLabel("neue Bestellmenge"));
        this.add(new JPanel());
        this.add(neuebetellteMenge);
        this.add(bestellteMengeAendern);
        pack();
    }

    /**
     * Fuegt dem Button einen ActionListener hinzu
     *
     * @param a
     */
    public void addActionListener(ActionListener a) {
        bestellteMengeAendern.addActionListener(a);

    }

    /**
     * Getter für den JButton bestellteMengeAendern
     * @return
     */
    public JButton getBestellteMengeAendern() {
        return bestellteMengeAendern;
    }

    /**
     * Getter für das JTextfield neuebetellteMenge
     * @return
     */
    public Integer getNeuebetellteMenge() {
        return Integer.parseInt(neuebetellteMenge.getText());
    }

    public void resetAllJTextfields() {
        neuebetellteMenge.setText("");
    }

}
