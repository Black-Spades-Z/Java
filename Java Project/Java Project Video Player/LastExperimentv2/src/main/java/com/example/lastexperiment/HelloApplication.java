package com.example.lastexperiment;

import Server.MainWindow;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("HeyMedia");
        stage.setMinHeight(440);
        stage.setMinWidth(600);


        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            private boolean once1 = false;

            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2 ){
                    if(once1 == false) {
                        stage.setFullScreen(true);
                        once1 = true;
                    }else if(once1 == true){
                        stage.setFullScreen(false);
                        once1 = false;
                    }
                }

            }
        });
        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        Runnable task = () ->
        {
            new MainWindow();
        };

        Thread thread = new Thread(task);
        thread.start();

        launch();
    }
}