package eshop.local.valueobjects;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 11.04.13
 * Time: 03:13
 * To change this template use File | Settings | File Templates.
 */
public class Lieferadresse extends Adresse{

    String lieferdatum;

    public Lieferadresse(String vorname, String nachname, String straße, String plz, String ort, String lieferdatum) {
        super(vorname, nachname, straße, plz, ort);
        this.lieferdatum = lieferdatum;
    }
}
