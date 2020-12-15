/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Client.Client_Patient_Ambulance;
import Patient.Patient;
import Patient.Patient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class ChatClientController extends Thread implements Initializable {

    @FXML public TextField msgField;
    @FXML public TextArea msgRoom;
    
    Patient patient;
    
    OutputStream os;
    ObjectOutputStream oos;
    PrintWriter writer;
    Socket socket;
    Socket socketChat;
    
    
    public void connectSocket() {
        try {
            socket = new Socket("localhost", 9000);
            System.out.println("Socket is connected with server!");
            os= socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            sendPatient();
            
            socketChat=new Socket("localhost", 9000);

            writer = new PrintWriter(socketChat.getOutputStream(), true);
            
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
    
    @FXML
    public void initData(Patient paciente) {
        this.patient=paciente;
        connectSocket();
        
    }

    
    @Override
    public void run() {

    }
    
    public void sendPatient() {
        try {
            oos.writeObject(patient);//patient es un ojbejto de la clase creada por adri
            oos.flush();
            
        } catch (IOException ex) {
            System.out.println("----Unable to write the object on the server.");
            Logger.getLogger(Client_Patient_Ambulance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
    }
    
    public void send() {
        String msg = msgField.getText();
        msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        msgRoom.appendText("Ambulance: " + msg + "\n");
        writer.println(msg);
        msgField.setText("");
        if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            releaseResources(os, oos, writer, socket, socketChat);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //connectSocket();
        //sendPatient();
        //run();
    }    
    


    private static void releaseResources(OutputStream os, ObjectOutputStream oos,  
                PrintWriter writer, Socket socket, Socket socketChat) {
        try {
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(Client_Patient_Ambulance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Client_Patient_Ambulance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        writer.close();
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client_Patient_Ambulance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            socketChat.close();
        } catch (IOException ex) {
            Logger.getLogger(Client_Patient_Ambulance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

    }
}