/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Client.Client_Patient_Ambulance;
import Patient.Patient;
import Patient.Patient;
import com.sun.corba.se.impl.io.IIOPInputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class ChatClientController extends Thread implements Initializable {

    @FXML public TextField msgField;
    @FXML public TextArea msgRoom;
    @FXML public Button send;
    
    Patient patient;
    
    ObjectOutputStream toServer;
    //OutputStream os;
    //ObjectOutputStream oos;
    //PrintWriter writer;
    Socket socket;
    Stage stage;
    
    public void exit(ActionEvent event) throws IOException {
        System.out.println("salir");
        try {
            if(!socket.isClosed()){
                toServer.writeObject("logout");
            }    
        } catch (IOException ex) {
            Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        releaseResources(toServer, socket);
        stage.close();
    }
    
    @FXML
    public void initData(Patient paciente, Stage stage, Socket socket, ObjectOutputStream output) {
        this.patient=paciente;
        this.stage=stage;
        this.socket=socket;
        this.toServer=output;
        
        stage.setOnCloseRequest((event) -> {
            System.out.println("salir");
            try {
                toServer.writeObject("logout");
            } catch (IOException ex) {
                Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
            releaseResources(toServer, socket);
            stage.close();
        });
    }

    
    
    @Override
    public void run() {
        

    }
    
    public void sendPatient() {
        try {
            toServer.writeObject(patient);//patient es un ojbejto de la clase creada por adri
            toServer.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    public void send() throws IOException {
        int read;
        try{
            read=socket.getInputStream().read();
            System.out.println(read);
            if(read == -1){
                System.out.println("closed");
                msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                msgRoom.appendText("Error in the connection\n");
                
            }else{
                String msg = msgField.getText();

                toServer.writeObject(msg);
                read=socket.getInputStream().read();
                if(read == -1){
                    System.out.println("closed");
                    msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                    msgRoom.appendText("Error in the connection\n");
                }else{
                    System.out.println(read);
                    toServer.writeObject((String) "check");
                    msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                    msgRoom.appendText("Ambulance: " + msg + "\n");

                    msgField.setText("");
                    if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
                        releaseResources(toServer, socket);
                    } 
                }
            }
        }catch(Exception e){
            msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            msgRoom.appendText("Error in the connection\n");
            
        }
    }
    private static void releaseResources(ObjectOutputStream oos, Socket socket) {
        if(!socket.isClosed()){    
            try {
                oos.close();
            } catch (IOException ex) { 
                Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    


    
}