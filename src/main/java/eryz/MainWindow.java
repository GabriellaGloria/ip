package eryz;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private EryzBot eryz;

    private Image userImage = new Image(
        this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image eryzImage = new Image(
        this.getClass().getResourceAsStream("/images/DaEryz.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the EryzBot instance */
    public void setEryzBot(EryzBot e) {
        eryz = e;
    }

    /**
     * Creates two dialog boxes, one echoing user input and 
     * the other containing EryzBot's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = eryz.getResponse(input);
        addDialogs(DialogBox.getUserDialog(input, userImage), DialogBox.getEryzBotDialog(response, eryzImage));
        userInput.clear();
    }
    
    /**
     * Adds the given dialog boxes to the dialog container.
     * 
     * This method accepts a variable number of DialogBox objects 
     * and adds them to the dialog container in the UI.
     * 
     * @param dialogs The DialogBox objects to be added to the container.
     */
    private void addDialogs(DialogBox... dialogs) {
        dialogContainer.getChildren().addAll(dialogs);
    }
    
}