package eshop.local.ui.gui.comp.tableModels;

import eshop.local.valueobjects.Rechnung;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 17.06.13
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */
public class KundenRechnungsTableModel extends AbstractTableModel {
    private static final long serialVersionUID = -937113488035886184L;

    private Vector<Rechnung> rechnungsVector;


    private String[] columnNames = {"ReNr", "KNr", "gesamt Preis"};

    private Class<?>[] columnClass = {Integer.class, Integer.class, double.class};

    /**
     * Konstruktor
     * @param alleAktuellenRechnungen
     */
    public KundenRechnungsTableModel(Vector<Rechnung> alleAktuellenRechnungen) {
        this.rechnungsVector = alleAktuellenRechnungen;
    }



    /**
     * definiert die Anzahl der Spalten
     * @return
     */
    @Override
    public int getRowCount() {
        return rechnungsVector.size();
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

        Rechnung rechnung = rechnungsVector.get(row);


        switch (col) {
            case 0:
                return rechnung.getrNr();
            case 1:
                return rechnung.getkNr();
            case 2:
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

