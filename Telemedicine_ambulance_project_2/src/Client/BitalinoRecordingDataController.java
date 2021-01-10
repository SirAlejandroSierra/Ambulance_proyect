/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import BITalino.BITalino;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import BITalino.BitalinoDemo;
import Patient.Patient;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author hecyebesdelpino
 */
public class BitalinoRecordingDataController implements Initializable {
    private Socket socket;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private String lead;
    private ObservableList list = FXCollections.observableArrayList();
    private BitalinoDemo bitalinodemo = new BitalinoDemo();
    
    @FXML private ChoiceBox choicebox;
    
    @FXML private Label label1;
   
    @FXML private Button next;
    @FXML private Button startrecording;
    @FXML private ImageView leadImage;
    
    private BITalino bitalino = null;
    private ArrayList<Integer> ecgValues3 = new ArrayList();
    private Patient patient = new Patient();
    private Stage window;
    

    public void init(Patient patient, Socket socket, Stage stage, ObjectInputStream oi, ObjectOutputStream oo)  {
      this.patient = patient;
      this.socket = socket;
      this.fromServer=oi;
      this.toServer=oo;
      this.patient.setECG(new ArrayList());
      this.window=stage;
        window.setOnCloseRequest((event) -> {
            releaseResources();
        });
    }    
    
    @FXML
    private void displayValue(ActionEvent event){
        String ecg = choicebox.getValue().toString();
        if (ecg.equalsIgnoreCase("")){
            label1.setText(" PLEASE SELECT THE LEAD");
        }
        else {
            label1.setText("                RECORDING...");
            setLead();
            BitalinoDemo.startECGvalues();
            this.ecgValues3 = BitalinoDemo.ecgValues;
            bitalinoSetting();
        }
    }
    
    private void setLead(){
        String l;
        l = choicebox.getValue().toString();
        boolean check = checkString(l);
        if (check){
            this.lead = l;
        }
    }
    
    public  String getLead(){
        return lead;
    }
    
    boolean checkString(String s) {
        if(s == null) { // checks if the String is null
            return false;
        }
        return true;
    }

    public void bitalinoSetting(){
        patient.setECGToZero();
        
        patient.setECG(ecgValues3);
        label1.setText("     DONE");
        next.setDisable(false);
    }
    
    @FXML
    public void changeSceneToECG(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ECG.fxml"));
        Parent ecgLoad = loader.load();
        
        Scene ecgScene = new Scene(ecgLoad);  
        
        ECGController controller = loader.getController();
        controller.initData(lead, patient, socket, window, fromServer, toServer);
        
            
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(ecgScene);
        window.show();
        
    }
    
    @FXML
    public void changeSceneToShowPatient(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowPatient.fxml"));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);             
        ShowPatientController controller = loader.getController();
        controller.initData(patient, socket, window, fromServer, toServer);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choicebox.getItems().addAll("Lead I","Lead II","Lead III","Lead aVR","Lead aVL","Lead aVF");
        //choicebox.setValue("Lead I");
        choicebox.setValue("");
        
        label1.setText(" ");
        next.setDisable(true);
    }
    
    private void releaseResources()  {
        try {
            toServer.writeObject("logout");
        } catch (IOException ex) { 
            Logger.getLogger(BitalinoRecordingDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            toServer.close();
        } catch (IOException ex) { 
            Logger.getLogger(BitalinoRecordingDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fromServer.close();
        } catch (IOException ex) {
            Logger.getLogger(BitalinoRecordingDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.close();
            System.out.println("socket closed");
        } catch (IOException ex) {
            Logger.getLogger(BitalinoRecordingDataController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
   
}

