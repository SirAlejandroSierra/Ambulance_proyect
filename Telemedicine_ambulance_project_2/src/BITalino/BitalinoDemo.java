package BITalino;


import java.util.ArrayList;
import java.util.Vector;
import javax.bluetooth.RemoteDevice;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BitalinoDemo {

    public static Frame[] frame;
    public static ArrayList<Integer> ecgValues = new ArrayList();

    public static void setEcgValues(ArrayList<Integer> ecgValues) {
        BitalinoDemo.ecgValues = ecgValues;
    }
    
    public ArrayList<Integer> getEcgValues() {
        return ecgValues;
    }
    
   public static void startECGvalues(){
       ecgValues = new ArrayList(); 
       
       BITalino bitalino = null;
        
        try {
            bitalino = new BITalino();

            String macAddress = "98:D3:91:FD:69:70";
            int SamplingRate = 100;
            bitalino.open(macAddress, SamplingRate);

            // start acquisition on analog channels A2 and A6
            //If you want A1, A3 and A4 you should use {0,2,3}
            int[] channelsToAcquire = {1};
            bitalino.start(channelsToAcquire);

            //read 10000 samples
            for (int j = 0; j < 3; j++) {

                //Read a block of 100 samples 
                frame = bitalino.read(10);
                

                System.out.println("size block: " + frame.length);

                //Print the samples
                for (int i = 0; i < frame.length; i++) {
                    System.out.println((j * 10 + i) + " seq: " + frame[i].seq + " --> "
                            + frame[i].analog[0]+ " ");
                    ecgValues.add(frame[i].analog[0]);
                    System.out.println(ecgValues.get(j * 10 + i));
                    
                }
                
            }
            
            setEcgValues(ecgValues);
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
