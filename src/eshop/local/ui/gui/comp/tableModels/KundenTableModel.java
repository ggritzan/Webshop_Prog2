package eshop.local.ui.gui.comp.tableModels;

import eshop.local.valueobjects.Kunde;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 07.06.13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class KundenTableModel extends AbstractTableModel {

    private static final long serialVersionUID = -5473134880839576184L;

    private Vector<Kunde> kundenVector;

    private String[] columnNames = {"KNr","Vorname", "Nachname", "Benutzername", "Passwort", "Email", "Telefon", "Straße", "PLZ", "Ort"};

    private Class<?>[] columnClass = {Integer.class,String.class, String.class, String.class, String.class, String.class, String.class,String.class, String.class, String.class};

    /**
     * Konstruktor
     * @param alleAktuellenKunden
     */
    public KundenTableModel(Vector<Kunde> alleAktuellenKunden) {
        this.kundenVector = alleAktuellenKunden;
    }

    /**
     * definiert die Anzahl der Spalten
     * @return
     */
    @Override
    public int getRowCount() {
        return kundenVector.size();
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
        Kunde kunde = kundenVector.get(row);

        switch (col) {
            case 0:
                return kunde.getNummer();
            case 1:
                return kunde.getVorname();
            case 2:
                return kunde.getNachname();
            case 3:
                return kunde.getBenutzername();
            case 4:
               return kunde.getPasswort();
            case 5:
               return kunde.getEmail();
            case 6:
               return kunde.getTelefon();
            case 7:
               return kunde.getAdresse().getStraße();
            case 8:
               return kunde.getAdresse().getPlz();
            case 9:
               return kunde.getAdresse().getOrt();
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
