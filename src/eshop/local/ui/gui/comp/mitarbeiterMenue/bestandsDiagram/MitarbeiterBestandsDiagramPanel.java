package eshop.local.ui.gui.comp.mitarbeiterMenue.bestandsDiagram;

import eshop.local.valueobjects.ArtikelBestandsGraph;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 09.06.13
 * Time: 18:34
 * To change this template use File | Settings | File Templates.
 */


public class MitarbeiterBestandsDiagramPanel extends JPanel {


    private MitarbeiterBestandsDiagram addMitarbeiterBestandsDiagram;
    private JButton repaintButton;

    public MitarbeiterBestandsDiagramPanel() {
        super();

        addMitarbeiterBestandsDiagram = new MitarbeiterBestandsDiagram();
        repaintButton = new JButton("Repaint");

        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Bestandsdiagramm");
        this.setBorder(ba);

        // Layout Ebene 0
        GridLayout layoutEbene0 = new GridLayout(2, 1);
        this.setLayout(layoutEbene0);	// 2 Zeilen, 1 Spalte

        // Hinzufügen der Elemente für die Ebene 0

        this.add(addMitarbeiterBestandsDiagram);
        this.add(repaintButton);

    }

    public JButton getRepaintButton() {
        return repaintButton;
    }

    public void artikelBestandGraphenzeichnen(Vector<ArtikelBestandsGraph> artikelBestandObjekte) {
        Vector<Integer> intGraph = new Vector<Integer>();

        for (int i = 0; i < artikelBestandObjekte.size(); i++) {
           intGraph.add(artikelBestandObjekte.get(i).getBestand());

        }

        addMitarbeiterBestandsDiagram.artikelBestandGraphenzeichnen(intGraph);

    }

    public void addMouseListener(MouseAdapter mA) {
        repaintButton.addMouseListener(mA);
    }
}

