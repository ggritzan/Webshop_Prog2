package eshop.local.ui.gui.comp;


import eshop.local.domain.EShopVerwaltung;
import eshop.local.exception.BenutzernameExistiertNichtException;
import eshop.local.exception.KundenNummerExistiertNichtException;
import eshop.local.exception.LeereEingabeException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginPanel extends JPanel{

    private JTextField loginName;	    // Textfeld fuer den Loginnamen
    private JTextField loginPassword;	// Textfeld für das Passwort
    private JButton loginButton;		// Button zum einloggen

    /**
     *  Konstructor
     *
     */
    public LoginPanel() {
        super();

        loginName = new JTextField();
        loginPassword = new JTextField();
        loginButton = new JButton("Login");

        // Eigenschaften
        Dimension d = new Dimension(100,150);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setMaximumSize(d);


        // Layout außen
        GridLayout layout = new GridLayout(5, 3);
        layout.setHgap(5);		// Lass' 5px Abstand zwischen den Komponenten
        this.setLayout(layout);	// 3 Zeilen, 2 Spalte

        // Layout innen
        JPanel frame = new JPanel();
        GridLayout layoutlog = new GridLayout(3, 3);
        layoutlog.setHgap(5);		// Lass' 5px Abstand zwischen den Komponenten
        frame.setLayout(layoutlog);

        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Login Screen");
        this.setBorder(ba);

        // Rahmen innen
        Border bi = BorderFactory.createTitledBorder("Login");
        this.setBorder(bi);



        // Hinzufuegen der Unterkomponenten zum inneren Layout (von oben links nach unten rechts)
        frame.add(new JPanel());
        frame.add(new JLabel("Username"));
        frame.add(loginName);
        frame.add(new JPanel());
        frame.add(new JLabel("Passwort"));
        frame.add(loginPassword);
        frame.add(new JPanel());
        frame.add(new JPanel());
        frame.add(loginButton);

        // // Hinzufuegen der Unterkomponenten zum äußeren Layout (von oben links nach unten rechts)
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(frame);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());



    }

    /**
     * Fuegt dem Button den gegebenen ActionListener hinzu
     * @param a
     */
    public void addActionListener(ActionListener a) {
        loginButton.addActionListener(a);
    }

    /**
     *
     * @param eShopVerwaltung
     * @return 1 für Mitarbeiter / 2 für Kunde / 0 keines von beiden
     * @throws IOException
     */
    public char verarbeiteLogin(EShopVerwaltung eShopVerwaltung) throws IOException {

            String bName = loginName.getText();
            String bPasswort = loginPassword.getText();

            if (eShopVerwaltung.findeMitarbeiter(bName, bPasswort)) {
                System.out.println("Ihr Login war erfolgreich.");
                System.out.println("Willkommen Mitarbeiter");
                return 'm';

            } else if (eShopVerwaltung.findeKunden(bName, bPasswort)) {
                System.out.println("Ihr Login war erfolgreich.");
                System.out.println("Willkommen Kunde");
                return 'k';


            } else {
                System.err.println("Benutzername oder Passwort leider falsch!");
                return 'u';

            }

    }


}
