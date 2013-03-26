/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 26.03.13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
package eshop.local.valueobjects;

public class Mitarbeiter extends Person {

    public Mitarbeiter (int age, int leaf, int zweige, double height, double waterReserve, double stammUmfang, String name) {
        super(age, leaf, height, waterReserve);
        this.zweige = zweige;
        this.stammUmfang = stammUmfang;
        this.name = name;
    }
}
