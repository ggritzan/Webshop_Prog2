package eshop.local.ui.gui.comp.mitarbeiterMenue.mitarbeiter;

import eshop.local.ui.gui.comp.registrierung.MitarbeiterRegistrierungPanel;
import eshop.local.ui.gui.comp.tableModels.MitarbeiterTableModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 08.06.13
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterMitarbeiterListePanel extends JPanel {
    // Objekte fuer die korrekte Darstellung der Tabellenansicht
    private JTable table;
    private MitarbeiterTableModel tmodel;            // TableModel fuer die Kunden-Tabelle
    private RowSorter<MitarbeiterTableModel> sorter;
    private MitarbeiterRegistrierungPanel addMitarbeiterRegistrierungPanel;


    public MitarbeiterMitarbeiterListePanel(Vector alleMitarbeiter) {
        // Tabelle erzeugen und Eigenschaften setzen
        table = new JTable();
        tmodel = new MitarbeiterTableModel(alleMitarbeiter);
        table.setModel(tmodel);
        sorter = new TableRowSorter<MitarbeiterTableModel>(tmodel);
        table.setRowSorter(sorter);


        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Mitarbeitermenü");
        this.setBorder(ba);

        // Layout Ebene 0
        GridLayout layoutEbene0 = new GridLayout(2, 1);
        this.setLayout(layoutEbene0);    // 1 Zeilen, 2 Spalte


        // Kundenregistrierungspanel erzeugen
        addMitarbeiterRegistrierungPanel = new MitarbeiterRegistrierungPanel();

        // Hinzufügen der Elemente für die Ebene 0
        this.add(new JScrollPane(table));
        this.add(addMitarbeiterRegistrierungPanel);
}

    public void addMouseListener(MouseAdapter mA) {
        table.addMouseListener(mA);
    }
}
