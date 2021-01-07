/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class ConnectToServerController implements Initializable {
    Socket socket;
    /**
     * Initializes the controller class.
     */
    @FXML
    public void connect(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("AmbulanceWindow.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        AmbulanceWindowController controller = loader.getController();

            //This line gets the Stage information
            
        try {
            socket = new Socket("localhost", 9000);
            System.out.println("Socket is connected with server!");
            
                
            controller.initData(socket);
            
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            window.setScene(scene);

            window.show();

        } catch (Exception e) {
            FXMLLoader loader3 = new FXMLLoader();
            loader3.setLocation(getClass().getResource("ErrorConnection.fxml"));
            Parent tableViewParent = loader3.load();

            Scene secondScene = new Scene(tableViewParent);

            Stage secondStage = new Stage();
            secondStage.setTitle("Error");
            secondStage.setScene(secondScene);

            secondStage.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
