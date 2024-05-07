package org.example.javaprojectpractise1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import javafx.fxml.FXMLLoader;
enum Semester {
    SEMESTER_1, SEMESTER_2, SEMESTER_3, SEMESTER_4,
    SEMESTER_5, SEMESTER_6, SEMESTER_7, SEMESTER_8
}

class Course {
    private String name;
    private List<Course> prerequisites;

    public Course(String name) {
        this.name = name;
        this.prerequisites = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void addPrerequisite(Course prerequisite) {
        prerequisites.add(prerequisite);
    }
    public String toString(){return name;}
}

public class CourseManagerFX extends Application {
    private Map<Semester, ObservableList<Course>> coursesBySemester;
    private  Map<String,Course>courseMap;

    @FXML
    private ComboBox<Semester> semesterComboBox;

    @FXML
    private ListView<Course> courseListView;

    @FXML
    private Button updateButton;

    @FXML
    public Button withdrawButton;

    public CourseManagerFX() {
        coursesBySemester = new EnumMap<>(Semester.class);
        initializeCourses();
    }

    @FXML
    private void initializeCourses() {
        for (Semester semester : Semester.values()) {
            coursesBySemester.put(semester, FXCollections.observableArrayList());
        }

        // Add courses for each semester with prerequisites
        addCourse(Semester.SEMESTER_1, "ICT");
        addCourse(Semester.SEMESTER_1, "Programming Fundamentals");
        addCourse(Semester.SEMESTER_1, "English");
        addCourse(Semester.SEMESTER_1, "Pre-Calculus");
        addCourse(Semester.SEMESTER_1, "Islamiat");
        addCourse(Semester.SEMESTER_1, "Pakistan Study");

        addCourse(Semester.SEMESTER_2, "OOP", "Programming Fundamentals");
        addCourse(Semester.SEMESTER_2, "Calculus", "Pre-Calculus");
        addCourse(Semester.SEMESTER_2, "CAPS");
        addCourse(Semester.SEMESTER_2, "Discrete Structures");
        addCourse(Semester.SEMESTER_2, "Applied Physics");

        addCourse(Semester.SEMESTER_3, "Data Structures", "OOP");
        addCourse(Semester.SEMESTER_3, "Digital Logic Design");
        addCourse(Semester.SEMESTER_3, "Multivariate Calculus", "Calculus");
        addCourse(Semester.SEMESTER_3, "Software Engineering");
        addCourse(Semester.SEMESTER_3, "Uni Elective1");

        addCourse(Semester.SEMESTER_4, "Operating Systems");
        addCourse(Semester.SEMESTER_4, "Probability and Statics");
        addCourse(Semester.SEMESTER_4, "Linear Algebra");
        addCourse(Semester.SEMESTER_4, "Data Base Systems");
        addCourse(Semester.SEMESTER_4, "Artificial Intelligence");

        addCourse(Semester.SEMESTER_5, "Differential Equations", "Calculus");
        addCourse(Semester.SEMESTER_5, "Computer Network");
        addCourse(Semester.SEMESTER_5, "Data Mining", "Data Base Systems");
        addCourse(Semester.SEMESTER_5, "Technical Writing");
        addCourse(Semester.SEMESTER_5, "Uni Elective2");

        addCourse(Semester.SEMESTER_6, "Machine Learning", "Artificial Intelligence");
        addCourse(Semester.SEMESTER_6, "Ai Elective1");
        addCourse(Semester.SEMESTER_6, "Information Security");
        addCourse(Semester.SEMESTER_6, "Big Data Analysis", "Data Structures");
        addCourse(Semester.SEMESTER_6, "Robotics Fundamentals");

        addCourse(Semester.SEMESTER_7, "Ai Elective2");
        addCourse(Semester.SEMESTER_7, "Ai Elective3");
        addCourse(Semester.SEMESTER_7, "Deep Learning", "Artificial Intelligence");
        addCourse(Semester.SEMESTER_7, "Uni Elective3");
        addCourse(Semester.SEMESTER_7, "FYP1");

        addCourse(Semester.SEMESTER_8, "Ai Elective4");
        addCourse(Semester.SEMESTER_8, "Ai Elective5");
        addCourse(Semester.SEMESTER_8, "Ai Elective6");
        addCourse(Semester.SEMESTER_8, "Ai Elective7");
        addCourse(Semester.SEMESTER_8, "FYP2", "FYP1");

    }

    private void addCourse(Semester semester, String courseName, String... prerequisites) {
        ObservableList<Course> courses = coursesBySemester.get(semester);
        Course course = new Course(courseName);
        for (String prerequisite : prerequisites) {
            for (Course c : courses) {
                if (c.getName().equals(prerequisite)) {
                    course.addPrerequisite(c);
                    break;
                }
            }
        }
        courses.add(course);
    }

   /* private void updatePrerequisites(Semester semester) {
        for (int i = semester.ordinal() + 1; i < Semester.values().length; i++) {
            Semester nextSemester = Semester.values()[i];
            ObservableList<Course> nextSemesterCourses = coursesBySemester.get(nextSemester);
            for (Course nextSemesterCourse : nextSemesterCourses) {
                nextSemesterCourse.getPrerequisites().removeIf(prerequisite -> coursesBySemester.get(semester).contains(prerequisite));
            }
        }
    }*/
    private void updatePrerequisites(Semester semester) {
        // Iterate through all semesters after the selected semester
        for (int i = semester.ordinal() + 1; i < Semester.values().length; i++) {
            Semester nextSemester = Semester.values()[i];
            ObservableList<Course> nextSemesterCourses = coursesBySemester.get(nextSemester);

            // Iterate through courses of the next semester
            for (Course nextSemesterCourse : nextSemesterCourses) {
                // Check if any of the prerequisites of the next semester course
                // is present in the selected semester
                boolean removeCourse = false;
                for (Course prerequisite : nextSemesterCourse.getPrerequisites()) {
                    if (coursesBySemester.get(semester).contains(prerequisite)) {
                        removeCourse = true;
                        break;
                    }
                }

                // Remove the course if any of its prerequisites is withdrawn
                if (removeCourse) {
                    nextSemesterCourses.remove(nextSemesterCourse);
                }
            }
        }
    }


    @FXML
    private void initialize() {

        semesterComboBox.getItems().addAll(Semester.values());
        semesterComboBox.setValue(Semester.SEMESTER_1);
        semesterComboBox.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");


        // Update ListView items when a new semester is selected
        semesterComboBox.setOnAction(event -> {
            updateListView();
        });

        updateListView();

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Courses.fxml"));
        loader.setController(this);
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Course Manager");
        primaryStage.show();
    }

    @FXML
    private void updatePrerequisitesAction() {
        Semester selectedSemester = semesterComboBox.getValue();
        updatePrerequisites(selectedSemester);
        updateListView();

        System.out.println("Update");
    }

    @FXML

    private void withdrawCourseAction() {
        Semester selectedSemester = semesterComboBox.getValue();
        Course selectedCourse = courseListView.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            coursesBySemester.get(selectedSemester).remove(selectedCourse);
            System.out.println(selectedCourse);
            // Update prerequisites for subsequent semesters
            for (int i = selectedSemester.ordinal() + 1; i < Semester.values().length; i++) {
                Semester nextSemester = Semester.values()[i];
                ObservableList<Course> nextSemesterCourses = coursesBySemester.get(nextSemester);
                nextSemesterCourses.forEach(course -> course.getPrerequisites().removeIf(prerequisite -> prerequisite.getName().equals(selectedCourse.getName())));
            }

            updateListView();
        }
    }

    private void updateListView() {
        Semester selectedSemester = semesterComboBox.getValue();
        ObservableList<Course> courses = coursesBySemester.get(selectedSemester);
        courseListView.setItems(courses);
    }

    @FXML Button backButton;

    @FXML
    public void setBackButton(ActionEvent event) throws IOException {    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomePage.fxml")));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("GRADES");
        stage.show();
    }

}
