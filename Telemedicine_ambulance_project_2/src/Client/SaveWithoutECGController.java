/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Patient.Patient;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hecyebesdelpino
 */
public class SaveWithoutECGController implements Initializable {

    Patient patient = new Patient();
    ObjectOutputStream toServer;
    Socket socket;
    Stage stage;
    
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
    public void initData (Patient p, Stage s, Socket socket){
        this.patient=p;
        this.stage=s;
        this.socket=socket;
    }
    
    
    @FXML
    public void yesButtonPushed(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ChatClient.fxml"));
        Parent clientChatParent = loader.load();
        Scene clientChatScene = new Scene(clientChatParent);
        ChatClientController controller = loader.getController();
        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            toServer = new ObjectOutputStream(socket.getOutputStream());
            toServer.writeObject("check");
            toServer.writeObject(patient);//patient es un objeto de la clase creada por adri
            toServer.flush();

            controller.initData(patient, window, socket, toServer);

            window.setScene(clientChatScene);

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
        //CERRAR LA SEGUNDA PANTALLA QUE APARECE
        //ABRIR DE NUEVO LA OTRA CON INIT
        
        /*
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowPatient.fxml"));
        Parent showParent = loader.load();

        Scene bitalinoScene = new Scene(showParent);

        ShowPatientController controller = loader.getController();
        controller.ECGnextButton(patient);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        

        window.setScene(bitalinoScene);
        window.show();
     */   
    }

}
