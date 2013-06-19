package eshop.local.ui.gui.comp.mitarbeiterMenue.bestandsDiagram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 19.06.13
 * Time: 23:12
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterBestandsDiagramWieVieleTageDialog extends JDialog {

    private JTextField anzuzeigendeTage;
    private JButton    bestandsGraphenAnzeigenButton;


    /**
     * Konstruktor
     */
    public MitarbeiterBestandsDiagramWieVieleTageDialog() {
        super();

        anzuzeigendeTage = new JTextField();
        bestandsGraphenAnzeigenButton = new JButton("Bestandsgraphen anzeigen");

        // Layout außen
        GridLayout layout = new GridLayout(2, 2);
        layout.setHgap(5);        // Lass' 5px Abstand zwischen den Komponenten
        this.setLayout(layout);    // 2 Zeilen, 2 Spalten



        // Hinzufuegen der Komponenten (von oben links nach unten rechts)
        this.add(new JLabel("Zeitraum in Tagen"));
        this.add(new JPanel());
        this.add(anzuzeigendeTage);
        this.add(bestandsGraphenAnzeigenButton);
        pack();
    }

    /**
     * Fuegt dem Button einen ActionListener hinzu
     *
     * @param a
     */
    public void addActionListener(ActionListener a) {
        bestandsGraphenAnzeigenButton.addActionListener(a);

    }

    /**
     * Getter für den JButton bestandAendern
     * @return
     */
    public JButton getbestandsGraphenAnzeigenButton() {
        return bestandsGraphenAnzeigenButton;
    }

    /**
     * Getter für das JTextfield neuerBestand
     * @return
     */
    public int getanzuzeigendeTage() {
        return Integer.parseInt(anzuzeigendeTage.getText());
    }

    /**
     *
     */
    public void resetJTextfield() {
        this.anzuzeigendeTage.setText("");
    }
}
