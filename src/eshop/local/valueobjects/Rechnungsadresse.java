package eshop.local.valueobjects;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 11.04.13
 * Time: 02:00
 * To change this template use File | Settings | File Templates.
 */
public class Rechnungsadresse extends Adresse {

    // Eigenschaften zur Beschreibung einer Rechnungsadresse
    private String email;

    /**
     * Konstruktor zuma anlegen einer neuen Rechnungsadresse
     *
     * @param vorname -> Vorname desjenigen der die Rechnung erhält, im weiteren der Kunde
     * @param nachname -> Nachname des Kunden
     * @param straße -> Straßenname der Straße in der der Kunde wohnt
     * @param plz -> Postleihzahl des Kunden
     * @param ort -> Name des Orts in dem der Kunde lebt
     * @param email -> Emailadresse des Kunden
     */
    public Rechnungsadresse(String vorname, String nachname, String straße, String plz, String ort, String email) {
        super(vorname, nachname, straße, plz, ort);
        this.email = email;
    }

    // Setter

    /**
     * Setter für die email
     *
     * @param email -> Die neue Emailadresse
     */
    public void setEmail(String email){
        this.email = email;
    }

    // Getter

    /**
     * Getter für die Emailadresse
     *
     * @return String -> Die Emailadresse
     */
    public String getEmail(){
        return this.email;
    }
}

