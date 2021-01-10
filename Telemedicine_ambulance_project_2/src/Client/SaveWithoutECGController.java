/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Patient.Patient;
import Patient.Users;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author hecyebesdelpino
 */
public class SaveWithoutECGController implements Initializable {

    private Patient patient = new Patient();
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private Socket socket;
    private Stage stage;
    private Stage thisStage;
    private ArrayList<Users> users = new ArrayList<Users>();
    
    @FXML
    private Button yes;
    @FXML
    private Button no;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML 
    public void initData (Patient p, Stage s, Stage thisStage, Socket socket, ObjectInputStream oi, ObjectOutputStream oo, ArrayList<Users> users){
        this.patient=p;
        this.stage=s;
        this.thisStage=thisStage;
        this.socket=socket;
        this.fromServer=oi;
        this.toServer=oo;
        this.users=users;
        this.thisStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    event.consume();
                }
            });
    }
    
    @FXML
    public void yesButtonPushed(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ChatClient.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        ChatClientController controller = loader.getController();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            toServer.writeObject("check");
            toServer.writeObject(patient);
            
            toServer.flush();

            controller.initData(patient, window, socket, fromServer, toServer);

            window.setScene(scene);

            window.show();
            stage.close();

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

    @FXML
    public void noButtonPushed(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
        
    }

}
