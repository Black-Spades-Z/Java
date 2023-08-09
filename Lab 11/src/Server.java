/*
 * Name : Azizbek Muminjonov
 * ID : U2110207
 * */


import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server {

    ArrayList<Socket> socketList = new ArrayList<>();
    public Server()
    {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("MultiThreadServer started at " + new Date() + '\n');

            // Number a client
            int clientNo = 1;

            while (true) {
                // Listen for a new connection request
                Socket socket = serverSocket.accept();
                socketList.add(socket);

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
                Server.HandleAClient task = new Server.HandleAClient(socket);
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

                // Continuously serve the client
                while (true) {
                    // Receive radius from the client
                    String message = inputFromClient.readUTF();


                    // Send area back to the client

                    for (int i = 0; i < socketList.size(); i++)
                    {
                        DataOutputStream outputToClient = new DataOutputStream(socketList.get(i).getOutputStream());
                        outputToClient.writeUTF("Message from port : " + socketList.get(i).getPort() + " \n"+ message +"\n");
                        outputToClient.flush();
                    }



                    // Getting Clients IP address


                    System.out.println("Message from the Client :  " + socket.getPort() + '\n' + message);
                }
            }
            catch(IOException e) {
                System.err.println(e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}
