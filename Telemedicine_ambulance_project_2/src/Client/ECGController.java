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
import java.io.ObjectOutputStream;
import java.net.Socket;
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
    private Socket socket;
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
    XYChart.Series series;
    
    private Stage window;
    private ObjectOutputStream toServer;

    
 
    
    public void initData(String lead, Patient patient, Socket socket, Stage stage){
        this.lead=lead;
        this.patient =patient;
        this.socket=socket;
        
        this.window=stage;
        window.setOnCloseRequest((event) -> {
            releaseResources(this.socket);
        });
        
        ecgValues = patient.getRecordedECG();
        
        if(!ecgValues.isEmpty()){
            for (int i = 0; i < ecgValues.size() ; i++) {
               series.getData().add(new XYChart.Data(""+i,ecgValues.get(i)));  
            }
            ecgGraphics.setTitle(""+lead);
            ecgGraphics.getData().addAll(series);
        }
    }

  
    /**
     * Initializes the controller class.
     */
    
    @Override
    public  void initialize(URL url, ResourceBundle rb) {
        series = new XYChart.Series();
        //ecgValues = BitalinoDemo.ecgValues;
        
        
    }
    
    
    
    @FXML
    public void closeButton(ActionEvent event)throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ShowPatient.fxml"));
        Parent showParent = loader.load();

        Scene bitalinoScene = new Scene(showParent);

        ShowPatientController controller = loader.getController();
        controller.ECGnextButton(patient, socket);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        

        window.setScene(bitalinoScene);
        window.show();
        
    }
    @FXML
    public void recordAgain(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BitalinoRecordingData.fxml"));
        Parent bitalinoRecordinParent = loader.load();

        Scene bitalinoScene = new Scene(bitalinoRecordinParent);

        BitalinoRecordingDataController controller = loader.getController();
        
        controller.init(patient, socket, window);
        
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        

        window.setScene(bitalinoScene);
        window.show();
        
    }
    
    private void releaseResources(Socket socket)  {
        try {
            toServer = new ObjectOutputStream(socket.getOutputStream());
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
            socket.close();
            System.out.println("socket closed");
        } catch (IOException ex) {
            Logger.getLogger(ShowPatientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
    

       