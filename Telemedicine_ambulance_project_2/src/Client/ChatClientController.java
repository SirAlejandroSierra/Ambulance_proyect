/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

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

    @FXML private TextField msgField;
    @FXML private TextArea msgRoom;
    @FXML private Button send;
    
    private Patient patient;
    
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
   
    private Socket socket;
    private Stage stage;
    
    public void exit(ActionEvent event) throws IOException {
        try {
            if(!socket.isClosed()){
                toServer.writeObject("logout");
            }    
        } catch (IOException ex) {
        }
        releaseResources();
        stage.close();
    }
    
    @FXML
    public void initData(Patient paciente, Stage stage, Socket socket, ObjectInputStream oi, ObjectOutputStream oo) {
        this.patient=paciente;
        this.stage=stage;
        this.socket=socket;
        this.toServer=oo;
        this.fromServer= oi;
        
        stage.setOnCloseRequest((event) -> {            
            try {
                if(socket.isClosed()){
                    toServer.writeObject("logout");}
            } catch (IOException ex) {
            }
            releaseResources();
            stage.close();
        });
    }

    
    
    @Override
    public void run() {
        

    }
    
    public void sendPatient() {
        try {
            toServer.writeObject(patient);
            toServer.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    public void send() throws IOException {
        int read;
        try{
            read= fromServer.readByte();
            if(read == -1){
                msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                msgRoom.appendText("Error in the connection\n");
                
            }else{
                String msg = msgField.getText();

                toServer.writeObject(msg);
                read=fromServer.readByte();
                if(read == -1){
                    msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                    msgRoom.appendText("Error in the connection\n");
                }else{
                    toServer.writeObject((String) "check");
                    msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                    msgRoom.appendText( msg + "\n");

                    msgField.setText("");
                    if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
                        releaseResources();
                    } 
                }
            }
        }catch(Exception e){
            msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            msgRoom.appendText("Error in the connection\n");
            
        }
    }
    
    private void releaseResources() {
        if(!socket.isClosed()){    
            try {
                toServer.close();
            } catch (IOException ex) { 
                Logger.getLogger(ChatClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fromServer.close();
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