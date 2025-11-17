package com.example.demo.controllers;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import com.example.demo.Utils.OpenViews;

public class AboutController {
    public void hand(javafx.scene.input.MouseEvent event) {
        Label hoveredLabel = (Label) event.getSource();
        hoveredLabel.setCursor(Cursor.HAND);
    }

    public void mouse(javafx.scene.input.MouseEvent event) {
        Label hoveredLabel = (Label) event.getSource();
        hoveredLabel.setCursor(Cursor.DEFAULT);
    }


    public void openMenu(javafx.scene.input.MouseEvent event) throws java.io.IOException {
        OpenViews.openMenu(event);
    }
}
