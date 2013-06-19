package eshop.local.ui.gui.comp.tableModels;

import eshop.local.valueobjects.Artikel;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 15.06.13
 * Time: 21:16
 * To change this template use File | Settings | File Templates.
 */


public class RechnungsArtikelTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -5473134131233586184L;


    private Vector<Artikel> artikelVector;

    private String[] columnNames = {"ArtNr", "Name", "Preis", "bestellte Menge"};

    private Class<?>[] columnClass = {Integer.class, String.class, Double.class, Integer.class};

    /**
     * Konstruktor
     * @param alleAktuellenArtikel
     */
    public RechnungsArtikelTableModel(Vector<Artikel> alleAktuellenArtikel) {
        this.artikelVector = alleAktuellenArtikel;

    }

    /**
     * definiert die Anzahl der Zeilen
     * @return
     */
    @Override
    public int getRowCount() {
        return artikelVector.size();
    }

    /**
     * definiert die Anzahl der Spalten
     * @return
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Methode um einen Wert aus der Tabelle auszulesen
     * @param row
     * @param col
     * @return
     */
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
                return artikel.getBestellteMenge();
            default:
                return null;
        }

    }

    /**
     * gibt den Namen eines Zeileneintrags an der Stelle "col" zurueck
     * @param col
     * @return
     */
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    /**
     * gibt die Klasse eines Zeileneintrags an der Stelle "col" zurueck
     * @param col
     * @return
     */
    @Override
    public Class<?> getColumnClass(int col) {
        return columnClass[col];
    }

// Setter
    /**
     * Aktualisiert den artikelVector
     */
    public void updateTableData(Vector<Artikel> rechnungsArtikel) {
        this.artikelVector = rechnungsArtikel;

    }
}
