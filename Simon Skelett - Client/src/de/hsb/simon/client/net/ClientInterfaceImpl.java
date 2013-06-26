package de.hsb.simon.client.net;

import de.hsb.simon.commons.ServerInterface;
import de.hsb.simon.commons.SessionInterface;
import de.root1.simon.Lookup;
import de.root1.simon.Simon;
import de.root1.simon.annotation.SimonRemote;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

import java.lang.Override;
import java.net.UnknownHostException;

@SimonRemote(value = {ClientInterface.class})

public class ClientInterfaceImpl implements ClientInterface {

	private Lookup lookup;
	private ServerInterface server;
	private SessionInterface session;
	
	public ClientInterfaceImpl() {
		//
	}

    public void connenToServer() throws UnknownHostException, LookupFailedException, EstablishConnectionFailed {

        //Lookup für den Server einrichten
        lookup = Simon.createNameLookup("127.0.0.1", 4753);

        //Server-Objekt aufsuchen
        server = (ServerInterface)lookup.lookup("myServer");

        //Client auf dem Server anmelden und Session empfangen
        session = server.login(this);
    }

    public void sendMessage(){
        session.
    }

    @Override

    public void unreference(){

    }
}
