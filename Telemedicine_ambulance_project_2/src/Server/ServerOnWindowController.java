/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Patient.Patient;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class ServerOnWindowController implements Initializable {

    public ObservableList <Patient> patients_= FXCollections.observableArrayList();
    @FXML private TableView <Patient> tableView;
    @FXML private TableColumn<Patient, String> patientName;
    @FXML private TableColumn<Patient, String> ambulance;
    @FXML private TableColumn<Patient, Date> date;
        
    public static ArrayList<Thread> threads=new ArrayList<Thread>();
    public Server_two server; 
    
    @FXML public TextArea chatWindow;
    
    
    public void initData(Server_two server){
        this.server=server;
    }
    
    public void close(ActionEvent event) throws IOException{
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        if(server.getClients().isEmpty()){
            
            StackPane secondaryLayout = new StackPane();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PasswordWindow.fxml"));
            Parent passwordWindow = loader.load();

            Scene secondScene = new Scene(passwordWindow);
            
            PasswordWindowController controller= loader.getController();
            controller.initData(server, window);
            
            Stage secondStage = new Stage();
            secondStage.setTitle("Password");
            secondStage.setScene(secondScene);
            
            secondStage.show();

            
            
        }else{
        
            StackPane secondaryLayout2 = new StackPane();

            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("ErrorWindow.fxml"));
            Parent tableViewParent = loader2.load();

            Scene secondScene = new Scene(tableViewParent);

            Stage secondStage = new Stage();
            secondStage.setTitle("Error");
            secondStage.setScene(secondScene);

            secondStage.show();
        }
    }
    
    public void changePassword(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NewPasswordWindow.fxml"));
        Parent passwordWindow = loader.load();

        Scene secondScene = new Scene(passwordWindow);

        NewPasswordWindowController controller= loader.getController();
        controller.initData(server);

        Stage secondStage = new Stage();
        secondStage.setTitle("New Password");
        secondStage.setScene(secondScene);

        secondStage.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
