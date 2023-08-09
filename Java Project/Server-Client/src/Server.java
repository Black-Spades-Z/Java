import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Server{
    private ServerSocket serverSocket;
    //socket server port on which it will listen
    private static int port = 9876;
    static ArrayList clients = new ArrayList<>();
    Message message = null;

    public Server(ServerSocket serverSocket) throws UnknownHostException {
        this.serverSocket=serverSocket;
        System.out.println("Server IP: " + InetAddress.getLocalHost().getHostAddress());
        System.out.println("Waiting for the client request");
    }

    public void runServer(){
        try{
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A new user has joined");
                Player player = new Player(socket);
                System.out.println("Running a thread");
                Thread thread = new Thread(player);
                thread.start();
                String[] names=player.GetAllNames();

                for(int i = 0; i < names.length;i++){
                    System.out.println(names[i]);
                }

            }
        } catch (IOException e) {
            CloseEverything();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void CloseEverything(){
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Server server = new Server(serverSocket);
        server.runServer();
    }
}