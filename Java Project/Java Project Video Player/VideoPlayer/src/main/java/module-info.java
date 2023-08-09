module com.videoplayer {
    requires javafx.controls;

    requires javafx.media;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.videoplayer to javafx.fxml;
    exports com.videoplayer;
}