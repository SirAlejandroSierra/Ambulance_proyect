package Server;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server_Hospital_Chat_Server_Client implements Runnable {

    String readString;
    Socket socket;

    public Server_Hospital_Chat_Server_Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader consolee = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                try {
                    
                    readString = consolee.readLine();
                    if (readString.toLowerCase().contains("delete ambulance")) {
                        releaseResources(printWriter, consolee, socket);
                    }
                    
                    printWriter.println(readString);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    private static void releaseResources(PrintWriter printWriter, BufferedReader consolee, Socket socket) {

        printWriter.close();
        try {
            consolee.close();
        } catch (IOException ex) {
            Logger.getLogger(Server_Hospital.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server_Hospital.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
