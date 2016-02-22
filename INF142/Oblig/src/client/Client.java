package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by KristianOs on 22.02.2016.
 */
public class Client {

    public static void main(String[] args) throws Exception {

        while(true){
            int choice = -1;
            String serverResponce[];
            Scanner input = new Scanner(System.in);
            Socket clientSocket = new Socket("localhost", 5600);

            DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            printOutWelcomeMessage();
            choice = Integer.parseInt(input.next());
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.print("Enter a number you would like to add to server number (If no number is detected it will send '0') : ");
                    toServer.writeBytes("1%" + input.next() + "\n");
                    break;
                case 2:
                    System.out.print("Enter a number you would like to subtract from server number (If no number is detected it will send '0') : ");
                    toServer.writeBytes("2%" + input.next() + "\n");
                    break;
                case 3:
                    toServer.writeBytes("3%0\n");
                    break;
                case 4:
                    toServer.writeBytes("4%0\n");
                    break;
                case 0:
                    System.exit(0);
                default:
                    break;
            }
            System.out.println("FROM SERVER: ");
            serverResponce = fromServer.readLine().split("%");

            for (String x : serverResponce) {
                System.out.println(x);

            }
            clientSocket.close();
        }

    }
    private static void printOutWelcomeMessage() {
        System.out.print("Welcome to the Client!\n" +
                "To add a number: \t\t press '1'\n" +
                "To subtract a number: \t press '2'\n" +
                "To show result: \t\t press '3'\n" +
                "To show log: \t\t\t press '4'\n" +
                "To exit the client: \t press '0' \n" +
                "Input from user: ");
    }
}
