package eshop.local.ui.gui.comp.kundenMenue.rechnung;

import eshop.local.ui.gui.comp.tableModels.KundenRechnungsTableModel;
import eshop.local.ui.gui.comp.tableModels.RechnungsArtikelTableModel;
import eshop.local.ui.gui.comp.tableModels.RechnungsTableModel;
import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Rechnung;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 15.06.13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class KundenRechnungsListePanel extends JPanel {
    // Objekte fuer die korrekte Darstellung der Tabellenansicht
    private JTable table;
    private KundenRechnungsTableModel tmodel;            // TableModel fuer die Rechnungs-Tabelle
    private RowSorter<KundenRechnungsTableModel> sorter;
    private KundenRechnungsArtikelListePanel addKundenRechnungsArtikelListePanel;

    /**
     * Konstruktor
     * @param alleRechnungen
     */
    public KundenRechnungsListePanel(Vector<Rechnung> alleRechnungen) {
        // Tabelle erzeugen und Eigenschaften setzen
        table = new JTable();
        tmodel = new KundenRechnungsTableModel(alleRechnungen);
        table.setModel(tmodel);
        sorter = new TableRowSorter<KundenRechnungsTableModel>(tmodel);
        table.setRowSorter(sorter);


        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Rechnungsmenü");
        this.setBorder(ba);

        // Layout Ebene 0
        GridLayout layoutEbene0 = new GridLayout(2, 1);
        this.setLayout(layoutEbene0);    // 2 Zeilen, 1 Spalte


        // Hinzufügen der Elemente für die Ebene 0
        this.add(new JScrollPane(table));
        this.add(new JPanel());
    }

    /**
     * Initialisiert den Mouselistener
     * @param mA
     */
    public void addMouseListener(MouseAdapter mA) {
        table.addMouseListener(mA);
    }


// Setter
    /**
     * Erzeugt entweder ein neues KundenRechnungsArtikelListePanel mit den RechnungsArtikel oder
     * aktualisiert die im KundenRechnungsArtikelListePanel angezeigten RechnungsArtikel
     */
    public void updateTableData(Vector<Artikel> rechnungsArtikel) {

        if(addKundenRechnungsArtikelListePanel == null){
            addKundenRechnungsArtikelListePanel = new KundenRechnungsArtikelListePanel(rechnungsArtikel);
            // entfernt alle Komponenten vom Panel
            this.removeAll();
            // Hinzufügen der Elemente für die Ebene 0
            this.add(new JScrollPane(table));
            this.add(new JScrollPane(addKundenRechnungsArtikelListePanel));
        } else {
            this.addKundenRechnungsArtikelListePanel.updateTableData(rechnungsArtikel);
            this.addKundenRechnungsArtikelListePanel.getTmodel().fireTableDataChanged();
            // entfernt alle Komponenten vom Panel
            this.removeAll();
            // Hinzufügen der Elemente für die Ebene 0
            this.add(new JScrollPane(table));
            this.add(new JScrollPane(addKundenRechnungsArtikelListePanel));

        }



    }


// Getter

    /**
     * Getter fuer der TableModel
     * @return
     */
    public KundenRechnungsTableModel getTmodel() {
        return tmodel;
    }

    /**
     * Getter fuer der TableModel des MitarbeiterRechnungsArtikelListePanel
     * @return
     */
    public RechnungsArtikelTableModel getRechnungArtikelTmodel() {
        return addKundenRechnungsArtikelListePanel.getTmodel();
    }
}