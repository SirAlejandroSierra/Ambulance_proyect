/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telemedicine_ambulance_project_2;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import telemedicine_ambulance_project_2.Patient;

/**
 *
 * @author AdriCortellucci
 */
public class FXMLDocumentServerController implements Initializable {
    
    Patient patient;
    
    @FXML private Label nameLabel;
    @FXML private Label ageLabel;
    @FXML private Label accuracyLabel;
    @FXML private Label genderLabel;
    @FXML private Label idLabel;
    @FXML private Label overweightLabel;
    
    @FXML private Label systolicLabel;
    @FXML private Label diastolicLabel;
    @FXML private Label BPLabel;
    @FXML private Label heartRateLabel;
    @FXML private Label smokerLabel;
    @FXML private Label alcoholLabel;
    @FXML private Label diabeticLabel;
    @FXML private Label cholesterolLabel;
    @FXML private Label prevEventLabel;
    @FXML private Label familyLabel;

    @FXML private Label chestPainLabel;
    @FXML private Label chestPressureLabel;
    @FXML private Label neckLabel;
    @FXML private Label armLabel;
    @FXML private Label backLabel;
    @FXML private Label shortnessLabel;
    @FXML private Label sweatingLabel;
    @FXML private Label nauseaLabel;
    @FXML private Label vomitingLabel;
    @FXML private Label anxietyLabel;
    @FXML private Label coughLabel;
    @FXML private Label dizzinessLabel;
    @FXML private Label notesLabel;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FileInputStream fi;
        try {
            fi = new FileInputStream(new File("Objects.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            patient = (Patient) oi.readObject();

            System.out.println(patient.toString());

            oi.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentServerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentServerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        nameLabel.setText(patient.getName());
        ageLabel.setText(Integer.toString(patient.getAge()));
        accuracyLabel.setText(String.valueOf(patient.isAccurateAge()));
        genderLabel.setText(patient.getGender().toString());
        idLabel.setText(patient.getId());
        overweightLabel.setText(String.valueOf(patient.isOverweight()));
        
        systolicLabel.setText(Float.toString(patient.getSystolicPressure()));
        diastolicLabel.setText(Float.toString(patient.getDiastolicPressure()));
        BPLabel.setText(patient.getTension().toString());
        heartRateLabel.setText(patient.getHeartRate().toString());
        smokerLabel.setText(patient.getSmoker().toString());
        alcoholLabel.setText(patient.getDrinker().toString());
        diabeticLabel.setText(patient.getDiabetic().toString());
        cholesterolLabel.setText(patient.getDiabetic().toString());
        prevEventLabel.setText(patient.getPreviousEvent().toString());
        familyLabel.setText(patient.getFamilyHistory().toString());
        
        chestPainLabel.setText(patient.getChestPain().toString());
        chestPressureLabel.setText(patient.getChestPressure().toString());
        neckLabel.setText(patient.getNeckPain().toString());
        armLabel.setText(patient.getArmPain().toString());
        backLabel.setText(patient.getBackPain().toString());
        shortnessLabel.setText(String.valueOf(patient.getShortnessOfBreath()));
        sweatingLabel.setText(String.valueOf(patient.getSweating()));        
        nauseaLabel.setText(patient.getNausea().toString());
        vomitingLabel.setText(patient.getVomiting().toString());
        anxietyLabel.setText(patient.getAnxiety().toString());
        coughLabel.setText(patient.getCough().toString());
        dizzinessLabel.setText(patient.getDizziness().toString());
        notesLabel.setText(patient.getNotes());
        
    }    
    
}
