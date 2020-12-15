/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telemedicine_ambulance_project_2;

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
import static telemedicine_ambulance_project_2.Server_Hospital_Thread_Object.releaseObjectInputStream;

/* Thread Class for each incoming client */
public class ClientThread implements Runnable {

        private Patient patient;
	/* The socket of the client */
	private Socket clientSocket;
	/* Server class from which thread was called */
	private Server_two baseServer;
	private ObjectInputStream objectInputStream;
	/* The name of the client */
	private String clientName;

	public ClientThread(Socket clientSocket, Server_two baseServer) {
		this.setClientSocket(clientSocket);
		this.baseServer = baseServer;
		try {
			/*
			 * Reader to get all incoming messages that the client passes to the
			 * server
			 */
                        
			objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		
                try {
                    Object tmp;
                    tmp = objectInputStream.readObject();
                    patient = (Patient) tmp;
                    
                    baseServer.patients.add(patient);
                   
                    System.out.println(patient.toString());

                    releaseObjectInputStream(objectInputStream);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Server_Hospital_Thread_Object.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        
        public static void releaseObjectInputStream(ObjectInputStream o) {

        try {
            o.close();
        } catch (IOException ex) {
            Logger.getLogger(Server_Hospital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
