package onlinechat.onlinechat02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;




public class HelloController {
    // com.client.server address
    private static final String SERVER_HOST = "localhost";
    // port
    private static final int SERVER_PORT = 3443;
    // com.client socket
    private Socket clientSocket;
    // importing message
    private Scanner inMessage;
    // outgoing message
    private PrintWriter outMessage;
    // next variables for GUI

    // Name of Client
    private String clientName = "";
    // String for mainPane
    private String messageForPane = "";

    public static String numberOfClients;

    // получаем имя клиента
    public String getClientName() {
        return this.clientName;
    }
    public void setClientName(String name)
    {
        clientName = name;
    }

    @FXML
    private BorderPane mainBorderPane;


    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextArea mainArea;

    @FXML
    private Label mainInfo;

    public void setMainInfo(Label mainInfo) {
        this.mainInfo = mainInfo;
        mainInfo.setText("Clients in chat : ");
    }

    @FXML
    private HBox mainHbox;

    @FXML
    private TextField textField;

    @FXML
    private TextField nameField;

    @FXML
    private Button sendButton;

    @FXML
    protected void setSendButton(ActionEvent event)
    {
        if(!textField.getText().trim().isEmpty() && !nameField.getText().trim().isEmpty())
        {
            setClientName(nameField.getText());
            sendMsg();
            textField.setText("");
        }
    }

    // конструктор
    public HelloController() {
        try {
            // подключаемся к серверу
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // в отдельном потоке начинаем работу с сервером
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // бесконечный цикл
                    while (true) {
                        // если есть входящее сообщение
                        if (inMessage.hasNext()) {
                            // считываем его
                            String inMes = inMessage.nextLine();
                            String clientsInChat = "Клиентов в чате = ";
                            if (inMes.indexOf(clientsInChat) == 0) {
                                numberOfClients = inMes;
                            } else {
                                // выводим сообщение
                                mainArea.appendText(inMes);
                                // добавляем строку перехода
                                mainArea.appendText("\n");
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        // добавляем обработчик события закрытия окна клиентского приложения
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
//                try {
//                    // здесь проверяем, что имя клиента непустое и не равно значению по умолчанию
//                    if (!clientName.isEmpty() && clientName != "Введите ваше имя: ") {
//                        outMessage.println(clientName + " вышел из чата!");
//                    } else {
//                        outMessage.println("Участник вышел из чата, так и не представившись!");
//                    }
//                    // отправляем служебное сообщение, которое является признаком того, что клиент вышел из чата
//                    outMessage.println("##session##end##");
//                    outMessage.flush();
//                    outMessage.close();
//                    inMessage.close();
//                    clientSocket.close();
//                } catch (IOException exc) {
//
//                }
//            }
//        });
//        // отображаем форму
//        setVisible(true);
    }

    // отправка сообщения
    public void sendMsg() {
        // формируем сообщение для отправки на сервер
        String messageStr = nameField.getText() + ": " + textField.getText();
        // отправляем сообщение
        outMessage.println(messageStr);
        outMessage.flush();
        textField.setText("");
    }
}