package eryz;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class is responsible for launching the EryzBot JavaFX application.
 * It initializes the main window and sets up the scene and stage.
 */
public class Main extends Application {

    private EryzBot eryz = new EryzBot("./data/eryz.txt");  // Instance of the EryzBot that manages tasks.

    /**
     * The start method is the entry point for JavaFX applications.
     * It loads the FXML layout for the main window, sets up the scene, and displays the stage.
     * It also injects the EryzBot instance into the controller for interaction.
     * 
     * @param stage The primary stage for this application, onto which the scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file for the main window.
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            
            // Set up the scene and the stage.
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);  // Set minimum height for the window.
            stage.setMinWidth(417);   // Set minimum width for the window.
            
            // Inject the EryzBot instance into the controller.
            fxmlLoader.<MainWindow>getController().setEryzBot(eryz);
            
            // Display the stage (main window).
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // Handle any I/O exceptions that occur while loading the FXML.
        }
    }

    /**
     * The main method is the entry point for the Java application.
     * It launches the JavaFX application by calling the launch() method.
     * 
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);  // Launch the JavaFX application.
    }
}
