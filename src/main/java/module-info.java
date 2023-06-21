module com.controller{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.controller to javafx.fxml;
    exports com.controller;
    exports com.Model;
    opens com.Model to javafx.fxml;
    exports com.Tela;
    opens com.Tela to javafx.fxml;
}