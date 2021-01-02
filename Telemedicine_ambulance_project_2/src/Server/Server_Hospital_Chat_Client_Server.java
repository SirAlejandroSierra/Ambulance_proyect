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

public class Server_Hospital_Chat_Client_Server implements Runnable {
    private String received;
    private Socket socket;

    public Server_Hospital_Chat_Client_Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while ((received = bufferedReader.readLine()) != null) {

                if (received.toLowerCase().contains("stop")) {
                    System.out.println("---The ambulance stopped the connection");
                    releaseResources(bufferedReader, socket);
                    break;

                }
                System.out.println("    Ambulance: " + received);


            }

        } catch (IOException ex) {
            System.out.println("The ambulance was disconnected from the server.");
        }
    }

    private static void releaseResources(BufferedReader bufferedReader, Socket socket) {
        try {
            bufferedReader.close();
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
