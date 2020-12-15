/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Patient.Patient;
import Patient.BasicOptions;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
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
public class PainInfoController implements Initializable {
    
    Patient patient= new Patient();

    @FXML private RadioButton YesCPn;
    @FXML private RadioButton NoCPn;
    @FXML private RadioButton UnknownCPn;
    private ToggleGroup chestPain;
    
    @FXML private RadioButton YesCPrs;
    @FXML private RadioButton NoCPrs;
    @FXML private RadioButton UnknownCPrs;
    private ToggleGroup chestPresure;
    
    @FXML private RadioButton YesNP;
    @FXML private RadioButton NoNP;
    @FXML private RadioButton UnknownNP;
    private ToggleGroup neckPain;
    
    @FXML private RadioButton YesAP;
    @FXML private RadioButton NoAP;
    @FXML private RadioButton UnknownAP;
    private ToggleGroup armPain;
    
    @FXML private RadioButton YesBP;
    @FXML private RadioButton NoBP;
    @FXML private RadioButton UnkownBP;
    private ToggleGroup backPain;
    
    @FXML private RadioButton YesSB;
    @FXML private RadioButton NoSB;
    private ToggleGroup shortBreath;
    
    @FXML private RadioButton yesSw;
    @FXML private RadioButton NoSw;
    private ToggleGroup sweating;
    
    @FXML private RadioButton YesNa;
    @FXML private RadioButton NoNa;
    @FXML private RadioButton UnknownNa;
    private ToggleGroup nauseas;
    
    @FXML private RadioButton YesVo;
    @FXML private RadioButton NoVo;
    @FXML private RadioButton UnknownVo;
    private ToggleGroup vomiting;
    
    @FXML private RadioButton YesAnx;
    @FXML private RadioButton NoAnx;
    @FXML private RadioButton UnknownAnx;
    private ToggleGroup anxiety;

    @FXML private RadioButton YesCo;
    @FXML private RadioButton NoCo;
    @FXML private RadioButton UnknownCo;
    private ToggleGroup cough;

    @FXML private RadioButton YesDizz;
    @FXML private RadioButton NoDizz;
    @FXML private RadioButton UnknownDizz;
    private ToggleGroup dizziness;
    
    @FXML TextField notesField;

    
    public void initDataBack(Patient paciente){
        this.patient= paciente;
        
        BasicOptions CPr= patient.getChestPressure();
        if(CPr.equals(BasicOptions.YES)){
            chestPresure.selectToggle(YesCPrs);
        }
        if(CPr.equals(BasicOptions.NO)){
            chestPresure.selectToggle(NoCPrs);
        }
        if(CPr.equals(BasicOptions.UNKNOWN)){
            chestPresure.selectToggle(UnknownCPrs);
        }
        
        BasicOptions CPn= patient.getChestPain();
        if(CPn.equals(BasicOptions.YES)){
            chestPain.selectToggle(YesCPn);
        }
        if(CPn.equals(BasicOptions.NO)){
            chestPain.selectToggle(NoCPn);
        }
        if(CPn.equals(BasicOptions.UNKNOWN)){
            chestPain.selectToggle(UnknownCPn);
        }
        
        BasicOptions NP= patient.getNeckPain();
        if(NP.equals(BasicOptions.YES)){
            neckPain.selectToggle(YesNP);
        }
        if(NP.equals(BasicOptions.NO)){
            neckPain.selectToggle(NoNP);
        }
        if(NP.equals(BasicOptions.UNKNOWN)){
            neckPain.selectToggle(UnknownNP);
        }
        
        BasicOptions AP= patient.getArmPain();
        if(AP.equals(BasicOptions.YES)){
            armPain.selectToggle(YesAP);
        }
        if(AP.equals(BasicOptions.NO)){
            armPain.selectToggle(NoAP);
        }
        if(AP.equals(BasicOptions.UNKNOWN)){
            armPain.selectToggle(UnknownAP);
        }
        
        BasicOptions BP= patient.getBackPain();
        if(BP.equals(BasicOptions.YES)){
            backPain.selectToggle(YesBP);
        }
        if(BP.equals(BasicOptions.NO)){
            backPain.selectToggle(NoBP);
        }
        if(BP.equals(BasicOptions.UNKNOWN)){
            backPain.selectToggle(UnkownBP);
        }
        
        boolean SB= patient.getShortnessOfBreath();
        if(SB==true){
            shortBreath.selectToggle(YesSB);
        }
        if(SB==false){
            shortBreath.selectToggle(NoSB);
        }
        
        boolean Sw= patient.getSweating();
        if(Sw==true){
            sweating.selectToggle(yesSw);
        }
        if(Sw==false){
            sweating.selectToggle(NoSw);
        }
        
        BasicOptions Na= patient.getNausea();
        if(Na.equals(BasicOptions.YES)){
            nauseas.selectToggle(YesNa);
        }
        if(Na.equals(BasicOptions.NO)){
            nauseas.selectToggle(NoNa);
        }
        if(Na.equals(BasicOptions.UNKNOWN)){
            nauseas.selectToggle(UnknownNa);
        }
        
        BasicOptions Vo= patient.getVomiting();
        if(Vo.equals(BasicOptions.YES)){
            vomiting.selectToggle(YesVo);}
        
        if(Vo.equals(BasicOptions.NO)){
            vomiting.selectToggle(NoVo);
        }
        if(Vo.equals(BasicOptions.UNKNOWN)){
            vomiting.selectToggle(UnknownVo);
        }
        
        BasicOptions Anx= patient.getAnxiety();
        if(Anx.equals(BasicOptions.YES)){
            anxiety.selectToggle(YesAnx);}
        
        if(Anx.equals(BasicOptions.NO)){
            anxiety.selectToggle(NoAnx);
        }
        if(Anx.equals(BasicOptions.UNKNOWN)){
            anxiety.selectToggle(UnknownAnx);
        }
        
        BasicOptions Co= patient.getCough();
        if(Co.equals(BasicOptions.YES)){
            cough.selectToggle(YesCo);}
        
        if(Co.equals(BasicOptions.NO)){
            cough.selectToggle(NoCo);
        }
        if(Co.equals(BasicOptions.UNKNOWN)){
            cough.selectToggle(UnknownCo);
        }
        
        BasicOptions Dizz= patient.getDizziness();
        if(Dizz.equals(BasicOptions.YES)){
            dizziness.selectToggle(YesDizz);}
        
        if(Dizz.equals(BasicOptions.NO)){
            dizziness.selectToggle(NoDizz);
        }
        if(Dizz.equals(BasicOptions.UNKNOWN)){
            dizziness.selectToggle(UnknownDizz);
        }
        
        notesField.setText(patient.getNotes());
    }
    
    public void initData(Patient paciente){
        this.patient= paciente;
    }
    
    public void chestPainSetting(){
        if(this.chestPain.getSelectedToggle().equals(this.YesCPn)){
            patient.setChestPain(BasicOptions.YES);
        }
        if(this.chestPain.getSelectedToggle().equals(this.NoCPn)){
            patient.setChestPain(BasicOptions.NO);
        }
        if(this.chestPain.getSelectedToggle().equals(this.UnknownCPn)){
            patient.setChestPain(BasicOptions.UNKNOWN);
        }
    }
    
    public void chestPresureSetting(){
        if(this.chestPresure.getSelectedToggle().equals(this.YesCPrs)){
            patient.setChestPressure(BasicOptions.YES);
        }
        if(this.chestPresure.getSelectedToggle().equals(this.NoCPrs)){
            patient.setChestPressure(BasicOptions.NO);
        }
        if(this.chestPresure.getSelectedToggle().equals(this.UnknownCPrs)){
            patient.setChestPressure(BasicOptions.UNKNOWN);
        }
    }
    
    public void neckPainSetting(){
        if(this.neckPain.getSelectedToggle().equals(this.YesNP)){
            patient.setNeckPain(BasicOptions.YES);
        }
        if(this.neckPain.getSelectedToggle().equals(this.NoNP)){
            patient.setNeckPain(BasicOptions.NO);
        }
        if(this.neckPain.getSelectedToggle().equals(this.UnknownNP)){
            patient.setNeckPain(BasicOptions.UNKNOWN);
        }
    }
    
    public void armPainSetting(){
        if(this.armPain.getSelectedToggle().equals(this.YesAP)){
            patient.setArmPain(BasicOptions.YES);
        }
        if(this.armPain.getSelectedToggle().equals(this.NoAP)){
            patient.setArmPain(BasicOptions.NO);
        }
        if(this.armPain.getSelectedToggle().equals(this.UnknownAP)){
            patient.setArmPain(BasicOptions.UNKNOWN);
        }
    }
    
    public void backPainSetting(){
        if(this.backPain.getSelectedToggle().equals(this.YesBP)){
            patient.setBackPain(BasicOptions.YES);
        }
        if(this.backPain.getSelectedToggle().equals(this.NoBP)){
            patient.setBackPain(BasicOptions.NO);
        }
        if(this.backPain.getSelectedToggle().equals(this.UnkownBP)){
            patient.setBackPain(BasicOptions.UNKNOWN);
        }
    }
    
    public void shortnessSetting(){
        if(this.shortBreath.getSelectedToggle().equals(this.YesSB)){
            patient.setShortnessOfBreath(true);
        }
        if(this.shortBreath.getSelectedToggle().equals(this.NoSB)){
            patient.setShortnessOfBreath(false);
        }
    }
    
    public void sweatingSetting(){
        if(this.sweating.getSelectedToggle().equals(this.yesSw)){
            patient.setSweating(true);
        }
        if(this.sweating.getSelectedToggle().equals(this.NoSw)){
            patient.setSweating(false);
        }
    }
    
    public void nauseasSetting(){
        if(this.nauseas.getSelectedToggle().equals(this.YesNa)){
            patient.setNausea(BasicOptions.YES);
        }
        if(this.nauseas.getSelectedToggle().equals(this.NoNa)){
            patient.setNausea(BasicOptions.NO);
        }
        if(this.nauseas.getSelectedToggle().equals(this.UnknownNa)){
            patient.setNausea(BasicOptions.UNKNOWN);
        }
    }
    
    public void vomitingSetting(){
        if(this.vomiting.getSelectedToggle().equals(this.YesVo)){
            patient.setVomiting(BasicOptions.YES);
        }
        if(this.vomiting.getSelectedToggle().equals(this.NoVo)){
            patient.setVomiting(BasicOptions.NO);
        }
        if(this.vomiting.getSelectedToggle().equals(this.UnknownVo)){
            patient.setVomiting(BasicOptions.UNKNOWN);
        }   
    }
    
    public void anxietySetting(){
        if(this.anxiety.getSelectedToggle().equals(this.YesAnx)){
            patient.setAnxiety(BasicOptions.YES);
        }
        if(this.anxiety.getSelectedToggle().equals(this.NoAnx)){
            patient.setAnxiety(BasicOptions.NO);
        }
        if(this.anxiety.getSelectedToggle().equals(this.UnknownAnx)){
            patient.setAnxiety(BasicOptions.UNKNOWN);
        }
    }
    
    public void coughSetting(){
        if(this.cough.getSelectedToggle().equals(this.YesCo)){
            patient.setCough(BasicOptions.YES);
        }
        if(this.cough.getSelectedToggle().equals(this.NoCo)){
            patient.setCough(BasicOptions.NO);
        }
        if(this.cough.getSelectedToggle().equals(this.UnknownCo)){
            patient.setCough(BasicOptions.UNKNOWN);
        }
    }
    
    public void dizzinessSetting(){
        if(this.dizziness.getSelectedToggle().equals(this.YesDizz)){
            patient.setDizziness(BasicOptions.YES);
        }
        if(this.dizziness.getSelectedToggle().equals(this.NoDizz)){
            patient.setDizziness(BasicOptions.NO);
        }
        if(this.dizziness.getSelectedToggle().equals(this.UnknownDizz)){
            patient.setDizziness(BasicOptions.UNKNOWN);
        }
    }
    
    public void setNotes(){
        String notes= notesField.getText();
        if(notes.equals("")){
            patient.setNotes(" "); 
        }else{
            patient.setNotes(notes);
        }
    }
    
    @FXML
    private void savePatient(ActionEvent event) throws IOException {
        chestPainSetting();
        chestPresureSetting();
        neckPainSetting();
        armPainSetting();
        backPainSetting();
        shortnessSetting();
        sweatingSetting();
        nauseasSetting();
        vomitingSetting();
        anxietySetting();
        coughSetting();
        dizzinessSetting();
        setNotes();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowPatient.fxml"));
        Parent showPatientParent = loader.load();
        
        Scene painInfoScene = new Scene(showPatientParent);
        ShowPatientController controller = loader.getController();
        controller.initData(patient);
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(painInfoScene);
            window.show();
        
    }
    
    
    public void backButtonPushed(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MedicalInfo.fxml"));
        Parent painInfoParent = loader.load();
        
        Scene painInfoScene = new Scene(painInfoParent);
        MedicalInfoController controller = loader.getController();
        controller.initDataBack(patient);
                
                
            //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(painInfoScene);
        window.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
// TODO
        chestPain = new ToggleGroup();
        this.YesCPn.setToggleGroup(chestPain);
        this.NoCPn.setToggleGroup(chestPain);
        this.UnknownCPn.setToggleGroup(chestPain);
        chestPain.selectToggle(NoCPn);
        
        chestPresure = new ToggleGroup();
        this.YesCPrs.setToggleGroup(chestPresure);
        this.NoCPrs.setToggleGroup(chestPresure);
        this.UnknownCPrs.setToggleGroup(chestPresure);
        chestPresure.selectToggle(NoCPrs);
        
        neckPain = new ToggleGroup();
        this.NoNP.setToggleGroup(neckPain);
        this.YesNP.setToggleGroup(neckPain);
        this.UnknownNP.setToggleGroup(neckPain);
        neckPain.selectToggle(NoNP);

        armPain = new ToggleGroup();
        this.YesAP.setToggleGroup(armPain);
        this.NoAP.setToggleGroup(armPain);
        this.UnknownAP.setToggleGroup(armPain);
        armPain.selectToggle(NoAP);
        
        backPain = new ToggleGroup();
        this.YesBP.setToggleGroup(backPain);
        this.NoBP.setToggleGroup(backPain);
        this.UnkownBP.setToggleGroup(backPain);
        backPain.selectToggle(NoBP);
        
        shortBreath = new ToggleGroup();
        this.YesSB.setToggleGroup(shortBreath);
        this.NoSB.setToggleGroup(shortBreath);
        shortBreath.selectToggle(NoSB);
        
        sweating = new ToggleGroup();
        this.yesSw.setToggleGroup(sweating);
        this.NoSw.setToggleGroup(sweating);
        sweating.selectToggle(NoSw);
        
        nauseas = new ToggleGroup();
        this.YesNa.setToggleGroup(nauseas);
        this.NoNa.setToggleGroup(nauseas);
        this.UnknownNa.setToggleGroup(nauseas);
        nauseas.selectToggle(NoNa);
        
        vomiting = new ToggleGroup();
        this.YesVo.setToggleGroup(vomiting);
        this.NoVo.setToggleGroup(vomiting);
        this.UnknownVo.setToggleGroup(vomiting);
        vomiting.selectToggle(NoVo);
        
        anxiety = new ToggleGroup();
        this.YesAnx.setToggleGroup(anxiety);
        this.NoAnx.setToggleGroup(anxiety);
        this.UnknownAnx.setToggleGroup(anxiety);
        anxiety.selectToggle(NoAnx);
        
        cough = new ToggleGroup();
        this.YesCo.setToggleGroup(cough);
        this.NoCo.setToggleGroup(cough);
        this.UnknownCo.setToggleGroup(cough);
        cough.selectToggle(NoCo);
        
        dizziness = new ToggleGroup();
        this.YesDizz.setToggleGroup(dizziness);
        this.NoDizz.setToggleGroup(dizziness);
        this.UnknownDizz.setToggleGroup(dizziness);
        dizziness.selectToggle(NoDizz);

    }    
    
}
