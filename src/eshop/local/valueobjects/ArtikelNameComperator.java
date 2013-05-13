package eshop.local.valueobjects;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: ninadoge
 * Date: 13.05.13
 * Time: 15:26
 * To change this template use File | Settings | File Templates.
 */
public class ArtikelNameComperator implements Comparator<Artikel> {
    public int compare(Artikel a1, Artikel a2) {
        if (a1.getName() == null && a2.getName() == null) {
            return 0;
        }
        if (a1.getName() == null) {
            return 1;
        }
        if (a2.getName() == null) {
            return -1;
        }
        return a1.getName().compareTo(a2.getName());
    }
}
