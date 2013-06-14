package de.root1.simon.codesample.client;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 14.06.13
 * Time: 11:21
 * To change this template use File | Settings | File Templates.
 */


import de.root1.simon.exceptions.SimonRemoteException;
import de.root1.simon.codesample.common.ClientCallbackInterface;

public class ClientCallbackImpl implements ClientCallbackInterface {

    private static final long serialVersionUID = 1L;

    public void callback(String text) throws SimonRemoteException {

        System.out.println("This message was received from the server: "+text);

    }
}