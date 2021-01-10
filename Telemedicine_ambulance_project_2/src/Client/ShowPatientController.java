/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Client.PainInfoController;
import Client.ChatClientController;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author AdriCortellucci
 */
public class ShowPatientController implements Initializable {

    private Patient patient = new Patient();
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;

    private Socket socket;

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

    @FXML
    private Button SaveButton;
    @FXML
    private Button recordECG;
    @FXML
    private Label labelECGRecorded;
    @FXML
    private Label labelECG;
    
    private Stage window;
    private ArrayList<Users> users = new ArrayList<Users>();

    public void initData(Patient paciente, Socket socket, Stage stage, ObjectInputStream oi, ObjectOutputStream oo,  ArrayList<Users> users) throws IOException {
        this.patient = paciente;
        this.socket=socket;
        this.fromServer=oi;
        this.toServer= oo;
        this.window=stage;
        this.users=users;
        window.setOnCloseRequest((event) -> {
            releaseResources();
        });
        
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
        labelECG.setText("");
    }

    @FXML
    public void backButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PainInfo.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        PainInfoController controller = loader.getController();
        controller.initDataBack(patient, socket, window, fromServer, toServer, users);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

    }

    public void ECGnextButton(Patient patient, Socket socket, ObjectInputStream oi, ObjectOutputStream oo, ArrayList<Users> users){
        this.socket=socket;
        this.fromServer=oi;
        this.toServer=oo;
        if(patient.getRecordedECG().isEmpty()){
           // recordECG.setText("ECG not recorded yet");
            labelECG.setText("ECG not recorded yet");
        }else{
            labelECG.setText("ECG succesfully recorded ");
        }
        this.patient = patient;
    }
    
    @FXML
    public void recordECGButtonPushed(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BitalinoRecordingData.fxml"));
        Parent bitalinoParent = loader.load();

        Scene bitalinoScene = new Scene(bitalinoParent);

        BitalinoRecordingDataController controller = loader.getController();
        controller.init(patient, socket, window, fromServer, toServer, users);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        

        window.setScene(bitalinoScene);
        window.show();
        
    }
    
    @FXML
    public void nextButtonPushed(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        if(patient.getRecordedECG().isEmpty()){
    
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SaveWithoutECG.fxml"));
            Parent saveWithoutECG = loader.load();

            Scene secondScene = new Scene(saveWithoutECG);
            Stage secondStage = new Stage();
            
            SaveWithoutECGController controller = loader.getController();
            controller.initData(patient, window, secondStage, socket, fromServer, toServer, users);
            
            
            secondStage.setTitle("Save without ECG");
            secondStage.setScene(secondScene);

            secondStage.show();
            
        }else{
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("ChatClient.fxml"));
            Parent clientChatParent = loader.load();

            Scene clientChatScene = new Scene(clientChatParent);

            ChatClientController controller = loader.getController();

            
            try {
                toServer.writeObject("check");
                toServer.writeObject(patient);
                toServer.flush();

                controller.initData(patient, window, socket, fromServer, toServer);

                window.setScene(clientChatScene);

                window.show();

            } catch (Exception e) {
                FXMLLoader loader3 = new FXMLLoader();
                loader3.setLocation(getClass().getResource("ErrorConnection.fxml"));
                Parent tableViewParent = loader3.load();

                Scene secondScene = new Scene(tableViewParent);

                Stage secondStage = new Stage();
                secondStage.setTitle("Error");
                secondStage.setScene(secondScene);

                secondStage.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    private void releaseResources()  {
        try {
            toServer.writeObject("logout");
        } catch (IOException ex) { 
            Logger.getLogger(ShowPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            toServer.close();
        } catch (IOException ex) { 
            Logger.getLogger(ShowPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            fromServer.close();
        } catch (IOException ex) {
            Logger.getLogger(ShowPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ShowPatientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
