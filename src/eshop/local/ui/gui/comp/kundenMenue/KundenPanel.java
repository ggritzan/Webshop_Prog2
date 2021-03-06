package eshop.local.ui.gui.comp.kundenMenue;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 31.05.13
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 31.05.13
 * Time: 17:16
 * To change this template us File | Settings | File Templates.
 */
public class KundenPanel extends JPanel {
    private JButton artikelButton;
    private JButton warenkorbButton;
    private JButton rechnungenButton;
    private JButton logoutButton;


    public KundenPanel() {
        super();

        artikelButton = new JButton("Artikel");
        warenkorbButton = new JButton("Warenkorb");
        rechnungenButton = new JButton("Rechnungen");
        logoutButton = new JButton("Logout");




        // Layout Ebene 0
        GridLayout layoutEbene0 = new GridLayout(1, 2);
        this.setLayout(layoutEbene0);	// 1 Zeilen, 2 Spalte

        // Layout Ebene 1
        JPanel frameEbene1 = new JPanel();
        GridLayout layoutEbene1 = new GridLayout(15, 1);
        layoutEbene1.setHgap(5);        // Lass' 5px Abstand zwischen den Komponenten
        frameEbene1.setLayout(layoutEbene1);	// 15 Zeilen, 1 Spalte

        // Hinzufügen der Elemente für die Ebene 3
        frameEbene1.add(new JPanel());
        frameEbene1.add(new JPanel());
        frameEbene1.add(artikelButton);
        frameEbene1.add(warenkorbButton);
        frameEbene1.add(rechnungenButton);
        frameEbene1.add(new JPanel());
        frameEbene1.add(new JPanel());
        frameEbene1.add(logoutButton);
        frameEbene1.add(new JPanel());
        frameEbene1.add(new JPanel());
        frameEbene1.add(new JPanel());
        frameEbene1.add(new JPanel());
        frameEbene1.add(new JPanel());
        frameEbene1.add(new JPanel());



        // Hinzufügen der Elemente für die Ebene 0
        this.add(new JPanel());
        this.add(frameEbene1);

    }

    /**
     * Getter fuer den ArtikelButton
     * @return
     */
    public JButton getArtikelButton() {
        return artikelButton;
    }

    /**
     * Getter fuer den WarenkorbButton
     * @return
     */
    public JButton getWarenkorbButton() {
        return warenkorbButton;
    }

    /**
     * Getter fuer den RechnungenButton
     * @return
     */
    public JButton getRechnungenButton() {
        return rechnungenButton;
    }

    /**
     * Getter fuer den LogoutButton
     * @return
     */
    public JButton getLogoutButton() {
        return logoutButton;
    }

    /**
     * Fuegt allen Buttons einen Actionlistener hinzu
     * @param a
     */
    public void addActionListener(ActionListener a) {
        artikelButton.addActionListener(a);
        warenkorbButton.addActionListener(a);
        rechnungenButton.addActionListener(a);
        logoutButton.addActionListener(a);
    }

}


