package com.example.lastexperiment;

import javafx.application.Platform;
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
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.scene.control.Button;


import java.io.File;




public class HelloController {
    private MediaPlayer mediaPlayer;
    private String filePath;
    private boolean changeMute = false;
    private boolean videoStopped = false;

    private Duration duration;
    @FXML
    private Slider slider;

    @FXML
    private Slider seekSlider;

    @FXML
    private MediaView mediaView;

    @FXML
    protected Button muteButton;

    @FXML
    protected Button pauseButton;

    @FXML
    protected Button stopButton;

    @FXML
    protected Button openfileButton;

    @FXML
    protected Button fasterButton;

    @FXML
    protected Button slowerButton;

    @FXML
    protected Text timetext;




    //play icon
    Image playit = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\LastExperimentv3\\target\\classes\\icons\\play.png");
    ImageView playIt = new ImageView(playit);

    //pause icon
    Image pauseit = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\LastExperimentv3\\target\\classes\\icons\\pause.png");
    ImageView pauseIt = new ImageView(pauseit);

    //mute icon
    Image muteit = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\LastExperimentv3\\target\\classes\\icons\\mute.png");
    ImageView muteIt = new ImageView(muteit);

    //unmute icon
    Image unmuteit = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\LastExperimentv3\\target\\classes\\icons\\unmute.png");
    ImageView unmuteIt = new ImageView(unmuteit);

    //stop icon
    Image stopit = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\LastExperimentv3\\target\\classes\\icons\\stop.png");
    ImageView stopIt = new ImageView(stopit);

    //Open File icon
    Image openit = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\LastExperimentv3\\target\\classes\\icons\\openfile.png");
    ImageView openIt = new ImageView(openit);

    //faster icon
    Image fasterit = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\LastExperimentv3\\target\\classes\\icons\\faster.png");
    ImageView fasterIt = new ImageView(fasterit);

    //slower icon
    Image slowerit = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\LastExperimentv3\\target\\classes\\icons\\slower.png");
    ImageView slowerIt = new ImageView(slowerit);

    // Formating time to text
    private static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 -
                    durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }

    protected void updateValues() {
        if (timetext !=null && seekSlider != null && duration != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = mediaPlayer.getCurrentTime();
                    timetext.setText(formatTime(currentTime, duration));
                    seekSlider.setDisable(duration.isUnknown());
                    if (!seekSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !seekSlider.isValueChanging()) {
                        seekSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
                    }
                }
            });
        }
    }



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
            slider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    changeMute = false;
                    Image backk = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\LastExperimentv3\\target\\classes\\icons\\unmute.png");
                    ImageView backImage = new ImageView(backk);
                    backImage.setPreserveRatio(true);
                    backImage.setFitWidth(20);
                    muteButton.setGraphic(backImage);
                }
            });


            mediaPlayer.getCurrentTime().toString();
            seekSlider.valueProperty().addListener(new InvalidationListener() {
                public void invalidated(Observable ov) {
                    if (seekSlider.isValueChanging()) {
                        // multiply duration by percentage calculated by slider position
                        mediaPlayer.seek(duration.multiply(seekSlider.getValue() / 100.0));
                    }
                }
            });


            seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                }
            });

            StackPane trackPane = (StackPane) seekSlider.lookup(".track");

            seekSlider.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                    String style = String.format("-fx-background-color: linear-gradient(to right, #8A2BE2 %d%%, #969696 %d%%);",
                            new_val.intValue(), new_val.intValue());
                    trackPane.setStyle(style);
                }
            });

            trackPane.setStyle("-fx-background-color: linear-gradient(to right, #2D819D 0%, #969696 0%);");

            mediaPlayer.setOnReady(new Runnable() {
                public void run() {
                    duration = mediaPlayer.getMedia().getDuration();
                    updateValues();
                }
            });

            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                  @Override
                  public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                      updateValues();
                  }
              });

            muteButton.setDisable(false);
            stopButton.setDisable(false);
            pauseButton.setDisable(false);
            slowerButton.setDisable(false);
            fasterButton.setDisable(false);
            mediaPlayer.play();
        }
    }

    @FXML
    protected void stopVideo(ActionEvent event){
        try {
            mediaPlayer.stop();
        }catch (Exception e){
            System.out.println("Not running");
        }
    }


    @FXML
    protected void pauseVideo(ActionEvent event){
        try{
            if(videoStopped == false){
                videoStopped = true;
                mediaPlayer.pause();
                pauseButton.setGraphic(playIt);
            }else if(videoStopped == true){
                videoStopped = false;
                mediaPlayer.play();
                mediaPlayer.setRate(1);
                pauseButton.setGraphic(pauseIt);
            }
        }catch (Exception e){
            System.out.println("Not running");
        }

    }


    @FXML
    protected void fasterVideo(ActionEvent event){
        try{
            mediaPlayer.play();
            mediaPlayer.setRate(mediaPlayer.getRate()*1.2);
        }catch (Exception e){
            System.out.println("Not running");
        }
    }


    @FXML
    protected void slowerVideo(ActionEvent event){
        try{
            mediaPlayer.play();
            mediaPlayer.setRate(mediaPlayer.getRate()*0.8);
        }catch (Exception e){
            System.out.println("Not running");
        }
    }


    @FXML
    protected void muteSound(ActionEvent event){
        try {
            if (changeMute == false) {
                mediaPlayer.setVolume(0);
                changeMute = true;
                muteButton.setGraphic(muteIt);
            } else if (changeMute == true) {
                mediaPlayer.setVolume(slider.getValue() / 100);
                changeMute = false;
                muteButton.setGraphic(unmuteIt);
            }
        }catch (Exception e){
            System.out.println("Media Player is not running..");
        }
    }

}