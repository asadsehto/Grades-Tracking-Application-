package org.example.javaprojectpractise1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class HelpApp  {

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane pane1, pane2, pane3;

    @FXML
    private Label label1, label2, label3;

    public void questionAnswers() {
        accordion = new Accordion();

        TitledPane pane1 = new TitledPane("What are the CS101 prerequisites?", new Label());
        TitledPane pane2 = new TitledPane("How many credits is CS102?", new Label());
        TitledPane pane3 = new TitledPane("Who teaches CS201?", new Label());

        pane1.setOnMouseClicked(e -> setAnswerForPane(pane1, "What are the CS101 prerequisites?"));
        pane2.setOnMouseClicked(e -> setAnswerForPane(pane2, "How many credits is CS102?"));
        pane3.setOnMouseClicked(e -> setAnswerForPane(pane3, "Who teaches CS201?"));

        accordion.getPanes().addAll(pane1, pane2, pane3);

    }

    private void setAnswerForPane(TitledPane pane, String question) {
        Label label = (Label) pane.getContent();
        String answer = getAnswerForQuestion(question);
        label.setText(answer);
        // Set the content to the updated label
        pane.setContent(label);
    }

    private String getAnswerForQuestion(String question) {
        switch (question) {
            case "What are the CS101 prerequisites?":
                return "CS101 requires CS100 as a prerequisite.";
            case "How many credits is CS102?":
                return "CS102 is a 4-credit course.";
            case "Who teaches CS201?":
                return "CS201 is taught by Prof. Smith.";
            default:
                return "Sorry, I don't have an answer for that question.";
        }
    }

    @FXML
    public void setAnswerForPane(javafx.scene.input.MouseEvent event) {
        TitledPane pane = (TitledPane) event.getSource();
        String question = pane.getText();
        Label label = (Label) pane.getContent();
        String answer = getAnswerForQuestion(question);
        label.setText(answer);
    }

}
