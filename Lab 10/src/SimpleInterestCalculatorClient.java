import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SimpleInterestCalculatorClient {


    private DataOutputStream toServer;
    private DataInputStream fromServer;


    public SimpleInterestCalculatorClient() {

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);
            // Socket socket = new Socket("130.254.204.36", 8000);
            // Socket socket = new Socket("drake.Armstrong.edu", 8000);

            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(
                    socket.getInputStream());

            // Create an output stream to send data to the server
            toServer =
                    new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
