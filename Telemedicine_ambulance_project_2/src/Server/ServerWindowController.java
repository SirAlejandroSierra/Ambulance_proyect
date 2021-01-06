/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Patient.Patient;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AdriCortellucci
 */
public class ServerWindowController implements Initializable {

    public ObservableList<Patient> patients_ = FXCollections.observableArrayList();
    @FXML
    private TableView<Patient> tableView;
    @FXML
    private TableColumn<Patient, String> patientName;
    @FXML
    private TableColumn<Patient, String> ambulance;
    @FXML
    private TableColumn<Patient, Date> date;

    @FXML
    private TextField password;
    @FXML
    private TextField passwordRepeat;

    @FXML
    private Label label;

    public Server_two server = null;

    public void open(ActionEvent event) throws IOException, ClassNotFoundException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ServerOnWindow.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        //access the controller and call a method
        ServerOnWindowController controller = loader.getController();

        if (!password.getText().equals(passwordRepeat.getText())) {
            label.setText("Passwords don´t match");
        } else {

            server = new Server_two(9000, controller, password.getText());

            ObjectInputStream input = null;
            File file = new File("./Files/serverPatients.txt");
            ArrayList<Patient> objectsList = new ArrayList<>();

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.length() != 0) {
                input = new ObjectInputStream(new FileInputStream("./Files/serverPatients.txt"));

                while (true) {
                    try {
                        Object obj = input.readObject();
                        objectsList.add((Patient) obj);
                    } catch (EOFException exc) {
                        break;
                    }
                }
            }

            System.out.println(objectsList);
            server.setPatients(objectsList);

            System.out.println("connected");

            Thread serverThread = (new Thread(server));
            serverThread.start();
            //threads.add(serverThread);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Stage secondStage = new Stage();
            secondStage.setTitle("New Password");
            secondStage.setScene(scene);

            controller.initData(server, window, secondStage);

            secondStage.show();
            window.close();

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Server_two server = null;
        label.setText("");
    }

}
