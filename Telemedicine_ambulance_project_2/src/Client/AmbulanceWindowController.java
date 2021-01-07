/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Patient.Ambulance;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
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
    
    public void initData(Socket socket){
        this.socket=socket;
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
        controller.initData(ambulance, date, socket);
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(PersonalInfo);
        window.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
