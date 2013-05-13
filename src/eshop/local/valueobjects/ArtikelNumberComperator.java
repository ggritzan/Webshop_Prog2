package eshop.local.valueobjects;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: ninadoge
 * Date: 13.05.13
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
public class ArtikelNumberComperator implements Comparator<Artikel> {
    public int compare(Artikel a1, Artikel a2) {
        String b1 = String.valueOf(a1.getNummer());
        String b2 = String.valueOf(a2.getNummer());
        if (b1 == null && b2 == null) {
            return 0;
        }
        if (b1 == null) {
            return 1;
        }
        if (b2 == null) {
            return -1;
        }
        return b1.compareTo(b2);
    }
}
