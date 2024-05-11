module com.example.dronecs420 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dronecs420 to javafx.fxml;
    exports com.example.dronecs420;
}