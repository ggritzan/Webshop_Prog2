package eshop.local.ui.gui.comp.registrierung;

import eshop.local.domain.EShopVerwaltung;
import eshop.local.exception.BenutzernameExistiertBereitsException;
import eshop.local.exception.LeereEingabeException;
import eshop.local.valueobjects.Adresse;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 31.05.13
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public class KundenRegistrierungPanel extends JPanel {

    private JTextField vorname;
    private JTextField nachname;
    private JTextField benutzername;
    private JTextField passwort;
    private JTextField email;
    private JTextField telefon;
    private JTextField straße;
    private JTextField plz;
    private JTextField ort;
    private JButton kundenRegisterButton;
    private JButton backToLoginButton;

    /**
     * Konstructor
     */
    public KundenRegistrierungPanel() {
        super();


        vorname = new JTextField();
        nachname = new JTextField();
        benutzername = new JTextField();
        passwort = new JTextField();
        email = new JTextField();
        telefon = new JTextField();
        straße = new JTextField();
        plz = new JTextField();
        ort = new JTextField();
        kundenRegisterButton = new JButton("Register");
        backToLoginButton = new JButton("Back");
        // Layout Ebene 0
        GridLayout layout = new GridLayout(20, 4);
        layout.setHgap(15);        // Lass' 5px Abstand zwischen den Komponenten
        this.setLayout(layout);    // 20 Zeilen, 4 Spalten

        // Rahmen Ebene 1
        Border ba = BorderFactory.createTitledBorder("Kundenregistrierung");
        this.setBorder(ba);

        // Hinzufuegen der Unterkomponenten zum inneren Layout (von oben links nach unten rechts)
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());


        this.add(new JLabel("Vorname:"));
        this.add(vorname);
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JLabel("Nachname:"));
        this.add(nachname);
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JLabel("Benutzername:"));
        this.add(benutzername);
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JLabel("Passwort:"));
        this.add(passwort);
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JLabel("Email:"));
        this.add(email);
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JLabel("Telefon:"));
        this.add(telefon);
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JLabel("Straße:"));
        this.add(straße);
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JLabel("PLZ:"));
        this.add(plz);
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JLabel("Ort:"));
        this.add(ort);
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(kundenRegisterButton);
        this.add(new JPanel());

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(backToLoginButton);
        this.add(new JPanel());

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());


    }

    /**
     * Getter für kundenRegisterButton
     * @return
     */
    public JButton getKundenRegisterButton() {
        return kundenRegisterButton;
    }

    /**
     * Getter für backToLoginButton
     * @return
     */
    public JButton getBackToLoginButton() {
        return backToLoginButton;
    }

    /**
     * Getter für JTextfield vorname
     * @return
     */
    public JTextField getVorname() {
        return vorname;
    }

    /**
     * Getter für JTextfield nachname
     * @return
     */
    public JTextField getNachname() {
        return nachname;
    }

    /**
     * Getter für JTextfield benutzername
     * @return
     */
    public JTextField getBenutzername() {
        return benutzername;
    }

    /**
     * Getter für JTextfield passwort
     * @return
     */
    public JTextField getPasswort() {
        return passwort;
    }

    /**
     * Getter für JTextfield email
     * @return
     */
    public JTextField getEmail() {
        return email;
    }

    /**
     * Getter für JTextfield telefon
     * @return
     */
    public JTextField getTelefon() {
        return telefon;
    }

    /**
     * Getter für JTextfield plz
     * @return
     */
    public JTextField getPlz() {
        return plz;
    }

    /**
     * Getter für JTextfield straße
     * @return
     */
    public JTextField getStraße() {
        return straße;
    }

    /**
     * Getter für JTextfield ort
     * @return
     */
    public JTextField getOrt() {
        return ort;
    }

    /**
     * Fuegt dem kundenRegisterButton & dem backToLoginButton einen ActionListener hinzu
     *
     * @param a
     */
    public void addActionListener(ActionListener a) {
        kundenRegisterButton.addActionListener(a);
        backToLoginButton.addActionListener(a);

    }

    /**
     * Löscht alle JTextfields der Kundenregistrierung
     */
    public void resetAllJTextfields() {
        vorname.setText("");
        nachname.setText("");
        benutzername.setText("");
        passwort.setText("");
        email.setText("");
        telefon.setText("");
        straße.setText("");
        plz.setText("");
        ort.setText("");
    }


}
