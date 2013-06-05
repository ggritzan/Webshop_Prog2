package eshop.local.ui.gui.comp;

import eshop.local.valueobjects.Artikel;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 05.06.13
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
public class ArtikelTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -5473134880835886184L;

    private Vector<Artikel> artikelVector;

    private String[] columnNames = {"ArtNr", "Bezeichnung", "Preis", "Bestand"};

    private Class<?>[] columnClass = {Integer.class, String.class, Float.class, Integer.class};


    public ArtikelTableModel(Vector<Artikel> alleAktuellenArtikel) {
        this.artikelVector = alleAktuellenArtikel;
    }


    @Override
    public int getRowCount() {
        return artikelVector.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Artikel artikel = artikelVector.get(row);

        switch (col) {
            case 0:
                return artikel.getNummer();
            case 1:
                return artikel.getName();
            case 2:
                return artikel.getPreis();
            case 3:
                return artikel.getBestand();
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
