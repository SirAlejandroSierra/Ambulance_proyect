/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Patient.Ambulance;
import Patient.Gender;
import Patient.Patient;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
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

/**
 *
 * @author AdriCortellucci
 */
public class PersonalInfoController implements Initializable {

    private boolean accurateAge;
    private boolean overweithPat;

    private Ambulance ambulance;

    private Patient patient = new Patient();

    @FXML
    private Label ambulnceNum;
    @FXML
    private Label datee;
    @FXML
    private Button next;

    @FXML
    private TextField textField;

    @FXML
    private TextField ageField;

    @FXML
    private RadioButton accurate;
    @FXML
    private RadioButton approximate;
    private ToggleGroup ageAccuracy;

    @FXML
    private TextField idField;

    @FXML
    private RadioButton YesBtn;
    @FXML
    private RadioButton NoBtn;
    private ToggleGroup overweight;

    @FXML
    private RadioButton MaleBtn;
    @FXML
    private RadioButton FemaleBtn;
    private ToggleGroup genderSelect;
    private Socket socket;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private Stage window;
    

    public void initData(Ambulance ambulance, Date date, Socket socket, Stage stage, ObjectInputStream oi, ObjectOutputStream oo) {
        this.ambulance = ambulance;
        //this.date = date;
        patient.setAmbulance(ambulance);
        patient.setDate(date);
        ambulnceNum.setText(patient.getAmbulance().toString());
        datee.setText(patient.getDate().toString());
        this.socket=socket;
        this.window=stage;
        this.fromServer=oi;
        this.toServer=oo;
        window.setOnCloseRequest((event) -> {
            releaseResources();
        });
    }

    public void initDataBack(Patient paciente, Socket socket, Stage stage, ObjectInputStream oi, ObjectOutputStream oo) {
        this.socket=socket;
        this.patient = paciente;
        this.fromServer=oi;
        this.toServer=oo;
        textField.setText(patient.getName());
        ageField.setText(Integer.toString(patient.getAge()));
        boolean accuracyB = patient.isAccurateAge();
        if (accuracyB == true) {
            ageAccuracy.selectToggle(accurate);
        }
        if (accuracyB == false) {
            ageAccuracy.selectToggle(approximate);
        }

        Gender genderB = patient.getGender();
        if (genderB.equals(Gender.FEMALE)) {
            genderSelect.selectToggle(FemaleBtn);
        }
        if (genderB.equals(Gender.MALE)) {
            genderSelect.selectToggle(MaleBtn);
        }

        idField.setText(patient.getId());

        boolean overweighted = patient.isOverweight();
        if (overweighted == true) {
            overweight.selectToggle(YesBtn);
        }
        if (overweighted == false) {
            overweight.selectToggle(NoBtn);
        }
        
        this.window=stage;
        window.setOnCloseRequest((event) -> {
            releaseResources();
        });
    }

    public void setOverweight() {
        if (this.overweight.getSelectedToggle().equals(this.YesBtn)) {
            patient.setOverweight(true);
        }
        if (this.overweight.getSelectedToggle().equals(this.NoBtn)) {
            patient.setOverweight(false);
        }
    }

    public void setAccuracy() {
        if (this.ageAccuracy.getSelectedToggle().equals(this.accurate)) {
            patient.setAccurateAge(true);
        }
        if (this.ageAccuracy.getSelectedToggle().equals(this.approximate)) {
            patient.setAccurateAge(accurateAge);
        }
    }

    public void setGender() {
        if (this.genderSelect.getSelectedToggle().equals(this.MaleBtn)) {
            patient.setGender(Gender.MALE);
        }
        if (this.genderSelect.getSelectedToggle().equals(this.FemaleBtn)) {
            patient.setGender(Gender.FEMALE);

        }
    }

    boolean checkString(String s) {
        if (s == null) { // checks if the String is null
            return false;
        }
        if (s.equals("")) { 
            return false;
        }
        int len = s.length();
        for (int i = 0; i < len; i++) {
            // checks whether the character is not a letter
            // if it is not a letter ,it will return false
            if ((Character.isLetter(s.charAt(i))) == false && (Character.isSpaceChar(s.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public boolean setName() {
        String namePatient = textField.getText();
        boolean isString = checkString(namePatient);
        if (isString == false) {
            textField.setStyle("-fx-background-color: red");
        } else {
            patient.setName(namePatient);
            textField.setStyle("-fx-background-color: white");
        }
        return isString;
    }

    boolean checkInteger(String s) {
        if (s == null) { // checks if the String is null
            return false;
        }
        if (s.equals("")) { // checks if the String is null
            return false;
        }
        try {
            int ageNumber = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean setAge() {
        String ageString = ageField.getText();
        boolean isNumber = checkInteger(ageString);
        if (isNumber == false) {
            ageField.setStyle("-fx-background-color: red");
        } else {
            int age = Integer.parseInt(ageString);
            patient.setAge(age);
            ageField.setStyle("-fx-background-color: white");
        }
        return isNumber;
    }

    boolean checkID(String s) {
        String[] numZeroNine = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        if (s == null) { // checks if the String is null
            return false;
        }
        if (s.equals("")) { // checks if the String is null
            return false;
        }
        if (s.length() != 9) {
            return false;
        }
        if (Character.isLetter(s.charAt(8)) == false) {
            return false;
        }
        try {
            Integer.parseInt(s.substring(1, 7));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean setID() {
        String idString = idField.getText();
        boolean isID = checkID(idString);
        if (isID == false) {
            idField.setStyle("-fx-background-color: red");
        } else {
            patient.setId(idString);
            idField.setStyle("-fx-background-color: white");
        }
        return isID;
    }
    
    public void backToAmbulance(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AmbulanceWindow.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        AmbulanceWindowController controller=loader.getController();
        controller.initData(socket, window, fromServer, toServer);

        window.setScene(scene);
        window.show();
    }

    public void changeSceneToMedicalInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MedicalInfo.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        MedicalInfoController controller = loader.getController();
        boolean nameIsGood = setName();
        boolean ageIsGood = setAge();
        boolean idIsGood = setID();
        setOverweight();
        setGender();
        setAccuracy();

        if ((nameIsGood == true) && (ageIsGood == true) && (idIsGood == true)) {
            controller.initData(patient, socket, window, fromServer, toServer);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        overweight = new ToggleGroup();
        this.NoBtn.setToggleGroup(overweight);
        this.YesBtn.setToggleGroup(overweight);
        overweight.selectToggle(NoBtn);

        ageAccuracy = new ToggleGroup();
        this.accurate.setToggleGroup(ageAccuracy);
        this.approximate.setToggleGroup(ageAccuracy);
        ageAccuracy.selectToggle(accurate);

        genderSelect = new ToggleGroup();
        this.MaleBtn.setToggleGroup(genderSelect);
        this.FemaleBtn.setToggleGroup(genderSelect);
        genderSelect.selectToggle(FemaleBtn);

    }
    
    private void releaseResources()  {
        try {
            toServer.writeObject("logout");
        } catch (IOException ex) {
            Logger.getLogger(PersonalInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            toServer.close();
        } catch (IOException ex) {
            Logger.getLogger(PersonalInfoController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            fromServer.close();
        } catch (IOException ex) {
            Logger.getLogger(PersonalInfoController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try {
            socket.close();
            System.out.println("socket closed");
        } catch (IOException ex) {
            Logger.getLogger(PersonalInfoController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
