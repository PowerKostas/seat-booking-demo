package com.example.demo.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.Event;

// Μέθοδοι που χρησιμοποιούνται συχνά
public class OpenViews {
    public static void openHome(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(OpenViews.class.getResource("/com/example/demo/Views/home-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(root);
    }

    public static void openMenu(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(OpenViews.class.getResource("/com/example/demo/Views/menu-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(root);
    }
}
