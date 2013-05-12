package eshop.local.persistence;

import java.io.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Noshaba
 * Date: 12.05.13
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
public class Log {

    public void write (File dateiName, String text) throws IOException {
        BufferedWriter schreibeStrom = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dateiName, true)));
        schreibeStrom.write(text);
        schreibeStrom.newLine();
        schreibeStrom.close();
    }
}
