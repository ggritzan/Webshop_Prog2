package eshop.local.ui.gui.comp.mitarbeiterMenue.artikel;

import eshop.local.domain.EShopVerwaltung;
import eshop.local.exception.ArtikelExestierBereitsException;
import eshop.local.exception.LeereEingabeException;
import eshop.local.exception.MitarbeiterExistiertNichtException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 05.06.13
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterArtikelHinzufuegenPanel extends JPanel {

    private static final long serialVersionUID = 6711014823212243842L;

    private JTextField artikelName;            // Textfeld fuer den Artikelname
    private JTextField artikelBeschreibung;    // Textfeld fuer die Beschreibung
    private JTextField artikelPreis;           // Textfeld fure den Preis
    private JButton artikelHinzufuegenButton;                // Button zum Erzeugen eines Artikels

    /**
     * Konstruktor
     */
    public MitarbeiterArtikelHinzufuegenPanel() {
        super();

        artikelName = new JTextField();
        artikelBeschreibung = new JTextField();
        artikelPreis = new JTextField();
        artikelHinzufuegenButton = new JButton("Hinzufügen");

        // Layout Ebene 0
        GridLayout layoutEbene0 = new GridLayout(3, 1);
        layoutEbene0.setHgap(5);        // Lass' 5px Abstand zwischen den Komponenten
        this.setLayout(layoutEbene0);    // 3 Zeilen, 1 Spalten

        // Layout Ebene 1
        JPanel frameEbene1 = new JPanel();
        GridLayout layoutEbene1 = new GridLayout(2, 4);
        layoutEbene1.setHgap(5);        // Lass' 5px Abstand zwischen den Komponenten
        frameEbene1.setLayout(layoutEbene1);    // 2 Zeilen, 4 Spalten



        // Rahmen
        Border b = BorderFactory.createTitledBorder("Neuen Artikel anlegen");
        frameEbene1.setBorder(b);

        // Hinzufuegen der Unterkomponenten (von oben links nach unten rechts) zu Ebene 1
        frameEbene1.add(new JLabel("Name"));
        frameEbene1.add(new JLabel("Beschreibung"));
        frameEbene1.add(new JLabel("Preis"));
        frameEbene1.add(new JLabel());
        frameEbene1.add(artikelName);
        frameEbene1.add(artikelBeschreibung);
        frameEbene1.add(artikelPreis);
        frameEbene1.add(artikelHinzufuegenButton);

        // Hinzufuegen der Komponenten zu Ebene 0
        this.add(frameEbene1);
        this.add(new JPanel());
        this.add(new JPanel());



    }

    /**
     *
     * @return
     */
    public JButton getArtikelHinzufuegenButton() {
        return artikelHinzufuegenButton;
    }

    public String getArtikelName() {
        return artikelName.getText();
    }

    public String getArtikelBeschreibung() {
        return artikelBeschreibung.getText();
    }

    public double getArtikelPreis() {
        String puffer = artikelPreis.getText();
        double puffer2 = Double.parseDouble(puffer);
        return puffer2;
    }

    public void addActionListener(ActionListener a) {
        artikelHinzufuegenButton.addActionListener(a);
    }

    public void resetAllJTextfields(){
        this.artikelName.setText("");
        this.artikelBeschreibung.setText("");
        this.artikelPreis.setText("");
    }


}
