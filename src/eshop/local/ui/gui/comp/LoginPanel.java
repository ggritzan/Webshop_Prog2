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

        // Layout Ebene0
        GridLayout layoutEbene0 = new GridLayout(5, 3); // 5 Zeilen, 3 Spalten
        layoutEbene0.setHgap(5);		// Lass' 5px Abstand zwischen den Komponenten
        this.setLayout(layoutEbene0);	// setzt das Layout für die Ebene0

        // Layout Ebene1
        JPanel ebene1 = new JPanel();   // erzeugt ein neues JPanel
        GridLayout layoutEbene1 = new GridLayout(3, 3); // 3 Zeilen, 3 Spalten
        layoutEbene1.setHgap(5);		// Lass' 5px Abstand zwischen den Komponenten
        ebene1.setLayout(layoutEbene1); // setzt das Layout für die Ebene1

        // Layout Ebene2
        JPanel ebene2 = new JPanel();   // erzeugt ein neues JPanel
        GridLayout layoutEbene2 = new GridLayout(3, 3); // 3 Zeilen, 3 Spalten
        layoutEbene1.setHgap(5);		// Lass' 5px Abstand zwischen den Komponenten
        ebene2.setLayout(layoutEbene2); // setzt das Layout für die Ebene2

        // Rahmen Ebene0
        Border ba = BorderFactory.createTitledBorder("Login");
        this.setBorder(ba);

        // Hinzufuegen der Unterkomponenten zum inneren Layout (von oben links nach unten rechts)
        ebene2.add(new JPanel());
        ebene2.add(new JPanel());
        ebene2.add(new JPanel());
        ebene2.add(registerButton);
        ebene2.add(new JPanel());
        ebene2.add(new JPanel());
        ebene2.add(new JPanel());
        ebene2.add(new JPanel());
        ebene2.add(new JPanel());

        // Hinzufuegen der Unterkomponenten zum inneren Layout (von oben links nach unten rechts)
        ebene1.add(new JPanel());
        ebene1.add(new JLabel("Username"));
        ebene1.add(loginName);
        ebene1.add(new JPanel());
        ebene1.add(new JLabel("Passwort"));
        ebene1.add(loginPassword);
        ebene1.add(new JPanel());
        ebene1.add(new JPanel());
        ebene1.add(loginButton);

        // Hinzufuegen der Unterkomponenten zum äußeren Layout (von oben links nach unten rechts)
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(ebene1);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(ebene2);
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
    }

    /**
     * Gibt den eingebenden Loginnamen als String zurueck
     * @return
     */
    public String getLoginName() {
        return loginName.getText();
    }

    /**
     * Gibt das eingegebende LoginPassword als String zurueck
     * @return
     */
    public String getLoginPassword() {

        return loginPassword.getText();

    }

    /**
     * Gibt den LoginButton zurueck
     * @return
     */
    public JButton getLoginButton() {
        return loginButton;
    }

    /**
     * gibt den RegisterButton zurueck
     * @return
     */
    public JButton getRegisterButton() {
        return registerButton;
    }

    /**
     * Fuegt den Buttons (loginButton, registerButton) ActionListener hinzu
     * @param a
     */
    public void addActionListener(ActionListener a) {
        loginButton.addActionListener(a);
        registerButton.addActionListener(a);
    }

    /**
     * Löscht alle JTextfields des LoginPanels
     */
    public void resetJTextfields() {
        loginName.setText("");
        loginPassword.setText("");
    }

}
