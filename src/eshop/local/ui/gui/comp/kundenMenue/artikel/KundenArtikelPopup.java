package eshop.local.ui.gui.comp.kundenMenue.artikel;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 14.06.13
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
public class KundenArtikelPopup extends JPopupMenu {

    private JMenuItem demWarenkorbHinzufuegen;


    /**
     *  Konstruktor
     */
    public KundenArtikelPopup() {
        super();

        demWarenkorbHinzufuegen = new JMenuItem("dem Warenkorb hinzufügen");


        this.add(demWarenkorbHinzufuegen);

    }

// Gettter
    /**
     * Getter fuer das JMenuItem demWarenkorbHinzufuegen
     * @return
     */
    public JMenuItem getdemWarenkorbHinzufuegen() {
        return demWarenkorbHinzufuegen;
    }



// Actionlistener
    /**
     * Fügt den JMenuItems demWarenkorbHinzufuegen & artikelBeschreibungAnzeigen einen Actionlistener hinzu
     * @param a
     */
    public void addActionListener(ActionListener a) {
        demWarenkorbHinzufuegen.addActionListener(a);

    }

}
