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
    private JPasswordField loginPassword;	// Textfeld für das Passwort
    private JButton loginButton;		// Button zum einloggen
    private JButton registerButton;     // Button um sich neu zu registrieren

    /**
     *  Konstructor
     *
     */
    public LoginPanel() {
        super();

        loginName = new JTextField();
        loginPassword = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        /** // Eigenschaften
        Dimension d = new Dimension(100,150);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setMaximumSize(d);
        */

        // Layout außen
        GridLayout layout = new GridLayout(5, 3);
        layout.setHgap(5);		// Lass' 5px Abstand zwischen den Komponenten
        this.setLayout(layout);	// 5 Zeilen, 3 Spalte

        // Layout innen
        JPanel framelog = new JPanel();
        GridLayout layoutlog = new GridLayout(3, 3);
        layoutlog.setHgap(5);		// Lass' 5px Abstand zwischen den Komponenten
        framelog.setLayout(layoutlog);

        JPanel frameregister = new JPanel();
        GridLayout layoutregister = new GridLayout(3, 3);
        layoutlog.setHgap(5);		// Lass' 5px Abstand zwischen den Komponenten
        frameregister.setLayout(layoutregister);


        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Login Screen");
        this.setBorder(ba);

        // Rahmen innen
        Border bi = BorderFactory.createTitledBorder("Login");
        this.setBorder(bi);



        // Hinzufuegen der Unterkomponenten zum inneren Layout (von oben links nach unten rechts)
        framelog.add(new JPanel());
        framelog.add(new JLabel("Username"));
        framelog.add(loginName);
        framelog.add(new JPanel());
        framelog.add(new JLabel("Passwort"));
        framelog.add(loginPassword);
        framelog.add(new JPanel());
        framelog.add(new JPanel());
        framelog.add(loginButton);

        // Hinzufuegen der Unterkomponenten zum inneren Layout (von oben links nach unten rechts)
        frameregister.add(new JPanel());
        frameregister.add(new JPanel());
        frameregister.add(new JPanel());
        frameregister.add(registerButton);
        frameregister.add(new JPanel());
        frameregister.add(new JPanel());
        frameregister.add(new JPanel());
        frameregister.add(new JPanel());
        frameregister.add(new JPanel());




        // // Hinzufuegen der Unterkomponenten zum äußeren Layout (von oben links nach unten rechts)
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(framelog);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(frameregister);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());




    }

    public String getLoginName() {
        return loginName.getText();
    }

    public String getLoginPassword() {

        return loginPassword.getText();

    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    /**
     * Fuegt dem Button den gegebenen ActionListener hinzu
     * @param a
     */
    public void addActionListener(ActionListener a) {
        loginButton.addActionListener(a);
        registerButton.addActionListener(a);
    }

    /**
     * Löscht alle JTextfields
     */
    public void resetJTextfields() {
        loginName.setText("");
        loginPassword.setText("");

    }

}
