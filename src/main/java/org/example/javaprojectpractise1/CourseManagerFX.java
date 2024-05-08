package org.example.javaprojectpractise1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

enum Semester {
    SEMESTER_1, SEMESTER_2, SEMESTER_3, SEMESTER_4,
    SEMESTER_5, SEMESTER_6, SEMESTER_7, SEMESTER_8
}
public class CourseManagerFX  {
        private final Map<Semester, ObservableList<String>> coursesBySemester;
        private final Map<Semester, ObservableList<String>> prerequisitesBySemester;

        @FXML
        public ListView<String> courseListView = new ListView<>();

        @FXML
       public Label label = new Label("PRE-REQUISITES:");

        @FXML
        public ComboBox<Semester> semesterComboBox = new ComboBox<>();

        @FXML
        public ListView<String> prerequisitesListView = new ListView<>();

        @FXML
        public Button BackButton;

        public CourseManagerFX() {
            coursesBySemester = new EnumMap<>(Semester.class);
            prerequisitesBySemester = new EnumMap<>(Semester.class);
           // initializeCourses();
            initializePrerequisites();
        }
        @FXML
    public void initialize() {
        initializeCourses();
        initializePrerequisites();
        semesterComboBox.setItems(FXCollections.observableArrayList(Semester.values()));

    }
        @FXML
        private void initializeCourses() {

            for (Semester semester : Semester.values()) {
                coursesBySemester.put(semester, FXCollections.observableArrayList());
            }
            addCourse(Semester.SEMESTER_1, "ICT", "N/A");
            addCourse(Semester.SEMESTER_1, "Programming Fundamentals", "N/A");
            addCourse(Semester.SEMESTER_1, "English", "N/A");
            addCourse(Semester.SEMESTER_1, "Precalculus", "N/A");
            addCourse(Semester.SEMESTER_1, "Islamiat", "N/A");
            addCourse(Semester.SEMESTER_1, "Pak Study", "N/A");

            addCourse(Semester.SEMESTER_2, "OOP", "Programming Fundamentals");
            addCourse(Semester.SEMESTER_2, "Calculus", "Precalculus");
            addCourse(Semester.SEMESTER_2, "CAPS", "N/A");
            addCourse(Semester.SEMESTER_2, "Discrete", "N/A");
            addCourse(Semester.SEMESTER_2, "Applied Physics", "N/A");

            // Semester 3
            addCourse(Semester.SEMESTER_3, "Data Structures", "Programming Fundamentals");
            addCourse(Semester.SEMESTER_3, "Multivariate Calculus", "Calculus");
            addCourse(Semester.SEMESTER_3, "DLD", "N/A");
            addCourse(Semester.SEMESTER_3, "Software Engineering", "N/A");
            addCourse(Semester.SEMESTER_3, "Uni Elective1", "N/A");

            // Semester 4
            addCourse(Semester.SEMESTER_4, "Operating Systems", "N/A");
            addCourse(Semester.SEMESTER_4, "Probability And Statistics", "N/A");
            addCourse(Semester.SEMESTER_4, "Linear Algebra", "N/A");
            addCourse(Semester.SEMESTER_4, "Database Systems", "N/A");
            addCourse(Semester.SEMESTER_4, "Artificial Intelligence", "N/A");

            // Semester 5
            addCourse(Semester.SEMESTER_5, "Differential Equations", "Calculus");
            addCourse(Semester.SEMESTER_5, "Computer Networks", "N/A");
            addCourse(Semester.SEMESTER_5, "Data mining", "Database Systems");
            addCourse(Semester.SEMESTER_5, "Technical Writing", "N/A");
            addCourse(Semester.SEMESTER_5, "Uni Elective2", "N/A");

            // Semester 6
            addCourse(Semester.SEMESTER_6, "Machine Learning", "Artificial Intelligence");
            addCourse(Semester.SEMESTER_6, "Design and Analysis of Algorithms", "Data Structures");
            addCourse(Semester.SEMESTER_6, "Ai Elective1", "N/A");
            addCourse(Semester.SEMESTER_6, "Information Security", "N/A");
            addCourse(Semester.SEMESTER_6, "Big Data Analysis", "N/A");
            addCourse(Semester.SEMESTER_6, "Robotics Fundamentals", "N/A");

            // Semester 7
            addCourse(Semester.SEMESTER_7, "Deep Learning", "Artificial Intelligence");
            addCourse(Semester.SEMESTER_7, "Ai Elective2", "N/A");
            addCourse(Semester.SEMESTER_7, "Ai Elective3", "N/A");
            addCourse(Semester.SEMESTER_7, "Uni Elective3", "N/A");
            addCourse(Semester.SEMESTER_7, "Fyp1", "N/A");

            // Semester 8

            addCourse(Semester.SEMESTER_8, "Fyp2", "Fyp1");
            addCourse(Semester.SEMESTER_8, "Ai Elective4", "N/A");
            addCourse(Semester.SEMESTER_8, "Ai Elective5", "N/A");
            addCourse(Semester.SEMESTER_8, "Ai Elective6", "N/A");
            addCourse(Semester.SEMESTER_8, "Ai Elective7", "N/A");
        }

        private void addCourse(Semester semester, String courseName, String... prerequisites) {
            ObservableList<String> courses = coursesBySemester.get(semester);
            courses.add(courseName);

            ObservableList<String> prereqList = prerequisitesBySemester.getOrDefault(semester, FXCollections.observableArrayList());
            Collections.addAll(prereqList, prerequisites);
            prerequisitesBySemester.put(semester, prereqList);
        }



@FXML
private void initializePrerequisites() {
            for (Semester semester : Semester.values()) {
                ObservableList<String> prereqList = prerequisitesBySemester.getOrDefault(semester, FXCollections.observableArrayList());
                prerequisitesBySemester.put(semester, prereqList);
            }
        }


    @FXML
    public void semesterComboBoxAction(ActionEvent event) {
        Semester selectedSemester = semesterComboBox.getValue();
        ObservableList<String> courses = coursesBySemester.get(selectedSemester);
        courseListView.setItems(courses);

        ObservableList<String> prerequisites = prerequisitesBySemester.get(selectedSemester);
        prerequisitesListView.getItems().clear(); // Clear the ListView before adding new items
        if (prerequisites.isEmpty()) {
            prerequisitesListView.setItems(FXCollections.singletonObservableList("N/A"));
        } else {
            prerequisitesListView.setItems(prerequisites);
        }
    }
        @FXML
    public void BackButtonAction(ActionEvent event) throws IOException {
           CGPACalculator cgpacalculator = new CGPACalculator();
           cgpacalculator.loadHomePage(event);
        }
        }