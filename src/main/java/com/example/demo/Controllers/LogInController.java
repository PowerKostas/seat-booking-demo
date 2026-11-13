package com.example.demo.Controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;

public class LogInController extends BaseController {
    // Επειδή όταν ανοίγει το login popup, δεν επικοινωνώ εδώ με τον αρχικό controller, πρέπει να τον στείλω από πριν
    private BaseController parentController;
    public void setParentController(BaseController parentController) { // Δέχεται όλων των ειδών controller
        this.parentController = parentController;
    }


    @FXML
    private TextField usernameTextField;

    @FXML private Label errorLabel;


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


    // Κλείνει το popup
    public void close(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void logIn(javafx.scene.input.MouseEvent event) {
        if (usernameTextField.getText().isBlank()) { // Αν το username text field είναι κενό
            errorLabel.setText("Παρακαλώ συμπληρώστε το πεδίο!");
            return;
        }

        setUsername(usernameTextField.getText()); // Θέτει το username, για να υπάρχει για κάθε παράθυρο


        parentController.getUsernameLabel().setVisible(true);
        parentController.getUsernameLabel().setManaged(true);
        parentController.getUsernameLabel().setText(usernameTextField.getText());
        parentController.getLogInLabel().setText("Αποσύνδεση");


        Stage logInStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        logInStage.close();
    }
}
