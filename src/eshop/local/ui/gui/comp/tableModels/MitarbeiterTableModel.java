package eshop.local.ui.gui.comp.tableModels;

import eshop.local.valueobjects.Mitarbeiter;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 07.06.13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -5473198412969576184L;

    private HashMap<Integer, Mitarbeiter> mitarbeiterHashMap;

    private String[] columnNames = {"MNr","Vorname", "Nachname", "Benutzername", "Passwort", "Email", "Telefon", "Straße", "PLZ", "Ort"};

    private Class<?>[] columnClass = {Integer.class,String.class, String.class, String.class, String.class, String.class, String.class,String.class, String.class, String.class};

    /**
     * Konstruktor
     * @param alleAktuellenMitarbeiter
     */
    public MitarbeiterTableModel(HashMap<Integer, Mitarbeiter> alleAktuellenMitarbeiter) {
        this.mitarbeiterHashMap = alleAktuellenMitarbeiter;
    }

    /**
     * definiert die Anzahl der Spalten
     * @return
     */
    @Override
    public int getRowCount() {
        return mitarbeiterHashMap.size();
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

        Vector<Mitarbeiter> getValue = new Vector<Mitarbeiter>();

        // Füllt den ArtikelVector getValue mit den Artikel aus der Hashmap
        for (Mitarbeiter elem : mitarbeiterHashMap.values()){
            getValue.add(elem);
        }

        Mitarbeiter mitarbeiter = getValue.get(row);

        switch (col) {
            case 0:
                return mitarbeiter.getmNr();
            case 1:
                return mitarbeiter.getVorname();
            case 2:
                return mitarbeiter.getNachname();
            case 3:
                return mitarbeiter.getBenutzername();
            case 4:
                return mitarbeiter.getPasswort();
            case 5:
                return mitarbeiter.getEmail();
            case 6:
                return mitarbeiter.getTelefon();
            case 7:
                return mitarbeiter.getAdresse().getStraße();
            case 8:
                return mitarbeiter.getAdresse().getPlz();
            case 9:
                return mitarbeiter.getAdresse().getOrt();
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
