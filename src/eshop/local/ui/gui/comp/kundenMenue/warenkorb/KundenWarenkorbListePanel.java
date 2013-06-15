package eshop.local.ui.gui.comp.kundenMenue.warenkorb;

import eshop.local.ui.gui.comp.kundenMenue.artikel.KundenArtikelDetailsPanel;
import eshop.local.ui.gui.comp.tableModels.WarenkorbTableModel;
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
 * Date: 14.06.13
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */


public class KundenWarenkorbListePanel extends JPanel {

    // Objekte fuer die korrekte Darstellung der Tabellenansicht
    private JTable table;
    private WarenkorbTableModel tmodel;            // TableModel fuer die Warenkorb-Tabelle
    private RowSorter<WarenkorbTableModel> sorter;
    private KundenArtikelDetailsPanel addKundenArtikelDetailsPanel;



    public KundenWarenkorbListePanel(HashMap<Integer, Artikel> alleArtikelImWarenkorb) {

        // Initialisierung eines KundenArtikelDetailsPanel
        addKundenArtikelDetailsPanel   = new KundenArtikelDetailsPanel("");

        // Tabelle erzeugen und Eigenschaften setzen
        table = new JTable();
        tmodel = new WarenkorbTableModel(alleArtikelImWarenkorb);
        table.setModel(tmodel);
        sorter = new TableRowSorter<WarenkorbTableModel>(tmodel);
        table.setRowSorter(sorter);


        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Warenkorb");
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

//Getter

    // Gibt das TableModel zurueck
    public WarenkorbTableModel getTmodel() {
        return tmodel;
    }
}
