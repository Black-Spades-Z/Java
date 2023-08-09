// Name: Shahboz Raimdjanov
// Project: Lab 07
// Instructor: Abhijit Tarawade
// Date: 02.11.22

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
    public void start(Stage stage) throws Exception {
       stage.setTitle("JavaFx Login Page Example");
        
        stage.setWidth(600);
        stage.setHeight(300);


        Label username = new Label("UserName");
        TextField txtu = new TextField();
        HBox hb1 = new HBox(username, txtu);

        Label password = new Label("Password  ");
        PasswordField txtp = new PasswordField();
        HBox hb2 = new HBox(password, txtp);

        VBox vb = new VBox(hb1, hb2);

        Button submit = new Button("Submit");
        Button clear = new Button("Clear");

        submit.setOnAction(s -> {System.out.println(txtu.getText()); System.out.println(txtp.getText());});
        clear.setOnAction(s -> {txtu.clear(); txtp.clear();});
       

        Pane root = new Pane();
        submit.setLayoutX(220);
        submit.setLayoutY(75);
        clear.setLayoutX(280);
        clear.setLayoutY(75);
        vb.setLayoutX(150);
        vb.setLayoutY(20);
        root.getChildren().add(submit);
        root.getChildren().add(clear);
        root.getChildren().add(vb);
        stage.setScene(new Scene(root, 600, 300));
        stage.show();	
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}