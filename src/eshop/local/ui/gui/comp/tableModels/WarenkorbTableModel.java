package eshop.local.ui.gui.comp.tableModels;

import eshop.local.valueobjects.Artikel;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 14.06.13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public class WarenkorbTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -937113488035807424L;

    private HashMap<Integer, Artikel> warenkorbHashMap;

    private String[] columnNames = {"ArtikelNr", "Name", "bestellte Menge", "Preis"};

    private Class<?>[] columnClass = {Integer.class, String.class, Integer.class, double.class};

    /**
     * Konstruktor
     * @param alleArtikelImWarenkorb
     */
    public WarenkorbTableModel(HashMap<Integer, Artikel> alleArtikelImWarenkorb) {
        this.warenkorbHashMap = alleArtikelImWarenkorb;
    }

    /**
     * definiert die Anzahl der Spalten
     * @return
     */
    @Override
    public int getRowCount() {
        return warenkorbHashMap.size();
    }

    /**
     * definiert die Anzahl der Zeilen
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

        Vector<Artikel> getValue = new Vector<Artikel>();

        // Füllt den ArtikelVector getValue mit den Artikel aus der Hashmap
        for (Artikel elem : warenkorbHashMap.values()){
            getValue.add(elem);
        }

        Artikel artikel = getValue.get(row);


        switch (col) {
            case 0:
                return artikel.getNummer();
            case 1:
                return artikel.getName();
            case 2:
                return artikel.getBestellteMenge();
            case 3:
                return artikel.getPreis();
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


}
