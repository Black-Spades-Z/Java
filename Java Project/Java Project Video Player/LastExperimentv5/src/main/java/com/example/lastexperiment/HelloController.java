package com.example.lastexperiment;

import Server.MainWindow;
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


import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;


import Server.Client;

import static Server.Client.clientSocketList;
import static com.example.lastexperiment.HelloApplication.clientCommandList;

public class HelloController {
    private MediaPlayer mediaPlayer;
    private String filePath;
    private boolean changeMute = false;
    private boolean videoStopped = false;


    private PrintWriter outMessage;



    public void sendStopMessage(String command) {
        outMessage.println(command);
        outMessage.flush();
    }


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
    String playPath = FileSystems.getDefault().getPath("src\\main\\resources\\icons\\play.png").toAbsolutePath().toString();
    Image playit = new Image(playPath);
    ImageView playIt = new ImageView(playit);

    //pause icon
    String pausePath = FileSystems.getDefault().getPath("src\\main\\resources\\icons\\pause.png").toAbsolutePath().toString();
    Image pauseit = new Image(pausePath);
    ImageView pauseIt = new ImageView(pauseit);

    //mute icon
    String mutePath = FileSystems.getDefault().getPath("src\\main\\resources\\icons\\mute.png").toAbsolutePath().toString();
    Image muteit = new Image(mutePath);
    ImageView muteIt = new ImageView(muteit);

    //unmute icon
    String unmutePath = FileSystems.getDefault().getPath("src\\main\\resources\\icons\\unmute.png").toAbsolutePath().toString();
    Image unmuteit = new Image(unmutePath);
    ImageView unmuteIt = new ImageView(unmuteit);


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
                }
            });
        }
    }




    @FXML
    protected void handleButtonAction(ActionEvent event){

        always(event);
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
                    muteButton.setGraphic(unmuteIt);
                }
            });


            mediaPlayer.setOnReady(new Runnable() {
                public void run() {
                    duration = mediaPlayer.getMedia().getDuration();
                    seekSlider.setMax((int) Math.floor(duration.toSeconds()));
                    updateValues();
                }
            });


            seekSlider.valueProperty().addListener(new InvalidationListener() {
                public void invalidated(Observable ov) {
                    if (seekSlider.isValueChanging()) {
                        // multiply duration by percentage calculated by slider position
                        mediaPlayer.seek(duration.multiply(seekSlider.getValue()/100.0));
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
                            new_val.intValue()/20, new_val.intValue()/3);
                    trackPane.setStyle(style);
                }
            });


            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                  @Override
                  public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                      updateValues();
                  }
              });



            slider.setDisable(false);
            seekSlider.setDisable(false);
            muteButton.setDisable(false);
            stopButton.setDisable(false);
            pauseButton.setDisable(false);
            slowerButton.setDisable(false);
            fasterButton.setDisable(false);
            mediaPlayer.play();

        }
    }

    @FXML
    synchronized protected void stopVideo(ActionEvent event){
        try {

            {
                try {
                    outMessage = new PrintWriter(clientSocketList.get(0).getOutputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            mediaPlayer.stop();
            seekSlider.setValue(0);
            seekSlider.setDisable(true);
            pauseButton.setGraphic(playIt);
            videoStopped = true;
            sendStopMessage("##session##stop##");
        }catch (Exception e){
            System.out.println("Not running");
        }
    }

    @FXML
    synchronized protected void stopVideoRecieved(ActionEvent event){
        try {

            {
                try {
                    outMessage = new PrintWriter(clientSocketList.get(0).getOutputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            mediaPlayer.stop();
            seekSlider.setValue(0);
            seekSlider.setDisable(true);
            pauseButton.setGraphic(playIt);
            videoStopped = true;
        }catch (Exception e){
            System.out.println("Not running");
        }
    }



    @FXML
    synchronized protected void pauseVideo(ActionEvent event){
        try{
            if(videoStopped == false){
                videoStopped = true;
                mediaPlayer.pause();
                pauseButton.setGraphic(playIt);
                sendStopMessage("##session##play##");
            }else if(videoStopped == true){
                videoStopped = false;
                seekSlider.setDisable(false);
                mediaPlayer.play();
                mediaPlayer.setRate(1);
                pauseButton.setGraphic(pauseIt);
                sendStopMessage("##session##play##");
            }
        }catch (Exception e){
            System.out.println("Not running");
        }

    }

    @FXML
    synchronized protected void pauseVideoRecieved(ActionEvent event){
        try{
            if(videoStopped == false){
                videoStopped = true;
                mediaPlayer.pause();
                pauseButton.setGraphic(playIt);
            }else if(videoStopped == true){
                videoStopped = false;
                seekSlider.setDisable(false);
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

    private void always(ActionEvent event) {
        Runnable checkerCommands = () ->
        {
            do {

                if (clientCommandList.get(0).equals("##session##stop##")) {
                    stopVideoRecieved(event);
                    clientCommandList.clear();
                    clientCommandList.add(0, "");
                } else if (clientCommandList.get(0).equals("##session##play##")) {
                    pauseVideoRecieved(event);
                    clientCommandList.clear();
                    clientCommandList.add(0, "");
                }
                System.out.println("Working");
                try {
                    Thread.sleep(100);        //Приостановка потока на 1 сек.
                } catch (InterruptedException e) {
                }
            }
            while (true);
        };
        new Thread(checkerCommands).start();

    }

}