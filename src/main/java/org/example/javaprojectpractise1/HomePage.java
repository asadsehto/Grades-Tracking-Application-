package org.example.javaprojectpractise1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomePage   {
    // load landing page
    @FXML
    public void loadLandingPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LandingPage.fxml")));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("CGPA CALCULATOR");
        stage.show();
    }

    @FXML
    private void openHelpBot(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HelpBot.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Help Bot");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error opening Help Bot");
            alert.setContentText("An error occurred while trying to open Help Bot.");
            alert.showAndWait();
        }
    }

    @FXML
    private void openCurses(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Courses.fxml")));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Courses");
        stage.show();
    }

}
