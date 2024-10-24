module com.paint100 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires jdk.httpserver;
    requires log4j.api;

    opens com.paint100 to javafx.fxml;
    exports com.paint100;
}