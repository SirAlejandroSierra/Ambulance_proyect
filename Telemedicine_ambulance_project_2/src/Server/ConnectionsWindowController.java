/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Patient.Ambulance;
import Patient.Patient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class ConnectionsWindowController implements Initializable {

    public ObservableList <ClientThread> connections= FXCollections.observableArrayList();
    
    @FXML private TableView <ClientThread> tableView;
    @FXML private TableColumn<ClientThread, SocketAddress> address;
    @FXML private TableColumn<ClientThread, Integer> port;
    @FXML private TableColumn<ClientThread, Ambulance> ambulance;
    @FXML private TableColumn<ClientThread, String> patientID;
    
    @FXML private Button detailedPersonViewButton;
    
    public ArrayList<ClientThread> connectionsList= new ArrayList<ClientThread>();
    
    public Server_two server;
    
    public void initData(Server_two server){
        this.server=server;
        connectionsList= this.server.getClientThreads();
        fillConnections();

    }
    
    public void changeSceneToDetailedPersonView(ActionEvent event) throws IOException
    {
        
        StackPane secondaryLayout = new StackPane();
          
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowConnection.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene secondScene = new Scene(tableViewParent);
           
        ShowConnectionController controller = loader.getController();
        controller.initData(tableView.getSelectionModel().getSelectedItem());
        
        Stage secondStage = new Stage();
        secondStage.setTitle("New Stage");
        secondStage.setScene(secondScene);
            
        secondStage.show();
        /*
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowPatient_1.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        ShowPatientController_1 controller = loader.getController();
        controller.initData(tableView.getSelectionModel().getSelectedItem());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();*/
    }
    
    public void userClickedOnTable()
    {
        this.detailedPersonViewButton.setDisable(false);
    }
    
    public void fillConnections() {
        connections=FXCollections.observableArrayList(server.getClientThreads());
        tableView.setItems(connections);
        
        /*
        try
        {
            FileOutputStream fos = new FileOutputStream("patients.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(server.patients);
            oos.close();
            fos.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }*/
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
// TODO
         //set up the columns in the table
        address.setCellValueFactory(new PropertyValueFactory<ClientThread, SocketAddress>("address"));
        port.setCellValueFactory(new PropertyValueFactory<ClientThread, Integer>("port"));
        ambulance.setCellValueFactory(new PropertyValueFactory<ClientThread, Ambulance>("clientAmbulance"));
        patientID.setCellValueFactory(new PropertyValueFactory<ClientThread, String>("clientID"));
        
        this.detailedPersonViewButton.setDisable(true);
        
        //Disable the detailed person view button until a row is selected
        //fillPatientsInitially();
        
        
    }
}
