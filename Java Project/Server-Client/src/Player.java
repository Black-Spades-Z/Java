import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Player implements Runnable{
    public static ArrayList<Player>clientHandlers = new ArrayList<>();
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    Socket socket;
    String name;


    public Player(Socket socket) throws IOException, ClassNotFoundException {
        try {
            this.socket = socket;
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Message message = (Message)objectInputStream.readObject();
            name= message.getText();

            clientHandlers.add(this);


            SendToAll(new Message(name + " has entered the chat."));//           System.out.println(name + " has entered the room.");

        } catch (IOException e){
            CloseEverything(socket,objectOutputStream,objectInputStream);
        }
    }

    @Override
    public void run() {
        Message message;
        try{

            while (socket.isConnected()) {//close when there is a winner
                try {
                    message = (Message) objectInputStream.readObject();
                    SendToAll(new Message(message.getText().toUpperCase()));
                }catch (IOException | ClassNotFoundException e){
                    CloseEverything(socket,objectOutputStream,objectInputStream);
                    break;
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void CloseEverything(Socket socket, ObjectOutputStream objectOutputStream,ObjectInputStream objectInputStream) throws IOException {
        RemovePlayer();
        try {
            if(objectOutputStream!=null)
            objectOutputStream.close();
            if(objectInputStream!=null)
                objectInputStream.close();
            if(socket!=null)
                socket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void SendToAll(Message message) throws IOException {//Change instance of message to Game
        for(Player player:clientHandlers){
            try{
                if(!player.name.equals(name)){//Sends to everyone but him
                    player.objectOutputStream.writeObject(message);
                    player.objectOutputStream.flush();
                }
            } catch (IOException e) {
                CloseEverything(socket,objectOutputStream,objectInputStream);
            }
        }
    }

    public void RemovePlayer() throws IOException {
        clientHandlers.remove(this);
        SendToAll(new Message(name+" has left the room."));
    }

    public String[] GetAllNames(){
        String[]playerNames= new String[clientHandlers.size()];
        for(int i = 0;i <clientHandlers.size();i++){
            playerNames[i]= clientHandlers.get(i).name;
        }
        return playerNames;
    }
}
