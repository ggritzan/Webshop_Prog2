package eshop.local.ui.gui.comp.kundenMenue.warenkorb;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 14.06.13
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */
public class KundenWarenkorbPopup extends JPopupMenu {

    private JMenuItem warenkorbBestellen;
    private JMenuItem bestellteMengeAendern;
    private JMenuItem loeschen;

    /**
     * Konstruktor
     */
    public KundenWarenkorbPopup() {
        super();
        warenkorbBestellen = new JMenuItem("Warenkorb bestellen");
        bestellteMengeAendern = new JMenuItem("bestellte Menge anpassen");
        loeschen = new JMenuItem("löschen");

        this.add(warenkorbBestellen);
        this.add(bestellteMengeAendern);
        this.add(loeschen);
    }

// Gettter

    /**
     * Getter fuer das JMenuItem WarenkorbBestellen
     *
     * @return
     */
    public JMenuItem getWarenkorbBestellen() {
        return warenkorbBestellen;
    }

    /**
     * Getter fuer das JMenuItem bestellteMengeAendern
     *
     * @return
     */
    public JMenuItem getBestellteMengeAendern() {
        return bestellteMengeAendern;
    }

    /**
     * Getter fuer das JMenueItem loeschen
     *
     * @return
     */
    public JMenuItem getLoeschen() {
        return loeschen;
    }


// Actionlistener

    /**
     * Fügt den JMenuItems bestellteMengeAendern & loeschen einen Actionlistener hinzu
     *
     * @param a
     */
    public void addActionListener(ActionListener a) {
        warenkorbBestellen.addActionListener(a);
        bestellteMengeAendern.addActionListener(a);
        loeschen.addActionListener(a);
    }

}
