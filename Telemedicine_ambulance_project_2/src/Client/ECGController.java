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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author hecyebesdelpino
 */
public class ECGController implements Initializable {

    //Frame frame = new Frame();
    @FXML
    private LineChart<?, ?> ecgGraphics;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    
     //public static Frame[] frame;
    
    //ArrayList<Integer> ecgValues = new ArrayList();

    /**
     * Initializes the controller class.
     */
 
  
  
    @Override
    public  void initialize(URL url, ResourceBundle rb) {
        XYChart.Series series = new XYChart.Series();
        //Frame[] frame = BITalino.BitalinoDemo.frame;
        ArrayList<Integer> ecgValues = new ArrayList();
        // ArrayList<Integer> ecgValues = new ArrayList<Integer>();
        // BitalinoDemo.
        
        //System.out.println("Hola");
        //System.out.println(ecgValues.get(0));
                
     
        
       /* for (int i = 0; i < ecgValues.size() ; i++) {
           series.getData().add(new XYChart.Data(i,ecgValues.get(i)));
          //series.getData().add(new XYChart.Data(i,frame[i].analog[0]));   
        }*/

        
        
        ecgGraphics.getData().addAll(series);
        
        
    }    
    

    }
    




        /*
        series.getData().add(new XYChart.Data("1",497));
        series.getData().add(new XYChart.Data("2",536));
        series.getData().add(new XYChart.Data("3",527));
        series.getData().add(new XYChart.Data("4",514));
        series.getData().add(new XYChart.Data("5",420));
        series.getData().add(new XYChart.Data("6",516));
        series.getData().add(new XYChart.Data("7",521));
        series.getData().add(new XYChart.Data("8",468));
        series.getData().add(new XYChart.Data("9",503));
        series.getData().add(new XYChart.Data("10",519));
        series.getData().add(new XYChart.Data("11",540));
        series.getData().add(new XYChart.Data("12",545));
        series.getData().add(new XYChart.Data("13",527));
        series.getData().add(new XYChart.Data("14",488));
        series.getData().add(new XYChart.Data("15",539));
        series.getData().add(new XYChart.Data("16",556));
        series.getData().add(new XYChart.Data("17",474));
        series.getData().add(new XYChart.Data("18",496));
        series.getData().add(new XYChart.Data("19",521));
        series.getData().add(new XYChart.Data("20",520));
        series.getData().add(new XYChart.Data("21",511));
        series.getData().add(new XYChart.Data("22",341));
        series.getData().add(new XYChart.Data("23",503));
        series.getData().add(new XYChart.Data("24",516));
        series.getData().add(new XYChart.Data("25",464));
        series.getData().add(new XYChart.Data("26",485));
        series.getData().add(new XYChart.Data("27",509));
        series.getData().add(new XYChart.Data("28",510));
        series.getData().add(new XYChart.Data("29",509));
        series.getData().add(new XYChart.Data("30",495));
        series.getData().add(new XYChart.Data("31",363));
        series.getData().add(new XYChart.Data("32",486));
        series.getData().add(new XYChart.Data("33",529));
        series.getData().add(new XYChart.Data("34",455));
        series.getData().add(new XYChart.Data("35",499));
        series.getData().add(new XYChart.Data("36",495));
        series.getData().add(new XYChart.Data("37",523));
        series.getData().add(new XYChart.Data("38",518));
        series.getData().add(new XYChart.Data("39",509));
        series.getData().add(new XYChart.Data("40",427));
*/