/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Patient.Patient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AdriCortellucci
 */
public class Pruebla {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        FileInputStream fis=null;
        ObjectInputStream input=null;
        File file = new File("./Files/serverPatients.txt");
        ArrayList<Patient> objectsList = new ArrayList<>();

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.length() != 0){
            try {
                input = new ObjectInputStream(new FileInputStream("./Files/serverPatients.txt"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Pruebla.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("read");
            while(true){
                Object obj = input.readObject();
                System.out.println(obj);
                if (obj != null) {
                    objectsList.add((Patient) obj);
                } else {
                    input.close();
                    break;
                }
            }
        }
        System.out.println(objectsList);
    }
    
}
