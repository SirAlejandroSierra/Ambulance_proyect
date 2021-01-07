/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hecyebesdelpino
 */
public class ConfirmRecordAgainController implements Initializable {

    @FXML
    private TextField areYouSure;
    @FXML
    private Button yes;
    @FXML
    private Button no;

    /**
     * Initializes the controller class.
     */
     
    @FXML
    public void yesButtonPushed(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BitalinoRecordingDataController.fxml"));
        Parent bitalinoRecordinParent = loader.load();

        Scene bitalinoScene = new Scene(bitalinoRecordinParent);

        BitalinoRecordingDataController controller = loader.getController();
        
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        

        window.setScene(bitalinoScene);
        window.show();
        
    }
    
     @FXML
    public void noButtonPushed(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ECG.fxml"));
        Parent ecgLoad = loader.load();
        
        Scene ecgScene = new Scene(ecgLoad);             
        //access the controller and call a method
        ECGController controller = loader.getController();
        //controller.initData(lead);
        
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(ecgScene);
            window.show();
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
