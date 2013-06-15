package eshop.local.ui.gui.comp.kundenMenue.artikel;

import eshop.local.ui.gui.comp.mitarbeiterMenue.artikel.MitarbeiterArtikelHinzufuegenPanel;
import eshop.local.ui.gui.comp.mitarbeiterMenue.artikel.MitarbeiterMassengutartikelHinzufuegenPanel;
import eshop.local.ui.gui.comp.tableModels.ArtikelTableModel;
import eshop.local.valueobjects.Artikel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 14.06.13
 * Time: 13:24
 * To change this template use File | Settings | File Templates.
 */
public class KundenArtikelListePanel extends JPanel {

    // Objekte fuer die korrekte Darstellung der Tabellenansicht
    private JTable table;
    private ArtikelTableModel tmodel;            // TableModel fuer die Artikel-Tabelle
    private RowSorter<ArtikelTableModel> sorter;
    private KundenArtikelDetailsPanel addKundenArtikelDetailsPanel;

    public KundenArtikelListePanel(HashMap<Integer,Artikel> alleArtikel) {

        // Initialisierung eines KundenArtikelDetailsPanel
        addKundenArtikelDetailsPanel   = new KundenArtikelDetailsPanel("");

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

        // Hinzufügen der Elemente für die Ebene 0
        this.add(new JScrollPane(table));
        this.add(addKundenArtikelDetailsPanel);


    }


    public void addMouseListener(MouseAdapter mA) {
        table.addMouseListener(mA);
    }


//Setter
    public void updateKundenArtikelDetailsPanel(String artikelDetails){
          addKundenArtikelDetailsPanel.updateKundenArtikelDetailsPanel(artikelDetails);
          this.removeAll();
          this.add(new JScrollPane(table));
          this.add(addKundenArtikelDetailsPanel);
    }

// Getter
    public ArtikelTableModel getTmodel() {
        return tmodel;
    }

}
