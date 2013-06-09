package eshop.local.ui.gui.comp.mitarbeiterMenue.mitarbeiter;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 09.06.13
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterMitarbeiterPopup extends JPopupMenu {
    private JMenuItem loeschen;

    /**
     * Konstruktor
     */
    public MitarbeiterMitarbeiterPopup() {
        super();

        loeschen = new JMenuItem("löschen");

        this.add(loeschen);
    }

    /**
     * Getter fuer das JMenueItem loeschen
     *
     * @return
     */
    public JMenuItem getLoeschen() {
        return loeschen;
    }

    /**
     * Fügt den JMenuItems loeschen einen Actionlistener hinzu
     *
     * @param a
     */
    public void addActionListener(ActionListener a) {
        loeschen.addActionListener(a);
    }
}
