/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.MedicalInfoController;
import Patient.Patient;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class ServerWindowController implements Initializable {

    public ObservableList <Patient> patients_= FXCollections.observableArrayList();
    @FXML private TableView <Patient> tableView;
    @FXML private TableColumn<Patient, String> patientName;
    @FXML private TableColumn<Patient, String> ambulance;
    @FXML private TableColumn<Patient, Date> date;
    
    @FXML private TextField password;
    @FXML private TextField passwordRepeat;
    
    @FXML private Label label;
    
    public static ArrayList<Thread> threads=new ArrayList<Thread>();
    public Server_two server=null;    
    
    public void open(ActionEvent event) throws IOException, ClassNotFoundException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ServerOnWindow.fxml"));
        Parent serverOnParent = loader.load();
        
        Scene MedicalInfoScene = new Scene(serverOnParent);
        
        //access the controller and call a method
        ServerOnWindowController controller = loader.getController();
        
        if(!password.getText().equals(passwordRepeat.getText())){
            label.setText("Passwords don´t match");
        }else{
            
                server = new Server_two(9000, controller, password.getText());
                
                ObjectInputStream input=null;
                File file = new File("./Files/serverPatients.txt");
                ArrayList<Patient> objectsList = new ArrayList<>();

                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                if (file.length() != 0){
                    try {
                        input = new ObjectInputStream(new FileInputStream("./Files/serverPatients.txt"));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Pruebla.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    while(true){
                        try{
                            Object obj = input.readObject();
                            objectsList.add((Patient) obj);
                        } catch (EOFException exc) {
                            break;
                        }
                    }
                }
                
                System.out.println(objectsList);
                server.setPatients(objectsList);
                
                
                System.out.println("connected");
                
           
                
                Thread serverThread = (new Thread(server));
                serverThread.start();
                //threads.add(serverThread);

            controller.initData(server);

                //This line gets the Stage information
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(MedicalInfoScene);
                window.show();
                
                
        }
        
        
    }
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Server_two server=null; 
        label.setText("");
    }    
    
}
