package main.simplemessagingapp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUISceneController implements Initializable {
    @FXML private VBox messageSendingContainer;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox messageReceivingContainer;
    @FXML private TextField messageEnteringField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageEnteringField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });
        messageEnteringField.requestFocus();
    }

    public void sendMessage() {
        String message = messageEnteringField.getText();
        if (!message.isEmpty()) {
            Label sendingMessage = createLabel(message, CssFiles.CHAT_BUBBLE_SENDER);
            Label fakeEmptyLabel = createLabel(message, CssFiles.CHAT_BUBBLE_SENDER);
            fakeEmptyLabel.setVisible(false);

            messageSendingContainer.getChildren().add(sendingMessage);
            messageReceivingContainer.getChildren().add(fakeEmptyLabel);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(20), event -> {
                        scrollPane.setVvalue(scrollPane.getVmax());
                    })
            );
            timeline.play();
        }
    }

    private Label createLabel(String message, String cssFileName) {
        Label newMessageLabel = new Label(message);
        newMessageLabel.getStylesheets().add(cssFileName);
        newMessageLabel.setVisible(true);
        Insets insets = new Insets(5, 5, 5, 5);
        newMessageLabel.setPadding(insets);
        messageEnteringField.clear();
        return newMessageLabel;
    }

    public void receiveMessage() {
        String message = messageEnteringField.getText();
        if (!message.isEmpty()) {
            Label sendingMessage = createLabel(message, CssFiles.CHAT_BUBBLE_RECEIVER);
            Label fakeEmptyLabel = createLabel(message, CssFiles.CHAT_BUBBLE_RECEIVER);
            fakeEmptyLabel.setVisible(false);

            messageSendingContainer.getChildren().add(fakeEmptyLabel);
            messageReceivingContainer.getChildren().add(sendingMessage);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(20), event -> {
                        scrollPane.setVvalue(scrollPane.getVmax());
                    })
            );
            timeline.play();
        }
    }

}
