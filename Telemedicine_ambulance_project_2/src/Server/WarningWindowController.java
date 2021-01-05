/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class WarningWindowController implements Initializable {
    private Server_two server;
    /**
     * Initializes the controller class.
     */
    public void initData(){
        
    }
    
    @FXML
    void CloseConnections(ActionEvent event) throws IOException {
        for(Socket client: server.getClients()){
            try {
                client.close();
            } catch (IOException ex) {
                Logger.getLogger(WarningWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        StackPane secondaryLayout = new StackPane();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PasswordWindow.fxml"));
        Parent passwordWindow = loader.load();

        Scene secondScene = new Scene(passwordWindow);

        Stage secondStage = new Stage();
        secondStage.setTitle("Password");
        secondStage.setScene(secondScene);

        secondStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
