/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class NewPasswordWindowController implements Initializable {

    private Server_two server;
    
    @FXML private TextField current;
    @FXML private TextField newPass;
    @FXML private TextField newPassRepeat;
    @FXML private Label label; 
    @FXML private Button button;

    public void initData(Server_two server){
        this.server=server;
    }
    
    @FXML
    void changePassword(ActionEvent event) {
        if(!current.getText().equals(server.getPassword())){
            label.setText("That´s not the current password");
        }else if(!newPass.getText().equals(newPassRepeat.getText())){
            label.setText("New password is not matching");
        }else{
            server.setPassword(newPass.getText());
            
            Stage stage = (Stage) button.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        label.setText("");
    }    
    
}
