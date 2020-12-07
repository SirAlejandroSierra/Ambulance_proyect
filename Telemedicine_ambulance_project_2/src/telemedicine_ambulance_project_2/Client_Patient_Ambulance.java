/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telemedicine_ambulance_project_2;

/*hola*
*HOLA
HOLAAAAA*/
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client_Patient_Ambulance {

    public static void main(String args[]) throws IOException {
        BufferedReader consolee = new BufferedReader(new InputStreamReader(System.in));
        String readString = " ";
        String serverIP = " ";
        int portNumber = 0;
        try {

            System.out.println("Introduce the IP address of the server: ");
            readString = consolee.readLine();
            serverIP = readString;
            System.out.println("Introduce the local port from the server: ");
            readString = consolee.readLine();
            portNumber = Integer.parseInt(readString);
            while (portNumber < 1024 || portNumber > 49151) {
                System.out.println("This port is not available for network users. Try again: ");
                readString = consolee.readLine();
                portNumber = Integer.parseInt(readString);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Starting connection with server...");
        Socket socket = new Socket(serverIP, portNumber);
        /*Comunication coming from server*/
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String received = "";
        /*End comunication coming from server*/
 /*Send object to server*/
 /*OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(patient);//patient es un ojbejto de la clase creada por adri
            objectOutputStream.flush();
        } catch (IOException ex) {
            System.out.println("Unable to write the object on the server.");
            Logger.getLogger(Client_Patient_Ambulance.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            releaseObjectOutputStream(objectOutputStream);
        }------------*/
 /*end send object to server*/
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Connection established.");
        System.out.println("When you want to end connection with server, type stop.");
        System.out.println("If you wish to communicate with the hospital, write here: ");
        while (true) {
            try {
                readString = consolee.readLine();
                printWriter.println(readString);
                if (readString.equalsIgnoreCase("stop")) {
                    System.out.println("FinishClient");
                    releaseResources(printWriter, consolee, bufferedReader, socket);/*Antes no estaba bufferedReader*/
                    System.exit(0);
                }
                /*comunication coming from server*/
                received = bufferedReader.readLine();
                System.out.println("Hospital: " + received);

                /*end comunication coming from server*/
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    private static void releaseResources(PrintWriter printWriter,
            BufferedReader consolee, BufferedReader bufferedReader, Socket socket) {

        try {
            consolee.close();
        } catch (IOException ex) {
            Logger.getLogger(Client_Patient_Ambulance.class.getName()).log(Level.SEVERE, null, ex);

        }
        try {
            bufferedReader.close();
        } catch (IOException ex) {
            Logger.getLogger(Client_Patient_Ambulance.class.getName()).log(Level.SEVERE, null, ex);
        }
        printWriter.close();
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client_Patient_Ambulance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void releaseObjectOutputStream(ObjectOutputStream o) {
        try {
            o.close();
        } catch (IOException ex) {
            Logger.getLogger(Client_Patient_Ambulance.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}
