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
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

    /**
     * Initializes the controller class.
     */
 
    @FXML
    public void initData(String lead){
        this.lead=lead;
    }

  
    @Override
    public  void initialize(URL url, ResourceBundle rb) {
        XYChart.Series series = new XYChart.Series();
        ecgValues = BitalinoDemo.ecgValues;
        for (int i = 0; i < ecgValues.size() ; i++) {
           series.getData().add(new XYChart.Data(""+i,ecgValues.get(i)));  
        }
        ecgGraphics.setTitle(""+lead);
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
        series.getData().add(new XYChart.Data(0,ecgValues.get(0)));
        series.getData().add(new XYChart.Data(1,ecgValues.get(1)));
        series.getData().add(new XYChart.Data(2,ecgValues.get(2)));
        series.getData().add(new XYChart.Data(3,ecgValues.get(3)));
        series.getData().add(new XYChart.Data(4,ecgValues.get(4)));
        series.getData().add(new XYChart.Data(5,ecgValues.get(5)));
        series.getData().add(new XYChart.Data(6,ecgValues.get(6)));
        series.getData().add(new XYChart.Data(7,ecgValues.get(7)));
        series.getData().add(new XYChart.Data(8,ecgValues.get(8)));
        series.getData().add(new XYChart.Data(9,ecgValues.get(9)));
        series.getData().add(new XYChart.Data(10,ecgValues.get(10)));
        series.getData().add(new XYChart.Data(11,ecgValues.get(11)));
        series.getData().add(new XYChart.Data(12,ecgValues.get(12)));
        series.getData().add(new XYChart.Data(13,ecgValues.get(13)));
        series.getData().add(new XYChart.Data(14,ecgValues.get(14)));
        series.getData().add(new XYChart.Data(15,ecgValues.get(15)));
        series.getData().add(new XYChart.Data(16,ecgValues.get(16)));
        series.getData().add(new XYChart.Data(17,ecgValues.get(17)));
        series.getData().add(new XYChart.Data(18,ecgValues.get(18)));
        series.getData().add(new XYChart.Data(19,ecgValues.get(19)));
        series.getData().add(new XYChart.Data(20,ecgValues.get(20)));
        series.getData().add(new XYChart.Data(21,ecgValues.get(21)));
        series.getData().add(new XYChart.Data(22,ecgValues.get(22)));
        series.getData().add(new XYChart.Data(23,ecgValues.get(23)));
        series.getData().add(new XYChart.Data(24,ecgValues.get(24)));
        series.getData().add(new XYChart.Data(25,ecgValues.get(25)));
        series.getData().add(new XYChart.Data(26,ecgValues.get(26)));
        series.getData().add(new XYChart.Data(27,ecgValues.get(27)));
        series.getData().add(new XYChart.Data(28,ecgValues.get(28)));
        series.getData().add(new XYChart.Data(29,ecgValues.get(29)));
        series.getData().add(new XYChart.Data(30,ecgValues.get(30)));
        series.getData().add(new XYChart.Data(31,ecgValues.get(31)));
        series.getData().add(new XYChart.Data(32,ecgValues.get(32)));
        series.getData().add(new XYChart.Data(33,ecgValues.get(33)));
        series.getData().add(new XYChart.Data(34,ecgValues.get(34)));
        series.getData().add(new XYChart.Data(35,ecgValues.get(35)));
        series.getData().add(new XYChart.Data(36,ecgValues.get(36)));
        series.getData().add(new XYChart.Data(37,ecgValues.get(37)));
        series.getData().add(new XYChart.Data(38,ecgValues.get(38)));
        series.getData().add(new XYChart.Data(39,ecgValues.get(39)));
        series.getData().add(new XYChart.Data(40,ecgValues.get(40)));
        series.getData().add(new XYChart.Data(41,ecgValues.get(41)));
        series.getData().add(new XYChart.Data(42,ecgValues.get(42)));
        series.getData().add(new XYChart.Data(43,ecgValues.get(43)));
        series.getData().add(new XYChart.Data(44,ecgValues.get(44)));
        series.getData().add(new XYChart.Data(45,ecgValues.get(45))); 
        series.getData().add(new XYChart.Data(46,ecgValues.get(46)));
        series.getData().add(new XYChart.Data(47,ecgValues.get(47)));
        series.getData().add(new XYChart.Data(48,ecgValues.get(48)));
        series.getData().add(new XYChart.Data(49,ecgValues.get(49)));
        series.getData().add(new XYChart.Data(50,ecgValues.get(50)));
        series.getData().add(new XYChart.Data(51,ecgValues.get(51)));
        series.getData().add(new XYChart.Data(52,ecgValues.get(52)));
        series.getData().add(new XYChart.Data(53,ecgValues.get(53)));
        series.getData().add(new XYChart.Data(54,ecgValues.get(54)));
        series.getData().add(new XYChart.Data(55,ecgValues.get(55)));
        series.getData().add(new XYChart.Data(56,ecgValues.get(56)));
        series.getData().add(new XYChart.Data(57,ecgValues.get(57)));
        series.getData().add(new XYChart.Data(58,ecgValues.get(58)));
        series.getData().add(new XYChart.Data(59,ecgValues.get(59)));
        series.getData().add(new XYChart.Data(60,ecgValues.get(60)));
        series.getData().add(new XYChart.Data(61,ecgValues.get(61)));
        series.getData().add(new XYChart.Data(62,ecgValues.get(62)));
        series.getData().add(new XYChart.Data(63,ecgValues.get(63)));
        series.getData().add(new XYChart.Data(64,ecgValues.get(64)));
        series.getData().add(new XYChart.Data(65,ecgValues.get(65)));
        series.getData().add(new XYChart.Data(66,ecgValues.get(66)));
        series.getData().add(new XYChart.Data(67,ecgValues.get(67)));
        series.getData().add(new XYChart.Data(68,ecgValues.get(68)));
        series.getData().add(new XYChart.Data(69,ecgValues.get(69)));
        series.getData().add(new XYChart.Data(70,ecgValues.get(70)));
        series.getData().add(new XYChart.Data(71,ecgValues.get(71)));
        series.getData().add(new XYChart.Data(72,ecgValues.get(72)));
        series.getData().add(new XYChart.Data(73,ecgValues.get(73)));
        series.getData().add(new XYChart.Data(74,ecgValues.get(74)));
        series.getData().add(new XYChart.Data(75,ecgValues.get(75)));

*/