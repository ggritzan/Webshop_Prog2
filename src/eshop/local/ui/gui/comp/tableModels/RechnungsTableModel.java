package eshop.local.ui.gui.comp.tableModels;

import eshop.local.valueobjects.Rechnung;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
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

    private HashMap<Integer, Rechnung> rechnungsHashMap;

    private String[] columnNames = {"ReNr", "KNr", "bestellte Artikel", "gesamt Preis"};

    private Class<?>[] columnClass = {Integer.class, Integer.class, JComboBox.class, double.class};

    /**
     * Konstruktor
     * @param alleAktuellenRechnungen
     */
    public RechnungsTableModel(HashMap<Integer, Rechnung> alleAktuellenRechnungen) {
        this.rechnungsHashMap = alleAktuellenRechnungen;
    }

    /**
     * definiert die Anzahl der Spalten
     * @return
     */
    @Override
    public int getRowCount() {
        return rechnungsHashMap.size();
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

        Vector<Rechnung> getValue = new Vector<Rechnung>();

        // Füllt den ArtikelVector getValue mit den Artikel aus der Hashmap
        for (Rechnung elem : rechnungsHashMap.values()){
            getValue.add(elem);
        }

        Rechnung rechnung = getValue.get(row);


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
