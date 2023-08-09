import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Connection {
    public static ArrayList <Connection> clients = new ArrayList<>();
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String UserName;

    public Connection (Socket socket) throws IOException {
        this.socket = socket;
        //read from socket to ObjectInputStream object
        oos = new ObjectOutputStream(socket.getOutputStream());
        //create ObjectOutputStream object
        ois = new ObjectInputStream(socket.getInputStream());

        //Add a username of the client
        // this.UserName =

        clients.add(this);
        //Method that will print out every user's action
        SendMessage("SERVER: " + UserName + " has entered the game.");
        CloseEverything(socket, ois, oos);
    }

    public void SendMessage(String text){
        for(Connection client:clients){
            try{
                if(!client.UserName.equals(UserName)){
                    client.oos.writeObject(new Message(text));
                    client.oos.flush();
                }
            }catch (IOException e){
                CloseEverything(socket,ois,oos);
            }
        }
    }

    public void RemoveClient(){
        clients.remove(this);
        SendMessage("SERVER: " + UserName + " has left the game.");

    }

    public void CloseEverything(Socket socket, ObjectInputStream ois, ObjectOutputStream oos){
        RemoveClient();
        try {
            if(ois!=null){
                ois.close();
            }
            if(oos!=null){
                oos.close();
            }
            if(socket!=null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
