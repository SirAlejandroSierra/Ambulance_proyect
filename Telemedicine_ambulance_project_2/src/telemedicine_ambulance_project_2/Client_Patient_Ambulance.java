/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telemedicine_ambulance_project_2;


import Server.FXMLDocumentServerController;
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
        
        //------------ READ PATIENT
        FileInputStream fi;
        Patient patient = new Patient();
        try {
            fi = new FileInputStream(new File("Objects.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            patient = (Patient) oi.readObject();

            //System.out.println(patient.toString());

            oi.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentServerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentServerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //----------------------------
        
        /*Comunication coming from server*/
       
        String received = "";
        
        /*End comunication coming from server*/
        /*Send object to server*/
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(patient);//patient es un ojbejto de la clase creada por adri
            objectOutputStream.flush();
            
        } catch (IOException ex) {
            System.out.println("Unable to write the object on the server.");
            Logger.getLogger(Client_Patient_Ambulance.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        /*end send object to server*/
        Socket socketChat = new Socket(serverIP, portNumber);
        PrintWriter printWriter = new PrintWriter(socketChat.getOutputStream(), true);
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socketChat.getInputStream()));
        System.out.println("Connection established.");
        System.out.println("When you want to end connection with server, type stop.");
        System.out.println("If you wish to communicate with the hospital, write here: ");
        
        while (true) {
            
            try {
                readString = consolee.readLine();
                printWriter.println(readString);
                
                if (readString.contains("stop")) {
                    System.out.println("FinishClient");
                    releaseResources(printWriter, consolee, bufferedReader, socket, objectOutputStream);/*Antes no estaba bufferedReader*/
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
        BufferedReader consolee, BufferedReader bufferedReader, Socket socket, ObjectOutputStream o) {
        try {
            o.close();
        } catch (IOException ex) {
            Logger.getLogger(Client_Patient_Ambulance.class.getName()).log(Level.SEVERE, null, ex);

        }
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
}



