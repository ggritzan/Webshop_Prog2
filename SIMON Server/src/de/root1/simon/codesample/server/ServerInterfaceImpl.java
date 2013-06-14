package de.root1.simon.codesample.server;

/**
 * Created with IntelliJ IDEA.
 * User: Giacomo
 * Date: 14.06.13
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */

import de.root1.simon.Simon;
import de.root1.simon.exceptions.SimonRemoteException;
import de.root1.simon.codesample.common.ClientCallbackInterface;
import de.root1.simon.codesample.common.ServerInterface;

public class ServerInterfaceImpl implements ServerInterface {

    private static final long serialVersionUID = 1L;

    public void login(ClientCallbackInterface clientCallback) throws SimonRemoteException {

        clientCallback.callback("This is the callback. " +
                "Your address is "+
                Simon.getRemoteInetSocketAddress(clientCallback).getAddress()+" "+
                "and your are connected from port "+
                Simon.getRemoteInetSocketAddress(clientCallback).getPort());

    }
}