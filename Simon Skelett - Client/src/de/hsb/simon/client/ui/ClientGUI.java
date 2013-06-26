package de.hsb.simon.client.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.hsb.simon.client.net.ClientInterfaceImpl;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

public class ClientGUI extends JFrame {
	
	private static final long serialVersionUID = 8996112056079575641L;

	private JTextArea area;
	private JButton button;
	
	private ClientInterfaceImpl connection;
	
	public ClientGUI() {
		super("Simon Client");
		
		connection = new ClientInterfaceImpl();
		
		this.setLayout(new BorderLayout());
		this.setSize(400, 300);
		
		area = new JTextArea();
		area.setEditable(false);
		
		button = new JButton("Sende ein Hallo");
		
		this.add(new JScrollPane(area), BorderLayout.CENTER);
		this.add(button, BorderLayout.SOUTH);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//
			}
		});
		
		this.setVisible(true);

        try{
            connection.connectToServer();
        } catch (UnknownHostException | LookupFailedException | EstablishConnectionFailed e){
            JOptionPane.showMessageDialog(this, e.getMessage);
        }
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new ClientGUI();
	}
}
