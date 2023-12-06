package main.simplemessagingapp;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneLoaderController.setMainStage(stage);
        stage.setTitle("Messaging App");
        SceneLoaderController.loadScene("main-ui-scene.fxml");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}