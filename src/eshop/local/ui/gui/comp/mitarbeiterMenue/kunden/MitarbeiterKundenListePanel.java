package eshop.local.ui.gui.comp.mitarbeiterMenue.kunden;

import eshop.local.ui.gui.comp.registrierung.KundenRegistrierungPanel;
import eshop.local.ui.gui.comp.tableModels.KundenTableModel;

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
 * Time: 00:47
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterKundenListePanel extends JPanel{

    // Objekte fuer die korrekte Darstellung der Tabellenansicht
    private JTable table;
    private KundenTableModel tmodel;            // TableModel fuer die Kunden-Tabelle
    private RowSorter<KundenTableModel> sorter;
    private KundenRegistrierungPanel addKundenRegistrierungPanel;


    public MitarbeiterKundenListePanel(Vector alleKunden) {
        // Tabelle erzeugen und Eigenschaften setzen
        table = new JTable();
        tmodel = new KundenTableModel(alleKunden);
        table.setModel(tmodel);
        sorter = new TableRowSorter<KundenTableModel>(tmodel);
        table.setRowSorter(sorter);


        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Kundenmenü");
        this.setBorder(ba);

        // Layout Ebene 0
        BorderLayout layoutEbene0 = new BorderLayout();
        this.setLayout(layoutEbene0);


        // Kundenregistrierungspanel erzeugen
        addKundenRegistrierungPanel = new KundenRegistrierungPanel();

        // Hinzufügen der Elemente für die Ebene 0
        this.add(new JScrollPane(table), BorderLayout.CENTER);


    }





    public void addMouseListener(MouseAdapter mA) {
        table.addMouseListener(mA);
    }

}
