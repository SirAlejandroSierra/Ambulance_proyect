/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.ClientThread;
import Patient.Patient;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Server_two implements Runnable {
	private int portNumber;
	private ServerSocket socket;
        public ArrayList<Patient> patients;
	private ArrayList<Socket> clients;
	private ArrayList<ClientThread> clientThreads;
	public ObservableList<String> serverLog;
	public ObservableList<String> clientNames;
        
	public Server_two(int portNumber) throws IOException {
            this.portNumber = portNumber;
            serverLog = FXCollections.observableArrayList();
            clientNames = FXCollections.observableArrayList();
            patients = new ArrayList<Patient>();
            clients = new ArrayList<Socket>();
            clientThreads = new ArrayList<ClientThread>();
            socket = new ServerSocket(portNumber);
		
	}

	public void run() {

            try {
		/* Infinite loop to accept any incoming connection requests */
		while (true) {
                    
                    Socket clientSocket = socket.accept();
                    
                    /* Add the incoming socket connection to the list of clients */
                    clients.add(clientSocket);
                    
                    ClientThread clientThreadHolderClass = new ClientThread(
						clientSocket, this);
                    Thread clientThread = new Thread(clientThreadHolderClass);
                    clientThreads.add(clientThreadHolderClass);
                    System.out.println("accepted");
                    clientThread.setDaemon(true);
                    clientThread.start();}
            } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
            }
	}

	public void clientDisconnected(ClientThread client) {

            Platform.runLater(new Runnable() {

            @Override
            public void run() {
		// TODO Auto-generated method stub
		serverLog.add("Client "
			+ client.getClientSocket().getRemoteSocketAddress()
			+ " disconnected");
		clients.remove(clientThreads.indexOf(client));
		clientNames.remove(clientThreads.indexOf(client));
		clientThreads.remove(clientThreads.indexOf(client));
            }
	});
		
		
	}

}
