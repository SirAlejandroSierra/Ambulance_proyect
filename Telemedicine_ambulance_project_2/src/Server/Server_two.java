/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Server.ClientThread;
import Patient.Patient;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Server_two implements Runnable {
    private String password;
    private ServerOnWindowController window;
    private int portNumber;
    private ServerSocket socket;
    public ArrayList<Patient> patients;
    private ArrayList<Socket> clients;
    private ArrayList<ClientThread> clientThreads;
    public ObservableList<String> serverLog;
    public ObservableList<String> clientNames;

    public Server_two(int portNumber, ServerOnWindowController window, String password) throws IOException {
        this.window=window;
        this.portNumber = portNumber;
        this.password=password;
        serverLog = FXCollections.observableArrayList();
        clientNames = FXCollections.observableArrayList();
        patients = new ArrayList<Patient>();
        clients = new ArrayList<Socket>();
        clientThreads = new ArrayList<ClientThread>();
        socket = new ServerSocket(portNumber);

    }

    public ServerSocket getSocket(){
        return socket;
    }

    public String getPassword(){
        return password;
    }

    public ServerOnWindowController getWindow(){
        return window;
    }

    public  ArrayList<Socket> getClients(){
        return clients;
    }
    public  ArrayList<ClientThread> getClientThreads(){
        return clientThreads;
    }
    public  ArrayList<Patient> getPatients(){
        return patients;
    }

    public void setPassword(String newPassword){
        this.password=newPassword;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }



    public void run() {
        
        while (socket.isClosed()==false) {
            Socket clientSocket=null;
            try{
                clientSocket = socket.accept();
            }catch(Exception e){
                System.out.println("exception");
                break;
            }

            clients.add(clientSocket);

            ClientThread clientThreadHolderClass = new ClientThread(
                                        clientSocket, this, window);
            Thread clientThread = new Thread(clientThreadHolderClass);
            clientThreads.add(clientThreadHolderClass);
            System.out.println("accepted");
            clientThread.start();
        }
           
    }

    public void close(){
        for(ClientThread client: clientThreads){
            client.releaseResources();
        }
        
        for(Socket sock: clients){
            try {
                sock.close();
                System.out.println("close sockets");
            } catch (IOException ex) {
                Logger.getLogger(Server_two.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        try {
            socket.close();
            System.out.println("close socket");
        } catch (IOException ex) {
            Logger.getLogger(Server_two.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void clientDisconnected(Socket socket, ClientThread client) {
        clients.remove(socket);
        clientThreads.remove(client);
    }

}
