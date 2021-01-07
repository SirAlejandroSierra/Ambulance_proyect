/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

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
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private LineChart<?, ?> ecgGraphics;
    
    ArrayList<Integer> ecgValues = new ArrayList<Integer>();
   // XYChart.Series series = new XYChart.Series();
    Patient patient;
    XYChart.Series series;

   
    public void initData(Patient patient){
        this.patient =patient;
        
        //ecgValues = BitalinoDemo.ecgValues;
        ecgValues = patient.getRecordedECG();
        if(!ecgValues.isEmpty()){
            for (int i = 0; i < ecgValues.size() ; i++) {
               series.getData().add(new XYChart.Data(""+i,ecgValues.get(i)));  
            }
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
}
    

       