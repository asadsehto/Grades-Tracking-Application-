package org.example.javaprojectpractise1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.EnumMap;
import java.util.Map;

enum Semester {
    SEMESTER_1, SEMESTER_2, SEMESTER_3, SEMESTER_4,
    SEMESTER_5, SEMESTER_6, SEMESTER_7, SEMESTER_8
}

public class CourseManagerFX extends Application {
    private Map<Semester, ObservableList<String>> coursesBySemester;
    private Map<Semester, ObservableList<String>> prerequisitesBySemester;

    public CourseManagerFX() {
        coursesBySemester = new EnumMap<>(Semester.class);
        prerequisitesBySemester = new EnumMap<>(Semester.class);
        initializeCourses();
        initializePrerequisites();
    }

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

        // Add more courses for the remaining semesters
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
        for (String prereq : prerequisites) {
            prereqList.add(prereq);
        }
        prerequisitesBySemester.put(semester, prereqList);
    }

    private void initializePrerequisites() {
        for (Semester semester : Semester.values()) {
            ObservableList<String> prereqList = prerequisitesBySemester.getOrDefault(semester, FXCollections.observableArrayList());
            prerequisitesBySemester.put(semester, prereqList);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        ComboBox<Semester> semesterComboBox = new ComboBox<>();
        semesterComboBox.getItems().addAll(Semester.values());
        semesterComboBox.setValue(Semester.SEMESTER_1);
        grid.add(new Label("Select Semester:"), 0, 0);
        grid.add(semesterComboBox, 1, 0);

        ListView<String> courseListView = new ListView<>();
        grid.add(new Label("Courses:"), 0, 1);
        grid.add(courseListView, 0, 2);

        ListView<String> prerequisitesListView = new ListView<>();
        grid.add(new Label("Prerequisites:"), 1, 1);
        grid.add(prerequisitesListView, 1, 2);
        semesterComboBox.setOnAction(event -> {
            Semester selectedSemester = semesterComboBox.getValue();
            ObservableList<String> courses = coursesBySemester.get(selectedSemester);
            courseListView.setItems(courses);

            ObservableList<String> prerequisites = prerequisitesBySemester.get(selectedSemester);
            if (prerequisites.isEmpty()) {
                prerequisitesListView.setItems(FXCollections.singletonObservableList("N/A"));
            } else {
                prerequisitesListView.setItems(prerequisites);
            }
        });

        Scene scene = new Scene(grid, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Course Manager");
        primaryStage.show();
    }
}





