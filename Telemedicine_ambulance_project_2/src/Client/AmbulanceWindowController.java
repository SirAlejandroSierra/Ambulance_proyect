/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Patient.Ambulance;
import java.io.IOException;
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
   ObjectOutputStream toServer;
   Stage window;
   
 
   
    public void initData(Socket socket, Stage stage){
        this.socket=socket;
        this.window=stage;
        window.setOnCloseRequest((event) -> {
            releaseResources(this.socket);
        });
        
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
        Parent personalInfoParent = loader.load();

        Scene PersonalInfo = new Scene(personalInfoParent);

        PersonalInfoController controller = loader.getController();
        Date date = new Date();
        controller.initData(ambulance, date, socket, window);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(PersonalInfo);
        window.show();
    }
    
    private void releaseResources(Socket socket)  {
        try {
            toServer = new ObjectOutputStream(socket.getOutputStream());
            toServer.writeObject("logout");
        } catch (IOException ex) {
            Logger.getLogger(ShowPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            toServer.close();
        } catch (IOException ex) {
            Logger.getLogger(ShowPatientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            socket.close();
            System.out.println("socket closed");
        } catch (IOException ex) {
            Logger.getLogger(ShowPatientController.class.getName()).log(Level.SEVERE, null, ex);
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
