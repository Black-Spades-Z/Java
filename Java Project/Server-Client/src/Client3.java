import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client3 {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        String ip;
        Scanner in = new Scanner(System.in);
        System.out.print("IP: ");
        ip=in.nextLine();
        while (true){

            //establish socket connection to server
            socket = new Socket(ip, 9876);

            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            System.out.print("Message: ");
            String word = in.nextLine();
            Message ms=new Message(word);
            System.out.println("Sent: " + ms.getText()+"   "+ms.getEx());

            oos.writeObject(new Message(word));

            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();
            System.out.println("Recieved: " + message.getText()+"   "+message.getEx());
            if(message.getEx()==true){
                ois.close();
                oos.close();
                socket.close();
                break;
            }
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }

    }
}