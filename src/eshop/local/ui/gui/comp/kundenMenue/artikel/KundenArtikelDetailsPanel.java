package eshop.local.ui.gui.comp.kundenMenue.artikel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 14.06.13
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */

public class KundenArtikelDetailsPanel extends JTextArea {

        private JTextArea textField;


    public KundenArtikelDetailsPanel(String artikelDetails) {

        textField = new JTextArea(artikelDetails);

        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Artikel Details");
        this.setBorder(ba);

        // Layout Ebene 0
        BorderLayout layoutEbene0 = new BorderLayout();
        this.setLayout(layoutEbene0);    // 2 Zeilen, 1 Spalte

        textField.setLineWrap(true);

        textField.setWrapStyleWord(true);

        // Hinzufügen der Elemente für die Ebene 0
        this.add(new JScrollPane(textField), BorderLayout.CENTER);



    }

    /**
     * Updatet den Text de Detail Panels
     */
    public void updateKundenArtikelDetailsPanel(String artikelDetails) {
        textField.setText(artikelDetails);
        textField.validate();
    }






}
