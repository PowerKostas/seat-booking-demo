package com.example.demo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.io.IOException;
import com.example.demo.Utils.OpenViews;

public class HomeController extends BaseController {
    @FXML
    private ScrollPane moviesScrollPane;


    public void initialize() {
        if (getUsername() == null) { // Αν είναι συνδεδεμένος
            getUsernameLabel().setManaged(false); // Για την πρώτη φορά μόνο
        }

        else {
            setUsernameLabel(getUsername());
            setLogInLabel("Αποσύνδεση");
        }


        if (moviesScrollPane.getContent() instanceof HBox moviesHBox) {
            for (Node node : moviesHBox.getChildren()) {
                if (node instanceof VBox moviesVBox) {
                    for (Node child : moviesVBox.getChildren()) {
                        if (child instanceof ImageView imageView) {
                            imageView.fitHeightProperty().bind(moviesScrollPane.heightProperty().multiply(0.8));
                        }
                    }
                }
            }
        }
    }


    public void moviesEnter(javafx.scene.input.MouseEvent event) {
        VBox hoveredVBox = (VBox) event.getSource();
        hoveredVBox.setStyle("-fx-border-color: orange; -fx-border-width: 2;");
    }

    public void moviesExit(javafx.scene.input.MouseEvent event) {
        VBox hoveredVBox = (VBox) event.getSource();
        hoveredVBox.setStyle("-fx-border-color: transparent; -fx-border-width: 2;");
    }

    public void buttonEnter(javafx.scene.input.MouseEvent event) {
        Button hoveredButton = (Button) event.getSource();
        hoveredButton.setCursor(Cursor.HAND);
        hoveredButton.setStyle("-fx-background-color: darkorange;");
    }

    public void buttonExit(javafx.scene.input.MouseEvent event) {
        Button hoveredButton = (Button) event.getSource();
        hoveredButton.setCursor(Cursor.DEFAULT);
        hoveredButton.setStyle("-fx-background-color: orange;");
    }

    public void hand(javafx.scene.input.MouseEvent event) {
        Label hoveredLabel = (Label) event.getSource();
        hoveredLabel.setCursor(Cursor.HAND);
    }

    public void mouse(javafx.scene.input.MouseEvent event) {
        Label hoveredLabel = (Label) event.getSource();
        hoveredLabel.setCursor(Cursor.DEFAULT);
    }


    public void openMenu(javafx.scene.input.MouseEvent event) throws IOException {
        OpenViews.openMenu(event);
    }

    public void openLogIn() throws IOException {
        openLogIn(this);
    }

    public void openBooking(javafx.scene.input.MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/Views/booking-view.fxml"));
        Parent root = loader.load();
        BookingController bookingController = loader.getController();
        Button clickedButton = (Button) event.getSource();

        if (clickedButton.getId().equals("button1")) {
            bookingController.setMovieImage("/com/example/demo/Images/adventureCosmos.jpg");
            bookingController.setMovieLabel("Adventure Cosmos");
            bookingController.prepareRenderSeats(1);
        }

        else if (clickedButton.getId().equals("button2")) {
            bookingController.setMovieImage("/com/example/demo/Images/danceFloor.jpg");
            bookingController.setMovieLabel("Dance Floor");
            bookingController.prepareRenderSeats(2);
        }

        else if (clickedButton.getId().equals("button3")) {
            bookingController.setMovieImage("/com/example/demo/Images/desertMan.jpg");
            bookingController.setMovieLabel("Desert Man");
            bookingController.prepareRenderSeats(3);
        }

        else if (clickedButton.getId().equals("button4")) {
            bookingController.setMovieImage("/com/example/demo/Images/liveMusic.jpg");
            bookingController.setMovieLabel("Live Music");
            bookingController.prepareRenderSeats(4);
        }

        else if (clickedButton.getId().equals("button5")) {
            bookingController.setMovieImage("/com/example/demo/Images/threeFriends.jpg");
            bookingController.setMovieLabel("Three Friends");
            bookingController.prepareRenderSeats(5);
        }

        else if (clickedButton.getId().equals("button6")) {
            bookingController.setMovieImage("/com/example/demo/Images/filmNoir.jpg");
            bookingController.setMovieLabel("Film Noir");
            bookingController.prepareRenderSeats(6);
        }


        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(root);
    }
}
