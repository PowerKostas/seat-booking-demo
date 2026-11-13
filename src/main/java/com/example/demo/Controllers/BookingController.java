package com.example.demo.Controllers;

import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.io.IOException;
import java.util.*;
import javafx.scene.shape.Arc;
import com.example.demo.Utils.OpenViews;

public class BookingController extends BaseController {
    @FXML
    private BorderPane rootBorderPane;

    @FXML
    private VBox seatsVBox;

    @FXML
    private ImageView movieImage;
    public void setMovieImage(String link) {
        Image image = new Image(getClass().getResource(link).toString());
        movieImage.setImage(image);
    }

    @FXML
    private Label movieLabel;
    public void setMovieLabel(String text) {
        movieLabel.setText(text);
    }

    @FXML
    private Arc screen;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;


    // Hashmap που δείχνει σε κάθε ταινία, ποίες θέσεις έχει αγοράσει το κάθε username
    private static Map<Integer, Map<String, Set<Integer>>> moviesUsersSeats = new HashMap<>();
    private Map<String, Set<Integer>> usersSeats = new HashMap<>();
    public void prepareRenderSeats(int movieNum) {
        moviesUsersSeats.putIfAbsent(movieNum, new HashMap<>());
        usersSeats = moviesUsersSeats.get(movieNum);
        renderSeats();
    }


    public void initialize() {
        if (getUsername() == null) { // Αν είναι συνδεδεμένος
            getUsernameLabel().setManaged(false); // Για την πρώτη φορά μόνο
        }

        else {
            setUsernameLabel(getUsername());
            setLogInLabel("Αποσύνδεση");
        }


        button1.setMouseTransparent(true);
        button2.setMouseTransparent(true);
        button3.setMouseTransparent(true);

        screen.radiusXProperty().bind(seatsVBox.widthProperty().divide(2));

        movieImage.fitHeightProperty().bind(rootBorderPane.heightProperty().multiply(0.85));
    }

    // Ετοιμάζει την εμφάνιση κάθε θέσης
    public void renderSeats() {
        int limit = 4;
        int count = 0;
        for (Node rowNode : seatsVBox.getChildren()) {
            count += 1;

            if (count == 1) {
                continue;
            }

            if (count > limit)
                break;

            HBox row = (HBox) rowNode;
            for (Node seatNode : row.getChildren()) {
                Button seat = (Button) seatNode;
                int seatNum = ((seatsVBox.getChildren().indexOf(row) - 1) * 10) + row.getChildren().indexOf(seat) + 1;

                seat.prefHeightProperty().bind(row.heightProperty().multiply(0.225));
                seat.setText(String.format("%02d", seatNum));
                seat.setStyle("-fx-background-color: white; -fx-background-radius: 10;");


                if (!usersSeats.isEmpty()) {
                    for (Map.Entry<String, Set<Integer>> userSeats : usersSeats.entrySet()) { // Για κάθε username, παίρνει τις αγορασμένες θέσεις
                        if (userSeats.getValue().contains(seatNum)) {
                            if (userSeats.getKey().equals(getUsername())) {
                                seat.setStyle("-fx-background-color: green; -fx-background-radius: 10;");
                            }

                            else {
                                seat.setMouseTransparent(true);
                                seat.setStyle("-fx-background-color: red; -fx-background-radius: 10;");
                            }
                        }
                    }
                }
            }
        }
    }


    public void hand(javafx.scene.input.MouseEvent event) {
        Object source = event.getSource();

        if (source instanceof Label label) {
            label.setCursor(Cursor.HAND);
        }

        else if (source instanceof Button button) {
            button.setCursor(Cursor.HAND);
        }
    }

    public void mouse(javafx.scene.input.MouseEvent event) {
        Object source = event.getSource();

        if (source instanceof Label label) {
            label.setCursor(Cursor.DEFAULT);
        }

        else if (source instanceof Button button) {
            button.setCursor(Cursor.DEFAULT);
        }
    }


    public void openHome(javafx.scene.input.MouseEvent event) throws IOException {
        OpenViews.openHome(event);
    }

    public void openLogIn(javafx.scene.input.MouseEvent event) throws IOException {
        Object source = event.getSource();

        // Αν πατηθεί θέση πρώτα συνδέεται και μετά αγοράζει τη θέση
        if (source instanceof Button clickedButton) {
            if (getUsername() == null) { // Αν δεν είναι συνδεδεμένος, ανοίγει το popup σύνδεσης
                openLogIn(this);
                renderSeats();
            }


            usersSeats.putIfAbsent(getUsername(), new HashSet<>());
            Set<Integer> userSeats = usersSeats.get(getUsername());

            if (getUsername() != null) { // Αν συνδέθηκε, η θέση που πατήθηκε αλλάζει χρώμα (αγοράζεται)
                if (clickedButton.getStyle().contains("white")) {
                    userSeats.add(Integer.parseInt(clickedButton.getText()));
                    clickedButton.setStyle("-fx-background-color: green; -fx-background-radius: 10;");
                }

                else {
                    userSeats.remove(Integer.parseInt(clickedButton.getText()));
                    clickedButton.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
                }
            }
        }

        else { // Αν πατηθεί το label, απλά συνδέεται
            openLogIn(this);
            renderSeats();
        }
    }
}
