/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Patient.Patient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author AdriCortellucci
 */
public class ShowConnectionController implements Initializable {

    ClientThread client;
    Patient patient = new Patient();

    @FXML
    private Label addressLabel;
    @FXML
    private Label portLabel;
    
    @FXML
    private Label dateLabel;
    @FXML
    private Label ambulanceLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label accuracyLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label overweightLabel;

    @FXML
    private Label systolicLabel;
    @FXML
    private Label diastolicLabel;
    @FXML
    private Label BPLabel;
    @FXML
    private Label heartRateLabel;
    @FXML
    private Label smokerLabel;
    @FXML
    private Label alcoholLabel;
    @FXML
    private Label diabeticLabel;
    @FXML
    private Label cholesterolLabel;
    @FXML
    private Label prevEventLabel;
    @FXML
    private Label familyLabel;

    @FXML
    private Label chestPainLabel;
    @FXML
    private Label chestPressureLabel;
    @FXML
    private Label neckLabel;
    @FXML
    private Label armLabel;
    @FXML
    private Label backLabel;
    @FXML
    private Label shortnessLabel;
    @FXML
    private Label sweatingLabel;
    @FXML
    private Label nauseaLabel;
    @FXML
    private Label vomitingLabel;
    @FXML
    private Label anxietyLabel;
    @FXML
    private Label coughLabel;
    @FXML
    private Label dizzinessLabel;
    @FXML
    private Label notesLabel;

    public void initData(ClientThread client) throws IOException {
        this.client = client;
        this.patient=client.getPatient();

        addressLabel.setText(client.getAddress().toString());
        portLabel.setText(Integer.toString(client.getPort()));
        dateLabel.setText(patient.getDate().toString());
        ambulanceLabel.setText(patient.getAmbulance().toString());
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
    
    @FXML 
    public void showECG(ActionEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ECG.fxml"));
        Parent parent = loader.load();

        Scene secondScene = new Scene(parent);

        ECGController controller = loader.getController();
        controller.initData(patient);
            
        Stage secondStage = new Stage();
        secondStage.setTitle("ECG");
        secondStage.setScene(secondScene);

        secondStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
// TODO
    }

}
