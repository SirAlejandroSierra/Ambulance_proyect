/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Patient.Patient;
import Patient.Level;
import Patient.Family;
import Patient.LevelUnknown;
import Patient.Event;
import Patient.BasicOptions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author AdriCortellucci
 */
public class MedicalInfoController implements Initializable {
    private Patient patient= new Patient();
    
    @FXML private TextField systolic;
    @FXML private TextField diastolic;
    
    @FXML private RadioButton Hypertension;
    @FXML private RadioButton NormalBP;
    @FXML private RadioButton Hypotension;
    @FXML private RadioButton UnknownBP;
    private ToggleGroup tensionCond;
    
    @FXML private RadioButton LowHR;
    @FXML private RadioButton NormalHR;
    @FXML private RadioButton HighHR;
    private ToggleGroup heartRate;
   
    @FXML private RadioButton YesDrinker;
    @FXML private RadioButton NoDrinker;
    @FXML private RadioButton UnknownDrinker;
    private ToggleGroup drinker;
    
    @FXML private RadioButton YesSmoker;
    @FXML private RadioButton NoSmoker;
    @FXML private RadioButton UnknownSmoker;
    private ToggleGroup smoker;
    
    @FXML private RadioButton YesDiabetic;
    @FXML private RadioButton NoDiabetic;
    @FXML private RadioButton UnknownDiabetic;
    private ToggleGroup diabetic;
    
    @FXML private RadioButton LowCholesterol;
    @FXML private RadioButton NormalCholesterol;
    @FXML private RadioButton HighCholesterol;
    @FXML private RadioButton UnknownCholesterol;
    private ToggleGroup cholesterol;

    @FXML private ChoiceBox CardiacEvent;

    @FXML private ChoiceBox FamilyEvent;
    
    private Socket socket;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private Stage window;
    
    
    public void initDataBack(Patient paciente, Socket socket, Stage stage, ObjectInputStream oi, ObjectOutputStream oo){
        this.socket=socket;
        this.patient= paciente;
        this.fromServer=oi;
        this.toServer=oo;
        systolic.setText(Float.toString(patient.getSystolicPressure()));
        diastolic.setText(Float.toString(patient.getDiastolicPressure()));
        this.window=stage;
        window.setOnCloseRequest((event) -> {
            releaseResources();
        });
        
        LevelUnknown BP= patient.getTension();
        if(BP.equals(LevelUnknown.HIGH)){
            tensionCond.selectToggle(Hypertension);
        }
        if(BP.equals(LevelUnknown.NORMAL)){
            tensionCond.selectToggle(NormalBP);
        }
        if(BP.equals(LevelUnknown.LOW)){
            tensionCond.selectToggle(Hypotension);
        }
        if(BP.equals(LevelUnknown.UNKNOWN)){
            tensionCond.selectToggle(UnknownBP);
        }
        
        Level HR= patient.getHeartRate();
        if(HR.equals(Level.HIGH)){
            heartRate.selectToggle(HighHR);
        }
        if(HR.equals(Level.NORMAL)){
            heartRate.selectToggle(NormalHR);
        }
        if(HR.equals(Level.LOW)){
            heartRate.selectToggle(LowHR);
        }
        
        BasicOptions Sm= patient.getSmoker();
        if(Sm.equals(BasicOptions.YES)){
            smoker.selectToggle(YesSmoker);
        }
        if(Sm.equals(BasicOptions.NO)){
            smoker.selectToggle(NoSmoker);
        }
        if(Sm.equals(BasicOptions.UNKNOWN)){
            smoker.selectToggle(UnknownSmoker);
        }
        
        BasicOptions Dr= patient.getDrinker();
        if(Dr.equals(BasicOptions.YES)){
            drinker.selectToggle(YesDrinker);
        }
        if(Dr.equals(BasicOptions.NO)){
            drinker.selectToggle(NoDrinker);
        }
        if(Dr.equals(BasicOptions.UNKNOWN)){
            drinker.selectToggle(UnknownDrinker);
        }
        
        BasicOptions Di= patient.getDiabetic();
        if(Di.equals(BasicOptions.YES)){
            diabetic.selectToggle(YesDiabetic);
        }
        if(Di.equals(BasicOptions.NO)){
            diabetic.selectToggle(NoDiabetic);
        }
        if(Di.equals(BasicOptions.UNKNOWN)){
            diabetic.selectToggle(UnknownDiabetic);
        }
        
        LevelUnknown Ch= patient.getChosterol();
        if(Ch.equals(LevelUnknown.HIGH)){
            cholesterol.selectToggle(HighCholesterol);
        }
        if(Ch.equals(LevelUnknown.NORMAL)){
            cholesterol.selectToggle(NormalCholesterol);
        }
        if(Ch.equals(LevelUnknown.LOW)){
            cholesterol.selectToggle(LowCholesterol);
        }
        if(Ch.equals(LevelUnknown.UNKNOWN)){
            cholesterol.selectToggle(UnknownCholesterol);
        }
        
        Event prev= patient.getPreviousEvent();
        if(prev.equals(Event.INFARCTION)){
            CardiacEvent.setValue("Infarction");
        }
        if(prev.equals(Event.ICTUS)){
            CardiacEvent.setValue("Ictus");
        }
        if(prev.equals(Event.ARTERIALDISEASE)){
            CardiacEvent.setValue("Arterial disease");
        }
        if(prev.equals(Event.OTHERS)){
            CardiacEvent.setValue("Other");
        }
        if(prev.equals(Event.NO)){
            CardiacEvent.setValue("None");
        }
        
        Family fam= patient.getFamilyHistory();
        if(fam.equals(Family.MOTHER)){
            FamilyEvent.setValue("Mother");
        }
        if(fam.equals(Family.FATHER)){
            FamilyEvent.setValue("Father");
        }
        if(fam.equals(Family.SIBLING)){
            FamilyEvent.setValue("Sibling");
        }
        if(fam.equals(Family.OTHERS)){
            FamilyEvent.setValue("Other");
        }
        if(fam.equals(Family.NO)){
            FamilyEvent.setValue("None");
        }
        
    }
    
    public void initData(Patient paciente, Socket socket, Stage stage, ObjectInputStream oi, ObjectOutputStream oo){
        this.patient= paciente;
        this.socket=socket;
        this.fromServer=oi;
        this.toServer=oo;
        this.window=stage;
        window.setOnCloseRequest((event) -> {
            releaseResources();
        });
    }
    
    public void settingTension(){
        if(this.tensionCond.getSelectedToggle().equals(this.Hypertension)){
            patient.setTension(LevelUnknown.HIGH);
        }
        if(this.tensionCond.getSelectedToggle().equals(this.NormalBP)){
            patient.setTension(LevelUnknown.NORMAL);
        }
        if(this.tensionCond.getSelectedToggle().equals(this.Hypotension)){
            patient.setTension(LevelUnknown.LOW);
        }
        if(this.tensionCond.getSelectedToggle().equals(this.UnknownBP)){
            patient.setTension(LevelUnknown.UNKNOWN);
        }    
    }
    
    public void settingHeartRate(){
        if(this.heartRate.getSelectedToggle().equals(this.LowHR)){
            patient.setHeartRate(Level.LOW);
        }
        if(this.heartRate.getSelectedToggle().equals(this.NormalHR)){
            patient.setHeartRate(Level.NORMAL);
        }
        if(this.heartRate.getSelectedToggle().equals(this.HighHR)){
            patient.setHeartRate(Level.HIGH);
        }
    }
    
    public void settingSmoker(){
        if(this.smoker.getSelectedToggle().equals(this.NoSmoker)){
            patient.setSmoker(BasicOptions.NO);
        }
        if(this.smoker.getSelectedToggle().equals(this.YesSmoker)){
            patient.setSmoker(BasicOptions.YES);
        }
        if(this.smoker.getSelectedToggle().equals(this.UnknownSmoker)){
            patient.setSmoker(BasicOptions.UNKNOWN);
        }
    }
    
    public void settingDinker(){
        if(this.drinker.getSelectedToggle().equals(this.NoDrinker)){
            patient.setDrinker(BasicOptions.NO);
        }
        if(this.drinker.getSelectedToggle().equals(this.YesDrinker)){
            patient.setDrinker(BasicOptions.YES);
        }
        if(this.drinker.getSelectedToggle().equals(this.UnknownDrinker)){
            patient.setDrinker(BasicOptions.UNKNOWN);
        }
    }
    
    public void settingDiabetic(){
        if(this.diabetic.getSelectedToggle().equals(this.NoDiabetic)){
            patient.setDiabetic(BasicOptions.NO);
        }
        if(this.diabetic.getSelectedToggle().equals(this.YesDiabetic)){
            patient.setDiabetic(BasicOptions.YES);
        }
        if(this.diabetic.getSelectedToggle().equals(this.UnknownDiabetic)){
            patient.setDiabetic(BasicOptions.UNKNOWN);
        }
    }
    
    public void settingCholesterol(){
        if(this.cholesterol.getSelectedToggle().equals(this.LowCholesterol)){
            patient.setChosterol(LevelUnknown.LOW);
        }
        if(this.cholesterol.getSelectedToggle().equals(this.NormalCholesterol)){
            patient.setChosterol(LevelUnknown.NORMAL);
        }
        if(this.cholesterol.getSelectedToggle().equals(this.HighCholesterol)){
            patient.setChosterol(LevelUnknown.HIGH);
        }
        if(this.cholesterol.getSelectedToggle().equals(this.UnknownCholesterol)){
            patient.setChosterol(LevelUnknown.UNKNOWN);
        }
    }
    
    public void settingCardiacEvent(){
        if(CardiacEvent.getValue().equals("Infarction")){
            patient.setPreviousEvent(Event.INFARCTION);
        }
        if(CardiacEvent.getValue().equals("Ictus")){
            patient.setPreviousEvent(Event.ICTUS);
        }
        if(CardiacEvent.getValue().equals("Arterial disease")){
            patient.setPreviousEvent(Event.ARTERIALDISEASE);
        }
        if(CardiacEvent.getValue().equals("Other")){
            patient.setPreviousEvent(Event.OTHERS);
        }
        if(CardiacEvent.getValue().equals("None")){
            patient.setPreviousEvent(Event.NO);
        }
    }
    
    public void settingFamilyEvent(){
        if(FamilyEvent.getValue().equals("Mother")){
            patient.setFamilyHistory(Family.MOTHER);
        }
        if(FamilyEvent.getValue().equals("Father")){
            patient.setFamilyHistory(Family.FATHER);
        }
        if(FamilyEvent.getValue().equals("Sibling")){
            patient.setFamilyHistory(Family.SIBLING);
        }
        if(FamilyEvent.getValue().equals("Other")){
            patient.setFamilyHistory(Family.OTHERS);
        }
        if(FamilyEvent.getValue().equals("None")){
            patient.setFamilyHistory(Family.NO);
        }     
    }
    
    boolean checkFloat(String s) {
        if (s == null){ // checks if the String is null 
            return false;}
        if (s.equals("")){ 
         return false;}
        try{
            float floatNumber= Float.parseFloat(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
   }
    
    public boolean setSystolic(){
        String systolicPresure = systolic.getText();
        boolean isFloat= checkFloat(systolicPresure);
        if(isFloat==false){
            systolic.setStyle("-fx-background-color: red");
        }
        else{
            float systPres=Float.parseFloat(systolicPresure);
            patient.setSystolicPressure(systPres);
            systolic.setStyle("-fx-background-color: white");
        }
        return isFloat;
    }
    
    public boolean setDiastolic(){
        String diastolicPresure = diastolic.getText();
        boolean isFloat= checkFloat(diastolicPresure);
        if(isFloat==false){
            diastolic.setStyle("-fx-background-color: red");
        }
        else{
            float systPres=Float.parseFloat(diastolicPresure);
            patient.setDiastolicPressure(systPres);
            diastolic.setStyle("-fx-background-color: white");
        }
        return isFloat;
    }
    
    public void changeSceneToPainInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PainInfo.fxml"));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);
        
        PainInfoController controller = loader.getController();
        settingTension();
        settingHeartRate();
        settingSmoker();
        settingDinker();
        settingDiabetic();
        settingCholesterol();
        settingCardiacEvent();
        settingFamilyEvent();
        
        boolean systolicIsGood=setSystolic();
        boolean diastolicIsGood=setDiastolic();
        
        if((systolicIsGood==true)&&(diastolicIsGood==true)){

            controller.initData(patient, socket, window, fromServer, toServer);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        }
    }
        
    public void backButtonPushed(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PersonalInfo.fxml"));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);
        PersonalInfoController controller = loader.getController();
        controller.initDataBack(patient, socket, window, fromServer, toServer);
                
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tensionCond = new ToggleGroup();
        this.Hypertension.setToggleGroup(tensionCond);
        this.NormalBP.setToggleGroup(tensionCond);
        this.Hypotension.setToggleGroup(tensionCond);
        this.UnknownBP.setToggleGroup(tensionCond);
        tensionCond.selectToggle(NormalBP);
        
        heartRate = new ToggleGroup();
        this.LowHR.setToggleGroup(heartRate);
        this.NormalHR.setToggleGroup(heartRate);
        this.HighHR.setToggleGroup(heartRate);
        heartRate.selectToggle(NormalHR);
        
        drinker = new ToggleGroup();
        this.NoDrinker.setToggleGroup(drinker);
        this.YesDrinker.setToggleGroup(drinker);
        this.UnknownDrinker.setToggleGroup(drinker);
        drinker.selectToggle(NoDrinker);
        
        smoker = new ToggleGroup();
        this.NoSmoker.setToggleGroup(smoker);
        this.YesSmoker.setToggleGroup(smoker);
        this.UnknownSmoker.setToggleGroup(smoker);
        smoker.selectToggle(NoSmoker);
        
        diabetic = new ToggleGroup();
        this.YesDiabetic.setToggleGroup(diabetic);
        this.NoDiabetic.setToggleGroup(diabetic);
        this.UnknownDiabetic.setToggleGroup(diabetic);
        diabetic.selectToggle(NoDiabetic);
        
        cholesterol = new ToggleGroup();
        this.LowCholesterol.setToggleGroup(cholesterol);
        this.NormalCholesterol.setToggleGroup(cholesterol);
        this.HighCholesterol.setToggleGroup(cholesterol);
        this.UnknownCholesterol.setToggleGroup(cholesterol);
        cholesterol.selectToggle(NormalCholesterol);

        CardiacEvent.getItems().addAll("Infarction", "Ictus", "Arterial disease","Other","None");
        CardiacEvent.setValue("None");
        
        FamilyEvent.getItems().addAll("Mother", "Father", "Sibling","Other","None");
        FamilyEvent.setValue("None");
    }    
    
    private void releaseResources()  {
        try {
            toServer.writeObject("logout");
        } catch (IOException ex) {
            Logger.getLogger(MedicalInfoController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            toServer.close();
        } catch (IOException ex) {
            Logger.getLogger(MedicalInfoController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        try {
            fromServer.close();
        } catch (IOException ex) {
            Logger.getLogger(MedicalInfoController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            socket.close();
            System.out.println("socket closed");
        } catch (IOException ex) {
            Logger.getLogger(MedicalInfoController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
    }
    
}
