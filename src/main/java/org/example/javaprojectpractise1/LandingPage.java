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
public class LandingPage implements Initializable {

    @FXML
    private Label cgpaLabel;

    @FXML
    private TableView<Subject> subjectsTable;

    @FXML
    private TableColumn<Subject, String> subjectColumn = new TableColumn<>("Subject");

    @FXML
    private TableColumn<Subject, Double> GPAColumn = new TableColumn<>("GPA");

    @FXML
    private TextField subjectField, obtainedMarksField, totalMarksField, creditHoursField;

    @FXML
    private ObservableList<Subject> subjects = FXCollections.observableArrayList();

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
    private void handleAddSubjectAction(ActionEvent event) {
        String subjectName = subjectField.getText();
        double obtainedMarks = Double.parseDouble(obtainedMarksField.getText());
        double totalMarks = Double.parseDouble(totalMarksField.getText());
        int creditHours = Integer.parseInt(creditHoursField.getText());
        double gpa = calculateGPA(obtainedMarks, totalMarks);
        Subject newSubject = new Subject(subjectName, obtainedMarks, totalMarks, creditHours, gpa);
        subjectsTable.getItems().add(new Subject(subjectName, gpa));
        subjectField.clear();
        obtainedMarksField.clear();
        totalMarksField.clear();
        creditHoursField.clear();
        subjects.add(newSubject);
        subjectsTable.refresh();
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
        else if (percentage >= 90) return 3.7;
        else if (percentage >= 85) return 3.3;
        else if (percentage >= 80) return 3.0;
        else if (percentage >= 75) return 2.7;
        else if (percentage >= 70) return 2.3;
        else if (percentage >= 65) return 2.0;
        else if (percentage >= 60) return 1.7;
        else return 0;
    }

    @FXML
    TextField Username = new TextField();
    @FXML
    TextField Password = new TextField();
    @FXML
    Parent root;
    private DBHandler handler;

    @FXML
    public void signUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signUp.fxml")));
        Stage stage ;
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign up");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        String username = Username.getText();
        String password = Password.getText();

        // retrive data from the database
        Connection connection = handler.getConnection();
        String query = "SELECT * from userdetails where names = ? and password = ?";

        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = count + 1;
            }
            if (count == 1) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LandingPage.fxml")));
                Stage stage ;
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Hello");
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Username or Password");
                alert.setContentText("Please enter correct username and password");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
