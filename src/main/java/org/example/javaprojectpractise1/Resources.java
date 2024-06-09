package org.example.javaprojectpractise1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public class Resources {
    @FXML
    private void openelearning(ActionEvent event) {
        String url = "https://elearning.iba-suk.edu.pk/login/index.php";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void cmsPortal(ActionEvent event) {
        String url = "http://sibagrades.iba-suk.edu.pk:86/Default.aspx";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void backToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomePage.fxml")));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("HomePage");
        stage.show();
    }
    @FXML
    private void handleGradingPolicyButton(ActionEvent event) {
        try {
            Parent gradingPage = FXMLLoader.load(getClass().getResource("gradingView.fxml"));
            Scene gradingScene = new Scene(gradingPage);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(gradingScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handlestdAssessment(ActionEvent event){
        try {
            Parent gradingPage = FXMLLoader.load(getClass().getResource("stdAssessment.fxml"));
            Scene gradingScene = new Scene(gradingPage);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(gradingScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void closebuttonHandle(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("resources.fxml")));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Resources");
        stage.show();
    }

    @FXML
    private void CSlink(ActionEvent event) {
        String url = "https://www.iba-suk.edu.pk/offered-programs/computer-science";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleLinks(ActionEvent event){
        try {
            Parent gradingPage = FXMLLoader.load(getClass().getResource("Links.fxml"));
            Scene gradingScene = new Scene(gradingPage);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(gradingScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





