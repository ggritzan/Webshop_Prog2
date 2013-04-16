package eshop.local.valueobjects;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 16.04.13
 * Time: 01:40
 * To change this template use File | Settings | File Templates.
 */
public class Rechnung {

    // noch nicht fertig

    public void ausgabe(Kunde k, Artikel artikel){
        System.out.println("Vorname:" + k.getVorname());
        System.out.println("Nachname:" + k.getNachname());
        System.out.println("Kundennummer:" + k.getkNr());
        System.out.println("E-Mail:" + k.getEmail());
        System.out.println("Adresse:" + k.getAdresse());
    }

}
