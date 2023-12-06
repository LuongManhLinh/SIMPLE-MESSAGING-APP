module main.simplemessagingapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens main.simplemessagingapp to javafx.fxml;
    exports main.simplemessagingapp;
}