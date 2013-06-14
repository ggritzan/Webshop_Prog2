package eshop.local.ui.gui.comp.mitarbeiterMenue.bestandsDiagram;

import eshop.local.valueobjects.ArtikelBestandsGraph;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 09.06.13
 * Time: 18:34
 * To change this template use File | Settings | File Templates.
 */


public class MitarbeiterBestandsDiagramPanel extends JPanel {

    // Erstellung eines Integer Vektor für die Bestandswerte des Artikels
    Vector<Integer> data = new Vector<Integer>();


    public MitarbeiterBestandsDiagramPanel(Vector<ArtikelBestandsGraph> artikelLog) {


        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Bestandsdiagramm");
        this.setBorder(ba);

        // Layout Ebene 0
        BorderLayout layoutEbene0 = new BorderLayout();
        this.setLayout(layoutEbene0);

        // Für alle übergebenen ArtikelBestandsGraph Objekte wird der Int wert des Bestands ausgelesen
        for (int i = 0; i < artikelLog.size(); i++) {
            int bestand = ((ArtikelBestandsGraph) artikelLog.elementAt(i)).getBestand();
            data.add(bestand);
        };


    }


}



