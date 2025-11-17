package com.example.demo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

// Γενικό controller για να δέχεται μία μέθοδος μόνο αυτό και όχι κάθε ενα inheritor
public abstract class BaseController {
    // Τα παρακάτω θέτονται για κάθε παράθυρο που κάνει extend
    @FXML
    private Label usernameLabel;
    public void setUsernameLabel(String text) {
        usernameLabel.setText(text);
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    @FXML
    private Label logInLabel;
    public void setLogInLabel(String text) {
        logInLabel.setText(text);
    }

    public Label getLogInLabel() {
        return logInLabel;
    }


    private static String username;
    public static void setUsername(String text) {
        username = text;
    }

    public static String getUsername() {
        return username;
    }


    public void openLogIn(BaseController controller) throws IOException {
        if (controller.getLogInLabel().getText().equals("Σύνδεση")) { // Άν δεν είναι συνδεδεμένος
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/Views/logIn-view.fxml"));
            Parent root = loader.load();
            LogInController logInController = loader.getController();
            logInController.setParentController(controller); // Δίνει τι είδους controller είναι


            // Ανοίγει popup
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.showAndWait();
        }

        else { // Αν αποσυνδέεται
            username = null;
            controller.getUsernameLabel().setVisible(false);
            controller.getUsernameLabel().setManaged(false);
            controller.getLogInLabel().setText("Σύνδεση");
        }
    }
}
