package eshop.local.ui.gui.comp.mitarbeiterMenue.rechnung;

import eshop.local.ui.gui.comp.tableModels.RechnungsArtikelTableModel;
import eshop.local.valueobjects.Artikel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 15.06.13
 * Time: 22:13
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterRechnungsArtikelListePanel extends JPanel {

    // Objekte fuer die korrekte Darstellung der Tabellenansicht
    private JTable table;
    private RechnungsArtikelTableModel tmodel;            // TableModel fuer die RechnungsArtikel-Tabelle
    private RowSorter<RechnungsArtikelTableModel> sorter;
    private Vector<Artikel> alleRechnungsArtikel;

    /**
     * Konstruktor
     * @param alleRechnungsArtikel
     */
    public MitarbeiterRechnungsArtikelListePanel(Vector<Artikel> alleRechnungsArtikel) {

        // überschreibt den lokalen alleRechnungsArtikel Vektor mit den dem Konstruktor übergebenden Artikeln
        this.alleRechnungsArtikel = alleRechnungsArtikel;

        // Tabelle erzeugen und Eigenschaften setzen
        table = new JTable();
        tmodel = new RechnungsArtikelTableModel(this.alleRechnungsArtikel);
        table.setModel(tmodel);
        sorter = new TableRowSorter<RechnungsArtikelTableModel>(tmodel);
        table.setRowSorter(sorter);


        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Artikel der Bestellung");
        this.setBorder(ba);

        // Layout Ebene 0
        BorderLayout layoutEbene0 = new BorderLayout();
        this.setLayout(layoutEbene0);

        // Hinzufügen der Elemente für die Ebene 0
        this.add(new JScrollPane(table), BorderLayout.CENTER);



    }

// Setter
    /**
     * Aktualisiert die im Panel angezeigten RechnungsArtikel
     */
    public void updateTableData(Vector<Artikel> rechnungsArtikel) {
        this.alleRechnungsArtikel = rechnungsArtikel;

    }




// Getter
    /**
     * Getter fuer das RechnungsArtikelTableModel
     * @return
     */
    public RechnungsArtikelTableModel getTmodel() {
        return tmodel;
    }


}
