module org.example.javaprojectpractise1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
  //  requires mysql.connector.j;
    requires java.desktop;
    requires httpclient;
    requires mysql.connector.j;


    opens org.example.javaprojectpractise1 to javafx.fxml;
    exports org.example.javaprojectpractise1;
}