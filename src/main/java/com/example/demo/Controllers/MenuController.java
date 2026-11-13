package com.example.demo.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.Event;
import java.io.IOException;
import com.example.demo.Utils.OpenViews;

public class MenuController {
    @FXML
    private VBox rootVBox;


    public void initialize() {
        for (Node node : rootVBox.getChildren()) {
            StackPane menuStackPane = (StackPane) node;
            if (menuStackPane.getChildren().getFirst() instanceof Button menuButton) {
                menuButton.prefWidthProperty().bind(rootVBox.widthProperty().multiply(0.5));
            }
        }
    }


    public void menuEnter(javafx.scene.input.MouseEvent event) {
        Button hoveredButton = (Button) event.getSource();
        hoveredButton.setCursor(Cursor.HAND);
        hoveredButton.setStyle("-fx-background-color: #191919; -fx-border-color: #767676; -fx-border-width: 2; -fx-background-radius: 10; -fx-border-radius: 10;");
    }

    public void menuExit(javafx.scene.input.MouseEvent event) {
        Button hoveredButton = (Button) event.getSource();
        hoveredButton.setCursor(Cursor.DEFAULT);
        hoveredButton.setStyle("-fx-background-color: #2D2D2D; -fx-border-color: #767676; -fx-border-width: 2; -fx-background-radius: 10; -fx-border-radius: 10;");
    }


    public void openHome(javafx.scene.input.MouseEvent event) throws java.io.IOException {
        OpenViews.openHome(event);
    }

    public void openInfo(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(OpenViews.class.getResource("/com/example/demo/Views/info-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(root);
    }

    public void openAbout(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(OpenViews.class.getResource("/com/example/demo/Views/about-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(root);
    }
}
