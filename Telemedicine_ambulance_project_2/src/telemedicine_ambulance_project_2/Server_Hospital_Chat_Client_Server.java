/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telemedicine_ambulance_project_2;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server_Hospital_Chat_Client_Server implements Runnable {

    String received;
    Socket socket;

    public Server_Hospital_Chat_Client_Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String readString = "";
        BufferedReader consolee = new BufferedReader(new InputStreamReader(System.in)); //Si lo cerramos, luego no funciona

        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while ((received = bufferedReader.readLine()) != null) {

                if (received.toLowerCase().contains("stop")) {
                    System.out.println("---The ambulance stopped the connection");
                    releaseResources(bufferedReader, printWriter, socket);
                    break;
                }
                System.out.println("    Ambulance: " + received);

                readString = consolee.readLine();
                printWriter.println(readString);

            }

        } catch (IOException ex) {
            System.out.println("The ambulance was disconnected from the server.");
        }
    }

    private static void releaseResources(BufferedReader bufferedReader, PrintWriter printWriter, Socket socket) {
        printWriter.close();
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
