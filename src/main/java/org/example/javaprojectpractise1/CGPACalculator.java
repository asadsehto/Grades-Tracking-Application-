package org.example.javaprojectpractise1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import org.example.DBConnection.DBHandler;
import java.io.IOException;
import java.util.Objects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CGPACalculator  extends UserLogin implements Initializable  {

    @FXML
    private Label cgpaLabel;

    @FXML
    private TableView<Subject> subjectsTable = new TableView<>();

    @FXML
    private TableColumn<Subject, String> subjectColumn = new TableColumn<>("Subject");

    @FXML
    private TableColumn<Subject, Double> GPAColumn = new TableColumn<>("GPA");

    @FXML
    private TextField subjectField, obtainedMarksField, totalMarksField, creditHoursField;


    @FXML
    private Button cgpaButton = new Button("CGPA");

    @FXML
    private ObservableList<Subject> subjects = FXCollections.observableArrayList();



    private DBHandler handler;

    @FXML
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        Username.setStyle("-fx-text-inner-color: black;");
        Password.setStyle("-fx-text-inner-color: black;");
        handler = new DBHandler();
        subjectColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        subjectColumn.setMinWidth(200);
        GPAColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getGpa()));
        GPAColumn.setMinWidth(100);
    }

    @FXML
    private void handleAddSubjectAction(ActionEvent event) throws SQLException {
        String subjectName = subjectField.getText();
        double obtainedMarks = Double.parseDouble(obtainedMarksField.getText());
        double totalMarks = Double.parseDouble(totalMarksField.getText());
        int creditHours = Integer.parseInt(creditHoursField.getText());
        double gpa = calculateGPA(obtainedMarks, totalMarks);
        subjects.add(new Subject(subjectName,obtainedMarks,totalMarks,creditHours,gpa));
        if (subjectName.isEmpty()) {
            System.out.println("Subject name is empty");
            return;
        }
        subjectsTable.setItems(subjects);
        // Clear the input fields
        subjectField.clear();
        obtainedMarksField.clear();
        totalMarksField.clear();
        creditHoursField.clear();

        // Refresh the TableView
        subjectsTable.refresh();

        // Insert the grade into the database
        insertGrade(Username.getText(), subjectName, gpa);
    }


    @FXML
    private void handleCalculateCGPAAction() {
        double cgpa = calculateCGPA(subjects);
        cgpaLabel.setText(String.format("CGPA: %.2f", cgpa));
    }

    private double calculateCGPA(ObservableList<Subject> subjects) {
        double totalGradePoints = 0;
        double totalCreditHours = 0;
        for (Subject subject : subjects) {
            double gpa = calculateGPA(subject.getObtainedMarks(), subject.getTotalMarks());
            totalGradePoints += gpa * subject.getCreditHours();
            totalCreditHours += subject.getCreditHours();
        }
        return totalCreditHours > 0 ? totalGradePoints / totalCreditHours : 0;
    }

    private double calculateGPA(double obtainedMarks, double totalMarks) {
        double percentage = (obtainedMarks / totalMarks) * 100;
        if (percentage >= 93) return 4.0;
        else if (percentage >= 87) return 3.7;
        else if (percentage >= 82) return 3.3;
        else if (percentage >= 77) return 3.0;
        else if (percentage >= 72) return 2.7;
        else if (percentage >= 68) return 2.3;
        else if (percentage >= 64) return 2.0;
        else if (percentage >= 60) return 1.67;
        else return 0;
    }

    @FXML
    public void loadHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomePage.fxml")));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }


    private void insertGrade(String username, String subjectName, double gpa) throws SQLException {
        Connection connection = handler.getConnection();
        String insertQuery = "INSERT INTO user_grades (username, subject_name, gpa) VALUES (?, ?, ?)";

        try {
            PreparedStatement pst = connection.prepareStatement(insertQuery);
            pst.setString(1, username);
            pst.setString(2, subjectName);
            pst.setDouble(3, gpa);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }


    private ObservableList<Subject> getGradesForUser(String username) throws SQLException {
        ObservableList<Subject> subjects = FXCollections.observableArrayList();
        Connection connection = handler.getConnection();
        String selectQuery = "SELECT subject_name, gpa FROM user_grades WHERE username = ?";

        try {
            PreparedStatement pst = connection.prepareStatement(selectQuery);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String subjectName = rs.getString("subject_name");
                double gpa = rs.getDouble("gpa");
                subjects.add(new Subject(subjectName, gpa));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return subjects;
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


}
