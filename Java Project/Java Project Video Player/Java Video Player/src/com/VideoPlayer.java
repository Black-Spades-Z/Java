package com;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class VideoPlayer extends Application
{
    private Scene mainScene;
    private BorderPane mainBorderPanel;
    private VBox mainBox;
    private HBox Tools, mainTools, otherTools;
    private Button playButton, pauseButton, nextButton, PreviousButton, volumeUpButton, volumeDownButton, muteButton;
    private Label videoName;


    public void start(Stage mainStage) throws Exception
    {
        mainStage.setTitle("Video Player");
        //mainStage.getIcons().add(new Image("/icons/icon.png"));

        mainStage.show();

    }
}
