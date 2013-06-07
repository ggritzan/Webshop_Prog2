package eshop.local.ui.gui.comp.mitarbeiterMenue;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 07.06.13
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterArtikelPopup extends JPopupMenu {

    private JMenuItem bestandAendern;
    private JMenuItem loeschen;

    /**
     *  Konstruktor
     */
    public MitarbeiterArtikelPopup() {
        super();

        bestandAendern = new JMenuItem("Bestand ändern");
        loeschen = new JMenuItem("löschen");

        this.add(bestandAendern);
        this.add(loeschen);
    }

    /**
     * Getter fuer das JMenuItem bestandAendern
     * @return
     */
    public JMenuItem getBestandAendern() {
        return bestandAendern;
    }

    /**
     * Getter fuer das JMenueItem loeschen
     * @return
     */
    public JMenuItem getLoeschen() {
        return loeschen;
    }

    /**
     * Fügt den JMenuItems bestandAendern & loeschen einen Actionlistener hinzu
     * @param a
     */
    public void addActionListener(ActionListener a) {
        bestandAendern.addActionListener(a);
        loeschen.addActionListener(a);
    }

}
