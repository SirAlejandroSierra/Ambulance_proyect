/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import BITalino.BITalino;
import BITalino.BITalinoException;
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
import static BITalino.BitalinoDemo.frame;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author hecyebesdelpino
 */
public class BitalinoRecordingDataController implements Initializable {
 
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
      loadData();
      
        System.out.println("Estoy aquí");
        ecgValues3 = BitalinoDemo.getECGValues();
        //try{
        //bitalinodemo.startECGvalues();
        //}catch (Exception e){
          //  e.printStackTrace();
            //System.out.println(e);
        //}
        //BitalinoDemo.startECGvalues();
        
        
        //starBitalino();
        //ArrayList<Integer> ecgValues = new ArrayList();
        /*ecgValues.add(14);
        ecgValues.add(12);
        ecgValues.add(9);   
        */
        System.out.println(ecgValues3.get(0));
        System.out.println(ecgValues3.get(1));
        System.out.println(ecgValues3.get(2));
      
    }    
    
    @FXML
    private void displayValue(ActionEvent event){
        String ecg = choicebox.getValue();
        if (ecg == null){
            screen.setText("  PLEASE SELECT THE LEAD");
        }
        else 
            screen.setText("                RECORDING...");
       
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
    

    
    @FXML
    public void startBitalino(ActionEvent event)throws IOException {
       
      //  BitalinoDemo.startECGvalues();
       // ArrayList<Integer> ecgValues = BitalinoDemo.getECGValues();
        //System.out.println(ecgValues.get(0));
        
    }
    
    
    
    
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
    
    public void starBitalino(){
        
        //ArrayList<Integer> ecgValues = new ArrayList<Integer>();
        try {
            bitalino = new BITalino();
            //find devices
            //Only works on some OS
            // Vector<RemoteDevice> devices = bitalino.findDevices();
            // System.out.println(devices);

            String macAddress = "98:D3:91:FD:69:70";
            int SamplingRate = 10;
            bitalino.open(macAddress, SamplingRate);

            // start acquisition on analog channels A2 and A6
            //If you want A1, A3 and A4 you should use {0,2,3}
            int[] channelsToAcquire = {1};
            bitalino.start(channelsToAcquire);

            //read 10000 samples
            for (int j = 0; j < 10; j++) {

                //Read a block of 10 samples 
                frame = bitalino.read(10);
                

                System.out.println("size block: " + frame.length);

                //Print the samples
                for (int i = 0; i < frame.length; i++) {
                    System.out.println((j * 10 + i) + " seq: " + frame[i].seq + " --> "
                            + frame[i].analog[0]+ " ");
                    ecgValues3.add(frame[i].analog[0]);
                    System.out.println(ecgValues3.get(j * 10 + i));
                    
                }
            }
            //stop acquisition
            bitalino.stop();
        } catch (BITalinoException ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //close bluetooth connection
                if (bitalino != null) {
                    bitalino.close();
                }
            } catch (BITalinoException ex) {
                Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

   }
    }

