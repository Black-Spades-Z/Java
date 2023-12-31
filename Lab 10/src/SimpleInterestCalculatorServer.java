///*
//* Name : Azizbek Muminjonov
//* ID : U2110207
//* Lab : 10
//* */
//
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//
//public class SimpleInterestCalculatorServer {
//
//
//    public static void main(String[] args) throws IOException {
//      new SimpleInterestCalculatorClient();
//    }
//
//    public SimpleInterestCalculatorServer()
//    {
//        // Place text area on the frame
//
//
//        try {
//            // Create a server socket
//            ServerSocket serverSocket = new ServerSocket(8000);
//
//            // Listen for a connection request
//            Socket socket = serverSocket.accept();
//
//            // Create data input and output streams
//            DataInputStream inputFromClient = new DataInputStream(
//                    socket.getInputStream());
//            DataOutputStream outputToClient = new DataOutputStream(
//                    socket.getOutputStream());
//
//            while (true) {
//                // Receive radius from the client
//                double radius = inputFromClient.readDouble();
//
//                // Compute area
//                double area = radius * radius * Math.PI;
//
//                // Send area back to the client
//                outputToClient.writeDouble(area);
//            }
//        }
//        catch(IOException ex) {
//            System.err.println(ex);
//        }
//
//    }
//
//}



import java.io.*;
import java.net.*;
import java.util.*;


public class SimpleInterestCalculatorServer
{


    public static void main(String[] args) {
        new SimpleInterestCalculatorServer();
    }

    public SimpleInterestCalculatorServer() {



        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("MultiThreadServer started at " + new Date() + '\n');

            // Number a client
            int clientNo = 1;

            while (true) {
                // Listen for a new connection request
                Socket socket = serverSocket.accept();

                // Display the client number
                System.out.println("Starting thread for client " + clientNo +
                        " at " + new Date() + '\n');

                // Find the client's host name, and IP address
                InetAddress inetAddress = socket.getInetAddress();
                System.out.println("Client " + clientNo + "'s host name is "
                        + inetAddress.getHostName() + "\n");
                System.out.println("Client " + clientNo + "'s IP Address is "
                        + inetAddress.getHostAddress() + "\n");

                // Create a new thread for the connection
                HandleAClient task = new HandleAClient(socket);
                Thread t1 = new Thread(task);
                // Start the new thread
                t1.start();

                // Increment clientNo
                clientNo++;
            }
        }
        catch(IOException ex) {
            System.err.println(ex);
        }
    }

    // Inner class
    // Define the thread class for handling new connection
    class HandleAClient implements Runnable {
        private Socket socket; // A connected socket

        /** Construct a thread */
        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        /** Run a thread */
        public void run() {
            try {
                // Create data input and output streams
                DataInputStream inputFromClient = new DataInputStream(
                        socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(
                        socket.getOutputStream());

                // Continuously serve the client
                while (true) {
                    // Receive radius from the client
                    double radius = inputFromClient.readDouble();

                    // Compute area
                    double area = radius * radius * Math.PI;

                    // Send area back to the client
                    outputToClient.writeDouble(area);

                    System.out.println("radius received from client: " +
                            radius + '\n');
                    System.out.println("Area found: " + area + '\n');
                }
            }
            catch(IOException e) {
                System.err.println(e);
            }
        }
    }
}