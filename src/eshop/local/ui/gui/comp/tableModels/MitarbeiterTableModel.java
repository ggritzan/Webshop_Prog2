package eshop.local.ui.gui.comp.tableModels;

import eshop.local.valueobjects.Mitarbeiter;

import javax.swing.table.AbstractTableModel;
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

    private Vector<Mitarbeiter> mitarbeiterVector;

    private String[] columnNames = {"Mitarbeiternummer","Vorname", "Nachname", "Benutzername", "Passwort", "Email", "Telefon", "Straße", "PLZ", "Ort"};

    private Class<?>[] columnClass = {int.class,String.class, String.class, String.class, String.class, String.class, String.class,String.class, String.class, String.class};

    /**
     * Konstruktor
     * @param alleAktuellenMitarbeiter
     */
    public MitarbeiterTableModel(Vector<Mitarbeiter> alleAktuellenMitarbeiter) {
        this.mitarbeiterVector = alleAktuellenMitarbeiter;
    }

    @Override
    public int getRowCount() {
        return mitarbeiterVector.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Mitarbeiter mitarbeiter = mitarbeiterVector.get(row);

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

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return columnClass[col];
    }

}
