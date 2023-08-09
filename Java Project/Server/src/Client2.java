/*
 * Name : Azizbek Muminjonov
 * ID : U2110207
 * */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {

    BufferedReader reader;
    DataInputStream fromServer;
    DataOutputStream toServer;
    Socket socket;

    public Client2() {
        try {
            // Create a socket to connect to the server
            socket = new Socket("localhost", 6969);

            Scanner sc = new Scanner(System.in);
            System.out.println("Please Enter Message or E -to exit");
            String message = sc.next();
            while (message != "E") {


                reader = new BufferedReader(new InputStreamReader(System.in));
                // Create an input stream to receive data from the server
                fromServer = new DataInputStream(
                        socket.getInputStream());

                // Create an output stream to send data to the server
                toServer = new DataOutputStream(socket.getOutputStream());
                toServer.writeUTF(message + '\n');
                toServer.flush();

                String response = fromServer.readLine();
                System.out.println("\n~" + response);
                System.out.println("Please Enter Message or E -to exit");
                message = sc.next();
            }

        } catch (IOException ex) {
            System.out.println(ex.toString() + '\n');
        }

    }

    public static void main(String[] args) throws IOException {

        new Client2();

    }
}