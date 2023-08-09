package com.videoplayer;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;



public class VideoPlayer extends Application
{
    private Scene mainScene, fullScreenScene;
    private Pane mainPane;
    private GridPane Tools, sliders;
    private BorderPane mainBorderPanel;
    private VBox mainBox;
    private HBox mainTools, volumeTools, timeTools;
    private Button playButton, stopButton, nextButton, previousButton, volumeUpButton, volumeDownButton, muteButton, menuButton;
    private Label videoName;
    private Label timeField;
    private Text timeValue;
    private Media media;
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private Group root;
    private double volume = 0.5;
    private Rectangle rectangle;
    private Slider timeSlider;
    private Separator separator;
    private Tooltip tooltip;
    private boolean play = true;
    private boolean open = false;
    private boolean fullScreen = true;
    private boolean visible = true;


    public VideoPlayer() {
    }


    public void start(Stage mainStage) throws Exception
    {

        // Main Stage parameters

        mainStage.setTitle("Video Player");
        mainStage.getIcons().add(new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\VideoPlayer\\icons\\icon.png"));
        mainStage.setMinHeight(600);
        mainStage.setMinWidth(1200);
        mainStage.setResizable(true);


        mainBorderPanel = new BorderPane();
        mainBorderPanel.setStyle("-fx-background-color: #9370DB;");




        mainPane = new Pane();
        mainPane.setPrefSize(1200, 600);
        mainPane.setStyle("-fx-background-color: #4B0082;");

        mainBorderPanel.setCenter(mainPane);




        // Play button
        playButton = new Button();
        Image playButtonImage = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\VideoPlayer\\icons\\play-button.png");
        Image pauseButtonImage = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\VideoPlayer\\icons\\pause-button.png");
        ImageView playButtonImageView = new ImageView(playButtonImage);
        ImageView pauseButtonImageView = new ImageView(pauseButtonImage);
        playButtonImageView.setFitHeight(10);
        playButtonImageView.setPreserveRatio(true);
        pauseButtonImageView.setFitHeight(10);
        pauseButtonImageView.setPreserveRatio(true);
        playButton.setGraphic(playButtonImageView);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(play)
                {

                    playButton.setGraphic(playButtonImageView);
                    mediaPlayer.pause();
                    play = false;
                }
                else {
                    playButton.setGraphic(pauseButtonImageView);
                    mediaPlayer.play();
                    play = true;
                }

            }
        });


        // Manu button
        menuButton = new Button();
        Image menuButtonImage = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\VideoPlayer\\icons\\menu-button.png");
        ImageView menuButtonImageView = new ImageView(menuButtonImage);
        menuButtonImageView.setFitHeight(10);
        menuButtonImageView.setPreserveRatio(true);
        menuButton.setGraphic(menuButtonImageView);
        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select file (.mp4)", "*.mp4");
                fileChooser.getExtensionFilters().add(filter);

                File file = fileChooser.showOpenDialog(null);
                String filePath = file.toURI().toString();

                if (filePath != null) {
                    if(open)
                    {
                        mediaPlayer.stop();
                    }

                    open = true;
                    media = new Media(filePath);
                    mediaPlayer = new MediaPlayer(media);
                    mediaView = new MediaView(mediaPlayer);

                    mediaView.fitHeightProperty().bind(mainPane.heightProperty());
                    mediaView.fitWidthProperty().bind(mainPane.widthProperty());
                    mediaView.setPreserveRatio(false);

                    mainPane.getChildren().add(mediaView);
                    mainBorderPanel.setCenter(mainPane);


                    filePath = null;

                    playButton.setGraphic(pauseButtonImageView);
                    play = true;

                    // Enable section

                    timeSlider.setDisable(false);
                    playButton.setDisable(false);
                    stopButton.setDisable(false);
                    nextButton.setDisable(false);
                    previousButton.setDisable(false);
                    muteButton.setDisable(false);

                    // Time slider changes


                    timeSlider.setValueChanging(true);





                    final double[] maxSeconds = new double[1];
                    final int[] maxHours = new int[1];
                    final int[] maxMinutes = new int[1];

                    final double[] movieDuration = {0};

                    maxMinutes[0] = 0;
                    maxSeconds[0] = 0;
                    maxHours[0] = 0;
                    final long[] seconds = {0};

                    final String[] sec = new String[1];
                    final String[] min = new String[1];
                    final String[] hour = new String[1];



                    mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                        @Override
                        public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {

                            movieDuration[0] = mediaPlayer.getTotalDuration().toMinutes();
                            timeSlider.setMaxWidth(movieDuration[0]);

                            timeSlider.setValue(t1.toMinutes() );
                            maxSeconds[0] = mediaPlayer.getTotalDuration().toSeconds();
                            maxMinutes[0] = (int) Math.round(maxSeconds[0] / 60);
                            maxHours[0] = Math.round(maxMinutes[0] / 60);
                            maxSeconds[0] = maxSeconds[0] - maxMinutes[0] * 60;
                            seconds[0] = (long) maxSeconds[0];
                            maxMinutes[0] = maxMinutes[0] - maxHours[0] * 60;



                            sec[0] = String.valueOf(t1.toSeconds()).split("\\.")[0];
                            min[0] = String.valueOf(t1.toMinutes()).split("\\.")[0];
                            hour[0] = String.valueOf(t1.toHours()).split("\\.")[0];

                            if(maxMinutes[0] < 10)
                            {
                                if (Integer.parseInt(min[0]) < 0)
                                {
                                    timeField.setText("0" + hour[0] +":0" +min[0] +":"+sec[0]+" | 0" + maxHours[0] + ":0" + maxMinutes[0] + ":" + seconds[0]);
                                }else {
                                    timeField.setText("0" + hour[0] +":" +min[0] +":"+sec[0]+" | 0" + maxHours[0] + ":0" + maxMinutes[0] + ":" + seconds[0]);
                                }

                            }
                            else
                            {

                                if (Integer.parseInt(min[0])< 0)
                                {
                                    timeField.setText("0" + hour[0] +":0" +min[0] +":"+sec[0]+" | 0" + maxHours[0] + ":" + maxMinutes[0] + ":" + seconds[0]);
                                }else {
                                    timeField.setText("0" + hour[0] +":" +min[0] +":"+sec[0]+" | 0" + maxHours[0] + ":" + maxMinutes[0] + ":" + seconds[0]);
                                }
                            }


                        }
                    });

                    StackPane trackPane = (StackPane) timeSlider.lookup(".track");

                    timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                        public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                            String style = String.format("-fx-background-color: linear-gradient(to right, #8A2BE2 %d%%, #969696 %d%%);",
                                    new_val.intValue(), new_val.intValue());
                            trackPane.setStyle(style);
                        }
                    });

                    trackPane.setStyle("-fx-background-color: linear-gradient(to right, #2D819D 0%, #969696 0%);");




                    //timeSlider.setValue(mediaPlayer.getCurrentTime().toMinutes() * 2);
                    timeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            mediaPlayer.seek(Duration.minutes(timeSlider.getValue()));
                        }
                    });





                    mediaPlayer.play();

                }
                }
            });



        // Slider Time
        timeSlider = new Slider();
        timeField = new Label("");




        // Stop button
        stopButton = new Button();
        Image stopButtonImage = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\VideoPlayer\\icons\\stop-button.png");
        ImageView stopButtonImageView = new ImageView(stopButtonImage);
        stopButtonImageView.setFitHeight(10);
        stopButtonImageView.setPreserveRatio(true);
        stopButton.setGraphic(stopButtonImageView);
        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediaPlayer.stop();
                timeSlider.setValue(0);
                play = false;
            }
        });


        // Previous button
        previousButton = new Button();
        Image previousButtonImage = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\VideoPlayer\\icons\\previous-button.png");
        ImageView previousButtonImageView = new ImageView(previousButtonImage);
        previousButtonImageView.setFitHeight(10);
        previousButtonImageView.setPreserveRatio(true);
        previousButton.setGraphic(previousButtonImageView);
        previousButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediaPlayer.seek(Duration.seconds(mediaPlayer.getCurrentTime().toSeconds() - 10));
            }
        });


        // Next button
        nextButton = new Button();
        Image nextButtonImage = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\VideoPlayer\\icons\\next-button.png");
        ImageView nextButtonImageView = new ImageView(nextButtonImage);
        nextButtonImageView.setFitHeight(10);
        nextButtonImageView.setPreserveRatio(true);
        nextButton.setGraphic(nextButtonImageView);
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               mediaPlayer.seek(Duration.seconds(mediaPlayer.getCurrentTime().toSeconds() + 10));
            }
        });


        mainTools = new HBox(playButton, stopButton,previousButton, nextButton);
        mainTools.setSpacing(10);
        mainTools.setPrefWidth(70);

        // Mute button
        muteButton = new Button();
        Image muteButtonImage = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\VideoPlayer\\icons\\volume-mute.png");
        ImageView muteButtonImageView = new ImageView(muteButtonImage);
        muteButtonImageView.setFitHeight(10);
        muteButtonImageView.setPreserveRatio(true);
        muteButton.setGraphic(muteButtonImageView);
        muteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediaPlayer.setMute(true);
            }
        });

        // Volume Down button
        volumeDownButton = new Button();
        Image volumeDownButtonImage = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\VideoPlayer\\icons\\volume-Down.png");
        ImageView volumeDownButtonImageView = new ImageView(volumeDownButtonImage);
        volumeDownButtonImageView.setFitHeight(10);
        volumeDownButtonImageView.setPreserveRatio(true);
        volumeDownButton.setGraphic(volumeDownButtonImageView);
        volumeDownButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediaPlayer.setMute(false);
                if (volume != 0.0){
                    volume = volume - 0.1;
                }

                mediaPlayer.setVolume(volume);
            }
        });

        // Volume Up button
        volumeUpButton = new Button();
        Image volumeUpButtonImage = new Image("D:\\Programming\\Java\\Projects\\Java Project\\Java Project Video Player\\VideoPlayer\\icons\\volume-up.png");
        ImageView volumeUpButtonImageView = new ImageView(volumeUpButtonImage);
        volumeUpButtonImageView.setFitHeight(10);
        volumeUpButtonImageView.setPreserveRatio(true);
        volumeUpButton.setGraphic(volumeUpButtonImageView);
        volumeUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediaPlayer.setMute(false);
                if (volume != 1.0)
                {
                    volume = volume + 0.1;
                }
                mediaPlayer.setVolume(volume);
            }
        });

        volumeTools = new HBox(muteButton, volumeDownButton, volumeUpButton);
        volumeTools.setSpacing(10);
        volumeTools.setPrefWidth(50);

        // Sliders Grid Pane
        
        sliders  = new GridPane();
        sliders.setStyle("-fx-background-color: #00FF00;");
        sliders.setPrefSize(1200, 1);
        
        // Columns in Grid Pane

        ColumnConstraints timeSliderCol = new ColumnConstraints();
        timeSliderCol.setPercentWidth(93);
        sliders.getColumnConstraints().add(timeSliderCol);

        ColumnConstraints volumeIconCol = new ColumnConstraints();
        volumeIconCol.setPercentWidth(2);
        sliders.getColumnConstraints().add(volumeIconCol);

        ColumnConstraints volumeIndicatorCol = new ColumnConstraints();
        volumeIndicatorCol.setPercentWidth(5);
        sliders.getColumnConstraints().add(volumeIndicatorCol);

        RowConstraints mainRow = new RowConstraints();
        mainRow.setMaxHeight(1);
        sliders.getRowConstraints().add(mainRow);
        sliders.setMaxHeight(1);





        sliders.add(timeSlider, 0, 0);
        sliders.setMargin(timeSlider, new Insets(5, 0, 0, 5));


        // Disable section

        timeSlider.setDisable(true);
        playButton.setDisable(true);
        stopButton.setDisable(true);
        nextButton.setDisable(true);
        previousButton.setDisable(true);
        muteButton.setDisable(true);



        Tools = new GridPane();
        Tools.setStyle("-fx-background-color: #778899;");
        Tools.setPrefSize(1200, 45);


        ColumnConstraints mainToolsCol = new ColumnConstraints();
        mainToolsCol.setPercentWidth(15);
        Tools.getColumnConstraints().add(mainToolsCol);

        ColumnConstraints indicatorToolsCol = new ColumnConstraints();
        indicatorToolsCol.setPercentWidth(35);
        Tools.getColumnConstraints().add(indicatorToolsCol);

        ColumnConstraints volumeToolsCol = new ColumnConstraints();
        volumeToolsCol.setPercentWidth(45);
        Tools.getColumnConstraints().add(volumeToolsCol);

        ColumnConstraints menuToolsCol = new ColumnConstraints();
        menuToolsCol.setPercentWidth(5);
        Tools.getColumnConstraints().add(menuToolsCol);


        Tools.setGridLinesVisible(true);



        Tools.add(mainTools,0,2);
        Tools.setMargin(mainTools, new Insets(10,0,0,5));
        Tools.add(timeField,1,2);
        Tools.setMargin(timeField, new Insets(12,0,0,5));
        Tools.add(menuButton,3,2);
        Tools.setMargin(menuButton, new Insets(10,0,0,5));
        Tools.setAlignment(Pos.CENTER);
//        Tools.add(volumeTools, 1,2);
//        Tools.setMargin(volumeTools, new Insets(10,0,0,20));



        mainBox = new VBox(sliders, Tools);
        mainBorderPanel.setBottom(mainBox);
        mainScene = new Scene(mainBorderPanel);

        // Set events for scene

        mainScene.setOnMouseClicked(mouseEvent ->
        {

            if (mouseEvent.getClickCount() == 1 && mouseEvent.getY() >= 800)
            {
                if(fullScreen)
                {
                    if(visible)
                    {
//                        mainBorderPanel.getBottom().setVisible(false);
//                        mainBorderPanel.getBottom().setDisable(true);

                        mainBorderPanel.setBottom(null);

                        visible = false;
                    }
                    else {
//                        mainBorderPanel.getBottom().setVisible(true);
//                        mainBorderPanel.getBottom().setDisable(false);
                        mainBorderPanel.setBottom(mainBox);
                        visible = true;
                    }
                }

            }
            if (mouseEvent.getClickCount() == 1 && mouseEvent.getY() <= 800)
            {
                if(open) {
                    if (play) {
                        mediaPlayer.pause();
                        play = false;
                    } else {
                        mediaPlayer.play();
                        play = true;
                    }
                }

            }
            if(mouseEvent.getClickCount() == 2 && mouseEvent.getY() <= 800)
            {
                if (!fullScreen)
                    {

                    mainStage.setFullScreen(true);
                    mainBorderPanel.setBottom(mainBox);
                    fullScreen = true;
                    }
                else {
                    mainStage.setFullScreen(false);
                    mainBorderPanel.setBottom(mainBox);
                    mainBorderPanel.getBottom().setVisible(true);
                    mainBorderPanel.getBottom().setDisable(false);
                    fullScreen = false;
                }
            }


        });


        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SPACE)
                {
                    if(play)
                    {
                        playButton.setGraphic(playButtonImageView);
                        mediaPlayer.pause();
                        play = false;
                    }
                    else{
                        playButton.setGraphic(pauseButtonImageView);
                        mediaPlayer.play();
                        play = true;
                    }
                }
            }
        });
        mainScene.addEventHandler(KeyEvent.KEY_PRESSED, ev->
        {
            if (ev.getCode() == KeyCode.ENTER)
            {
                if(play)
                {
                    playButton.setGraphic(playButtonImageView);
                    mediaPlayer.pause();
                    play = false;
                }
                else{
                    playButton.setGraphic(pauseButtonImageView);
                    mediaPlayer.play();
                    play = true;
                }

            }
            {

            }
        });
        mainStage.setScene(mainScene);

        mainStage.show();
    }

    public static void main(String[] args) {
        launch();


    }
}
