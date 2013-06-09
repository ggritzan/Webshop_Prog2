package eshop.local.ui.gui.comp.mitarbeiterMenue.rechnung;

import eshop.local.ui.gui.comp.tableModels.RechnungsTableModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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


    public MitarbeiterRechnungsListePanel(Vector alleRechnungen) {
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
        BorderLayout layoutEbene0 = new BorderLayout();
        this.setLayout(layoutEbene0);

        // Hinzufügen der Elemente für die Ebene 0
        this.add(new JScrollPane(table), BorderLayout.CENTER);





    }


    public void addMouseListener(MouseAdapter mA) {
        table.addMouseListener(mA);
    }




}
