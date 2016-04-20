package server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
/**
 * Created by KristianOs on 22.02.2016.
 */
public class Server {
    public static void main(String[] args) throws Exception {
        ArrayList<String> log = new ArrayList<String>();
        String[] clientPackage;
        int number = 0;
        ServerSocket welcomeSocket = new ServerSocket(5600);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Connection received from: " +
                    connectionSocket.getInetAddress().getHostName());
            BufferedReader inFromClient =
                    new BufferedReader(new
                            InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());
            clientPackage = inFromClient.readLine().split("%");
            System.out.print( "Data received: " );
            for (String x: clientPackage) {
                System.out.print(x);
            }
            System.out.println();
            switch (clientPackage[0]){
                case "1" :
                    int addNumber = Integer.parseInt(clientPackage[1]);
                    number += addNumber;
                    log.add("Added: \t\t\t" + addNumber);
                    outToClient.writeBytes("Added number: " + Integer.parseInt(clientPackage[1]) + "\n");
                    break;
                case "2" :
                    int subNumber = Integer.parseInt(clientPackage[1]);
                    number -= subNumber;
                    log.add("Subtracted: \t" + subNumber);
                    outToClient.writeBytes("Subtracted number: " + Integer.parseInt(clientPackage[1]) + "\n");
                    break;
                case "3" :
                    outToClient.writeBytes("The stored number is: " + number + "\n");
                    break;
                case "4" :
                    String stringToSend = "Full log:%";
                    for (String x : log) {
                        stringToSend += (x + "%");
                    }
                    stringToSend += "\n";
                    outToClient.writeBytes(stringToSend);
                    break;
            }
        }
    }


}