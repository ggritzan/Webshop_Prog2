package eshop.local.ui.gui.comp.mitarbeiterMenue.rechnung;

import eshop.local.ui.gui.comp.tableModels.RechnungsArtikelTableModel;
import eshop.local.ui.gui.comp.tableModels.RechnungsTableModel;
import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Rechnung;

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
 * Date: 09.06.13
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterRechnungsListePanel extends JPanel {
    // Objekte fuer die korrekte Darstellung der Tabellenansicht
    private JTable table;
    private RechnungsTableModel tmodel;            // TableModel fuer die Rechnungs-Tabelle
    private RowSorter<RechnungsTableModel> sorter;
    private MitarbeiterRechnungsArtikelListePanel addMitarbeiterRechnungsArtikelListePanel;

    /**
     * Konstruktor
     * @param alleRechnungen
     */
    public MitarbeiterRechnungsListePanel(HashMap<Integer,Rechnung> alleRechnungen) {



        // Tabelle erzeugen und Eigenschaften setzen
        table = new JTable();
        tmodel = new RechnungsTableModel(alleRechnungen);
        table.setModel(tmodel);
        sorter = new TableRowSorter<RechnungsTableModel>(tmodel);
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
     * Erzeugt entweder ein neues MitarbeiterRechnungsArtikelListePanel mit den RechnungsArtikel oder
     * aktualisiert die im MitarbeiterRechnungsArtikelListePanel angezeigten RechnungsArtikel
     */
    public void updateTableData(Vector<Artikel> rechnungsArtikel) {

        if(addMitarbeiterRechnungsArtikelListePanel == null){
            addMitarbeiterRechnungsArtikelListePanel = new MitarbeiterRechnungsArtikelListePanel(rechnungsArtikel);
            // entfernt alle Komponenten vom Panel
            this.removeAll();
            // Hinzufügen der Elemente für die Ebene 0
            this.add(new JScrollPane(table));
            this.add(new JScrollPane(addMitarbeiterRechnungsArtikelListePanel));
        } else {
            this.addMitarbeiterRechnungsArtikelListePanel.updateTableData(rechnungsArtikel);
            this.addMitarbeiterRechnungsArtikelListePanel.getTmodel().fireTableDataChanged();
            // entfernt alle Komponenten vom Panel
            this.removeAll();
            // Hinzufügen der Elemente für die Ebene 0
            this.add(new JScrollPane(table));
            this.add(new JScrollPane(addMitarbeiterRechnungsArtikelListePanel));

        }



    }


// Getter

    /**
     * Getter fuer der TableModel
     * @return
     */
    public RechnungsTableModel getTmodel() {
        return tmodel;
    }

    /**
     * Getter fuer der TableModel des MitarbeiterRechnungsArtikelListePanel
     * @return
     */
    public RechnungsArtikelTableModel getRechnungArtikelTmodel() {
        return addMitarbeiterRechnungsArtikelListePanel.getTmodel();
    }
}
