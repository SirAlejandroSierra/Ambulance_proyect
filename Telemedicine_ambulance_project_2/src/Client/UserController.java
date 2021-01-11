/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Patient.Users;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class UserController implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private Label error;

    private Socket socket;
    private ObjectOutputStream toServer;
    private Stage window;
    private ObjectInputStream fromServer;
    private ArrayList<Users> users = new ArrayList<Users>();

    public void initData(Socket socket, Stage stage, ObjectInputStream oi, ObjectOutputStream oo) throws IOException {
        this.socket = socket;
        this.window = stage;
        this.fromServer = oi;
        this.toServer = oo;
        try {
            users = (ArrayList<Users>) fromServer.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        window.setOnCloseRequest((event) -> {
            releaseResources();
        });
    }
    
    public void initDataBack(Socket socket, Stage stage, ObjectInputStream oi, ObjectOutputStream oo, ArrayList<Users> users) throws IOException {
        this.socket = socket;
        this.window = stage;
        this.fromServer = oi;
        this.toServer = oo;
        this.users=users;
        
        window.setOnCloseRequest((event) -> {
            releaseResources();
        });
    }

    public boolean checkUser(Users u, String pintroduced) throws NoSuchAlgorithmException, InvalidKeySpecException {
        for (Users user : users) {
            if (u.authenticate(pintroduced, user.getPassword(), user.getsalt())) {
                return true;
            }
        }
        return false;
    }

    @FXML
    public void next(ActionEvent event) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String pintroduced = password.getText();
        Users u = new Users(userName.getText(), password.getText());
        
        if (!checkUser(u, pintroduced)) {
            error.setText("Wrong user or password");
        } else {
            System.out.println("Good user");

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("AmbulanceWindow.fxml"));
            Parent parent = loader.load();

            Scene scene = new Scene(parent);

            AmbulanceWindowController controller = loader.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            try {
                controller.initData(socket, window, fromServer, toServer, users);
            } catch (Exception e) {
                e.printStackTrace();
            }

            window.setScene(scene);
            window.show();
        }
    }

    public void backToConnection(ActionEvent event) throws IOException {

        releaseResources();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ConnectToServer.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        window.setScene(scene);
        window.setOnCloseRequest((event2) -> {
            window.close();
        });
        window.show();
    }

    private void releaseResources() {
        try {
            toServer.writeObject("logout");
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            toServer.close();
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fromServer.close();
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        error.setText("");
    }

}
