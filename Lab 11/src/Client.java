/*
 * Name : Azizbek Muminjonov
 * ID : U2110207
 * */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client() {
        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);

            Scanner sc = new Scanner(System.in);
            System.out.println("Please Enter Message or E -to exit");
            String message = sc.next();
            while (message != "E") {


                // Create an input stream to receive data from the server
                DataInputStream fromServer = new DataInputStream(
                        socket.getInputStream());

                // Create an output stream to send data to the server
                DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
                toServer.writeUTF(message);
                toServer.flush();
                String response = fromServer.readUTF();

                System.out.println("\n~" + response);
                System.out.println("Please Enter Message or E -to exit");
                message = sc.next();
            }

        } catch (IOException ex) {
            System.out.println(ex.toString() + '\n');
        }

    }

    public static void main(String[] args) throws IOException {

        new Client();

    }
}