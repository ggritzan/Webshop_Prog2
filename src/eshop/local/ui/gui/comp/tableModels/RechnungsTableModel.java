package eshop.local.ui.gui.comp.tableModels;

import eshop.local.valueobjects.Artikel;
import eshop.local.valueobjects.Rechnung;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 07.06.13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class RechnungsTableModel extends AbstractTableModel {
    private static final long serialVersionUID = -937113488035886184L;

    private Vector<Rechnung> rechnungsVector;


    private String[] columnNames = {"ReNr", "KNr", "bestellte Artikel", "gesamt Preis"};

    private Class<?>[] columnClass = {Integer.class, Integer.class, JComboBox.class, double.class};

    /**
     * Konstruktor
     * @param alleAktuellenRechnungen
     */
    public RechnungsTableModel(Vector<Rechnung> alleAktuellenRechnungen) {
        this.rechnungsVector = alleAktuellenRechnungen;
    }


    @Override
    public int getRowCount() {
        return rechnungsVector.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Rechnung rechnung = rechnungsVector.get(row);


        switch (col) {
            case 0:
                return rechnung.getrNr();
            case 1:
                return rechnung.getkNr();
            case 2:
                return rechnung.getBestellteArtikel();
            case 3:
                return rechnung.getGesamtPreis();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return columnClass[col];
    }

}
