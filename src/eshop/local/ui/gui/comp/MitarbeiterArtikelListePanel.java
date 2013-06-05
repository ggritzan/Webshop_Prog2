package eshop.local.ui.gui.comp;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 05.06.13
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterArtikelListePanel extends JPanel {

    // Objekte fuer die korrekte Darstellung der Tabellenansicht
    private JTable table;
    private ArtikelTableModel tmodel;            // TableModel fuer die Artikel-Tabelle
    private MitarbeiterArtikelHinzufuegenPanel addMitarbeiterArtikelHinzufuegenPanel;

    public MitarbeiterArtikelListePanel(Vector alleArtikel) {
        // Tabelle erzeugen und Eigenschaften setzen
        table = new JTable();
        tmodel = new ArtikelTableModel(alleArtikel);
        table.setModel(tmodel);

        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Artikelmenü");
        this.setBorder(ba);

        // Layout Ebene 0
        GridLayout layoutEbene0 = new GridLayout(2, 1);
        this.setLayout(layoutEbene0);    // 1 Zeilen, 2 Spalte


        // Initialisierung eines addMitarbeiterArtikelHinzufuegenPanel
        addMitarbeiterArtikelHinzufuegenPanel   = new MitarbeiterArtikelHinzufuegenPanel();

        // Hinzufügen der Elemente für die Ebene 0
        this.add(table);
        this.add(addMitarbeiterArtikelHinzufuegenPanel);


    }



    public void addActionListener(ActionListener a) {
        addMitarbeiterArtikelHinzufuegenPanel.addActionListener(a);
    }

    public String getArtikelName() {
        return addMitarbeiterArtikelHinzufuegenPanel.getArtikelName();
    }

    public String getArtikelBeschreibung() {
        return addMitarbeiterArtikelHinzufuegenPanel.getArtikelBeschreibung();
    }

    public double getArtikelPreis() {
        return addMitarbeiterArtikelHinzufuegenPanel.getArtikelPreis();
    }

    public JButton getArtikelHinzufuegenButton() {
        return addMitarbeiterArtikelHinzufuegenPanel.getArtikelHinzufuegenButton();
    }
}