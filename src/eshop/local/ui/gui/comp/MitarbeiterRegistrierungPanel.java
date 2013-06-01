package eshop.local.ui.gui.comp;

import eshop.local.domain.EShopVerwaltung;
import eshop.local.exception.BenutzernameExistiertBereitsException;
import eshop.local.exception.LeereEingabeException;
import eshop.local.exception.MitarbeiterExistiertBereitsException;
import eshop.local.valueobjects.Adresse;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 01.06.13
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class MitarbeiterRegistrierungPanel extends JPanel {

    private JTextField vorname;
    private JTextField nachname;
    private JTextField benutzername;
    private JTextField passwort;
    private JTextField email;
    private JTextField telefon;
    private JTextField straße;
    private JTextField plz;
    private JTextField ort;
    private JButton MitarbeiterRegisterButton;

    /**
     * Konstructor
     */
    public MitarbeiterRegistrierungPanel() {
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
        MitarbeiterRegisterButton = new JButton("Register");

        // Layout außen
        GridLayout layout = new GridLayout(20, 4);
        layout.setHgap(15);        // Lass' 5px Abstand zwischen den Komponenten
        this.setLayout(layout);    // 20 Zeilen, 4 Spalten

        // Rahmen außen
        Border ba = BorderFactory.createTitledBorder("Mitarbeiterregistrierung");
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
        this.add(MitarbeiterRegisterButton);
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

        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());
        this.add(new JPanel());


    }

    /**
     * @return
     */
    public JButton getMitarbeiterRegisterButton() {
        return MitarbeiterRegisterButton;
    }

    /**
     * Fuegt dem Button den gegebenen ActionListener hinzu
     *
     * @param a
     */
    public void addActionListener(ActionListener a) {
        MitarbeiterRegisterButton.addActionListener(a);

    }

    public void neuenMitarbeiterAnlegen(EShopVerwaltung eShopVerwaltung) throws IOException, LeereEingabeException {



        try {



            String vorname = this.vorname.getText();

            String nachname = this.nachname.getText();

            String benutzername = this.benutzername.getText();

            String passwort = this.passwort.getText();

            String email = this.email.getText();

            String telefon = this.telefon.getText();

            String straße = this.straße.getText();

            String plz = this.plz.getText();

            String ort = this.ort.getText();

            if (!(vorname.isEmpty() | nachname.isEmpty() | benutzername.isEmpty() | passwort.isEmpty() | email.isEmpty() | email.isEmpty() | telefon.isEmpty() | straße.isEmpty() | plz.isEmpty() | ort.isEmpty())) {
                Adresse adresse = new Adresse(vorname, nachname, straße, plz, ort);

                eShopVerwaltung.fuegeMitarbeiterEin(vorname, nachname, benutzername, passwort, email, telefon, adresse);

            } else {
                throw new LeereEingabeException();
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        } catch (MitarbeiterExistiertBereitsException mebe) {
            System.err.println(mebe.getMessage());
        }

    }

}
