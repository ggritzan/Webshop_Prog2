package eshop.local.valueobjects;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 11.04.13
 * Time: 01:33
 * To change this template use File | Settings | File Templates.
 */
public class Kundenadresse extends Adresse{

    //kNr fehlt noch...

    private String email;
    private String telefon;

    public Kundenadresse(String vorname, String nachname, String straße, String plz, String ort, String email, String telefon) {
        super(vorname, nachname, straße, plz, ort);
        this.email = email;
        this.telefon = telefon;
    }

    public String getEmail(){
        return this.email;
    }

    public String getTelefon(){
        return this.telefon;
    }
}
