module com.example.lastexperiment {
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.lastexperiment to jav1afx.fxml;
    exports com.example.lastexperiment;
}