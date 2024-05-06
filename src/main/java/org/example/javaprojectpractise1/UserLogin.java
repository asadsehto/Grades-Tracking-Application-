package org.example.javaprojectpractise1;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.desktop.OpenURIEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.DBConnection.DBHandler;
import java.io.*;
import java.sql.Connection;
import java.util.Objects;


public class UserLogin implements Initializable {

    @FXML
    protected TextField Username = new TextField();

    @FXML
    protected PasswordField Password = new PasswordField();
    @FXML
    TextField textField = new TextField();
    @FXML
    TextField textField1 = new TextField();
    @FXML
    TextField textField2 = new TextField();
    @FXML
    private ToggleGroup Gender;
    @FXML
    private RadioButton  radioM;
    @FXML
    private RadioButton radioF;
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    @FXML

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        textField.setStyle("-fx-text-inner-color: black;");
        textField1.setStyle("-fx-text-inner-color: black;");
        textField2.setStyle("-fx-text-inner-color: black;");
        handler = new DBHandler();
    }




    @FXML
    public void signUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signUp.fxml")));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sign up");
        stage.show();

    }
String username;
    String password;

    public String getUsername() {
        return this.username;
    }

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
         username = Username.getText(); // Fetch username from TextField
        password = Password.getText();

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
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomePage.fxml")));
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("GRADES");
                stage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Username or Password");
                alert.setContentText("Please enter correct username and password");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("Error occurred while accessing the database");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }








    @FXML
    public void backToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = new Stage();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("WELCOME TO GRADES");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void signUpD(ActionEvent event) throws SQLException {
        String insert = "INSERT INTO userdetails( names , password, confirmpassword , gender)" + "VALUES (?,?,?,?)";
        connection = handler.getConnection();
        try {
            pst = connection.prepareStatement(insert);
        } catch (SQLException e) {
        }
        try {
            pst.setString(1, textField.getText());
            pst.setString(2, textField1.getText());
            pst.setString(3, textField2.getText());
            pst.setString(4, getGender(event));
            pst.executeUpdate();
            if (textField1.getText().equals(textField2.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("You have signed up successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Password and Confirm Password do not match");
                alert.setContentText("Please enter correct password and confirm password");
                alert.showAndWait();
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
    public String getGender(ActionEvent event) {
        String gen = "";
        if(radioM.isSelected())
        {       gen = "Male";
        }
        else if(radioF.isSelected())
        {
          gen = "female";
        }
        return gen;}}
