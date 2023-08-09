package com.example.lastexperiment;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


import java.io.File;


public class HelloController {


    private MediaPlayer mediaPlayer;
    private String filePath;



    @FXML
    private Slider slider;

    @FXML
    private Slider seekSlider;

    @FXML
    private MediaView mediaView;

    @FXML
    private HBox sliders;

    @FXML
    protected void handleButtonAction(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select mp4 file", "*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        try {
        filePath = file.toURI().toString();
        }
        catch(NullPointerException e) {
            System.out.println("Null Value");
        }

        if(filePath != null){
         Media media = new Media(filePath);
         mediaPlayer = new MediaPlayer(media);
         mediaView.setMediaPlayer(mediaPlayer);

            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty height = mediaView.fitHeightProperty();

            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

            slider.setValue(mediaPlayer.getVolume() * 100);
            slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(slider.getValue()/100);
                }
            });

            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                    seekSlider.setValue(t1.toSeconds());
                }
            });

            seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                }
            });

            Image muteButtonImage = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\VideoPlayer\\icons\\volume-mute.png");
            ImageView muteButtonImageView = new ImageView(muteButtonImage);
            muteButtonImageView.setFitHeight(10);
            muteButtonImageView.setPreserveRatio(true);


            mediaPlayer.play();
        }
    }

    @FXML
    protected void pauseVideo(ActionEvent event){
        mediaPlayer.pause();
    }

    @FXML
    protected void playVideo(ActionEvent event){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }

    @FXML
    protected void stopVideo(ActionEvent event){
        mediaPlayer.stop();
    }

    @FXML
    protected void fastVideo(ActionEvent event){
        mediaPlayer.setRate(1.5);
    }

    @FXML
    protected void fasterVideo(ActionEvent event){
        mediaPlayer.setRate(2);
    }

    @FXML
    protected void slowVideo(ActionEvent event){
        mediaPlayer.setRate(0.75);
    }

    @FXML
    protected void slowerVideo(ActionEvent event){
        mediaPlayer.setRate(0.5);
    }

    @FXML
    protected void exitVideo(ActionEvent event){
        System.exit(0);
    }

    private boolean changeMute = false;
    @FXML
    protected void muteSound(ActionEvent event){
        if(changeMute == false) {
            mediaPlayer.setVolume(0);
            
            changeMute = true;

        }else if(changeMute == true){
            mediaPlayer.setVolume(slider.getValue()/100);
            changeMute = false;
        }
    }
}

