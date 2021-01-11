/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import BITalino.BITalino;
import BITalino.BITalinoException;
import BITalino.BitalinoDemo;
import BITalino.Frame;
import Patient.Patient;
import Patient.Users;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hecyebesdelpino
 */
public class ECGController implements Initializable {
    private Socket socket;
    private String lead;
    
    @FXML private NumberAxis y;
    @FXML private CategoryAxis x;
    @FXML private LineChart<?, ?> ecgGraphics;
    
    private ArrayList<Integer> ecgValues = new ArrayList();
    private Patient patient;
    
    @FXML private Button exit;
    @FXML private Button recordAgain;
    private XYChart.Series series;
    
    private Stage window;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private ArrayList<Users> users = new ArrayList<Users>();
 
    public void initData(String lead, Patient patient, Socket socket, Stage stage, ObjectInputStream oi, ObjectOutputStream oo,  ArrayList<Users> users){
        this.lead=lead;
        this.patient =patient;
        this.socket=socket;
        this.fromServer=oi;
        this.toServer=oo;
        this.users=users;
        this.window=stage;
        window.setOnCloseRequest((event) -> {
            releaseResources();
        });
        
        ecgValues = patient.getRecordedECG();
        
        if(!ecgValues.isEmpty()){
            for (int i = 0; i < ecgValues.size() ; i++) {
               series.getData().add(new XYChart.Data(""+i,ecgValues.get(i)));  
            }
            ecgGraphics.setTitle(""+lead);
            ecgGraphics.setCreateSymbols(false);
            ecgGraphics.getData().addAll(series);
        }
    }

  
    /**
     * Initializes the controller class.
     */
    
    @Override
    public  void initialize(URL url, ResourceBundle rb) {
        series = new XYChart.Series();               
    }
    
    @FXML
    public void closeButton(ActionEvent event)throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowPatient.fxml"));
        Parent showParent = loader.load();

        Scene bitalinoScene = new Scene(showParent);

        ShowPatientController controller = loader.getController();
        controller.ECGnextButton(patient, socket, fromServer, toServer, users);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        

        window.setScene(bitalinoScene);
        window.show();
        
    }
    @FXML
    public void recordAgain(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BitalinoRecordingData.fxml"));
        Parent bitalinoRecordinParent = loader.load();

        Scene bitalinoScene = new Scene(bitalinoRecordinParent);

        BitalinoRecordingDataController controller = loader.getController();
        
        controller.init(patient, socket, window,fromServer, toServer, users);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        

        window.setScene(bitalinoScene);
        window.show();
        
    }
    
    private void releaseResources()  {
        try {
            toServer.writeObject("logout");
        } catch (IOException ex) { 
            Logger.getLogger(ECGController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            toServer.close();
        } catch (IOException ex) { 
            Logger.getLogger(ECGController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fromServer.close();
        } catch (IOException ex) {
            Logger.getLogger(ECGController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ECGController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
    

       