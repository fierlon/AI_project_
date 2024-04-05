module com.project_ai {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project_ai to javafx.fxml;
    exports com.project_ai;
}