package com.example.lastexperiment;

import Server.MainWindow;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;

public class HelloApplication extends Application {


    public static final ArrayList<String> clientCommandList = new ArrayList<>(1);


    @Override
    public void start(Stage stage) throws IOException {

        clientCommandList.add("");

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("HeyMedia");
        String logoPath = FileSystems.getDefault().getPath("src\\main\\resources\\icons\\logo.png").toAbsolutePath().toString();
        stage.getIcons().add(new Image(logoPath));
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