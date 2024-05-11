package atomic.bomber;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AllScenes {
    private static final Stage currentStage = new Stage();

    public static Stage getScene() {
        return AllScenes.currentStage;
    }

    public static void runStartMenu() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("start-menu.fxml"));
        Scene scene;

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AllScenes.currentStage.setScene(scene);
        AllScenes.currentStage.show();
    }

    public static void runLoginMenu() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-menu.fxml"));
        Scene scene;

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AllScenes.currentStage.setScene(scene);
        AllScenes.currentStage.show();
    }
}
