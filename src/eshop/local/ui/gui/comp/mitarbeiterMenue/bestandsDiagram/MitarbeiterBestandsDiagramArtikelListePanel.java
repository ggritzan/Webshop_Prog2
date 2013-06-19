package eshop.local.ui.gui.comp.mitarbeiterMenue.bestandsDiagram;

import eshop.local.ui.gui.comp.tableModels.ArtikelTableModel;
import eshop.local.valueobjects.Artikel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 19.06.13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterBestandsDiagramArtikelListePanel extends JPanel {
    // Objekte fuer die korrekte Darstellung der Tabellenansicht
    private JTable table;
    private ArtikelTableModel tmodel;            // TableModel fuer die Artikel-Tabelle
    private RowSorter<ArtikelTableModel> sorter;


    public MitarbeiterBestandsDiagramArtikelListePanel(HashMap<Integer,Artikel> alleArtikel) {
        // Tabelle erzeugen und Eigenschaften setzen
        table = new JTable();
        tmodel = new ArtikelTableModel(alleArtikel);
        table.setModel(tmodel);
        sorter = new TableRowSorter<ArtikelTableModel>(tmodel);
        table.setRowSorter(sorter);


        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Artikelauswahl");
        this.setBorder(ba);

        // Layout Ebene 0
        BorderLayout layoutEbene0 = new BorderLayout();
        this.setLayout(layoutEbene0);

        // Hinzufügen der Elemente für die Ebene 0
        this.add(new JScrollPane(table), BorderLayout.CENTER);



    }

    public void addMouseListener(MouseAdapter mA) {
        table.addMouseListener(mA);
    }

    public ArtikelTableModel getTmodel() {
        return tmodel;
    }





}
