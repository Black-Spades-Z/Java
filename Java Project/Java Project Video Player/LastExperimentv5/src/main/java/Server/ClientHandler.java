package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static com.example.lastexperiment.HelloApplication.clientCommandList;

// реализуем интерфейс Runnable, который позволяет работать с потоками
public class ClientHandler implements Runnable {
    // экземпляр нашего сервера
    private Server server;
    // исходящее сообщение
    private PrintWriter outMessage;
    // Out boolean
    private DataOutputStream outBoolean;
    // входящее собщение
    private Scanner inMessage;
    // Receiving boolean
    private DataInputStream inBoolean;

    private static final String HOST = "172.18.2.173";
    private static final int PORT = 3443;
    // Client socket
    private Socket clientSocket = null;
    // Number of Viewers in Server, static area
    private static int clients_count = 0;

    // конструктор, который принимает клиентский сокет и сервер
    public ClientHandler(Socket socket, Server server) {
        try {
            clients_count++;
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    // Переопределяем метод run(), который вызывается когда
    // мы вызываем new Thread(client).start();
    @Override
    public void run() {
        try {
            while (true) {
                // сервер отправляет сообщение
                server.sendMessageToAllClients("New member in the Server!");
                server.sendMessageToAllClients("Viewers in Server = " + clients_count);
                break;
            }

            while (true) {
                // If there is message from viewer
                if (inMessage.hasNext()) {

                    String clientMessage = inMessage.nextLine();
                    // if viewer sends this message, he leaves the Server
                    // client is leaving Server
                    if (clientMessage.equalsIgnoreCase("##session##end##")) {
                        break;
                    }
                    if(clientMessage.equals("##session##stop##"))
                    {
                        clientCommandList.add(0,clientMessage);
                        // Printing to console
                        System.out.println(clientMessage);
                        // sending this message to all viewers
                        server.sendMessageToAllClients(clientMessage);
                    }
                    else if(clientMessage.equals("##session##play##"))
                    {
                        clientCommandList.add(0,clientMessage);
                        // Printing to console
                        System.out.println(clientMessage);
                        // sending this message to all viewers
                        server.sendMessageToAllClients(clientMessage);
                    }


                    // Printing to console
                    System.out.println(clientMessage);
                    // sending this message to all viewers
                    server.sendMessageToAllClients(clientMessage);
                }
                // stopping the thread for 0.1 sec
                Thread.sleep(100);
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            this.close();
        }
    }
    // отправляем сообщение
    public void sendMsg(String msg) {
        try {
            outMessage.println(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // клиент выходит из чата
    public void close() {
        // удаляем клиента из списка
        server.removeClient(this);
        clients_count--;
        server.sendMessageToAllClients("Viewers in Server = " + clients_count);
    }
}