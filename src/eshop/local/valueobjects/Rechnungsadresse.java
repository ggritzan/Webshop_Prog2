package eshop.local.valueobjects;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 11.04.13
 * Time: 02:00
 * To change this template use File | Settings | File Templates.
 */
public class Rechnungsadresse extends Adresse {

    private String email;

    public Rechnungsadresse(String vorname, String nachname, String straße, String plz, String ort, String email) {
        super(vorname, nachname, straße, plz, ort);
        this.email = email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }
}

