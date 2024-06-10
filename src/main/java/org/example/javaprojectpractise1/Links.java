package org.example.javaprojectpractise1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public class Links {
    @FXML
    private void CourseraLink(ActionEvent event) {
        String url = "https://www.coursera.org/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void edXLink(ActionEvent event) {
        String url = "https://www.edx.org/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void CodeAcademy(ActionEvent event) {
        String url = "https://www.codecademy.com/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void KhanAcademyLink(ActionEvent event) {
        String url = "https://www.khanacademy.org/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void Geek4geeks(ActionEvent event) {
        String url = "https://www.geeksforgeek.org/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void HackerRankLink(ActionEvent event) {
        String url = "https://www.hackerrank.com/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void LeetCodeLink(ActionEvent event) {
        String url = "https://www.leetcode.com/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void StackOverFLowlink(ActionEvent event) {
        String url = "https://www.stackoverflow.com/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void GithubLink(ActionEvent event) {
        String url = "https://www.github.com/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void W3schoolLink(ActionEvent event) {
        String url = "https://www.w3schools.com/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void closebuttonHandle(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("resources.fxml")));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Resources");
        stage.show();
    }
}
