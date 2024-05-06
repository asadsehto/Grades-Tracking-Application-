package org.example.javaprojectpractise1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

import java.awt.event.MouseEvent;

public class HelpApp  {

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane pane1, pane2, pane3 , pane4;

    @FXML
    private Label label1, label2, label3 , label4;

    public void questionAnswers() {
        accordion = new Accordion();

        TitledPane pane1 = new TitledPane("What does prerequisites mean?", new Label());
        TitledPane pane2 = new TitledPane("what is a 3 + 1 credit hour ?", new Label());
        TitledPane pane3 = new TitledPane("Where is the CS HOD office?", new Label());
        TitledPane pane4 = new TitledPane("How to withdraw courses ", new Label());

        pane1.setOnMouseClicked(e -> setAnswerForPane(pane1, "What does prerequisites mean?"));
        pane2.setOnMouseClicked(e -> setAnswerForPane(pane2, "what is a 3 + 1 credit hour ?"));
        pane3.setOnMouseClicked(e -> setAnswerForPane(pane3, "Where is the CS HOD office?"));
        pane4.setOnMouseClicked(e -> setAnswerForPane(pane4, "How to withdraw courses "));

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
            case "What does prerequisites mean?":
                return "Prerequisites are courses that must be taken before another course. For example, pre-calculus is a prerequisite for Calculus.";
            case "what is a 3 + 1 credit hour ?":
                return "A 3 + 1 credit hour course is a course that has 3 hours of lecture and 1 hour of lab per week.";
            case "Where is the CS HOD office?":
                return "The CS HOD office is located in the Computer Science department on the 1st floor of the Block 1 building.";
            case "How to withdraw courses ":
                return "To withdraw from a course, you need to fill out a course withdrawal form and submit it to the Cordinator's office.";
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
