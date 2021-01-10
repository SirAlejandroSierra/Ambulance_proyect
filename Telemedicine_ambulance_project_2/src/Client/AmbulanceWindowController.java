/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Patient.Ambulance;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class AmbulanceWindowController implements Initializable {

    private Ambulance ambulance;
    @FXML
    private Button ambulance1;
    @FXML
    private Button ambulance2;
    @FXML
    private Button ambulance3;
    @FXML
    private Button ambulance4;
    @FXML
    private Button ambulance5;
    @FXML
    private Button ambulance6;
    
   private Socket socket;
   private ObjectOutputStream toServer;
   private ObjectInputStream fromServer;
   private Stage window;
   
 
   
    public void initData(Socket socket, Stage stage, ObjectInputStream oi, ObjectOutputStream oo){
        this.socket=socket;
        this.window=stage;
        this.fromServer= oi;
        this.toServer= oo;
        
        window.setOnCloseRequest((event) -> {
            releaseResources();
        });
        
    }
    
    public void backToUser(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UserController.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        UserController controller=loader.getController();
        controller.initData(socket, window, fromServer, toServer);

        window.setScene(scene);
        window.show();
    }

    
    
    public void changeSceneToMedicalInfo(ActionEvent event) throws IOException {
        Button buttonAmbulance = (Button) event.getSource();

        switch (buttonAmbulance.getId()) {
            case "ambulance1":
                ambulance = Ambulance.Ambulance1;
                break;
            case "ambulance2":
                ambulance = Ambulance.Ambulance2;
                break;
            case "ambulance3":
                ambulance = Ambulance.Ambulance3;
                break;
            case "ambulance4":
                ambulance = Ambulance.Ambulance4;
                break;
            case "ambulance5":
                ambulance = Ambulance.Ambulance5;
                break;
            case "ambulance6":
                ambulance = Ambulance.Ambulance6;
                break;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PersonalInfo.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        PersonalInfoController controller = loader.getController();
        Date date = new Date();
        controller.initData(ambulance, date, socket, window, fromServer, toServer);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    
    private void releaseResources()  {
        try {
            toServer.writeObject("logout");
        } catch (IOException ex) {
            Logger.getLogger(AmbulanceWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            toServer.close();
        } catch (IOException ex) {
            Logger.getLogger(AmbulanceWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            fromServer.close();
        } catch (IOException ex) {
            Logger.getLogger(AmbulanceWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            socket.close();
            System.out.println("socket closed");
        } catch (IOException ex) {
            Logger.getLogger(AmbulanceWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
        

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
