module atomic.bomber {
    requires javafx.controls;
    requires javafx.fxml;


    opens atomic.bomber.controller to javafx.fxml;
    exports atomic.bomber;
}