import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client2 {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        Socket socket = null;
        String ip;
        Scanner in = new Scanner(System.in);
        System.out.print("IP: ");
        ip=in.nextLine();
        //stablish socket connection to server
        socket = new Socket(ip, 9876);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        System.out.println("Your name:");
        objectOutputStream.writeObject(new Message(in.nextLine()));

        while (true) {
            System.out.println("Send:");
            Message message = new Message(in.nextLine());
            objectOutputStream.writeObject(message);

            System.out.println("Recieved:");
            Message returnMessage = (Message) objectInputStream.readObject();
            System.out.println(returnMessage.getText());
        }
    }
}