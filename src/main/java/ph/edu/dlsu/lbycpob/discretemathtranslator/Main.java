package ph.edu.dlsu.lbycpob.discretemathtranslator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point of the application.
 *
 * <p>Launches the JavaFX application and loads the main view
 * from FXML.</p>
 */
public class Main extends Application {

    /**
     * Launches the JavaFX runtime.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Loads the FXML view and displays the primary stage.
     *
     * @param primaryStage the primary window provided by JavaFX
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainView.fxml"));
        primaryStage.setTitle("Discrete Math Translator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}