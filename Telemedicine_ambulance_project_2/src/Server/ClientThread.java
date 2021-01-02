/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Patient.Patient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Logger;

import javafx.application.Platform;
import Server.Server_Hospital;
import Server.Server_two;
import java.io.InputStream;
import java.util.logging.Level;

/* Thread Class for each incoming client */
public class ClientThread implements Runnable {
        private ServerOnWindowController window;
        private Patient patient;
	/* The socket of the client */
	private Socket clientSocket;
	/* Server class from which thread was called */
	private Server_two baseServer;
	private ObjectInputStream fromClient;
	/* The name of the client */
	private String clientName;
        private String received="";

	public ClientThread(Socket clientSocket, Server_two baseServer, ServerOnWindowController window) {
		this.clientSocket=clientSocket;
		this.baseServer = baseServer;
                this.window = window;
		try {
			/*
			 * Reader to get all incoming messages that the client passes to the
			 * server
			 */
			fromClient = new ObjectInputStream(clientSocket.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		
                try {
                    Object tmp;
                    tmp = fromClient.readObject();
                    patient = (Patient) tmp;
                    window.chatWindow.appendText(patient.getAmbulance()+":  connected \n");
                    baseServer.patients.add(patient);
                   
                    System.out.println(patient.toString());
                    
                    while (true) {
                        received = (String) fromClient.readObject();
                        if (received.toLowerCase().contains("stop")) {
                            System.out.println("---The ambulance stopped the connection");
                            releaseResources(fromClient, clientSocket);
                            break;
                        }

                        window.chatWindow.appendText(patient.getAmbulance() + ":  " + received + "\n");
                        //System.out.println("    Ambulance: " + received);
            }
                    
                } catch (IOException ex) {
                    window.chatWindow.appendText(patient.getAmbulance()+":  disconnected \n");
                    baseServer.clientDisconnected(clientSocket);
                    
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

	public String getClientName() {
		return this.clientName;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}
        
        public Patient getPatient() {
		return patient;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
   
        private static void releaseResources(ObjectInputStream o, Socket socket) {
        try {
            o.close();
        } catch (IOException ex) {
            Logger.getLogger(Server_Hospital.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server_Hospital.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
