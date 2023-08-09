package com.videoplayer;
//
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
//import java.io.File;
//
//public class Test extends Application
//{
//
//    public Test()
//    {
//    }
//
//    public void start(Stage stage) throws Exception
//    {
//        FileChooser fileChooser = new FileChooser();
//        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select file (.mp4)", "*.mp4");
//        fileChooser.getExtensionFilters().add(filter);
//
//        File file = fileChooser.showOpenDialog(null);
//        String filePath = file.toURI().toString();
//
//        Media pick = new Media(filePath); // replace this with your own audio file
//        MediaPlayer player = new MediaPlayer(pick);
//
//        // Add a mediaView, to display the media. Its necessary !
//        // This mediaView is added to a Pane
//        MediaView mediaView = new MediaView(player);
//
//        // Add to scene
//        Group root = new Group(mediaView);
//        Scene scene = new Scene(root, 500, 200);
//
//        // Show the stage
//        stage.setTitle("Media Player");
//        stage.setScene(scene);
//        stage.show();
//
//        // Play the media once the stage is shown
//        player.play();
//    }
//
//    public static void main(String[] args)
//    {
//   launch();
//    }
//
//
//}



import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application {
    // An instance variable to store the Text node reference
    private Text helpText = new Text();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        TextField fName = new TextField();
        TextField lName = new TextField();
        TextField salary = new TextField();

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> Platform.exit());

        fName.getProperties().put("microHelpText", "Enter the first name");
        lName.getProperties().put("microHelpText", "Enter the last name");
        salary.getProperties().put("microHelpText",
                "Enter a salary greater than $2000.00.");

        // The help text node is unmanaged
        helpText.setManaged(false);
        helpText.setTextOrigin(VPos.TOP);
        helpText.setFill(Color.RED);
        helpText.setFont(Font.font(null, 9));
        helpText.setMouseTransparent(true);

        // Add all nodes to a GridPane
        GridPane root = new GridPane();

        root.add(new Label("First Name:"), 1, 1);
        root.add(fName, 2, 1);
        root.add(new Label("Last Name:"), 1, 2);
        root.add(lName, 2, 2);

        root.add(new Label("Salary:"), 1, 3);
        root.add(salary, 2, 3);
        root.add(closeBtn, 3, 3);
        root.add(helpText, 4, 3);

        Scene scene = new Scene(root, 300, 100);

        // Add a change listener to the scene, so we know when
        // the focus owner changes and display the micro help
        scene.focusOwnerProperty().addListener(
                (ObservableValue<? extends Node> value, Node oldNode, Node newNode)
                        -> focusChanged(value, oldNode, newNode));
        stage.setScene(scene);
        stage.setTitle("Showing Micro Help");
        stage.show();
    }

    public void focusChanged(ObservableValue<? extends Node> value,
                             Node oldNode, Node newNode) {
        // Focus has changed to a new node
        String microHelpText = (String)newNode.getProperties().get("microHelpText");

        if (microHelpText != null && microHelpText.trim().length() > 0)  {
            helpText.setText(microHelpText);
            helpText.setVisible(true);

            // Position the help text node
            double x = newNode.getLayoutX() +
                    newNode.getLayoutBounds().getMinX() -
                    helpText.getLayoutBounds().getMinX();
            double y = newNode.getLayoutY() +
                    newNode.getLayoutBounds().getMinY() +
                    newNode.getLayoutBounds().getHeight() -
                    helpText.getLayoutBounds().getMinX();

            helpText.setLayoutX(x);
            helpText.setLayoutY(y);
            helpText.setWrappingWidth(newNode.getLayoutBounds().getWidth());
        }
        else {
            helpText.setVisible(false);
        }
    }
}