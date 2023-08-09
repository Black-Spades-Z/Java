/*
 * Name : Azizbek Muminjonov
 * ID : U2110207
 * */















































//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Date;
//
//
//
//public class Server {
//
//    public ArrayList<Socket> socketArrayList = new ArrayList<>();
//
//    public Server() {
//        try {
//            ServerSocket serverSocket = new ServerSocket(6969);
//            while (true) {
//                System.out.println("Server has started at " + new Date() + "\n");
//                Socket socket = null;
//                try {
//                    // Listening to Server for new connections
//                    socket = serverSocket.accept();
//
//                    System.out.println("New client has joined : " + socket.getPort() + "\n");
//                    socketArrayList.add(socket);
//
//                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//
//                    ClientHandller task = new ClientHandller(socket, inputStream);
//
//                    Thread thread = new Thread(task);
//
//                    thread.start();
//
//                } catch (Exception exception) {
//                    System.out.println(exception.getMessage());
//                }
//
//            }
//
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//        } finally {
//            System.out.println("Shutting down the server");
//        }
//    }
//
//    class ClientHandller implements Runnable {
//        private DataInputStream inputStream;
//        private DataOutputStream outputStream;
//        private Socket socket;
//
//        String message;
//
//        public ClientHandller(Socket socket1, DataInputStream inputStream1) {
//            socket = socket1;
//            inputStream = inputStream1;
//
//        }
//
//        public void run() {
//
//            String message;
//            try {
//                message = inputStream.readLine();
//                for (Socket socket1 : socketArrayList) {
//
//                    outputStream = new DataOutputStream(socket1.getOutputStream());
//                    outputStream.writeUTF("Message from " + socket.getPort() + message+ "\n");
//                    outputStream.flush();
//                }
//
//                System.out.println("Have sent");
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        new Server();
//    }
//
//}
//
//
//
//
//
//
//
//













//public class Server {
//
//    ArrayList<Socket> socketList = new ArrayList<>();
//    public Server()
//    {
//        try {
//            // Create a server socket
//            ServerSocket serverSocket = new ServerSocket(8000);
//            System.out.println("MultiThreadServer started at " + new Date() + '\n');
//
//            // Number a client
//            int clientNo = 1;
//
//            while (true) {
//                // Listen for a new connection request
//                Socket socket = serverSocket.accept();
//                socketList.add(socket);
//
//                // Display the client number
//                System.out.println("Starting thread for client " + clientNo +
//                        " at " + new Date() + '\n');
//
//                // Find the client's host name, and IP address
//                InetAddress inetAddress = socket.getInetAddress();
//                System.out.println("Client " + clientNo + "'s host name is "
//                        + inetAddress.getHostName() + "\n");
//                System.out.println("Client " + clientNo + "'s IP Address is "
//                        + inetAddress.getHostAddress() + "\n");
//
//                // Create a new thread for the connection
//                Server.HandleAClient task = new Server.HandleAClient(socket);
//                Thread t1 = new Thread(task);
//                // Start the new thread
//                t1.start();
//
//                // Increment clientNo
//                clientNo++;
//            }
//        }
//        catch(IOException ex) {
//            System.err.println(ex);
//        }
//    }
//
//    class HandleAClient implements Runnable {
//        BufferedReader inputFromClient;
//        BufferedWriter outputToClient;
//        private Socket socket; // A connected socket
//
//        /** Construct a thread */
//        public HandleAClient(Socket socket) {
//            this.socket = socket;
//        }
//
//        /** Run a thread */
//        public void run() {
//            try {
//                // Create data input and output streams
//                inputFromClient = new BufferedReader( new InputStreamReader(
//                        socket.getInputStream()));
//
//                // Continuously serve the client
//                while (true) {
//                    // Receive radius from the client
//                    String message = inputFromClient.readLine();
//
//
//                    // Send area back to the client
//
//                    for (int i = 0; i < socketList.size(); i++)
//                    {
//                        outputToClient = new BufferedWriter(new OutputStreamWriter(socketList.get(i).getOutputStream()));
//                        outputToClient.write("Message from port : " + socketList.get(i).getPort() + " \n"+ message +"\n");
//                        outputToClient.flush();
//                    }
//
//
//
//                    // Getting Clients IP address
//
//
//                    System.out.println("Message from the Client :  " + socket.getPort() + '\n' + message);
//                }
//            }
//            catch(IOException e) {
//                System.err.println(e);
//            }
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        new Server();
//    }
//}
