/*
 * Name : Azizbek Muminjonov
 * ID : U2110207
 * */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class BMI_Client {




    private DataOutputStream toServer;
    private DataInputStream fromServer;

    public static void main(String[] args) {
        new BMI_Client();
    }

    public BMI_Client() {
        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);

            Scanner sc=new Scanner(System.in);
            System.out.print("Please Enter Height (m) :  ");
            double height =sc.nextDouble();

            System.out.print("Please Enter Weight (kg) :  ");
            double weight =sc.nextDouble();
            while(height !=0)
            {


                // Create an input stream to receive data from the server
                fromServer = new DataInputStream(
                        socket.getInputStream());

                // Create an output stream to send data to the server
                toServer =  new DataOutputStream(socket.getOutputStream());

                toServer.writeDouble(height);
                toServer.writeDouble(weight);


                toServer.flush();

                double bmiIndex = fromServer.readDouble();

                System.out.println("\n\nReceived BMI index is : " + bmiIndex);
                InetAddress ip = socket.getInetAddress();
                System.out.println("Host Name: "+ip.getHostName());
                System.out.println("IP Address: "+ip.getHostAddress() + "\n\n\n\n\n\n");

                System.out.print("Please Enter Height (m) :  ");
                height =sc.nextDouble();

                System.out.print("Please Enter Weight (kg) :  ");
                weight =sc.nextDouble();

            }
        }catch (IOException ex) {
        System.out.println(ex.toString() + '\n');
        }
    }
}