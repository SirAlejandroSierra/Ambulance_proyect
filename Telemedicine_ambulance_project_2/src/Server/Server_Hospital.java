/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server_Hospital {

    public static void main(String args[]) throws IOException, ClassNotFoundException {
 
        ServerSocket serverSocket = new ServerSocket(9000);
        
        try {
            while (true) {
                Socket socketObject = serverSocket.accept();
                System.out.println("Welcome to HOSPITAL CEU server");
                
                System.out.println("    If you desire to shut down the server in any moment, type finish.");
                
                System.out.println("\nConnection with ambulance created\n\n");
                new Thread(new Server_Hospital_Chat_Client_Server(socketObject)).start();
                
                Socket socketChat = serverSocket.accept();
                
                new Thread(new Server_Hospital_Thread_Object(socketChat)).start();
                //new Thread(new Server_Hospital_Chat_Server_Client(socketChat)).start();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            closeServer(serverSocket);
        }
    }

    private static void closeServer(ServerSocket socketServidor) {/* releaseResources() ESTE METODO NO DEBERÍA EXISTIR*/
        try {
            socketServidor.close();
        } catch (IOException ex) {
            Logger.getLogger(Server_Hospital.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
