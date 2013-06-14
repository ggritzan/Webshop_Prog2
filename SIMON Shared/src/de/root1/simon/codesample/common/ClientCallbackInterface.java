package de.root1.simon.codesample.common;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 14.06.13
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */


import de.root1.simon.SimonRemote;
import de.root1.simon.exceptions.SimonRemoteException;

public interface ClientCallbackInterface extends SimonRemote {

    public void callback(String text) throws SimonRemoteException;

}