package atomic.bomber;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        AllScenes.getScene().setTitle("Atomic Bomber");
        AllScenes.runStartMenu();
    }

    public static void main(String[] args) {
        launch();
    }
}