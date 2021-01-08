/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Patient.Patient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class ServerOnWindowController implements Initializable {
    private Stage window;
    private Stage parent;
    public ObservableList <Patient> patients_= FXCollections.observableArrayList();
    @FXML private TableView <Patient> tableView;
    @FXML private TableColumn<Patient, String> patientName;
    @FXML private TableColumn<Patient, String> ambulance;
    @FXML private TableColumn<Patient, Date> date;
        
    public static ArrayList<Thread> threads=new ArrayList<Thread>();
    public Server_two server; 
    
    @FXML private Label address;
    @FXML private Label port;
    
    @FXML public TextArea chatWindow;
    
    
    public void initData(Server_two server, Stage parent, Stage stage) throws UnknownHostException{
        this.server=server;
        this.parent=parent;
        this.window=stage;
        
        address.setText(InetAddress.getLocalHost().getHostAddress());
        port.setText(String.valueOf(this.server.getSocket().getLocalPort()));
        
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    event.consume();
                }
            });
        
    }
    
    public void close() {
        
        //if(server.getClients().isEmpty()){
            
            StackPane secondaryLayout = new StackPane();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CloseWindow.fxml"));
            Parent passwordWindow=null;
            try {
                passwordWindow = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(ServerOnWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Scene secondScene = new Scene(passwordWindow);
            
            CloseWindowController controller= loader.getController();
            controller.initData(server, parent, window);
            
            Stage secondStage = new Stage();
            secondStage.setTitle("Close");
            secondStage.setScene(secondScene);
            
            secondStage.show();

            
            
        /*}else{
        
            StackPane secondaryLayout2 = new StackPane();

            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("ErrorWindow.fxml"));
            Parent tableViewParent = loader2.load();

            Scene secondScene = new Scene(tableViewParent);

            Stage secondStage = new Stage();
            secondStage.setTitle("Error");
            secondStage.setScene(secondScene);

            secondStage.show();
        }*/
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
    
    public void showPatients(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PatientsWindow.fxml"));
        Parent ServerPatientsWindow = loader.load();

        Scene secondScene = new Scene(ServerPatientsWindow);

        PatientsWindowController controller= loader.getController();
        controller.initData(server);

        Stage secondStage = new Stage();
        secondStage.setTitle("Patients");
        secondStage.setScene(secondScene);

        secondStage.show();
    }

    public void showConnections(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ConnectionsWindow.fxml"));
        Parent ServerPatientsWindow = loader.load();

        Scene secondScene = new Scene(ServerPatientsWindow);

        ConnectionsWindowController controller= loader.getController();
        controller.initData(server);

        Stage secondStage = new Stage();
        secondStage.setTitle("Connections");
        secondStage.setScene(secondScene);

        secondStage.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
