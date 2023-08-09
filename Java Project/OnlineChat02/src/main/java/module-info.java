module onlinechat.onlinechat02 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens onlinechat.onlinechat02 to javafx.fxml;
    exports onlinechat.onlinechat02;
}