/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Patient.Patient;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class ServerPatientsController implements Initializable {

    public ObservableList <Patient> patients_= FXCollections.observableArrayList();
    @FXML private TableView <Patient> tableView;
    @FXML private TableColumn<Patient, String> patientName;
    @FXML private TableColumn<Patient, String> ambulance;
    @FXML private TableColumn<Patient, Date> date;
    
    @FXML private Button detailedPersonViewButton;
    
    public static ArrayList<Thread> threads=new ArrayList<Thread>();
    public Server_two server;
    
    public void changeSceneToDetailedPersonView(ActionEvent event) throws IOException
    {
        
            StackPane secondaryLayout = new StackPane();
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ShowPatient_1.fxml"));
            Parent tableViewParent = loader.load();
        
            Scene secondScene = new Scene(tableViewParent);
            
            ShowPatientController_1 controller = loader.getController();
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
    
    public void refresh(ActionEvent event) throws IOException{
        fillPatients();
    }
    
    public void userClickedOnTable()
    {
        this.detailedPersonViewButton.setDisable(false);
    }
    
    public void connectServer() 
    {
        try {
            server = new Server_two(9000);
            System.out.println("connected");
            Thread serverThread = (new Thread(server));
            serverThread.start();
            threads.add(serverThread);
            
        } catch (IOException ex) {
            Logger.getLogger(ServerPatientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        /* Change the view of the primary stage */
    }
    
    public void fillPatients() {
        patients_=FXCollections.observableArrayList(server.patients);
        tableView.setItems(patients_);
         
        
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
        }
    }
    
    public void fillPatientsInitially() {
        try{
            File file = new File("patients.txt");
            ArrayList<Patient> namesList = new ArrayList<Patient>();
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.length() != 0){
            
                FileInputStream fis = new FileInputStream("patients.txt");
                
                ObjectInputStream ois = new ObjectInputStream(fis);
 
                namesList = (ArrayList) ois.readObject();
 
                ois.close();
                fis.close();
            }
            server.patients=namesList;
            patients_=FXCollections.observableArrayList(server.patients);
            tableView.setItems(patients_);
            
        }catch (FileNotFoundException ex) {
            Logger.getLogger(ServerPatientsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerPatientsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerPatientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
// TODO
        connectServer();
         //set up the columns in the table
        patientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        //patientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("id"));
        
        //Disable the detailed person view button until a row is selected
        this.detailedPersonViewButton.setDisable(true);
 
        fillPatientsInitially();
        
        
    }  
    
}
