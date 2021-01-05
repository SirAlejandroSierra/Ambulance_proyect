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
import java.util.ArrayList;

/**
 * FXML Controller class
 *
 * @author hecyebesdelpino
 */
public class BitalinoRecordingDataController implements Initializable {
 
    public static String lead;
    ObservableList list = FXCollections.observableArrayList();
    BitalinoDemo bitalinodemo = new BitalinoDemo();
    @FXML
    private ChoiceBox<String> choicebox;
    
    @FXML
    private Text screen;
    @FXML
    private TextField screen2;
    @FXML
    private Button next;
    @FXML
    private Button startrecording;
    @FXML
    private ImageView leadImage;
    
    BITalino bitalino = null;
    ArrayList<Integer> ecgValues3 = new ArrayList();
    Patient patient = new Patient();
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
      loadData();
        //BitalinoDemo.startECGvalues();
        ecgValues3 = BitalinoDemo.ecgValues;  
    }    
    
    @FXML
    private void displayValue(ActionEvent event){
        String ecg = choicebox.getValue();
        if (ecg == null){
            screen.setText("  PLEASE SELECT THE LEAD");
        }
        else {
            screen.setText("                RECORDING...");
            setLead();
            BitalinoDemo.startECGvalues();
            bitalinoSetting();
        }
    }
    
    
    private void loadData(){
       list.removeAll(list);
       String a = "Lead I";
       String b = "Lead II";
       String c = "Lead III";
       String d = "Lead aVR";
       String e = "Lead aVL";
       String f = "Lead aVF";
       list.addAll(a,b,c,d,e,f);
       choicebox.getItems().addAll(list);
    }
    
    private void setLead(){
        String l;
        l = choicebox.getValue();
        boolean check = checkString(l);
        if (check){
            this.lead = l;
        }
    }
    public static String getLead(){
        return lead;
    }
        boolean checkString(String s) {
        if(s == null) { // checks if the String is null
            return false;
        }
        return true;
    }

    public void bitalinoSetting(){
        patient.setECG(ecgValues3);
    }

    
    
    
    
    @FXML
    public void changeSceneToECG(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ECG.fxml"));
        Parent ecgLoad = loader.load();
        
        Scene ecgScene = new Scene(ecgLoad);             
        //access the controller and call a method
        ECGController controller = loader.getController();
      
 
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(ecgScene);
            window.show();
        
    }
    
   
    }

