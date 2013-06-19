package eshop.local.ui.gui.comp.mitarbeiterMenue.bestandsDiagram;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 19.06.13
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterBestandsDiagram extends JPanel {

    private int offsetx;
    private int offsety;
    private Vector<Integer> xAbstandswerte;
    private Vector<Integer> yBestandswerte;

    /**
     * Konstruktor für das MitarbeiterBestandsDiagram
     */
    public MitarbeiterBestandsDiagram() {


        // erzeugt einen Vector fuer die Abstandswerte
        xAbstandswerte = new Vector<Integer>();
        // erzeugt den Vector fuer die Bestandwerte
        yBestandswerte = new Vector<Integer>();

    }

    protected void paintGraph( Graphics g )
    {
        System.out.println("bis hier funktioniere ich noch 1");
        super.paintComponent( g );
        int arrayGroesse = yBestandswerte.size();
        int aktuellePosition = 0;
        System.out.println("bis hier funktioniere ich noch 2");
        do {
            g.drawLine(xAbstandswerte.get(aktuellePosition) + offsetx, offsety - yBestandswerte.get(aktuellePosition), xAbstandswerte.get((aktuellePosition)+1) + offsetx, offsety - yBestandswerte.get((aktuellePosition)+1));
            g.drawString(yBestandswerte.get(aktuellePosition).toString(), xAbstandswerte.get(aktuellePosition) + offsetx, offsety - yBestandswerte.get(aktuellePosition) );
            aktuellePosition++;

        } while (aktuellePosition < arrayGroesse-1 );
        g.drawString(yBestandswerte.get(aktuellePosition).toString(), xAbstandswerte.get(aktuellePosition) + offsetx, offsety - yBestandswerte.get(aktuellePosition) );
        System.out.println("bis hier funktioniere ich noch 3");
    }




    public void artikelBestandGraphenzeichnen(Vector<Integer> yBestandVector) {
        // speicher die gesamtgroesse des Panels in der Variable d
        Dimension d = this.getSize();
        // Versatz auf der X Achse
        offsetx = (20) ;
        System.out.println("" + offsetx);
        // Versatz auf der Y AChse
        offsety =  (int) d.getHeight() -20;
        System.out.println("" + offsety);
        this.yBestandswerte = yBestandVector;
        int abstand = (this.getWidth() -20) / yBestandswerte.size() ;

        for (int i = 0; i < yBestandswerte.size(); i++) {
            this.xAbstandswerte.add(abstand*i);
        }
        paintGraph(this.getGraphics());
    }




}
