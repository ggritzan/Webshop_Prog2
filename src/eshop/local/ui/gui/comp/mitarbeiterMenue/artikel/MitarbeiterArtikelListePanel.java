package eshop.local.ui.gui.comp.mitarbeiterMenue.artikel;

import eshop.local.ui.gui.comp.tableModels.ArtikelTableModel;
import eshop.local.valueobjects.Artikel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.HashMap;
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
    private RowSorter<ArtikelTableModel> sorter;
    private MitarbeiterArtikelHinzufuegenPanel addMitarbeiterArtikelHinzufuegenPanel;
    private MitarbeiterMassengutartikelHinzufuegenPanel addMitarbeiterMassengutartikelHinzufuegenPanel;

    public MitarbeiterArtikelListePanel(HashMap<Integer,Artikel> alleArtikel) {
        // Tabelle erzeugen und Eigenschaften setzen
        table = new JTable();
        tmodel = new ArtikelTableModel(alleArtikel);
        table.setModel(tmodel);
        sorter = new TableRowSorter<ArtikelTableModel>(tmodel);
        table.setRowSorter(sorter);


        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Artikelmenü");
        this.setBorder(ba);

        // Layout Ebene 0
        GridLayout layoutEbene0 = new GridLayout(2, 1);
        this.setLayout(layoutEbene0);    // 2 Zeilen, 1 Spalte

        // Layoout Ebene 1
        JPanel frameEbene1 = new JPanel();
        GridLayout layoutEbene1 = new GridLayout(5, 1);
        frameEbene1.setLayout(layoutEbene1);    // 5 Zeilen, 1 Spalte




        // Initialisierung eines addMitarbeiterArtikelHinzufuegenPanel
        addMitarbeiterArtikelHinzufuegenPanel   = new MitarbeiterArtikelHinzufuegenPanel();

        // Initialisierung eines addMitarbeiterMassengutartikelHinzufuegenPanel
        addMitarbeiterMassengutartikelHinzufuegenPanel   = new MitarbeiterMassengutartikelHinzufuegenPanel();

        // Hinzufügen der Elemente für die Ebene 1
        frameEbene1.add(new JPanel());
        frameEbene1.add(addMitarbeiterArtikelHinzufuegenPanel);
        frameEbene1.add(new JPanel());
        frameEbene1.add(addMitarbeiterMassengutartikelHinzufuegenPanel);
        frameEbene1.add(new JPanel());

        // Hinzufügen der Elemente für die Ebene 0
        this.add(new JScrollPane(table));
        this.add(frameEbene1);


    }



    public void addActionListener(ActionListener aL) {
        addMitarbeiterArtikelHinzufuegenPanel.addActionListener(aL);
        addMitarbeiterMassengutartikelHinzufuegenPanel.addActionListener(aL);
    }

    public void addMouseListener(MouseAdapter mA) {
        table.addMouseListener(mA);
    }

    public ArtikelTableModel getTmodel() {
        return tmodel;
    }

    // Fuer normale Artikel
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


    // Fuer Massengutartikel
    public String getMassengutArtikelName() {
        return addMitarbeiterMassengutartikelHinzufuegenPanel.getMassengutartikelName();
    }

    public String getMassengutArtikelBeschreibung() {
        return addMitarbeiterMassengutartikelHinzufuegenPanel.getMassengutartikelBeschreibung();
    }

    public double getMassengutartikelPreis() {
        return addMitarbeiterMassengutartikelHinzufuegenPanel.getMassengutartikelPreis();
    }

    public int getMassengutartikelPackungsgroesse() {
        return addMitarbeiterMassengutartikelHinzufuegenPanel.getMassengutartikelPackungsgroesse();
    }

    public JButton getMassengutartikelHinzufuegenButton() {
        return addMitarbeiterMassengutartikelHinzufuegenPanel.getMassengutArtikelHinzufuegenButton();
    }



    // Setzt alle JTextfield des Artikel hinzufügen Panels zurück
    public void resetAllJTextfields(){
        addMitarbeiterArtikelHinzufuegenPanel.resetAllJTextfields();
        addMitarbeiterMassengutartikelHinzufuegenPanel.resetAllJTextfields();
    }
}
