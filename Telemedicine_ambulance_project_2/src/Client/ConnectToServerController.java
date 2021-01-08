/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class ConnectToServerController implements Initializable {
    Socket socket;
    InetAddress serverAddress;
    int serverPort;
    
    
    @FXML private TextField address;
    @FXML private TextField port;
    @FXML private Label label;
    
    
    boolean checkNumericAndPoint(String s) {
        if (s == null) { // checks if the String is null
            return false;
        }
        if (s.equals("")) { // checks if the String is null
            return false;
        }
        int len = s.length();
        for (int i = 0; i < s.length(); i++) {
            // checks whether the character is not a letter
            // if it is not a letter ,it will return false
           
            if (!Character.isDigit(s.charAt(i))) {
                int comparison= Character.compare(s.charAt(i), '.');
                if (comparison != 0 ) {
                    return false;
                }

            }
        }
        return true;
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
    
    public void connectServer(InetAddress addressServer, int portServer, ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("AmbulanceWindow.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        AmbulanceWindowController controller = loader.getController();


        try {
            socket = new Socket(addressServer, portServer);
            System.out.println("Socket is connected with server!");


            controller.initData(socket);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(scene);

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
    
    @FXML
    public void connect(ActionEvent event) throws IOException{
        label.setText("");
        if(address.getText().equals("")||port.getText().equals("") || checkNumericAndPoint(address.getText()) || checkInteger(port.getText())){
            label.setText("ERROR! Check the data again");  
        } else{
            
            try{
            
            
                serverAddress= InetAddress.getByName(address.getText());
                
                serverPort= Integer.parseInt(port.getText());
                
                connectServer(serverAddress, serverPort, event);

                
            }catch(Exception e){
                label.setText("ERROR! Check the data again");
            }
            
        } 
    }
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label.setText("");
    }    
    
}
