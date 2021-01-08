/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.MedicalInfoController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class CloseWindowController implements Initializable {
    private String password;
    private Server_two server;
    private Stage prevWindow;
    private Stage parent;
    @FXML private TextField passwordField;
    @FXML private Label labelPassword;
    @FXML private Button button;
    
    public void initData (Server_two server, Stage parent, Stage window){
        this.server=server;
        this.password=this.server.getPassword();
        this.parent=parent;
        this.prevWindow=window;
    }
                
    @FXML
    void CheckPassword(ActionEvent event) throws IOException {
        if(password.equals(passwordField.getText())){
            server.close();
           
            Stage stage = (Stage) button.getScene().getWindow();
            // do what you have to do
            stage.close();
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ServerWindow.fxml"));
            Parent serverInitialWindow = loader.load();
        
            Scene MedicalInfoScene = new Scene(serverInitialWindow);
        
            //This line gets the Stage information
            
            parent.setScene(MedicalInfoScene);
            prevWindow.close();
            parent.show();
            
    
        } else {
            labelPassword.setText("Wrong password");
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        labelPassword.setText("");
    }    
    
}
