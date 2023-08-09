/*
* Name : Azizbek Muminjonov
* ID : U2110207
* */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class BMI_Server {

    public static void main(String[] args) {
        new BMI_Server();
    }

    public BMI_Server()
    {

        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started at " + new Date() + '\n');

            // Listen for a connection request
            Socket socket = serverSocket.accept();

            // Create data input and output streams
            DataInputStream inputFromClient = new DataInputStream(
                    socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(
                    socket.getOutputStream());

            while (true) {

                InetAddress ip= socket.getInetAddress();
                System.out.println("Host Name: "+ip.getHostName());
                System.out.println("IP Address: "+ip.getHostAddress());
                // Receive radius from the client

                double height = inputFromClient.readDouble();
                double weight = inputFromClient.readDouble();


                // Compute area
                double bmiIndex = weight / (height * height);

                // Send area back to the client
                outputToClient.writeDouble(bmiIndex);

            }
        }
        catch(IOException ex) {
            System.err.println(ex);
        }
    }

}
