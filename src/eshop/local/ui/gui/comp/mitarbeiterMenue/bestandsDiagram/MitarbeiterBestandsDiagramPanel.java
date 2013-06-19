package eshop.local.ui.gui.comp.mitarbeiterMenue.bestandsDiagram;

import eshop.local.valueobjects.ArtikelBestandsGraph;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
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
    Vector<Integer> yBestandswerte = new Vector<Integer>();
    Vector<Integer> xAbstandswerte = new Vector<Integer>();


    public MitarbeiterBestandsDiagramPanel() {

        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Bestandsdiagramm");
        this.setBorder(ba);

        // Layout Ebene 0
        BorderLayout layoutEbene0 = new BorderLayout();
        this.setLayout(layoutEbene0);

     }



    public void drawBestandsDiagram(Vector<ArtikelBestandsGraph> artikelLog, int abstandswert){
        // erzeugt den Vector fuer die Bestandwerte
        yBestandswerte = new Vector<Integer>();

        // erzeugt einen Vector fuer die Abstandswerte
        xAbstandswerte = new Vector<Integer>();
        // Füllt den Vector mit den Abstaenden
        int abstandsPuffer = abstandswert;
        for (int i = 0; i < artikelLog.size(); i++) {
            xAbstandswerte.add(abstandsPuffer);
            abstandsPuffer = abstandsPuffer + abstandswert;
        }

        // Für alle übergebenen ArtikelBestandsGraph Objekte wird der Int wert des Bestands ausgelesen
        for (int i = 0; i < artikelLog.size(); i++) {
            int bestand = ((ArtikelBestandsGraph) artikelLog.elementAt(i)).getBestand();
            yBestandswerte.add(bestand);
        };



    }

    public void paintComponent(Graphics g){
        Dimension d = this.getSize();
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
        g.drawLine(0,0,90,90);

    }





}



