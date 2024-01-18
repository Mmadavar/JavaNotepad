module com.example.javanotepad {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javanotepad to javafx.fxml;
    exports com.example.javanotepad;
}