/*
Name : Azizbek Muminjonov
ID : U2110207
*/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MyFxApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");
        
        primaryStage.setWidth(600);
        primaryStage.setHeight(300);

        Label username  = new Label("User Name  ");
        Label password = new Label("Password     ");

        TextField usernameInput = new TextField();
        PasswordField passwordInput = new PasswordField();



        Button submit = new Button ("Submit");
        submit.setOnAction(t -> {System.out.println(usernameInput.getText());System.out.println(passwordInput.getText()); });

        Button clear = new Button ("Clear");
        clear.setOnAction(t -> {
System.out.println("Cleared");
usernameInput.clear();
passwordInput.clear();});
    
        HBox hbox = new HBox(username, usernameInput );
        HBox hbox2 = new HBox(password, passwordInput );
        VBox vbox = new VBox(hbox, hbox2);

        Pane root = new Pane();
        submit.setLayoutX(220);
        submit.setLayoutY(75);
        clear.setLayoutX(280);
        clear.setLayoutY(75);
        vbox.setLayoutX(150);
        vbox.setLayoutY(20);
        root.getChildren().add(submit);
        root.getChildren().add(clear);
        root.getChildren().add(vbox);
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();	
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}