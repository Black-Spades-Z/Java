import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    Socket socket = null;
    private String name;
    int ClientID;
    static int id=0;
    Scanner in = new Scanner(System.in);
    ObjectInputStream objectInputStream = null;
    ObjectOutputStream objectOutputStream=null;

    public Client(Socket soc, String name) {
        try {
            this.socket = soc;
            this.name = name+id;
            id++;
            //Client's Name =(his actual username)+his # of created Client instance.

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            objectOutputStream.writeObject(new Message(name));

        } catch (IOException e) {
            CloseEverything(socket, objectOutputStream, objectInputStream);
        }
    }

    public void SendMessage() {
        try {
            while (socket.isConnected()) {
                System.out.println("Message:");
                objectOutputStream.writeObject(new Message(name + ": " + in.nextLine()));
            }

        } catch (IOException e) {
            CloseEverything(socket, objectOutputStream, objectInputStream);
        }
    }


    public void ListenToMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message MesFromChat;

                while (socket.isConnected()) {
                    try {
                        MesFromChat = (Message) objectInputStream.readObject();
                        System.out.println(MesFromChat.getText());

                    } catch (IOException e) {
                        CloseEverything(socket, objectOutputStream, objectInputStream);
                    } catch (ClassNotFoundException e) {
                        CloseEverything(socket, objectOutputStream, objectInputStream);
                    }
                }
            }
        }).start();
    }

    private void CloseEverything(Socket socket, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        try {
            if(objectInputStream!=null)
                objectInputStream.close();
            if(objectOutputStream!=null)
                objectOutputStream.close();
            if(socket!=null)
                socket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        System.out.print("IP: ");
        Scanner in = new Scanner(System.in);
        Socket socket;
            //establish socket connection to server
        socket = new Socket(in.nextLine(), 8000);
        System.out.println("Enter your name: ");
        String name = in.nextLine();
        Client client = new Client(socket,name);

        client.ListenToMessage();
        client.SendMessage();
    }
}