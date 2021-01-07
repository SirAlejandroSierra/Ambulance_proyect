/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import BITalino.BITalino;
import BITalino.BITalinoException;
import BITalino.BitalinoDemo;
//import static BITalino.BitalinoDemo.ecgValues;
import BITalino.Frame;
import Patient.Patient;
import java.io.IOException;
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

    //Frame frame = new Frame();
    String lead;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private LineChart<?, ?> ecgGraphics;
    
    ArrayList<Integer> ecgValues = new ArrayList();
   // XYChart.Series series = new XYChart.Series();
    Patient patient;
    @FXML
    private Button exit;
    @FXML
    private Button recordAgain;
    

    /**
     * Initializes the controller class.
     */
 
    public void initData(String lead, Patient patient){
        this.lead=lead;
        this.patient =patient;
    }

  
    @Override
    public  void initialize(URL url, ResourceBundle rb) {
        XYChart.Series series = new XYChart.Series();
        //ecgValues = BitalinoDemo.ecgValues;
        ecgValues = patient.getRecordedECG();
        for (int i = 0; i < ecgValues.size() ; i++) {
           series.getData().add(new XYChart.Data(""+i,ecgValues.get(i)));  
        }
        ecgGraphics.setTitle(""+lead);
        ecgGraphics.getData().addAll(series);
    }
    
    
    
    @FXML
    public void closeButton(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowPatient.fxml"));
        Parent showParent = loader.load();

        Scene bitalinoScene = new Scene(showParent);

        ShowPatientController controller = loader.getController();
        controller.ECGnextButton(patient);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        

        window.setScene(bitalinoScene);
        window.show();
        
    }
    @FXML
    public void recordAgain(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BitalinoRecordingDataController.fxml"));
        Parent bitalinoRecordinParent = loader.load();

        Scene bitalinoScene = new Scene(bitalinoRecordinParent);

        BitalinoRecordingDataController controller = loader.getController();
        
        controller.init(patient);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        

        window.setScene(bitalinoScene);
        window.show();
        
    }
}
    

       