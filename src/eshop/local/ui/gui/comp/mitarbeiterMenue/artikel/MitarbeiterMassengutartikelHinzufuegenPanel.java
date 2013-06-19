package eshop.local.ui.gui.comp.mitarbeiterMenue.artikel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 09.06.13
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterMassengutartikelHinzufuegenPanel extends JPanel {

    private static final long serialVersionUID = 6711014823212243842L;

    private JTextField artikelName;            // Textfeld fuer den Artikelname
    private JTextField artikelBeschreibung;    // Textfeld fuer die Beschreibung
    private JTextField artikelPreis;           // Textfeld fuer den Preis
    private JTextField artikelPackungsgroesse;        // Textfeld fuer die Packungsgröße
    private JButton MassengutartikelHinzufuegenButton;                // Button zum Erzeugen eines Artikels

    /**
     * Konstruktor
     */
    public MitarbeiterMassengutartikelHinzufuegenPanel() {
        super();

        artikelName = new JTextField();
        artikelBeschreibung = new JTextField();
        artikelPreis = new JTextField();
        artikelPackungsgroesse = new JTextField();
        MassengutartikelHinzufuegenButton = new JButton("Hinzufügen");



        // Layout Ebene 0
        GridLayout layoutEbene0 = new GridLayout(2, 5);
        layoutEbene0.setHgap(5);        // Lass' 5px Abstand zwischen den Komponenten
        this.setLayout(layoutEbene0);    // 2 Zeilen, 5 Spalten



        // Rahmen
        Border b = BorderFactory.createTitledBorder("Neuen Massengutartikel anlegen");
        this.setBorder(b);

        // Hinzufuegen der Unterkomponenten (von oben links nach unten rechts) zu Ebene 0
        this.add(new JLabel("Name"));
        this.add(new JLabel("Beschreibung"));
        this.add(new JLabel("Preis"));
        this.add(new JLabel("Packungsgröße"));
        this.add(new JLabel());
        this.add(artikelName);
        this.add(artikelBeschreibung);
        this.add(artikelPreis);
        this.add(artikelPackungsgroesse);
        this.add(MassengutartikelHinzufuegenButton);





    }

    /**
     *
     * @return
     */
    public JButton getMassengutArtikelHinzufuegenButton() {
        return MassengutartikelHinzufuegenButton;
    }

    public String getMassengutartikelName() {
        return artikelName.getText();
    }

    public String getMassengutartikelBeschreibung() {
        return artikelBeschreibung.getText();
    }

    public double getMassengutartikelPreis() {
        String puffer = artikelPreis.getText();
        double puffer2 = Double.parseDouble(puffer);
        return puffer2;
    }

    public int getMassengutartikelPackungsgroesse() {
        String puffer = artikelPackungsgroesse.getText();
        int puffer2 = Integer.parseInt(puffer);
        return puffer2;
    }

    public void addActionListener(ActionListener a) {
        MassengutartikelHinzufuegenButton.addActionListener(a);
    }

    public void resetAllJTextfields(){
        this.artikelName.setText("");
        this.artikelBeschreibung.setText("");
        this.artikelPreis.setText("");
        this.artikelPackungsgroesse.setText("");
    }

}
